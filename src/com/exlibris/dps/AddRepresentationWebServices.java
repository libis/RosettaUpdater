
package com.exlibris.dps;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-2b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "AddRepresentationWebServices", targetNamespace = "http://dps.exlibris.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface AddRepresentationWebServices {


    /**
     * 
     * @param arg5
     * @param arg4
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "AddRepresentationResult", targetNamespace = "")
    @RequestWrapper(localName = "addRepresentation", targetNamespace = "http://dps.exlibris.com/", className = "com.exlibris.dps.AddRepresentation")
    @ResponseWrapper(localName = "addRepresentationResponse", targetNamespace = "http://dps.exlibris.com/", className = "com.exlibris.dps.AddRepresentationResponse")
    public String addRepresentation(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        String arg4,
        @WebParam(name = "arg5", targetNamespace = "")
        String arg5);

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getHeartBit", targetNamespace = "http://dps.exlibris.com/", className = "com.exlibris.dps.GetHeartBit")
    @ResponseWrapper(localName = "getHeartBitResponse", targetNamespace = "http://dps.exlibris.com/", className = "com.exlibris.dps.GetHeartBitResponse")
    public String getHeartBit();

}
