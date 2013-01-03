
package com.demo.ws.endpoints;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.demo.ws.endpoints package. 
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

    private final static QName _CalculateScoringResponse_QNAME = new QName("http://endpoints.ws.demo.com/", "calculateScoringResponse");
    private final static QName _ApplicationDoc_QNAME = new QName("http://endpoints.ws.demo.com/", "applicationDoc");
    private final static QName _CalculateScoring_QNAME = new QName("http://endpoints.ws.demo.com/", "calculateScoring");
    private final static QName _AppField_QNAME = new QName("http://endpoints.ws.demo.com/", "appField");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.demo.ws.endpoints
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CalculateScoringResponse }
     * 
     */
    public CalculateScoringResponse createCalculateScoringResponse() {
        return new CalculateScoringResponse();
    }

    /**
     * Create an instance of {@link AppField }
     * 
     */
    public AppField createAppField() {
        return new AppField();
    }

    /**
     * Create an instance of {@link CalculateScoring }
     * 
     */
    public CalculateScoring createCalculateScoring() {
        return new CalculateScoring();
    }

    /**
     * Create an instance of {@link ApplicationDocument }
     * 
     */
    public ApplicationDocument createApplicationDocument() {
        return new ApplicationDocument();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateScoringResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoints.ws.demo.com/", name = "calculateScoringResponse")
    public JAXBElement<CalculateScoringResponse> createCalculateScoringResponse(CalculateScoringResponse value) {
        return new JAXBElement<CalculateScoringResponse>(_CalculateScoringResponse_QNAME, CalculateScoringResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApplicationDocument }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoints.ws.demo.com/", name = "applicationDoc")
    public JAXBElement<ApplicationDocument> createApplicationDoc(ApplicationDocument value) {
        return new JAXBElement<ApplicationDocument>(_ApplicationDoc_QNAME, ApplicationDocument.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateScoring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoints.ws.demo.com/", name = "calculateScoring")
    public JAXBElement<CalculateScoring> createCalculateScoring(CalculateScoring value) {
        return new JAXBElement<CalculateScoring>(_CalculateScoring_QNAME, CalculateScoring.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AppField }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoints.ws.demo.com/", name = "appField")
    public JAXBElement<AppField> createAppField(AppField value) {
        return new JAXBElement<AppField>(_AppField_QNAME, AppField.class, null, value);
    }

}
