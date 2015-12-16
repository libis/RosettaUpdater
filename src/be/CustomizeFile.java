package be;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.exlibris.dps.FixityEventException_Exception;
import com.exlibris.dps.IEWSException_Exception;
import com.exlibris.dps.IEWebServices;
import com.exlibris.dps.InvalidMIDException_Exception;
import com.exlibris.dps.InvalidTypeException_Exception;
import com.exlibris.dps.InvalidXmlException_Exception;
import com.exlibris.dps.LockedIeException_Exception;
import com.exlibris.dps.NotInPermanentException_Exception;
import com.exlibris.dps.Operation;
import com.exlibris.dps.RepresentationContent;
import com.exlibris.dps.UserAuthorizeException_Exception;

public class CustomizeFile {
	public IEWebServices ieWebService;
	public String pdsHandle;
	private DB db = null;
	
	private Logger logger = Logger.getLogger(CustomizeFile.class);
	
	//initialiseer RepresentationContent
	
	CustomizeFile(String pdsHandle,IEWebServices ieWebService,DB db) {
		this.ieWebService = ieWebService;
		this.pdsHandle = pdsHandle;
		this.db = db;
	}

	public boolean deleteFile(FileBean customer){
		List<RepresentationContent> representationContents = new ArrayList<RepresentationContent>();
		RepresentationContent repContent = new RepresentationContent();
		List<String> lijst=null;
		String flpid = null,reppid = null,iepid = null;

    	if (customer.getPid().startsWith("REP")) {
    		lijst = db.getIEAndFilePIDOfRepresenation(customer.getPid(),customer.getLabel());
    		iepid = lijst.get(0);
    		reppid = customer.getPid();
    		flpid = lijst.get(1);
    	} else {
    		lijst = db.getIEAndRepPidOfFilePid(customer.getPid());
    		flpid = customer.getPid();
    		reppid = lijst.get(1);
    		iepid = lijst.get(0);
    	}

		repContent.setOperation(Operation.REMOVE);
		repContent.setOldFilePid(flpid);

		representationContents.add(repContent);

		try {
			Long result = ieWebService.updateRepresentation(pdsHandle, iepid, reppid, "DELETE", representationContents);
		} catch (FixityEventException_Exception | IEWSException_Exception
				| InvalidMIDException_Exception
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
	
	public boolean addFile(FileBean customer,String filePath){
		List<RepresentationContent> representationContents = new ArrayList<RepresentationContent>();
		RepresentationContent repContent = new RepresentationContent();
		String flpid = null,reppid = null,iepid = null,label=null;

    	if (customer.getPid().startsWith("REP")) {
    		reppid = customer.getPid();
    		iepid = db.getIEPIDOfRepresenation(customer.getPid());
    		if (iepid == null) return false;
    	} else {
    		return false;
    	}
    	
    	int labelstartidx = filePath.lastIndexOf("/") == -1 ? filePath.lastIndexOf("\\") : filePath.lastIndexOf("/");
    	labelstartidx++;
    	label = filePath.substring(labelstartidx,filePath.lastIndexOf("."));
    	
    	if (customer.getOriginalfilepath() != null)	repContent.setFileOriginalPath(customer.getOriginalfilepath());
		repContent.setLabel(label);
		repContent.setOperation(Operation.ADD);
		repContent.setNewFile(filePath);
		
		representationContents.add(repContent);

		try {
			Long result = ieWebService.updateRepresentation(pdsHandle, iepid, reppid, "ADD", representationContents);
		} catch (FixityEventException_Exception | IEWSException_Exception
				| InvalidMIDException_Exception
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
	
	
	public boolean updateFile(FileBean customer){
		List<RepresentationContent> representationContents = new ArrayList<RepresentationContent>();
		RepresentationContent repContent = new RepresentationContent();
		List<String> lijst=null;
		String flpid = null,reppid = null,iepid = null,label=null;

    	if (customer.getPid().startsWith("REP")) {
    		lijst = db.getIEAndFilePIDOfRepresenation(customer.getPid(),customer.getLabel());
    		if (lijst.size() == 0) return false;
    		iepid = lijst.get(0);
    		reppid = customer.getPid();
    		flpid = lijst.get(1);
    	} else {
    		lijst = db.getIEAndRepPidOfFilePid(customer.getPid());
    		if (lijst.size() == 0) return false;
    		flpid = customer.getPid();
    		reppid = lijst.get(1);
    		iepid = lijst.get(0);
    	}
    	
    	int labelstartidx = customer.getFilepath().lastIndexOf("/") == -1 ? customer.getFilepath().lastIndexOf("\\") : customer.getFilepath().lastIndexOf("/");
    	labelstartidx++;
    	label = customer.getFilepath().substring(labelstartidx,customer.getFilepath().lastIndexOf("."));
    	
    	if (customer.getOriginalfilepath() != null)	repContent.setFileOriginalPath(customer.getOriginalfilepath());
		repContent.setLabel(label);
		repContent.setOperation(Operation.REPLACE);
		repContent.setOldFilePid(flpid);
		repContent.setNewFile(customer.getFilepath());
		
		representationContents.add(repContent);

		try {
			Long result = ieWebService.updateRepresentation(pdsHandle, iepid, reppid, "UPDATE", representationContents);
		} catch (FixityEventException_Exception | IEWSException_Exception
				| InvalidMIDException_Exception
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
