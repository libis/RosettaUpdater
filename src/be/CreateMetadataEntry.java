package be;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.namespace.QName;

import com.exlibris.dps.IEWebServices;
import com.exlibris.dps.IEWebServices_Service;
import com.exlibris.dps.PermanentManagerWS;
import com.exlibris.dps.PermanentManagerWS_Service;


public class CreateMetadataEntry {
		
	private IEWebServices  ieWebServices;
	private PermanentManagerWS pmWebServices;

	
	
	public CreateMetadataEntry(String generalFileName, String metadataFileName) {
		super();
	}

	private String readFile(String fileName) throws IOException {
	    File f = new File(fileName);
	    byte[] bytes = new byte[(int)f.length()];
	    FileInputStream fis = new FileInputStream(f);
	    fis.read(bytes);
	    return new String(bytes, "UTF-8");
	  }

	  public void soapCreateMetadataEntry(String generalFileName, String metadataFileName) {
	    try {
	    
	    	Thread.sleep(3000);
	    	
			ieWebServices = new IEWebServices_Service(new URL(Handle.prop.getProperty("IE_WSDL_URL")),new QName("http://dps.exlibris.com/", "IEWebServices")).getIEWebServicesPort();
			pmWebServices = new PermanentManagerWS_Service(new URL(Handle.prop.getProperty("PM_WSDL_URL")),new QName("http://dps.exlibris.com/", "PermanentManagerWS")).getPermanentManagerWSPort();

			String generalXml = readFile(generalFileName);
			String metadataXml = readFile(metadataFileName);

	      if(!metadataXml.contains("<xb:digital_entity_result") || !metadataFileName.equalsIgnoreCase("general.xml"))
	      {
		      Object[] parameters = new Object[] {generalXml, "", "descriptive",
		        "dc", metadataXml};
		
		    //  String ret = (String) pmWebServices.storeMetadata(arg0, arg1, arg2, arg3);
		
		     // extractMid(ret, metadataFileName);
	      }
	      else {
			throw new WrongFormatException("Het metadatabestand bevat de verkeerde informatie");
		}

	    } catch (IOException e) {
	      System.err.println(e.toString());
		} catch (InterruptedException e) {
			System.err.println(e.toString());
		}
	  }
	  
	  public void extractMid(String result, String metadataFileName) throws IOException
	  { 
		  if (!result.contains("error")) {
			 String REGEX = "<mid>(.*)</mid>";
			 Pattern p = Pattern.compile(REGEX);
			 Matcher items = p.matcher(result);
			 
			 if (items.find()) {
			   	 String mid = items.group(1);
			  // PrintStream out = new PrintStream(System.out, true, "UTF-8");
			      // out.println(ret);
			      
			      // Schrijf het resultaat naar een bestand. Het kan gebruikt worden om te zien of er geen foutmeldingen zijn opgetreden 
			      // Maak file
			      FileWriter fstream = new FileWriter(metadataFileName + "_" + mid + ".out");
			      BufferedWriter outPut = new BufferedWriter(fstream);
			      
			      // Schrijf de content
			      outPut.write(readFile(metadataFileName));
			    	    
			      //Sluit de file
			      outPut.close();
			 } else {
				throw new NotFoundException("Mid niet gevonden voor: " + result);
			 }
		}
	  }
}
