
/*
 *  ADD REPRESENTATIE : geen aanpassingen vereist, werkt perfect
 */


package be;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.xml.namespace.QName;

import oracle.net.aso.e;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.supercsv.cellprocessor.ConvertNullTo;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.StrRegEx;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.exlibris.core.infra.common.security.UserPrincipal;
import com.exlibris.core.infra.common.shared.dataObjects.KeyValuePair;
import com.exlibris.core.infra.common.util.Checksummer;
import com.exlibris.core.infra.svc.api.queue.WorkQueue;
import com.exlibris.core.infra.svc.api.queue.WorkQueueFactory;
import com.exlibris.core.sdk.exceptions.ProcessException;
import com.exlibris.digitool.repository.dataObjects.DescriptorRow;
import com.exlibris.digitool.repository.dataObjects.FileCharacteristics;
import com.exlibris.digitool.repository.dataObjects.RepresentationCharacteristics;
import com.exlibris.digitool.utils.CSVUtil;
import com.exlibris.dps.AddRepresentationWebServices;
import com.exlibris.dps.AddRepresentationWebServices_Service;
import com.exlibris.dps.DataManagerServicesWS;
import com.exlibris.dps.DataManagerServicesWS_Service;
import com.exlibris.dps.IeFilesInfo;
import com.exlibris.dps.Process;
import com.exlibris.dps.ProcessExecutionStatusInfo;
import com.exlibris.dps.SetMembers;
import com.exlibris.dps.sdk.pds.PdsClient;
import com.exlibris.repository.persistence.rip.HrRipRegistry;

public class AddRepresentation {
	
	static Properties prop = new Properties();		
	private static Logger logger = Logger.getLogger(Metadata.class);
	private static CustomizeFile cFile;
	public static  AddRepresentationWebServices arWebService;
	public static  DataManagerServicesWS dataManagerServiceWS;
	public static String pdsHandle;
	public static DB db;

	
	public static void main(String[] args) {

		try {
			if ((args.length == 0) || !(args[0].contains("properties"))) {	
		        System.err.println("Properties file missing or wrong");
		        System.exit(1);
		    }
			prop.load(new FileInputStream(args[0]));
			// Connecting to PDS
			PdsClient pds = PdsClient.getInstance();
			pds.init(prop.getProperty("PDS_URL"),false);
//			System.out.println(" "+prop.getProperty("institution")+" "+ prop.getProperty("userName")+ " "+ prop.getProperty("password"));
			pdsHandle = pds.login(prop.getProperty("institution"), prop.getProperty("userName"), prop.getProperty("password"));
			System.out.println("pdsHandle: " + pdsHandle);

			db = new DB(prop.getProperty("DB_URL"), prop.getProperty("DB_USER_ID"), prop.getProperty("DB_PASSWORD"));
			arWebService = new AddRepresentationWebServices_Service(new URL(prop.getProperty("AR_WSDL_URL")),new QName("http://dps.exlibris.com/", "AddRepresentationWebServices")).getAddRepresentationWebServicesPort();
//			dataManagerServiceWS = new DataManagerServicesWS_Service(new URL(prop.getProperty("DATA_WSDL_URL")),new QName("http://dps.exlibris.com/", "DataManagerServicesWS")).getDataManagerServicesWSPort();
			
	        ICsvBeanReader beanReader = null;
	        try {
	                beanReader = new CsvBeanReader(new FileReader(prop.getProperty("CSV_FILENAME")), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
	                
	                // the header elements are used to map the values to the bean (names must match)
	                final String[] header = beanReader.getHeader(true);
	                final CellProcessor[] processors = getProcessors();
	                
	                RepresentationBean customer;
	                while( (customer = beanReader.read(RepresentationBean.class, header, processors)) != null ) {
	                        System.out.println(String.format("lineNo=%s, rowNo=%s, customer=%s", beanReader.getLineNumber(),
	                                beanReader.getRowNumber(), customer));
	                        
	                        if (((customer.getPid().startsWith("IE"))) && (!"".equals(customer.getLabel())))
	                        {
	                        	addRep(customer);
	                        } else {
	                        	logger.error("Onvolledige gegevens voor "+ String.format("lineNo=%s, rowNo=%s, FILE=%s", beanReader.getLineNumber(),
	                                beanReader.getRowNumber(), customer));
	                        }
	                }
	                
	        }
	        finally {
	                if( beanReader != null ) {
	                        beanReader.close();
	                }
	        }
			
			


			  } catch (Exception e) {
				e.printStackTrace();
		  }			  
	}
	
	/**
	 * Sets up the processors used for the examples. There are 10 CSV columns, so 10 processors are defined. Empty
	 * columns are read as null (hence the NotNull() for mandatory columns).
	 * 
	 * @return the cell processors
	 */
	private static CellProcessor[] getProcessors() {
	        
	        final String emailRegex = "[a-z0-9\\._]+@[a-z0-9\\.]+"; // just an example, not very robust!
	        StrRegEx.registerMessage(emailRegex, "must be a valid email address");
	        
	        final CellProcessor[] processors = new CellProcessor[] { 
	                new NotNull(), // pid
	                new NotNull(), // preservationtype
	                new ConvertNullTo(""), // representationcode
	                new ConvertNullTo(""), // accessright
	                new ConvertNullTo(""), // label
	                new NotNull() // filepath

	        };
	        
	        return processors;
	}	
	
	
	public static  void addRep(RepresentationBean customer) {

		try {
			
			Statement stmt = null;
			ResultSet rset = null;
			String SSHDirectory = prop.getProperty("SSHDirectory") ;
			String directory = null;
			String importDir = SSHDirectory + "import";
			String filepath="";
			String filename = "";
			  
			Long sip = Long.valueOf(0);
			Long result = Long.valueOf(0);
			  
			List<FileCharacteristics> filesList = new ArrayList<FileCharacteristics>();
			List<Process> processList = null;
			  
			
			InputStream inputStream = null;
			String uniqId ="";
			String note = "";
			String label = "";
			
        	String[] fileList = customer.getFilepath().split(",");
        	int labelstartidx;
        	
    		for (int i = 0;i<fileList.length;i++){
    			
    			filename = "kbe000363.pdf";
    			filepath = fileList[i];
    			
    	    	labelstartidx = filepath.lastIndexOf("/") == -1 ? filepath.lastIndexOf("\\") : filepath.lastIndexOf("/");
    	    	labelstartidx++;
    	    	filename = filepath.substring(labelstartidx);
    			label= String.valueOf(labelstartidx);
    			
    			inputStream = new FileInputStream(new File(filepath));
    			uniqId = db.getUniqueId();
    			filesList.add(uploadFile(importDir, filename, inputStream, uniqId, note, label));
    		}    		
			
			completeUpdateRep(filesList, SSHDirectory, customer);
			
			directory = SSHDirectory.replace("M:","/nas/");
			directory = directory.replace("\\","/");
			
			String response = arWebService.addRepresentation(pdsHandle,directory, HrRipRegistry.RipType.API.toString(), customer.getPid(), String.valueOf(43L), String.valueOf(44L));

			System.out.println(response);
/*			
			SetMembers setMembers;
			
			ProcessExecutionStatusInfo processExecutionStatusInfo;
			processList = dataManagerServiceWS.getMaintenanceProcessList(pdsHandle);
			for (int i = 0;i<processList.size();i++) {
				Process process = processList.get(i);
				System.out.println(process.getName());
				if (process.getName().contains("Data Correction Process on set")) {
					try {
//						processExecutionStatusInfo = dataManagerServiceWS.getProcessExecutionStatus(pdsHandle,process.getProcessId());
//						System.out.println(processExecutionStatusInfo.getProcessExecutionStatus());
						setMembers= dataManagerServiceWS.getSetMembers(pdsHandle, process.getSetId());
						for (Iterator<IeFilesInfo> iter = setMembers.getIeFilesInfo().iterator();iter.hasNext();)
						{
								IeFilesInfo  fileinfo = iter.next();
								System.out.println(fileinfo.getIePID());
						}
					} catch (Exception ep) {
						System.out.println(ep.getMessage());
					}
				}
				
			}
*/
		  } catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	
	 public static void completeUpdateRep(List<FileCharacteristics> filesList, String directory, RepresentationBean customer)
			    throws Exception
			  {
			    List<String[]> rows = new ArrayList<String[]>();
			    DescriptorRow row = null;
			    
			    RepresentationCharacteristics rc = new RepresentationCharacteristics();
			    String accessrights = customer.getAccessright() == null ? "AR_EVERYONE" :customer.getAccessright();  
			    rc.setAccessRightsPolicy(accessrights);
			    rc.setRepresentationCode(customer.getRepresentationcode());
			    rc.setPreservationType(customer.getPreservationtype());
			    rc.setUsageType("VIEW");
			    rc.setLabel(customer.getLabel());
			    for (FileCharacteristics file : filesList) {
			      row = getDescriptorRow(file, customer.getPid(), rc.getPreservationType(), rc.getUsageType(), rc.getAccessRightsPolicy(), rc.getRepresentationCode(), rc.getProvenanceInformation(), rc.getLabel(), rc.getProperties());
			      rows.add(row.getRow());
			    }
			    CSVUtil.writeFile(FilenameUtils.concat(directory, "Import_Descriptor.csv"), rows, row.getHeaderRow());
			  }
	 

/*
	  public static String getUniqueId() {
		    ProcessServiceManager processServiceManager = (ProcessServiceManager)ServiceLocator.getInstance().lookUp(ProcessServiceManager.class);
		    return processServiceManager.getUniqueValue();
		  }
*/	  
	  public static DescriptorRow getDescriptorRow(FileCharacteristics file, String iePid, String presType, String usageType, String arPolicy, String repCode, String provInfo, String label, List<KeyValuePair<String, String>> properties)
			    throws Exception
			  {
			    DescriptorRow row = new DescriptorRow(new DescriptorRow.DescriptorHeader[] { DescriptorRow.DescriptorHeader.AccessRightsPolicy });

			    row.set(DescriptorRow.DescriptorHeader.IePid, iePid);
			    row.set(DescriptorRow.DescriptorHeader.RepresentationPid, com.exlibris.core.sdk.strings.StringUtils.isEmptyString(file.getParentPid()) ? "TEMP_REP" : file.getParentPid());

			    if (com.exlibris.core.sdk.strings.StringUtils.isEmptyString(file.getPid()))
			      row.set(DescriptorRow.DescriptorHeader.OriginalPid, "TEMP_FILE");
			    else {
			      row.set(DescriptorRow.DescriptorHeader.OriginalPid, file.getPid());
			    }

			    row.set(DescriptorRow.DescriptorHeader.Filename, file.getFilename());
			    
			    
			    file.setSourcePath(file.getSourcePath().replace("M:","/nas/"));
			    file.setSourcePath(file.getSourcePath().replace("\\","/"));

			    
			    row.set(DescriptorRow.DescriptorHeader.FileLocation, file.isOriginal() ? null : org.apache.commons.lang.StringUtils.removeEnd(file.getSourcePath(), file.getFilename()));
			    row.set(DescriptorRow.DescriptorHeader.PreservationType, presType);
			    row.set(DescriptorRow.DescriptorHeader.UsageType, usageType);
			    row.set(DescriptorRow.DescriptorHeader.AccessRightsPolicy, arPolicy);
			    row.set(DescriptorRow.DescriptorHeader.RepresentationCode, repCode);
			    row.set(DescriptorRow.DescriptorHeader.FileLabel, com.exlibris.core.sdk.strings.StringUtils.getNullSafe(file.getLabel()));
			    row.set(DescriptorRow.DescriptorHeader.Checksum, file.getChecksum());

			    String submissionReason = provInfo;
			    if ("DERIVATIVE_COPY".equals(presType))
			      submissionReason = "";
			    else if (com.exlibris.core.sdk.strings.StringUtils.isEmptyString(submissionReason)) {
			      submissionReason = ".";
			    }

			    row.set(DescriptorRow.DescriptorHeader.SubmissionReason, submissionReason);
			    row.set(DescriptorRow.DescriptorHeader.Notes, com.exlibris.core.sdk.strings.StringUtils.getNullSafe(file.getNote()));
			    row.set(DescriptorRow.DescriptorHeader.Label, com.exlibris.core.sdk.strings.StringUtils.getNullSafe(label));
			    row.addAdditionalParameters(properties);

			    return row;
			  }
	  
	  public static FileCharacteristics uploadFile(String importDir, String uploadFilename, InputStream inputStream, String uniqId, String note, String label) throws Exception
	  {
	    String filename = uploadFilename;
	    String tempDir = importDir;
	    String filePath = tempDir + File.separator + uniqId + File.separator + filename;
	    File targetFile = new File(filePath);

	    if (targetFile.exists()) {
	      throw new Exception("File with the same name already exists: " + targetFile.getAbsolutePath());
	    }
	    if (!targetFile.exists()) {
	      targetFile.getParentFile().mkdirs();
	      targetFile.createNewFile();
	    }
	    OutputStream outputStream = null;
	    outputStream = new BufferedOutputStream(new FileOutputStream(targetFile));
	    Checksummer checksummer;
	    try { checksummer = new Checksummer(inputStream, outputStream, true, false, false);
	      checksummer.createChecksumAndCopyFile();
	    } catch (Exception e) {
	      throw new Exception("Unable to upload file", e);
	    } finally {
	      inputStream.close();
	      outputStream.close();
	    }

	    return createFileCharacteristics(filename, filePath, checksummer, note, label);
	  }

	  public static FileCharacteristics createFileCharacteristics(String filename, String filePath, Checksummer checksummer, String note, String label)
	  {
	    FileCharacteristics fileCharacteristics = new FileCharacteristics();
	    fileCharacteristics.setFilename(filename);
	    fileCharacteristics.setDate(Calendar.getInstance().getTime());
	    fileCharacteristics.setLabel(label);
	    fileCharacteristics.setNote(note);
	    File targetFile = new File(filePath);
	    fileCharacteristics.setSizeKB(targetFile.length());
	    fileCharacteristics.setSourcePath(targetFile.getAbsolutePath());
	    fileCharacteristics.setOriginalFilename(filename);
	    fileCharacteristics.setOriginal(false);
	    fileCharacteristics.setChecksum(checksummer.getMD5());

	    return fileCharacteristics;
	  }
	
}
