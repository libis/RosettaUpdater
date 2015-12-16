
package com.exlibris.dps;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-2b01 
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "SetNotValidException", targetNamespace = "http://dps.exlibris.com/")
public class SetNotValidException_Exception
    extends java.lang.Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private SetNotValidException faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public SetNotValidException_Exception(String message, SetNotValidException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public SetNotValidException_Exception(String message, SetNotValidException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.exlibris.dps.SetNotValidException
     */
    public SetNotValidException getFaultInfo() {
        return faultInfo;
    }

}
