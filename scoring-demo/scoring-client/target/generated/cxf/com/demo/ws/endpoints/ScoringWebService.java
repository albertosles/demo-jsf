package com.demo.ws.endpoints;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.2.6
 * Fri Jan 04 14:54:22 ICT 2013
 * Generated source version: 2.2.6
 * 
 */
 
@WebService(targetNamespace = "http://endpoints.ws.demo.com/", name = "ScoringWebService")
@XmlSeeAlso({ObjectFactory.class})
public interface ScoringWebService {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "calculateScoring", targetNamespace = "http://endpoints.ws.demo.com/", className = "com.demo.ws.endpoints.CalculateScoring")
    @ResponseWrapper(localName = "calculateScoringResponse", targetNamespace = "http://endpoints.ws.demo.com/", className = "com.demo.ws.endpoints.CalculateScoringResponse")
    @WebMethod
    public double calculateScoring(
        @WebParam(name = "extSysId", targetNamespace = "")
        java.lang.Long extSysId,
        @WebParam(name = "appSpecId", targetNamespace = "")
        java.lang.Long appSpecId,
        @WebParam(name = "mapScheId", targetNamespace = "")
        java.lang.Long mapScheId,
        @WebParam(name = "appDoc", targetNamespace = "")
        com.demo.ws.endpoints.ApplicationDocument appDoc
    );
}
