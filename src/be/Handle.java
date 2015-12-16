package be;

import java.io.FileInputStream;
import java.util.Properties;

import com.exlibris.dps.sdk.pds.PdsClient;

public class Handle {

	
	
	/**
	 * @param args
	 */
	
	private static String pdsHandle;
	public static Properties prop; 
	private static PdsClient pds;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		pds = PdsClient.getInstance();
		pds.init(prop.getProperty("PDS_URL"),false);
    	prop = new Properties();
		try {
	    	prop.load(new FileInputStream("rosettaupd.properties"));
			pdsHandle = pds.login(prop.getProperty("institution"), prop.getProperty("userName"), prop.getProperty("password"));
			
			if (args[0].equals("createMetadataEntry")) {
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
