package be;

public class RepresentationBean extends EntityBean {

  	private String preservationtype;
  	private String label;
  	private String filepath;
  	private String representationcode;
  	private String accessright;
  	
  	public RepresentationBean(){
  	}
  	
  	public RepresentationBean(final String pid, final String preservationtype, final String representationcode, final String accessright,final String label, final String filepath) {
  		super(pid,null);
  		this.filepath = filepath;
  		this.label = label;
  		this.representationcode = representationcode;
  		this.accessright = accessright;
  		this.preservationtype = preservationtype;
  	}

	public String getPreservationtype() {
		return preservationtype;
	}

	public void setPreservationtype(String preservationtype) {
		this.preservationtype = preservationtype;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getRepresentationcode() {
		return representationcode;
	}

	public void setRepresentationcode(String representationcode) {
		this.representationcode = representationcode;
	}

	public String getAccessright() {
		return accessright;
	}

	public void setAccessright(String accessright) {
		this.accessright = accessright;
	}
  	
	@Override
 	public String toString() {
 		return String
 			.format(
 				"RepresentationBean [pid=%s, preservationtype=%s, representationcode=%s, accessright=%s,label=%s, filePath=%s]",
 				getPid(), getPreservationtype(),getRepresentationcode(),getAccessright(), getLabel(), getFilepath());
 	}
}
