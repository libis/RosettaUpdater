package be;

import java.io.FileInputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.namespace.QName;

import com.exlibris.dps.Operation;
import com.exlibris.dps.RepresentationContent;
import com.exlibris.dps.IEWebServices_Service;
import com.exlibris.dps.IEWebServices;

import com.exlibris.dps.sdk.pds.PdsClient;

public class AddFileToRepresentation {

	
	public static void main(String[] args) {

    	Properties prop = new Properties();		
		try {
			

			String pid;
			String label;
			String pdsHandle;
			prop.load(new FileInputStream("rosettaupd.properties"));			
			// Connecting to PDS
			PdsClient pds = PdsClient.getInstance();
			pds.init(prop.getProperty("PDS_URL"),false);
			String ins = prop.getProperty("institution");
			pdsHandle = pds.login(prop.getProperty("institution"), prop.getProperty("userName"), prop.getProperty("password"));
			System.out.println("pdsHandle: " + pdsHandle);

			
			  Statement stmt = null;
			  ResultSet rset = null;
			  String filePath = "";
			  Long sip = Long.valueOf(0);
			  Long result = Long.valueOf(0);
			  
			IEWebServices ieWebService = new IEWebServices_Service(new URL(prop.getProperty("IE_WSDL_URL")),new QName("http://dps.exlibris.com/", "IEWebServices")).getIEWebServicesPort();
			
			//initialiseer RepresentationContent
			List<RepresentationContent> representationContents = new ArrayList<RepresentationContent>();
			RepresentationContent repContent = new RepresentationContent();
	/*		
			//toevoegen file			
			repContent.setFileOriginalPath("/nas/vol03/tmp");
			repContent.setLabel("mirador");
			repContent.setOperation(Operation.ADD);
			repContent.setNewFile("/nas/vol03/tmp/Aanvraag van de vakbondspremie voor het referentiejaar 2014.pdf");

			representationContents.add(repContent);
			
			repContent = new RepresentationContent();
		
			repContent.setLabel("952176");
			repContent.setFileOriginalPath("/nas/vol03/tmp");
			repContent.setNewFile("/nas/vol03/tmp/952176.PDF");
			repContent.setOperation(Operation.ADD);
			
			representationContents.add(repContent);
			repContent = new RepresentationContent();

			
			repContent.setFileOriginalPath("/nas/vol03/tmp");
			repContent.setOperation(Operation.ADD);
			repContent.setLabel("kbe000363");
			repContent.setNewFile("/nas/vol03/tmp/kbe000363.pdf");

			representationContents.add(repContent);
		
			
			result = ieWebService.updateRepresentation(pdsHandle, "IE2516541", "REP2516544", "test", representationContents);
			
			representationContents.clear();

			//verwijderen file
			repContent.setOperation(Operation.REMOVE);
			repContent.setOldFilePid("FL3583907");

			
			representationContents.add(repContent);

			result = ieWebService.updateRepresentation(pdsHandle, "IE2516541", "REP2516544", "test", representationContents);
*/			
		
			representationContents.clear();

       		//vervangen file
			repContent.setFileOriginalPath("/nas/vol01/lias/2008/10/07/file_1/5255");
			repContent.setLabel("[beschrijving]");
			repContent.setOperation(Operation.REPLACE);
			repContent.setNewFile("/nas/vol03/tmp/tekstx.html");
			repContent.setOldFilePid("FL296942");
			
			representationContents.add(repContent);
			
			result = ieWebService.updateRepresentation(pdsHandle, "IE296940", "REP296941", "test", representationContents);
	
			  } catch (Exception e) {
				e.printStackTrace();
		  }			  
	}

	/*
	  public String getUniqueId() {
		    ProcessServiceManager processServiceManager = (ProcessServiceManager)ServiceLocator.getInstance().lookUp(ProcessServiceManager.class);
		    return processServiceManager.getUniqueValue();
		  }
	  
	  public static FileCharacteristics uploadFile(String importDir, String uploadFilename, InputStream inputStream, String uniqId, String note, String label) throws Exception
	  {
	    String filename = FileUtil.getFileName(uploadFilename);
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
	      IOUtil.shutdownStream(inputStream);
	      IOUtil.shutdownStream(outputStream);
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

	  public void replaceFile(FileCharacteristics uploadFile2, String fileIndex) {
		    uploadFile2.setParentPid(this.pid);
		    if (StringUtils.isEmpty(fileIndex)) {
		      this.files.add(uploadFile2);
		    } else {
		      int fIndex = Integer.parseInt(fileIndex);
		      if (fIndex < getFilesSize()) {
		        uploadFile2.setPid(((FileCharacteristics)this.files.get(fIndex)).getPid());
		        this.files.remove(fIndex);
		        this.files.add(fIndex, uploadFile2);
		      }
		    }
		  }
	  
	  protected List<HDeControl> getFiles(String pid)
	  {
	    RepositoryServices repositoryServices = (RepositoryServices)ServiceLocator.getInstance().lookUp(RepositoryServices.class);
	    if (repositoryServices == null) {
	      log.error("failed to lookup RepositoryServices", new String[0]);
	      return null;
	    }
	    List list = repositoryServices.getFiles(pid);
	    return list;
	  }
	  
	  private String handleRevisionStep1(AddRevisionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		    form.setUploadSingleHTML(getHtml(request, form, "formxml/add_rep_upload_single_form.xml", true, null));

		    String repPid = !StringUtils.isEmptyString(request.getParameter("pid")) ? request.getParameter("pid") : form.getPid();

		    RepresentationCharacteristics repChars = RepositoryUtils.getRepCharacteristics(repPid);

		    ObjectControl control = getControlByPid(form.getPid());

		    PremisObjectRep repObject = new PremisObjectRep(control2ode(control));

		    form.setPreservationType(repChars.getPreservationType());
		    form.setRepresentationCode(repChars.getRepresentationCode());
		    form.setLabel(repChars.getLabel());
		    form.setUsageType(repChars.getUsageType());
		    form.setAccessRightsPolicy(repChars.getAccessRightsPolicy());

		    List fileList = getFiles(repPid);
		    List<PidWrapper> pidWrappers = new ArrayList();
		    pidWrappers.addAll(fileList);

		    MetadataService metadataService = (MetadataService)ServiceLocator.getInstance().lookUp(MetadataService.class);
		    pidWrappers = metadataService.sortFilesByStructMap(repPid, pidWrappers);

		    for (PidWrapper fileControl : pidWrappers) {
		      ObjectControl fObjCont = new ObjectControl();
		      fObjCont.export((HDeControl)fileControl);
		      PremisObjectFile fileObject = new PremisObjectFile(control2MinOde(fObjCont));
		      repObject.addFile(fileObject);
		    }
		    repObject.setFileCount(pidWrappers.size());
		    form.setOrigFilesNum(pidWrappers.size());

		    List<FileCharacteristics> filesForForm = new ArrayList();
		    for (PremisObjectFile file : repObject.getFiles()) {
		      FileCharacteristics fc = new FileCharacteristics();
		      DnxDocumentHelper dd = new DnxDocumentHelper(getRepositoryServices().getDnxDocumentByPid(file.getDe().getPid()));
		      String fcLabel = StringUtils.isEmptyString(dd.getGeneralFileCharacteristics().getLabel()) ? "" : dd.getGeneralFileCharacteristics().getLabel();
		      fc.setLabel(fcLabel);
		      fc.setOriginalFilename(file.getDe().getStreamRef().getFileOriginalName());
		      fc.setSizeKB(Double.parseDouble(dd.getGeneralFileCharacteristics().getFileSizeBytes()));
		      fc.setPid(file.getDe().getPid());
		      String path = getRepositoryServices().getFilePath(fc.getPid());
		      fc.setSourcePath(path);
		      String fName = path.substring(path.lastIndexOf("\\") + 1, path.length());
		      fc.setFilename(fName);
		      fc.setParentPid(repPid);
		      filesForForm.add(fc);
		    }

		    if ((form.getFiles() == null) || (form.getFilesSize() == 0))
		      form.setFiles(filesForForm);
		    else {
		      for (FileCharacteristics f : filesForForm) {
		        boolean alreadyExists = false;
		        for (FileCharacteristics f1 : form.getFiles()) {
		          if (f.getPid().equals(f1.getPid()))
		            alreadyExists = true;
		        }
		        if (!alreadyExists)
		          form.addFile(f);
		      }
		    }
		    form.setSizeUnknown(true);
		    return "revisionStep1";
		  }	  
	  
	  
	  //na submit
	  public static void completeUpdateRep(List<FileCharacteristics> filesList, String directory, UserPrincipal up, String iePid, RepresentationCharacteristics rc)
			    throws Exception
			  {
			    List rows = new ArrayList();
			    DescriptorRow row = null;
			    RepresentationCharacteristics repChars = rc;
			    for (FileCharacteristics file : filesList) {
			      if (repChars == null)
			        repChars = getRepCharacteristics(file.getParentPid());
			      row = getDescriptorRow(file, iePid, repChars.getPreservationType(), repChars.getUsageType(), repChars.getAccessRightsPolicy(), repChars.getRepresentationCode(), repChars.getProvenanceInformation(), repChars.getLabel(), repChars.getProperties());

			      rows.add(row.getRow());
			    }

			    CSVUtil.writeFile(FilenameUtils.concat(directory, "Import_Descriptor.csv"), rows, row.getHeaderRow());

			    AddRepresentationManager manager = (AddRepresentationManager)ServiceLocator.getInstance().lookUp(AddRepresentationManager.class);
			    manager.addRepresentation(directory, up.getUserName(), up.getInstitutionPath(), HrRipRegistry.RipType.MEDITOR.toString(), iePid, Long.valueOf(43L), Long.valueOf(44L));
			  }	  
	  
	  
	    public String getDirectory(BaseWebeditorForm form, ExLogger log) {
		    String tempDir = form.getTempDir();
		
		    if (StringUtils.isEmpty(tempDir)) {
		      try {
		        GeneralParameterManager parameterManager = (GeneralParameterManager)ServiceLocator.getInstance().lookUp(GeneralParameterManager.class);
		        tempDir = parameterManager.getParameter("general", "operational_shared") + "/operational_export_directory/" + File.separator + form.getPid() + File.separator;
		
		        File dir = new File(tempDir);
		        dir.mkdirs();
		      } catch (Exception e) {
		        log.error("Failed to get repository tmp directory for IE: PID " + form.getPid(), e, new String[] { form.getPid() });
		      }
		
		      form.setTempDir(tempDir);
		    }

    return tempDir;
  }

	  
*/	  
}
