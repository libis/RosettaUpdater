package be;

    public class EntityBean {
   	
   	private String pid;
  	
  	private String email;
  	
  	public EntityBean() {
  	}
  	
  	public EntityBean(final String pid, final String email) {
  		this.pid = pid;
  		this.email = email;
  	}
  	
  	public String getPid() {
  		return pid;
  	}
  	
  	public void setPid(String pid) {
  		this.pid = pid;
  	}

  	public String getEmail() {
  		return email;
  	}

  	public void setEmail(String email) {
  		this.email = email;
  	}
  	
  	@Override
  	public int hashCode() {
  		final int prime = 31;
  		int result = 1;
  		result = prime * result + ((email == null) ? 0 : email.hashCode());
  		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
  		return result;
  	}
  	
  	@Override
  	public boolean equals(Object obj) {
  		if( this == obj ) {
  			return true;
  		}
  		if( obj == null ) {
  			return false;
  		}
  		if( !(obj instanceof EntityBean) ) {
  			return false;
  		}
  		EntityBean other = (EntityBean) obj;
  		if( email == null ) {
  			if( other.email != null ) {
  				return false;
  			}
  		} else if( !email.equals(other.email) ) {
  			return false;
  		}
  		if( pid == null ) {
  			if( other.pid != null ) {
  				return false;
  			}
  		} else if( !pid.equals(other.pid) ) {
  			return false;
  		}
  		return true;
  	}
  	
  	@Override
  	public String toString() {
  		return String
  			.format(
  				"PersonBean [pid=%s, email=%s]",
  				pid, email);
  	}
  	

}
