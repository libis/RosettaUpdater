package be;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.BindingProvider;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URLConnection;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.exlibris.core.sdk.formatting.DublinCore;
import com.exlibris.core.sdk.formatting.DublinCoreFactory;
import com.exlibris.dps.IEWebServices;
import com.exlibris.dps.IEWebServices_Service;
import com.exlibris.dps.MetaData;
import com.exlibris.dps.sdk.pds.PdsClient;

public class UpdateFileName {

	/**
	 * @param args
	 */
	public static Properties prop; 
	private static Logger logger = Logger.getLogger(UpdateFileName.class);
	public static String pdsHandle;	
	public static final String fileLabelKey="<key id=\"label\"/>";
	public static final String fileGroupIdKey="<key id=\"groupID\"/>";
	public static final String groupMetsEmptyLabelDiv ="\" TYPE=\"FILE\">";
	public static final String groupMetsFileDiv = "<mets:fptr FILEID=\"";
	public static final String fileMetadataHdr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	public static IEWebServices ieWebService=null;
	public static final String metsHeader = "<mets:mets xmlns:mets=\"http://www.loc.gov/METS/\">";
	public static final String objectCharacteristics = "<section id=\"objectCharacteristics\">"+
	"<record><key id=\"groupID\">Page test</key><key id=\"objectType\">FILE</key><key id=\"creationDate\">2015-09-25 15:08:47</key><key id=\"createdBy\">KULadmin</key><key id=\"modificationDate\">2015-10-16 16:31:17</key><key id=\"modifiedBy\">KULadmin</key><key id=\"owner\">CRS00.KUL.UA</key></record>"+
	"</section>\"";
	
	public static void main(String[] args) {

		List<MetaData> metaDatas = null;
		MetaData metadata = null;
		
    	Properties prop = new Properties();		
    	DB db = new DB();
		try {
			

			String flPid;
			String prPid;
			String flMid;
			String prMid;
			String prPidCurr="0";
			String label;
			String metsLabel;
			Statement stmt = null;
			ResultSet rset = null;
			String filePath = "";
			String dnxGroupId="";
			String dnxLabel="";
			String PRmetadata="";
			String FLmetadata="";
			String tempPrMetadata="";
			String restPrMetadata="";
			String IEmetadata="";
			String tmpMetadata ="";
			boolean first = true;
			
			
			prop.load(new FileInputStream("rosettaupd.properties"));			
			// Connecting to PDS
			PdsClient pds = PdsClient.getInstance();
			pds.init(prop.getProperty("PDS_URL"),false);
			pdsHandle = pds.login(prop.getProperty("institution"), prop.getProperty("userName"), prop.getProperty("password"));
			System.out.println("pdsHandle: " + pdsHandle);
			
			ieWebService = new IEWebServices_Service(new URL(prop.getProperty("IE_WSDL_URL")),new QName("http://dps.exlibris.com/", "IEWebServices")).getIEWebServicesPort();
			  
			try{
				
				db.getConnection();
				
			    stmt = db.getConn().createStatement();
			    
			    String query =  
					"select flpid,prpid,newgroupid,flmid,prmid "+
					"from ( "+
					"select flpid,prpid,flmid,prmid, "+
					"'Page '||to_number(substr(fileOriginalName,instr(fileOriginalName,'_',1,3)+1,6)) as newgroupid, "+
					"groupid, filelabel,replabel "+
					"from ( "+
					"select ccc.pid as flpid,cc.pid as prpid,m.mid as flmid,mr.mid as prmid, "+
					"substr(substr(m.value,instr(m.value,'<key id=\"fileOriginalName\"')+length('<key id=\"fileOriginalName\"')+1),1,instr(substr( m.value,instr(m.value,'<key id=\"fileOriginalName\"')+length('<key id=\"fileOriginalName\"')+1),'</key>')-1) as fileOriginalName, "+
					"substr(substr(m.value,instr(m.value,'<key id=\"groupID\"')+length('<key id=\"groupID\"')+1),1,instr(substr( m.value,instr(m.value,'<key id=\"groupID\"')+length('<key id=\"groupID\"')+1),'</key>')-1) as groupid, "+
					"substr(substr(m.value,instr(m.value,'<key id=\"label\"')+length('<key id=\"label\"')+1),1,instr(substr( m.value,instr(m.value,'<key id=\"label\"')+length('<key id=\"label\"')+1),'</key>')-1) as filelabel, "+
					"substr(substr( dbms_lob.substr(mr.value,5000,1),instr(mr.value,'<key id=\"label\"')+length('<key id=\"label\"')+1),1,instr(substr( dbms_lob.substr(mr.value,5000,1),instr(dbms_lob.substr(mr.value,5000,1),'<key id=\"label\"')+length('<key id=\"label\"')+1),'</key>')-1) as replabel "+
					"from hdecontrol c  "+
					"inner join hdecontrol cc on c.pid = cc.parentid "+
					"inner join hdecontrol ccc  on cc.pid = ccc.parentid "+
					"inner join hdepidmid pm on pm.pid = ccc.pid" +
					" "+
					"inner join hdemetadata m on (pm.mid = m.mid and m.mdid = 21) "+
					"inner join hdepidmid pmr on pmr.pid = cc.pid "+
					"inner join hdemetadata mr on (pmr.mid = mr.mid and mr.mdid = 32) "+
					"where "+
					"c.partitionc = 'BIBC, succeed' and "+
					"cc.preservationtype = 'PRESERVATION_MASTER' "+ 
					"and c.pid = 'IE401455' "+
					") "+
					"where groupid is null or filelabel is null "+
					") ";
			    
			    rset = stmt.executeQuery(query);
			    while (rset.next()) {
		    		flPid = rset.getString(1);
		    		prPid = rset.getString(2);
			    	label = rset.getString(3);
			    	flMid = rset.getString(4);
			    	prMid = rset.getString(5);

/*			    	
			    	if (first) {
			    		first = false;
			    		prPidCurr = prPid;
			    		PRmetadata = ieWebService.getMD(pdsHandle, prPid);
			    	}
					if (!prPid.equals(prPidCurr)) {
						metaDatas = new ArrayList<MetaData>();
						metadata = new MetaData();
						metadata.setContent(PRmetadata);
						metadata.setType("mets_section");
						metadata.setSubType("structMap");
						metadata.setMid(prMid);
						metaDatas.add(metadata);
						ieWebService.updateMD(pdsHandle, prPidCurr, metaDatas);
						PRmetadata = ieWebService.getMD(pdsHandle, prPid);
			    		prPidCurr = prPid;
					}
			    	
			    	
			    	dnxGroupId = "<key id=\"groupID\">"+label+"</key>";
			    	dnxLabel = "<key id=\"label\">"+label+"</key>";
			    	metsLabel = "<mets:div LABEL=\""+label+"\" TYPE=\"FILE\"><mets:fptr FILEID=\""+flPid;
*/
//			    	IEmetadata = ieWebService.getIEMD(pdsHandle, "REP401456");
//			    	IEmetadata = ieWebService.getMD(pdsHandle, "FL399737");
					BufferedReader br = new BufferedReader(new FileReader("3161789.xml"));
					String sCurrentLine; 
					while ((sCurrentLine = br.readLine()) != null) {
						IEmetadata += sCurrentLine;
					}	
					br.close();
							    	
			    	
//			    	FLmetadata = FLmetadata.replace(fileLabelKey, dnxLabel);
//			    	FLmetadata = FLmetadata.replace(fileGroupIdKey, dnxGroupId);
			    	
/*			    	
			    	String headPrMetadata = PRmetadata.substring(0,PRmetadata.indexOf(groupMetsFileDiv+flPid));
			    	tempPrMetadata = headPrMetadata.substring(0,headPrMetadata.lastIndexOf(groupMetsEmptyLabelDiv));
			    	restPrMetadata = PRmetadata.substring(tempPrMetadata.length());
			    	//label moet wel leeg zijn
			    	if (restPrMetadata.startsWith("\"")) {
				    	PRmetadata = tempPrMetadata+label+restPrMetadata;
			    	} 	
*/			    	
			    	DublinCore dc = getDC(IEmetadata);
			    	
			    	tmpMetadata = tmpMetadata.replace("<dc:title/>","<dc:title>met file titel</dc:title>");
//			    	replace(tmpMetadata, "dc:record", "dc:title", "met titel");
			    	
			    	
//					String tmpMetadata = getMetsSectionXml(PRmetadata);
//			    	PRmetadata= metsHeader.concat(PRmetadata);
//					tmpMetadata = tmpMetadata.substring(tmpMetadata.indexOf("<mets"));
//					replace(tmpMetadata,"groupID","",label) ;
//					IEmetadata = IEmetadata.replace(fileGroupIdKey, dnxGroupId);
					
					System.out.println(tmpMetadata);
					metaDatas = new ArrayList<MetaData>();
					metadata = new MetaData();
			        metadata.setMid("212548");
			        metadata.setContent(tmpMetadata);
			        metadata.setType("descriptive");
			        metadata.setSubType("dc");
					metaDatas.add(metadata);
					ieWebService.updateMD(pdsHandle, "IE399735", metaDatas);
					
		    	}
				metaDatas = new ArrayList<MetaData>();
				metadata = new MetaData();
				metadata.setContent(PRmetadata);
				metadata.setType("dnx");
				metadata.setSubType("dnx");
				metaDatas.add(metadata);
				ieWebService.updateMD(pdsHandle, prPidCurr, metaDatas);
			    
			    rset.close();
			    stmt.close();
	    
			  } catch (SQLException e){
				  e.printStackTrace();			  
				  }					  

		} catch (Exception e) {
			e.printStackTrace();
		}			  
	}
	
	
	   /**
     * gets the metadata for the pid, modifies it and sends it back for saving
     * @param cmd
     * @param pid
     * @return
     */
/*	
    private void updateIE(CmdMessage cmd, String pid) {
        PdsClient pds = PdsClient.getInstance();
        try {
            pds.init(cmd.getPds(), false);
            //IEWebServices ieWebService = new IEWebServices_Service(new URL( cmd.getEndpoint()+"?wsdl"),new QName("http://dps.exlibris.com/", "IEWebServices")).getIEWebServicesPort();
            
            BindingProvider ie_bindingProvider = (BindingProvider) ieWebService;
            ie_bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, cmd.getEndpoint());
            String pdsHandle = pds.login(
                    cmd.getInstitution(),
                    cmd.getUserName(),
                    cmd.getPassword());
            String result = ieWebService.getIEMD(pdsHandle, pid);
            String mdc = ieWebService.getMD(pdsHandle,pid);
            String dcXml = getDCXml(result);
            dcXml = replace(dcXml,cmd.getReplacekey(),cmd.getReplacekeyattribute(),cmd.getReplace());
            MetaData md = createMetadata(dcXml);

            ArrayList <MetaData> mds = new ArrayList<MetaData>();
            mds.add(md);
            String ok="ok";
            if (!cmd.testmodus) {
                ieWebService.updateMD(pdsHandle, pid, mds);
            } else {
                ok = "TEST ok";
            }
            //System.out.println(result);
        } catch (Exception e) {
            e.getMessage();
        }
        return;
    }

*/
    // some utils coming here

    private static String replace(String xml, String replacekey, String attr, String replacevalue) throws Exception {
        Document document = getXMLDocument(xml);
        Node node = document.getFirstChild();
        while( node != null) {
        	System.out.println(node.getNodeName());
        	node = node.getNextSibling();
        }
        NodeList nodes = document.getElementsByTagName(replacekey);
        if (nodes.getLength() == 0) {
            throw  new Exception("replace key not found");
        }
        boolean changed = false;
        for (int i = 0; i<nodes.getLength();i++) {
            Element replaceNode = (Element) nodes.item(i);
            if (attr.equals("") && !replaceNode.hasAttributes() ||
                    !attr.equals("") && replaceNode.hasAttribute(attr)) {
                Element newNode = (Element) replaceNode.cloneNode(true);
                newNode.setTextContent(replacevalue);
                replaceNode.getParentNode().appendChild(newNode);
                replaceNode.getParentNode().removeChild(replaceNode);
                changed=true;
            }
        }
        if (!changed) {
            throw  new Exception("replace key attr not found");
        }
        return getStringFromDocument(document);
    }
    
    private static DublinCore getDC(String iexml) throws Exception {
        Document doc = getXMLDocument(iexml);
        String mdWrap = getSection(doc,"//dmdSec//mdWrap");
        Document mdWr = getXMLDocument(mdWrap);
        String record = getSection(mdWr,"//mdWrap//record");
        DublinCore dc = DublinCoreFactory.getInstance().createDocument(record);

        return dc;

    }

    private static String getMetsSectionXml(String iexml) throws Exception {
        Document doc = getXMLDocument(iexml);
        return getSection(doc,"//structMap//div");
    }
    
    public static String getDCXml(String iexml) throws Exception {
        Document doc = getXMLDocument(iexml);
        String mdWrap = getSection(doc,"//dmdSec//mdWrap");
        Document mdWr = getXMLDocument(mdWrap);
        return getSection(mdWr,"//mdWrap//record");
    }

    private static Document getXMLDocument(String entity) throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        InputStream is = new ByteArrayInputStream(entity.getBytes("UTF-8"));
        return dBuilder.parse(is);
    }

    private static String getSection(Document doc, String expression) throws Exception {
        javax.xml.xpath.XPath xPath =  XPathFactory.newInstance().newXPath();
        StreamResult result = new StreamResult(new StringWriter());
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        Node node= (Node) xPath.compile(expression).evaluate(doc, XPathConstants.NODE);

        DOMSource source = new DOMSource(node);
        transformer.transform(source, result);
        return result.getWriter().toString();
    }

    private static MetaData createMetadata(DublinCore dc) throws IOException {
        MetaData md = new MetaData();
        md.setMid(null);
        md.setContent(dc.toXml());
        md.setType("descriptive");
        md.setSubType("dc");
        return md;
    }

    private static MetaData createMetadata(String dcxml) throws IOException {
        MetaData md = new MetaData();
        md.setMid(null);
        md.setContent(dcxml);
        md.setType("descriptive");
        md.setSubType("dc");
        return md;
    }

    public static String getStringFromDocument(Node doc) throws TransformerException {
        DOMSource domSource = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.transform(domSource, result);
        return writer.toString();
    }
	
/*			
	BufferedWriter writer = null;
	try
	{
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream("fl401457md"), "UTF8");
		BufferedWriter bw = new BufferedWriter(outputStreamWriter);
			bw.write(FLmetadata);
			bw.close();
			
//		writer = new BufferedWriter( new FileWriter("ie401455md"));
//	    writer.write(IEmetadata);
//	    writer.close();
//	    writer = new BufferedWriter( new FileWriter("rep399736-1md"));
//	    writer.write(REPmetadata);
//	    writer.close();
//	    writer = new BufferedWriter( new FileWriter("fl401457md"));
//	    writer.write(FLmetadata);
//	    writer.close();
	}
	catch ( IOException e)
	{
	}
	finally
	{
	    try
	    {
	        if ( writer != null)
	        writer.close( );
	    }
	    catch ( IOException e)
	    {
	    }
	}
				
	
//	db.deleteMetadata("IE223886", "42040");
//	db.deleteMetadata("IE223886", "81898");
*/
/*			
	String sCurrentLine;
	String mdContent = "";

	BufferedReader br = new BufferedReader(new FileReader("3161789.xml"));
	 
	while ((sCurrentLine = br.readLine()) != null) {
		mdContent += sCurrentLine;
	}	
	br.close();

	IEParser ie;
	ie = IEParserFactory.create();
	String dc = UpdateFileLabel.getDCXml(mdContent);
	DublinCore DC = DublinCoreFactory.getInstance().createDocument(dc);
	ie.setIEDublinCore(DC);
	

	
	List<MetaData> metaDatas = new ArrayList<MetaData>();
	MetaData repMetadata = new MetaData();
	repMetadata.setContent(mdContent);
	repMetadata.setMid("REP401456-1");
	repMetadata.setType("mets_section");
	repMetadata.setSubType("structMap");
	metaDatas.add(repMetadata);
	ieWebService.updateMD(pdsHandle, "REP401456", metaDatas);
*/		
	
	
}
