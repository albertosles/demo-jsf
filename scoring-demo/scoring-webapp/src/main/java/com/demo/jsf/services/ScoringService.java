package com.demo.jsf.services;

import com.demo.ws.dto.ApplicationDocument;


public interface ScoringService {
	final String SERVICE_ID = "scoringService";
	double calculateScoring(Long extSysId, Long appSpecId, Long mapScheId, ApplicationDocument appDoc);
}
