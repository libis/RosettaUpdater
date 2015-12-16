package be;

import java.io.FileInputStream;
import java.io.FileReader;
import java.net.URL;
import java.util.Properties;

import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.supercsv.cellprocessor.ConvertNullTo;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.StrRegEx;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.exlibris.dps.IEWebServices;
import com.exlibris.dps.IEWebServices_Service;
import com.exlibris.dps.sdk.pds.PdsClient;

public class Files {

	/**
	 * @param args
	 */

		static Properties prop = new Properties();		
		private static Logger logger = Logger.getLogger(Metadata.class);
		private static CustomizeFile cFile;
		public static  IEWebServices ieWebService;
		public static String pdsHandle;
		public static DB db;

		
		public static void main(String[] args) {

			try {

				prop.load(new FileInputStream("rosettaupd.properties"));			
				// Connecting to PDS
				PdsClient pds = PdsClient.getInstance();
				pds.init(prop.getProperty("PDS_URL"),false);
				pdsHandle = pds.login(prop.getProperty("institution"), prop.getProperty("userName"), prop.getProperty("password"));
				System.out.println("pdsHandle: " + pdsHandle);

				db = new DB(prop.getProperty("DB_URL"), prop.getProperty("DB_USER_ID"), prop.getProperty("DB_PASSWORD"));
				ieWebService = new IEWebServices_Service(new URL(prop.getProperty("IE_WSDL_URL")),new QName("http://dps.exlibris.com/", "IEWebServices")).getIEWebServicesPort();
				cFile = new CustomizeFile(pdsHandle,ieWebService,db);

				
		        ICsvBeanReader beanReader = null;
		        try {
		                beanReader = new CsvBeanReader(new FileReader(prop.getProperty("CSV_FILENAME")), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
		                
		                // the header elements are used to map the values to the bean (names must match)
		                final String[] header = beanReader.getHeader(true);
		                final CellProcessor[] processors = getProcessors();
		                
		                FileBean customer;
		                while( (customer = beanReader.read(FileBean.class, header, processors)) != null ) {
		                        System.out.println(String.format("lineNo=%s, rowNo=%s, customer=%s", beanReader.getLineNumber(),
		                                beanReader.getRowNumber(), customer));
		                        
		                        if ("".equals(customer.getFilepath())) {
//		                        	cFile.deleteFile(customer);
		                        } else if (((customer.getPid().startsWith("REP"))) && ("".equals(customer.getLabel())))
		                        {
		                        	String[] fileList = customer.getFilepath().split(",");
		                    		for (int i = 0;i<fileList.length;i++){
		                    			cFile.addFile(customer,fileList[i]);
		                    		}    		
		                        } else if ((((customer.getPid().startsWith("REP"))) && (!"".equals(customer.getLabel()))) ||
		                        			(((customer.getPid().startsWith("FL"))) && ("".equals(customer.getLabel())))) 
		                        {
		                        	cFile.updateFile(customer);
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
		                new ConvertNullTo(""), // label
		                new ConvertNullTo(""), // filepath
		                new ConvertNullTo("") // originalfilepath

		        };
		        
		        return processors;
		}	
		

}
