
package com.exlibris.dps;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ieFilesInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ieFilesInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fileInfoList" type="{http://dps.exlibris.com/}fileInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="filesSizeSum" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="iePID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ieFilesInfo", propOrder = {
    "fileInfoList",
    "filesSizeSum",
    "iePID"
})
public class IeFilesInfo {

    @XmlElement(nillable = true)
    protected List<FileInfo> fileInfoList;
    protected Long filesSizeSum;
    protected String iePID;

    /**
     * Gets the value of the fileInfoList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fileInfoList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFileInfoList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FileInfo }
     * 
     * 
     */
    public List<FileInfo> getFileInfoList() {
        if (fileInfoList == null) {
            fileInfoList = new ArrayList<FileInfo>();
        }
        return this.fileInfoList;
    }

    /**
     * Gets the value of the filesSizeSum property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFilesSizeSum() {
        return filesSizeSum;
    }

    /**
     * Sets the value of the filesSizeSum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFilesSizeSum(Long value) {
        this.filesSizeSum = value;
    }

    /**
     * Gets the value of the iePID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIePID() {
        return iePID;
    }

    /**
     * Sets the value of the iePID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIePID(String value) {
        this.iePID = value;
    }

}
