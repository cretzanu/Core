package com.business.apps.core.data.document.enums;

import java.util.Locale;

/**
 * @author Liviu Gabriel Cretu
 * @version 1.0
 * @created 04-Oct-2012 11:09:04
 */
public enum WorkflowStepType {
	DRAFT, // Save - ENC
	DRF, // Save - ENC
	ANN, // Cancel - ENC
	VAL, // Validate - ENC
	RVA, // Refuse - HOU 
	AVA, // Approve - HOU
	ROP, // Refuse - OPM
	AOP, // Approve - OPM
	CRS, // At least one accepted line is referred on a requisition line that is not closed - System
	CLO, // All lines are closed - OPM or system
	FO_ACCEPTED, 
	CER,
	CEO,
	CAN;

	public static boolean isForProposal(WorkflowStepType val) {

		if (val.equals(DRAFT) 
				|| val.equals(DRF) 
				|| val.equals(VAL)
				|| val.equals(AVA) 
				|| val.equals(AOP) 
				|| val.equals(CRS)
				|| val.equals(CLO) 
				|| val.equals(RVA) 
				|| val.equals(ROP)
				|| val.equals(CAN))
			return true;
		else
			return false;
	}

	public static boolean isForRequisition(WorkflowStepType val) {

		if (val.equals(DRAFT) 
				|| val.equals(DRF) 
				|| val.equals(VAL)
				|| val.equals(CER) 
				|| val.equals(FO_ACCEPTED) 
				|| val.equals(CEO)
				|| val.equals(CLO) 
				|| val.equals(CAN))
			return true;
		else
			return false;
	}

}