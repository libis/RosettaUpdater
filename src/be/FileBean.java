package be;

public class FileBean extends EntityBean {

	
  	private String reptype;
  	private String label;
  	private String filepath;
  	private String originalfilepath;

  	public FileBean(){
  	}
  	
  	public FileBean(final String pid, final String reptype, final String label, final String filepath,final String originalfilepath) {
  		super(pid,null);
  		this.filepath = filepath;
  		this.label = label;
  		this.reptype = reptype;
  		this.originalfilepath = originalfilepath;
  	}

	public String getReptype() {
		return reptype;
	}

	public void setReptype(String reptype) {
		this.reptype = reptype;
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
  	
	
 	public String getOriginalfilepath() {
		return originalfilepath;
	}

	public void setOriginalfilepath(String originalfilepath) {
		this.originalfilepath = originalfilepath;
	}

	@Override
 	public String toString() {
 		return String
 			.format(
 				"CustomerBean [pid=%s, filePath=%s, label=%s, reptype=%s, originalfilepath=%s]",
 				getPid(), getFilepath(), getLabel(),getReptype(),getOriginalfilepath());
 	}
	
  	
}
