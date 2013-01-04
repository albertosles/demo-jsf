package com.demo.ws.endpoints;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.demo.ws.dto.ApplicationDocument;

@WebService
public interface ScoringWebService {
	double calculateScoring(@WebParam(name = "extSysId") Long extSysId, 
			@WebParam(name = "appSpecId") Long appSpecId, 
			@WebParam(name = "mapScheId") Long mapScheId, 
			@WebParam(name = "appDoc") ApplicationDocument appDoc);
}
