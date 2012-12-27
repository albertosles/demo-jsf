package com.demo.ws.endpoints;

import javax.jws.WebService;

import com.demo.ws.dto.ApplicationDocument;

@WebService
public interface ScoringWebService {
	double calculateScoring(Long extSysId, Long appSpecId, Long mapScheId, ApplicationDocument appDoc);
}
