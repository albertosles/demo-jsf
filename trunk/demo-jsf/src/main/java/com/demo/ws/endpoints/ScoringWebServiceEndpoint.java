package com.demo.ws.endpoints;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.jsf.services.ScoringService;
import com.demo.ws.dto.ApplicationDocument;

@Service("scoringWebServiceEndpoint")
@WebService(serviceName = "ws/ScoringWebService", targetNamespace = "http://endpoints.ws.demo.com/")
public class ScoringWebServiceEndpoint implements ScoringWebService {

	@Autowired
	private ScoringService scoringService;
	
	@WebMethod
	public double calculateScoring(Long extSysId, Long appSpecId, Long mapScheId, ApplicationDocument appDoc) {
		return scoringService.calculateScoring(extSysId, appSpecId, mapScheId, appDoc);
	}
	
}