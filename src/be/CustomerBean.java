package be;
   /*
    * Copyright 2007 Kasper B. Graversen
    * 
    * Licensed under the Apache License, Version 2.0 (the "License");
    * you may not use this file except in compliance with the License.
    * You may obtain a copy of the License at
    * 
    *     http://www.apache.org/licenses/LICENSE-2.0
    * 
    * Unless required by applicable law or agreed to in writing, software
    * distributed under the License is distributed on an "AS IS" BASIS,
    * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    * See the License for the specific language governing permissions and
   * limitations under the License.
   */
  
  import java.util.Date;
  
  /**
   * Superclass bean to use when testing bean reading/writing.
   * 
   * @author James Bassett
   */
  public class CustomerBean extends EntityBean implements Customer {
  	
	  	private String xmlPath;
	  	private String item;
	  	private String attribute;
	  	private String value;
	  	private String newitem;
	  	private String newattribute;
	  	private String newvalue;
  	
  	private String mailingAddress;
  	
  	/**
 	 * Default Constructor.
  	 */
  	public CustomerBean() {
  	}
  	
  	/**
  	 * Constructs a CustomerBean.
  	 * 
  	 */
  	public CustomerBean(final String pid, final String xmlPath, final String item, final String attribute,
  		final String value, final String newItem, final String newAttribute, final String newValue
  		) {
  		
  		setPid(pid);
  		setXmlPath(xmlPath);
  		setItem(item);
  		setAttribute(attribute);
  		setValue(value);
  		setNewitem(newItem);
  		setNewattribute(newAttribute);
  		setNewvalue(newValue);
  	}
  	
  	
  	/**
  	 * @return the mailingAddress
  	 */
  	public String getMailingAddress() {
  		return mailingAddress;
  	}
  	
  	/**
 	 * @param mailingAddress
 	 *            the mailingAddress to set
 	 */
 	public void setMailingAddress(String mailingAddress) {
 		this.mailingAddress = mailingAddress;
 	}
 	
 	
 	public String getXmlPath() {
		return xmlPath;
	}

	public void setXmlPath(String xmlPath) {
		this.xmlPath = xmlPath;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getNewitem() {
		return newitem;
	}

	public void setNewitem(String newitem) {
		this.newitem = newitem;
	}

	public String getNewattribute() {
		return newattribute;
	}

	public void setNewattribute(String newattribute) {
		this.newattribute = newattribute;
	}

	public String getNewvalue() {
		return newvalue;
	}

	public void setNewvalue(String newvalue) {
		this.newvalue = newvalue;
	}


	@Override
 	public int hashCode() {
 		final int prime = 31;
 		int result = super.hashCode();
 		result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
 		return result;
 	}
 	
 	@Override
 	public boolean equals(final Object obj) {
 		if( this == obj ) {
 			return true;
 		}
 		if( !super.equals(obj) ) {
 			return false;
 		}
 		if( !(obj instanceof CustomerBean) ) {
 			return false;
 		}
 		CustomerBean other = (CustomerBean) obj;
 		if( mailingAddress == null ) {
 			if( other.mailingAddress != null ) {
 				return false;
 			}
 		} else if( !mailingAddress.equals(other.mailingAddress) ) {
 			return false;
 		}
 		return true;
 	}
 	
 	@Override
 	public String toString() {
 		return String
 			.format(
 				"CustomerBean [pid=%s, xmlPath=%s, item=%s, attribute=%s, value=%s, newItem=%s, newAttribute=%s, newValue=%s]",
 				getPid(), getXmlPath(), getItem(), getAttribute(), getValue(), getNewitem(),
 				getNewattribute(), getNewvalue());
 	}
 	
}