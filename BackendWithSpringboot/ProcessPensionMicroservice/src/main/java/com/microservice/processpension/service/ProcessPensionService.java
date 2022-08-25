package com.microservice.processpension.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.processpension.client.AuthClient;
import com.microservice.processpension.client.PensionDetailClient;
import com.microservice.processpension.entity.PensionDetails;
import com.microservice.processpension.entity.PensionTransactionDetail;
import com.microservice.processpension.entity.PensionerDetails;
import com.microservice.processpension.entity.ProcessPensionInput;
import com.microservice.processpension.exception.ResourceNotFoundException;
import com.microservice.processpension.repository.PensionTransactionDetailsRepository;
import com.microservice.processpension.repository.ProcessPensionRepository;

@Service
public class ProcessPensionService {

	@Autowired
	private PensionDetailClient pensionDetailClient;

	@Autowired
	private AuthClient authclient;

	@Autowired
	public PensionTransactionDetailsRepository transactiondetailsrepo;

	@Autowired
	public ProcessPensionRepository processpensionrepository;

	public PensionDetails getPensionerDetail(String token, ProcessPensionInput processPensionInput) {
		PensionerDetails pensionerDetailobject = pensionDetailClient.findByAadhaarNumber(token,
				processPensionInput.getAadhaarNumber());
		PensionDetails pensionDetailObject = new PensionDetails();
		try {
			pensionDetailObject = calculatePensionAmount(pensionerDetailobject.getBankDetails().getTypeOfBank(),
					pensionerDetailobject.getSalaryEarned(), pensionerDetailobject.getAllowances(),
					pensionerDetailobject.getTypeOfPension(), pensionerDetailobject.getBankDetails().getAccountNumber(),
					processPensionInput.getAadhaarNumber());
		} catch (Exception e) {

			throw new ResourceNotFoundException("Invalid Pensioner Input");
		}
		return pensionDetailObject;
	}

	public PensionDetails calculatePensionAmount(String typeOfBank, double salaryEarned, double allowances,
			String typeOfPension, String accountNumber, String aadharNo) {
		int serviceCharge = 0;
		double pensionAmount = 0;
		if (typeOfBank.equals("Public"))
			serviceCharge = 500;
		else if (typeOfBank.equals("Private"))
			serviceCharge = 550;
		if (typeOfPension.equals("Self"))
			pensionAmount = (0.8 * salaryEarned) + allowances;
		else if (typeOfPension.equals("Family"))
			pensionAmount = (0.5 * salaryEarned) + allowances;
		PensionDetails pensionDetailObject = new PensionDetails();
		pensionDetailObject.setBankServiceCharge(serviceCharge);
		pensionDetailObject.setPensionAmount(pensionAmount);
		pensionDetailObject.setAccountNumber(accountNumber);
		pensionDetailObject.setAadhaarNumber(aadharNo);
		processpensionrepository.save(pensionDetailObject);

		return pensionDetailObject;
	}

	public PensionTransactionDetail savePensionTransactionDetail(String token, PensionDetails pensionDetails)
			throws Exception {
		if (authclient.authorization(token)) {
			double transactionAmount = Double.valueOf(pensionDetails.getPensionAmount())
					- Double.valueOf(pensionDetails.getBankServiceCharge());
			System.out.println(transactionAmount);
			PensionTransactionDetail pensionTransactionDetail = new PensionTransactionDetail(
					pensionDetails.getAadhaarNumber(), transactionAmount, pensionDetails.getAccountNumber());
			transactiondetailsrepo.save(pensionTransactionDetail);
			System.out.println(pensionTransactionDetail);
			return pensionTransactionDetail;
		} else {
			throw new Exception("Invalid Token");
		}
	}
}


