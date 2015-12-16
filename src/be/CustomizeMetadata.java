package be;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.exlibris.dps.IEWSException_Exception;
import com.exlibris.dps.IEWebServices;
import com.exlibris.dps.InvalidMIDException_Exception;
import com.exlibris.dps.InvalidTypeException_Exception;
import com.exlibris.dps.InvalidXmlException_Exception;
import com.exlibris.dps.LockedIeException_Exception;
import com.exlibris.dps.MetaData;
import com.exlibris.dps.NotInPermanentException_Exception;
import com.exlibris.dps.UserAuthorizeException_Exception;



public class CustomizeMetadata {

	final String startDC = "<dc:";
	final String endDC = "</dc:";
	final String closeDC = ">";
	final String spatie = " "; 
	final String startDCRecord = "<dc:record";
	final String endDCRecord = "</dc:record>";
	public IEWebServices ieWebService;
	public String pdsHandle;
	private DB db = null;
	private Logger logger = Logger.getLogger(CustomizeMetadata.class);

	
	CustomizeMetadata(String pdsHandle,IEWebServices ieWebService,DB db) {
		this.ieWebService = ieWebService;
		this.pdsHandle = pdsHandle;
		this.db = db;
	}
	
	public boolean addTag(CustomerBean customer){
		List<MetaData> metaDatas = null;
		MetaData metadata = null;
		String md = null;
		try {
			 md = ieWebService.getMD(pdsHandle, customer.getPid());
		} catch (IEWSException_Exception | NotInPermanentException_Exception
				| UserAuthorizeException_Exception e1) {
			logger.error(e1.getMessage());
			return false;
		}
		
		
		String tag = startDC+customer.getNewitem().toLowerCase();
		if (!"".equals(customer.getNewattribute())) {
			tag+= spatie+customer.getNewattribute();
		}
		tag += closeDC+customer.getNewvalue()+endDC+customer.getNewitem().toLowerCase()+closeDC;
		tag += endDCRecord;
		
		md = md.replace(endDCRecord, tag);
		md = md.substring(md.indexOf(startDCRecord),md.indexOf(endDCRecord)+endDCRecord.length());
		metaDatas = new ArrayList<MetaData>();
		metadata = new MetaData();
        metadata.setMid(null);
        metadata.setContent(md);
        metadata.setType("descriptive");
        metadata.setSubType("dc");
		metaDatas.add(metadata);
		try {
			ieWebService.updateMD(pdsHandle, customer.getPid(), metaDatas);
		} catch (IEWSException_Exception | InvalidMIDException_Exception
				| InvalidTypeException_Exception
				| InvalidXmlException_Exception | LockedIeException_Exception
				| NotInPermanentException_Exception
				| UserAuthorizeException_Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}
	
	
	public boolean updateTag(CustomerBean customer){
		List<MetaData> metaDatas = null;
		MetaData metadata = null;
		String md = null;
		String mdUpd = null;
		try {
			 md = ieWebService.getMD(pdsHandle, customer.getPid());
		} catch (IEWSException_Exception | NotInPermanentException_Exception
				| UserAuthorizeException_Exception e1) {
			logger.error(e1.getMessage());
			return false;
		}
		
/*		
		String FLmetadata="";
		BufferedWriter writer = null;
		try{
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream("IE140245md"), "UTF8");
			BufferedWriter bw = new BufferedWriter(outputStreamWriter);
				bw.write(md);
				bw.close();
				
		} catch(IOException ioe) {
			System.err.println(ioe.getMessage());
		}
*/		

		md = md.substring(md.indexOf(startDCRecord),md.indexOf(endDCRecord)+endDCRecord.length());
		String tag = startDC+customer.getItem().toLowerCase();
		if (!"".equals(customer.getNewattribute())) {
			tag+= spatie+customer.getNewattribute();
		}
		tag += closeDC;
		String tagN = endDC+customer.getItem().toLowerCase()+closeDC;
		
		
		int index = 0;
		String workStr;
		index = md.length();
		while ((index = md.lastIndexOf(tag, index)) > 0) {
			workStr = md.substring(index);
			workStr = workStr.substring(0, workStr.indexOf(tagN));
			if ((workStr != null) && (workStr.contains(customer.getValue()))) {
				String newWorkStr = workStr.replace(customer.getValue(), customer.getNewvalue());
				md = md.replace(workStr, newWorkStr);
			}
			index -= tag.length();
		}
		
		metaDatas = new ArrayList<MetaData>();
		metadata = new MetaData();
        metadata.setMid(null);
        metadata.setContent(md);
        metadata.setType("descriptive");
        metadata.setSubType("dc");
		metaDatas.add(metadata);
		try {
			ieWebService.updateMD(pdsHandle, customer.getPid(), metaDatas);
		} catch (IEWSException_Exception | InvalidMIDException_Exception
				| InvalidTypeException_Exception
				| InvalidXmlException_Exception | LockedIeException_Exception
				| NotInPermanentException_Exception
				| UserAuthorizeException_Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}
	public boolean deleteTag(CustomerBean customer){
		List<MetaData> metaDatas = null;
		MetaData metadata = null;
		String md = null;
		String mdUpd = null;
		try {
			 md = ieWebService.getMD(pdsHandle, customer.getPid());
		} catch (IEWSException_Exception | NotInPermanentException_Exception
				| UserAuthorizeException_Exception e1) {
			logger.error(e1.getMessage());
			return false;
		}
		
		md = md.substring(md.indexOf(startDCRecord),md.indexOf(endDCRecord)+endDCRecord.length());
		String tag = startDC+customer.getItem().toLowerCase() + closeDC;
		String tagN = endDC+customer.getItem().toLowerCase()+closeDC;
		
		
		int index = 0;
		String workStr;
		index = md.length();
		while ((index = md.lastIndexOf(tag, index)) > 0) {
			workStr = md.substring(index);
			workStr = workStr.substring(0, workStr.indexOf(tagN)+tagN.length());
			if ((workStr != null) && (workStr.contains(customer.getValue()))) {
				md = md.replace(workStr,"");
			}
			index -= tag.length();
		}
		
		metaDatas = new ArrayList<MetaData>();
		metadata = new MetaData();
        metadata.setMid(null);
        metadata.setContent(md);
        metadata.setType("descriptive");
        metadata.setSubType("dc");
		metaDatas.add(metadata);
		try {
			ieWebService.updateMD(pdsHandle, customer.getPid(), metaDatas);
		} catch (IEWSException_Exception | InvalidMIDException_Exception
				| InvalidTypeException_Exception
				| InvalidXmlException_Exception | LockedIeException_Exception
				| NotInPermanentException_Exception
				| UserAuthorizeException_Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			return false;
		}		
		return true;
	}
	
	public boolean addXML(CustomerBean customer){
		List<MetaData> metaDatas = null;
		MetaData metadata = null;		
		String sCurrentLine;
		String mdContent = "";
		String mid = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(customer.getXmlPath()));
			while ((sCurrentLine = br.readLine()) != null) {
				mdContent += sCurrentLine;
			}
			br.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
			return false;
		}
/*		
		mdContent = mdContent.replace("<record", "<dc:record");
		mdContent = mdContent.replace("</record", "</dc:record");
*/		
		mid = db.getMID(customer.getPid(), "descriptive", "dc");
		metaDatas = new ArrayList<MetaData>();
		metadata = new MetaData();
        metadata.setMid(mid);
        metadata.setContent(mdContent);
        metadata.setType("descriptive");
        metadata.setSubType("dc");
		metaDatas.add(metadata);
		try {
			ieWebService.updateMD(pdsHandle, customer.getPid(), metaDatas);
		} catch (IEWSException_Exception | InvalidMIDException_Exception
				| InvalidTypeException_Exception
				| InvalidXmlException_Exception | LockedIeException_Exception
				| NotInPermanentException_Exception
				| UserAuthorizeException_Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}
}
