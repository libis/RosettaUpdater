package be;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.supercsv.cellprocessor.ConvertNullTo;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseBool;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.Truncate;
import org.supercsv.cellprocessor.constraint.LMinMax;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.StrRegEx;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.exlibris.dps.IEWebServices;
import com.exlibris.dps.IEWebServices_Service;
import com.exlibris.dps.MetaData;
import com.exlibris.dps.sdk.deposit.IEParser;
import com.exlibris.dps.sdk.deposit.IEParserFactory;
import com.exlibris.dps.sdk.pds.PdsClient;

public class Metadata {

	static Properties prop = new Properties();		
	private static Logger logger = Logger.getLogger(Metadata.class);
	private static CustomizeMetadata cMetadata;
	public static  IEWebServices ieWebService;
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
			pdsHandle = pds.login(prop.getProperty("institution"), prop.getProperty("userName"), prop.getProperty("password"));
			System.out.println("pdsHandle: " + pdsHandle);
			
			db = new DB(prop.getProperty("DB_URL"), prop.getProperty("DB_USER_ID"), prop.getProperty("DB_PASSWORD"));
			
			ieWebService = new IEWebServices_Service(new URL(prop.getProperty("IE_WSDL_URL")),new QName("http://dps.exlibris.com/", "IEWebServices")).getIEWebServicesPort();
			cMetadata = new CustomizeMetadata(pdsHandle,ieWebService,db);

			
	        ICsvBeanReader beanReader = null;
	        try {
	                beanReader = new CsvBeanReader(new FileReader(prop.getProperty("CSV_FILENAME")), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
	                
	                // the header elements are used to map the values to the bean (names must match)
	                final String[] header = beanReader.getHeader(true);
	                final CellProcessor[] processors = getProcessors();
	                
	                CustomerBean customer;
	                while( (customer = beanReader.read(CustomerBean.class, header, processors)) != null ) {
	                        System.out.println(String.format("lineNo=%s, rowNo=%s, customer=%s", beanReader.getLineNumber(),
	                                beanReader.getRowNumber(), customer));
	                        
	                        if (!"".equals(customer.getXmlPath())) {
	                        	cMetadata.addXML(customer);
	                        } else if ((!"".equals(customer.getItem())) && (!"".equals(customer.getNewitem()))) {
	                        	cMetadata.updateTag(customer);
	                        } else if (!"".equals(customer.getItem())) {
	                        	cMetadata.deleteTag(customer);
	                        } else if (!"".equals(customer.getNewitem())) {
	                        	cMetadata.addTag(customer);
	                        } else {
	                        	logger.error("Onvolledige gegevens voor "+ String.format("lineNo=%s, rowNo=%s, customer=%s", beanReader.getLineNumber(),
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
	                new ConvertNullTo(""), // csv path
	                new ConvertNullTo(""), // item
	                new ConvertNullTo(""), // attribute
	                new ConvertNullTo(""), // value
	                new ConvertNullTo(""), // newitem
	                new ConvertNullTo(""), // newattribute
	                new ConvertNullTo("") // newvalue
	        };
	        
	        return processors;
	}	
	
	private void readWithCsvBeanReader() throws Exception {
        
        ICsvBeanReader beanReader = null;
        try {
                beanReader = new CsvBeanReader(new FileReader(prop.getProperty("CSV_FILENAME")), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
                
                // the header elements are used to map the values to the bean (names must match)
                final String[] header = beanReader.getHeader(true);
                final CellProcessor[] processors = getProcessors();
                
                CustomerBean customer;
                while( (customer = beanReader.read(CustomerBean.class, header, processors)) != null ) {
                        System.out.println(String.format("lineNo=%s, rowNo=%s, customer=%s", beanReader.getLineNumber(),
                                beanReader.getRowNumber(), customer));
                        
                        if (!"".equals(customer.getXmlPath())) {
                        	cMetadata.addXML(customer);
                        } else if ((!"".equals(customer.getItem())) && (!"".equals(customer.getNewitem()))) {
//                        	cMetadata.updateTag(customer);
                        } else if (!"".equals(customer.getItem())) {
//                        	cMetadata.deleteTag(customer);
                        } else if (!"".equals(customer.getNewitem())) {
//                        	cMetadata.addTag(customer);
                        } else {
                        	logger.error("Onvolledige gegevens voor "+ String.format("lineNo=%s, rowNo=%s, customer=%s", beanReader.getLineNumber(),
                                beanReader.getRowNumber(), customer));
                        }
                }
                
        }
        finally {
                if( beanReader != null ) {
                        beanReader.close();
                }
        }
	}	

}
