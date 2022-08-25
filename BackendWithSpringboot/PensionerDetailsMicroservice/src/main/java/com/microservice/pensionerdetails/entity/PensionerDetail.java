package com.microservice.pensionerdetails.entity;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Access(value=AccessType.FIELD)
@Table(name = "pensiondetails")

public class PensionerDetail {

	@Id
	@Column(name = "AadhaarNumber")
	private Long aadhaarNumber;
	@Column(name = "Name")
	private String name;
	@Column(name = "DOB")
	private String dateOfBirth;
	@Column(name = "PAN")
	private String pan;
	@Column(name = "SalaryEarned")
	private long salaryEarned;
	@Column(name = "Allowances")
	private long allowances;
	@Column(name = "PensionType")
	private String typeOfPension;
	@Embedded
	private BankDetails bankDetails;

}
