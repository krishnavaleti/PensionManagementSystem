package com.microservice.processpension.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankDetails {
	private String bankName;
    private String accountNumber;
    private String typeOfBank;


}
