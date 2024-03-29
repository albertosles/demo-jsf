
/*
 * 
 */

package com.demo.ws.endpoints;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.2.6
 * Thu Jan 03 14:24:23 ICT 2013
 * Generated source version: 2.2.6
 * 
 */


@WebServiceClient(name = "ws/ScoringWebService", 
                  wsdlLocation = "file:/D:/Workspace/demo-client/src/main/resources/wsdl/ScoringWebServiceEndpoint.wsdl",
                  targetNamespace = "http://endpoints.ws.demo.com/") 
public class Ws_002fScoringWebService extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://endpoints.ws.demo.com/", "ws/ScoringWebService");
    public final static QName ScoringWebServiceEndpointPort = new QName("http://endpoints.ws.demo.com/", "ScoringWebServiceEndpointPort");
    static {
        URL url = null;
        try {
            url = new URL("file:/D:/Workspace/demo-client/src/main/resources/wsdl/ScoringWebServiceEndpoint.wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from file:/D:/Workspace/demo-client/src/main/resources/wsdl/ScoringWebServiceEndpoint.wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public Ws_002fScoringWebService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public Ws_002fScoringWebService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Ws_002fScoringWebService() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns ScoringWebService
     */
    @WebEndpoint(name = "ScoringWebServiceEndpointPort")
    public ScoringWebService getScoringWebServiceEndpointPort() {
        return super.getPort(ScoringWebServiceEndpointPort, ScoringWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ScoringWebService
     */
    @WebEndpoint(name = "ScoringWebServiceEndpointPort")
    public ScoringWebService getScoringWebServiceEndpointPort(WebServiceFeature... features) {
        return super.getPort(ScoringWebServiceEndpointPort, ScoringWebService.class, features);
    }

}
