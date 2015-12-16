
package com.exlibris.dps;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for setMembers complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="setMembers">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="filesCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ieFilesInfo" type="{http://dps.exlibris.com/}ieFilesInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="iesCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="iesFilesSizeSum" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="setId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="setName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "setMembers", propOrder = {
    "filesCount",
    "ieFilesInfo",
    "iesCount",
    "iesFilesSizeSum",
    "setId",
    "setName"
})
public class SetMembers {

    protected Integer filesCount;
    @XmlElement(nillable = true)
    protected List<IeFilesInfo> ieFilesInfo;
    protected Integer iesCount;
    protected Long iesFilesSizeSum;
    protected Long setId;
    protected String setName;

    /**
     * Gets the value of the filesCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFilesCount() {
        return filesCount;
    }

    /**
     * Sets the value of the filesCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFilesCount(Integer value) {
        this.filesCount = value;
    }

    /**
     * Gets the value of the ieFilesInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ieFilesInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIeFilesInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IeFilesInfo }
     * 
     * 
     */
    public List<IeFilesInfo> getIeFilesInfo() {
        if (ieFilesInfo == null) {
            ieFilesInfo = new ArrayList<IeFilesInfo>();
        }
        return this.ieFilesInfo;
    }

    /**
     * Gets the value of the iesCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIesCount() {
        return iesCount;
    }

    /**
     * Sets the value of the iesCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIesCount(Integer value) {
        this.iesCount = value;
    }

    /**
     * Gets the value of the iesFilesSizeSum property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIesFilesSizeSum() {
        return iesFilesSizeSum;
    }

    /**
     * Sets the value of the iesFilesSizeSum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIesFilesSizeSum(Long value) {
        this.iesFilesSizeSum = value;
    }

    /**
     * Gets the value of the setId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSetId() {
        return setId;
    }

    /**
     * Sets the value of the setId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSetId(Long value) {
        this.setId = value;
    }

    /**
     * Gets the value of the setName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSetName() {
        return setName;
    }

    /**
     * Sets the value of the setName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSetName(String value) {
        this.setName = value;
    }

}
