
package com.exlibris.dps;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.exlibris.dps package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetMDResponse_QNAME = new QName("http://dps.exlibris.com/", "getMDResponse");
    private final static QName _InvalidXmlException_QNAME = new QName("http://dps.exlibris.com/", "InvalidXmlException");
    private final static QName _UpdateRepresentationResponse_QNAME = new QName("http://dps.exlibris.com/", "updateRepresentationResponse");
    private final static QName _GetIEMDResponse_QNAME = new QName("http://dps.exlibris.com/", "getIEMDResponse");
    private final static QName _GetIEMD_QNAME = new QName("http://dps.exlibris.com/", "getIEMD");
    private final static QName _GenerateFixityEventResponse_QNAME = new QName("http://dps.exlibris.com/", "generateFixityEventResponse");
    private final static QName _UserAuthorizeException_QNAME = new QName("http://dps.exlibris.com/", "UserAuthorizeException");
    private final static QName _UpdateRepresentation_QNAME = new QName("http://dps.exlibris.com/", "updateRepresentation");
    private final static QName _FixityEventException_QNAME = new QName("http://dps.exlibris.com/", "FixityEventException");
    private final static QName _UpdateIEMDResponse_QNAME = new QName("http://dps.exlibris.com/", "updateIEMDResponse");
    private final static QName _UpdateMDResponse_QNAME = new QName("http://dps.exlibris.com/", "updateMDResponse");
    private final static QName _GenerateFixityEvent_QNAME = new QName("http://dps.exlibris.com/", "generateFixityEvent");
    private final static QName _GetRipStatusResponse_QNAME = new QName("http://dps.exlibris.com/", "getRipStatusResponse");
    private final static QName _NotInPermanentException_QNAME = new QName("http://dps.exlibris.com/", "NotInPermanentException");
    private final static QName _LockedIeException_QNAME = new QName("http://dps.exlibris.com/", "LockedIeException");
    private final static QName _GetIEResponse_QNAME = new QName("http://dps.exlibris.com/", "getIEResponse");
    private final static QName _UpdateMD_QNAME = new QName("http://dps.exlibris.com/", "updateMD");
    private final static QName _GetHeartBitResponse_QNAME = new QName("http://dps.exlibris.com/", "getHeartBitResponse");
    private final static QName _InvalidTypeException_QNAME = new QName("http://dps.exlibris.com/", "InvalidTypeException");
    private final static QName _GetHeartBit_QNAME = new QName("http://dps.exlibris.com/", "getHeartBit");
    private final static QName _GetMD_QNAME = new QName("http://dps.exlibris.com/", "getMD");
    private final static QName _GetRipStatus_QNAME = new QName("http://dps.exlibris.com/", "getRipStatus");
    private final static QName _GetIE_QNAME = new QName("http://dps.exlibris.com/", "getIE");
    private final static QName _InvalidMIDException_QNAME = new QName("http://dps.exlibris.com/", "InvalidMIDException");
    private final static QName _IEWSException_QNAME = new QName("http://dps.exlibris.com/", "IEWSException");
    private final static QName _UpdateIEMD_QNAME = new QName("http://dps.exlibris.com/", "updateIEMD");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.exlibris.dps
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FixityEventException }
     * 
     */
    public FixityEventException createFixityEventException() {
        return new FixityEventException();
    }

    /**
     * Create an instance of {@link UpdateRepresentation }
     * 
     */
    public UpdateRepresentation createUpdateRepresentation() {
        return new UpdateRepresentation();
    }

    /**
     * Create an instance of {@link GetIEMD }
     * 
     */
    public GetIEMD createGetIEMD() {
        return new GetIEMD();
    }

    /**
     * Create an instance of {@link GetIEMDResponse }
     * 
     */
    public GetIEMDResponse createGetIEMDResponse() {
        return new GetIEMDResponse();
    }

    /**
     * Create an instance of {@link UserAuthorizeException }
     * 
     */
    public UserAuthorizeException createUserAuthorizeException() {
        return new UserAuthorizeException();
    }

    /**
     * Create an instance of {@link GenerateFixityEventResponse }
     * 
     */
    public GenerateFixityEventResponse createGenerateFixityEventResponse() {
        return new GenerateFixityEventResponse();
    }

    /**
     * Create an instance of {@link UpdateMDResponse }
     * 
     */
    public UpdateMDResponse createUpdateMDResponse() {
        return new UpdateMDResponse();
    }

    /**
     * Create an instance of {@link UpdateIEMDResponse }
     * 
     */
    public UpdateIEMDResponse createUpdateIEMDResponse() {
        return new UpdateIEMDResponse();
    }

    /**
     * Create an instance of {@link GetMDResponse }
     * 
     */
    public GetMDResponse createGetMDResponse() {
        return new GetMDResponse();
    }

    /**
     * Create an instance of {@link UpdateRepresentationResponse }
     * 
     */
    public UpdateRepresentationResponse createUpdateRepresentationResponse() {
        return new UpdateRepresentationResponse();
    }

    /**
     * Create an instance of {@link InvalidXmlException }
     * 
     */
    public InvalidXmlException createInvalidXmlException() {
        return new InvalidXmlException();
    }

    /**
     * Create an instance of {@link GetIE }
     * 
     */
    public GetIE createGetIE() {
        return new GetIE();
    }

    /**
     * Create an instance of {@link GetHeartBit }
     * 
     */
    public GetHeartBit createGetHeartBit() {
        return new GetHeartBit();
    }

    /**
     * Create an instance of {@link GetMD }
     * 
     */
    public GetMD createGetMD() {
        return new GetMD();
    }

    /**
     * Create an instance of {@link GetRipStatus }
     * 
     */
    public GetRipStatus createGetRipStatus() {
        return new GetRipStatus();
    }

    /**
     * Create an instance of {@link IEWSException }
     * 
     */
    public IEWSException createIEWSException() {
        return new IEWSException();
    }

    /**
     * Create an instance of {@link UpdateIEMD }
     * 
     */
    public UpdateIEMD createUpdateIEMD() {
        return new UpdateIEMD();
    }

    /**
     * Create an instance of {@link InvalidMIDException }
     * 
     */
    public InvalidMIDException createInvalidMIDException() {
        return new InvalidMIDException();
    }

    /**
     * Create an instance of {@link LockedIeException }
     * 
     */
    public LockedIeException createLockedIeException() {
        return new LockedIeException();
    }

    /**
     * Create an instance of {@link GetIEResponse }
     * 
     */
    public GetIEResponse createGetIEResponse() {
        return new GetIEResponse();
    }

    /**
     * Create an instance of {@link GenerateFixityEvent }
     * 
     */
    public GenerateFixityEvent createGenerateFixityEvent() {
        return new GenerateFixityEvent();
    }

    /**
     * Create an instance of {@link GetRipStatusResponse }
     * 
     */
    public GetRipStatusResponse createGetRipStatusResponse() {
        return new GetRipStatusResponse();
    }

    /**
     * Create an instance of {@link NotInPermanentException }
     * 
     */
    public NotInPermanentException createNotInPermanentException() {
        return new NotInPermanentException();
    }

    /**
     * Create an instance of {@link GetHeartBitResponse }
     * 
     */
    public GetHeartBitResponse createGetHeartBitResponse() {
        return new GetHeartBitResponse();
    }

    /**
     * Create an instance of {@link InvalidTypeException }
     * 
     */
    public InvalidTypeException createInvalidTypeException() {
        return new InvalidTypeException();
    }

    /**
     * Create an instance of {@link UpdateMD }
     * 
     */
    public UpdateMD createUpdateMD() {
        return new UpdateMD();
    }

    /**
     * Create an instance of {@link Fixity }
     * 
     */
    public Fixity createFixity() {
        return new Fixity();
    }

    /**
     * Create an instance of {@link FixityEvent }
     * 
     */
    public FixityEvent createFixityEvent() {
        return new FixityEvent();
    }

    /**
     * Create an instance of {@link RepresentationContent }
     * 
     */
    public RepresentationContent createRepresentationContent() {
        return new RepresentationContent();
    }

    /**
     * Create an instance of {@link MetaData }
     * 
     */
    public MetaData createMetaData() {
        return new MetaData();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dps.exlibris.com/", name = "getMDResponse")
    public JAXBElement<GetMDResponse> createGetMDResponse(GetMDResponse value) {
        return new JAXBElement<GetMDResponse>(_GetMDResponse_QNAME, GetMDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidXmlException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dps.exlibris.com/", name = "InvalidXmlException")
    public JAXBElement<InvalidXmlException> createInvalidXmlException(InvalidXmlException value) {
        return new JAXBElement<InvalidXmlException>(_InvalidXmlException_QNAME, InvalidXmlException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateRepresentationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dps.exlibris.com/", name = "updateRepresentationResponse")
    public JAXBElement<UpdateRepresentationResponse> createUpdateRepresentationResponse(UpdateRepresentationResponse value) {
        return new JAXBElement<UpdateRepresentationResponse>(_UpdateRepresentationResponse_QNAME, UpdateRepresentationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetIEMDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dps.exlibris.com/", name = "getIEMDResponse")
    public JAXBElement<GetIEMDResponse> createGetIEMDResponse(GetIEMDResponse value) {
        return new JAXBElement<GetIEMDResponse>(_GetIEMDResponse_QNAME, GetIEMDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetIEMD }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dps.exlibris.com/", name = "getIEMD")
    public JAXBElement<GetIEMD> createGetIEMD(GetIEMD value) {
        return new JAXBElement<GetIEMD>(_GetIEMD_QNAME, GetIEMD.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateFixityEventResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dps.exlibris.com/", name = "generateFixityEventResponse")
    public JAXBElement<GenerateFixityEventResponse> createGenerateFixityEventResponse(GenerateFixityEventResponse value) {
        return new JAXBElement<GenerateFixityEventResponse>(_GenerateFixityEventResponse_QNAME, GenerateFixityEventResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserAuthorizeException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dps.exlibris.com/", name = "UserAuthorizeException")
    public JAXBElement<UserAuthorizeException> createUserAuthorizeException(UserAuthorizeException value) {
        return new JAXBElement<UserAuthorizeException>(_UserAuthorizeException_QNAME, UserAuthorizeException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateRepresentation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dps.exlibris.com/", name = "updateRepresentation")
    public JAXBElement<UpdateRepresentation> createUpdateRepresentation(UpdateRepresentation value) {
        return new JAXBElement<UpdateRepresentation>(_UpdateRepresentation_QNAME, UpdateRepresentation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FixityEventException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dps.exlibris.com/", name = "FixityEventException")
    public JAXBElement<FixityEventException> createFixityEventException(FixityEventException value) {
        return new JAXBElement<FixityEventException>(_FixityEventException_QNAME, FixityEventException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateIEMDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dps.exlibris.com/", name = "updateIEMDResponse")
    public JAXBElement<UpdateIEMDResponse> createUpdateIEMDResponse(UpdateIEMDResponse value) {
        return new JAXBElement<UpdateIEMDResponse>(_UpdateIEMDResponse_QNAME, UpdateIEMDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateMDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dps.exlibris.com/", name = "updateMDResponse")
    public JAXBElement<UpdateMDResponse> createUpdateMDResponse(UpdateMDResponse value) {
        return new JAXBElement<UpdateMDResponse>(_UpdateMDResponse_QNAME, UpdateMDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateFixityEvent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dps.exlibris.com/", name = "generateFixityEvent")
    public JAXBElement<GenerateFixityEvent> createGenerateFixityEvent(GenerateFixityEvent value) {
        return new JAXBElement<GenerateFixityEvent>(_GenerateFixityEvent_QNAME, GenerateFixityEvent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRipStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dps.exlibris.com/", name = "getRipStatusResponse")
    public JAXBElement<GetRipStatusResponse> createGetRipStatusResponse(GetRipStatusResponse value) {
        return new JAXBElement<GetRipStatusResponse>(_GetRipStatusResponse_QNAME, GetRipStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotInPermanentException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dps.exlibris.com/", name = "NotInPermanentException")
    public JAXBElement<NotInPermanentException> createNotInPermanentException(NotInPermanentException value) {
        return new JAXBElement<NotInPermanentException>(_NotInPermanentException_QNAME, NotInPermanentException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LockedIeException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dps.exlibris.com/", name = "LockedIeException")
    public JAXBElement<LockedIeException> createLockedIeException(LockedIeException value) {
        return new JAXBElement<LockedIeException>(_LockedIeException_QNAME, LockedIeException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetIEResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dps.exlibris.com/", name = "getIEResponse")
    public JAXBElement<GetIEResponse> createGetIEResponse(GetIEResponse value) {
        return new JAXBElement<GetIEResponse>(_GetIEResponse_QNAME, GetIEResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateMD }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dps.exlibris.com/", name = "updateMD")
    public JAXBElement<UpdateMD> createUpdateMD(UpdateMD value) {
        return new JAXBElement<UpdateMD>(_UpdateMD_QNAME, UpdateMD.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHeartBitResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dps.exlibris.com/", name = "getHeartBitResponse")
    public JAXBElement<GetHeartBitResponse> createGetHeartBitResponse(GetHeartBitResponse value) {
        return new JAXBElement<GetHeartBitResponse>(_GetHeartBitResponse_QNAME, GetHeartBitResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidTypeException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dps.exlibris.com/", name = "InvalidTypeException")
    public JAXBElement<InvalidTypeException> createInvalidTypeException(InvalidTypeException value) {
        return new JAXBElement<InvalidTypeException>(_InvalidTypeException_QNAME, InvalidTypeException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHeartBit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dps.exlibris.com/", name = "getHeartBit")
    public JAXBElement<GetHeartBit> createGetHeartBit(GetHeartBit value) {
        return new JAXBElement<GetHeartBit>(_GetHeartBit_QNAME, GetHeartBit.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMD }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dps.exlibris.com/", name = "getMD")
    public JAXBElement<GetMD> createGetMD(GetMD value) {
        return new JAXBElement<GetMD>(_GetMD_QNAME, GetMD.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRipStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dps.exlibris.com/", name = "getRipStatus")
    public JAXBElement<GetRipStatus> createGetRipStatus(GetRipStatus value) {
        return new JAXBElement<GetRipStatus>(_GetRipStatus_QNAME, GetRipStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetIE }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dps.exlibris.com/", name = "getIE")
    public JAXBElement<GetIE> createGetIE(GetIE value) {
        return new JAXBElement<GetIE>(_GetIE_QNAME, GetIE.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidMIDException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dps.exlibris.com/", name = "InvalidMIDException")
    public JAXBElement<InvalidMIDException> createInvalidMIDException(InvalidMIDException value) {
        return new JAXBElement<InvalidMIDException>(_InvalidMIDException_QNAME, InvalidMIDException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IEWSException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dps.exlibris.com/", name = "IEWSException")
    public JAXBElement<IEWSException> createIEWSException(IEWSException value) {
        return new JAXBElement<IEWSException>(_IEWSException_QNAME, IEWSException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateIEMD }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dps.exlibris.com/", name = "updateIEMD")
    public JAXBElement<UpdateIEMD> createUpdateIEMD(UpdateIEMD value) {
        return new JAXBElement<UpdateIEMD>(_UpdateIEMD_QNAME, UpdateIEMD.class, null, value);
    }

}
