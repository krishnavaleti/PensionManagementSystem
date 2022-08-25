package com.microservice.processpension.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TransactionDetail")

public class PensionTransactionDetail {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int transactionId;       
	private String aadhaarNumber;
	//TransactionAmount = PensionAmount - BankCharge
	private double transactionAmount;      
	private String accountNumber;  
	
	public PensionTransactionDetail(String aadharNumber, double transactionAmount, String accountNumber) {
		this.aadhaarNumber = aadharNumber;
		this.transactionAmount = transactionAmount;
		this.accountNumber = accountNumber;
	}



}	
