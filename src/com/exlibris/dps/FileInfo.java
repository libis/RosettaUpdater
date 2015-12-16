
package com.exlibris.dps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fileInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fileInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="filePid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fileSize" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="fileStorageId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fileUid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iePid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fileInfo", propOrder = {
    "filePid",
    "fileSize",
    "fileStorageId",
    "fileUid",
    "iePid"
})
public class FileInfo {

    protected String filePid;
    protected Long fileSize;
    protected String fileStorageId;
    protected String fileUid;
    protected String iePid;

    /**
     * Gets the value of the filePid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilePid() {
        return filePid;
    }

    /**
     * Sets the value of the filePid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilePid(String value) {
        this.filePid = value;
    }

    /**
     * Gets the value of the fileSize property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFileSize() {
        return fileSize;
    }

    /**
     * Sets the value of the fileSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFileSize(Long value) {
        this.fileSize = value;
    }

    /**
     * Gets the value of the fileStorageId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileStorageId() {
        return fileStorageId;
    }

    /**
     * Sets the value of the fileStorageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileStorageId(String value) {
        this.fileStorageId = value;
    }

    /**
     * Gets the value of the fileUid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileUid() {
        return fileUid;
    }

    /**
     * Sets the value of the fileUid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileUid(String value) {
        this.fileUid = value;
    }

    /**
     * Gets the value of the iePid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIePid() {
        return iePid;
    }

    /**
     * Sets the value of the iePid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIePid(String value) {
        this.iePid = value;
    }

}
