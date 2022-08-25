package com.microservice.processpension.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PensionerDetails {
	private String name;
	private String dateOfBirth;
	private String panNumber;
	private long salaryEarned;
	private long allowances;
	private String typeOfPension;
	private BankDetails bankDetails;


}
