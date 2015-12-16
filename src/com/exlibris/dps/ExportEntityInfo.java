
package com.exlibris.dps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for exportEntityInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="exportEntityInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fullExportPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="processExecutionId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "exportEntityInfo", propOrder = {
    "fullExportPath",
    "processExecutionId"
})
public class ExportEntityInfo {

    protected String fullExportPath;
    protected long processExecutionId;

    /**
     * Gets the value of the fullExportPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFullExportPath() {
        return fullExportPath;
    }

    /**
     * Sets the value of the fullExportPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFullExportPath(String value) {
        this.fullExportPath = value;
    }

    /**
     * Gets the value of the processExecutionId property.
     * 
     */
    public long getProcessExecutionId() {
        return processExecutionId;
    }

    /**
     * Sets the value of the processExecutionId property.
     * 
     */
    public void setProcessExecutionId(long value) {
        this.processExecutionId = value;
    }

}