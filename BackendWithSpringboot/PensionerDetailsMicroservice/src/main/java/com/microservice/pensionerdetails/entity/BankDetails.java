package com.microservice.pensionerdetails.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor

public class BankDetails {
	
	@Column(name = "BankName")
	 private String bankName;
	@Column(name = "AccountNumber")
     private String accountNumber;
	@Column(name = "BankType")
     private String typeOfBank;

}
