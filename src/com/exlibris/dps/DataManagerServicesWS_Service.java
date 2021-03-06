
package com.exlibris.dps;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-2b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "DataManagerServicesWS", targetNamespace = "http://dps.exlibris.com/", wsdlLocation = "file:/C:/Users/u0077845/Documents/NetBeansProjects/DataManagerWS/src/conf/xml-resources/web-services/DataManagerServicesWS/wsdl/libis-p-rosetta-3w.cc.kuleuven.be_1801/dpsws/repository/DataManagerServicesWS.wsdl")
public class DataManagerServicesWS_Service
    extends Service
{

    private final static URL DATAMANAGERSERVICESWS_WSDL_LOCATION;
    private final static WebServiceException DATAMANAGERSERVICESWS_EXCEPTION;
    private final static QName DATAMANAGERSERVICESWS_QNAME = new QName("http://dps.exlibris.com/", "DataManagerServicesWS");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/Users/u0077845/Documents/NetBeansProjects/DataManagerWS/src/conf/xml-resources/web-services/DataManagerServicesWS/wsdl/libis-p-rosetta-3w.cc.kuleuven.be_1801/dpsws/repository/DataManagerServicesWS.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        DATAMANAGERSERVICESWS_WSDL_LOCATION = url;
        DATAMANAGERSERVICESWS_EXCEPTION = e;
    }

    public DataManagerServicesWS_Service() {
        super(__getWsdlLocation(), DATAMANAGERSERVICESWS_QNAME);
    }

 
    public DataManagerServicesWS_Service(URL wsdlLocation) {
        super(wsdlLocation, DATAMANAGERSERVICESWS_QNAME);
    }

 
    public DataManagerServicesWS_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

     /**
     * 
     * @return
     *     returns DataManagerServicesWS
     */
    @WebEndpoint(name = "DataManagerServicesWSPort")
    public DataManagerServicesWS getDataManagerServicesWSPort() {
        return super.getPort(new QName("http://dps.exlibris.com/", "DataManagerServicesWSPort"), DataManagerServicesWS.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DataManagerServicesWS
     */
    @WebEndpoint(name = "DataManagerServicesWSPort")
    public DataManagerServicesWS getDataManagerServicesWSPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://dps.exlibris.com/", "DataManagerServicesWSPort"), DataManagerServicesWS.class, features);
    }

    private static URL __getWsdlLocation() {
        if (DATAMANAGERSERVICESWS_EXCEPTION!= null) {
            throw DATAMANAGERSERVICESWS_EXCEPTION;
        }
        return DATAMANAGERSERVICESWS_WSDL_LOCATION;
    }

}
