package com.microservice.pensionerdetails.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.pensionerdetails.entity.BankDetails;
import com.microservice.pensionerdetails.entity.PensionerDetail;
import com.microservice.pensionerdetails.exception.ResourceNotFoundException;
import com.microservice.pensionerdetails.repository.PensionDetailsRepository;

@Service
public class PensionDetailsServiceImpl {
	@Autowired
	public PensionDetailsRepository pensionrepository;
	
	String filePath = "PensionerDetail.csv";
	String line = "";
	List<PensionerDetail> allPensionDetailList = new ArrayList<>();
	int count=0;
	

	@PostConstruct
	public void loadPensionDetailstoDatabase() {
		try {
			BufferedReader br=new BufferedReader(new FileReader(filePath));
			System.out.println(br.readLine());
			while((line=br.readLine())!=null) {
				System.out.println(line);
				String[] data=line.split(",");
				if (count != 0) {
					String[] lines = line.split(",");
					String name = lines[0];
					String dob = lines[1];
					String aadhaarNumber = lines[2];
					long aadhaarNo=Long.parseLong(aadhaarNumber);
					String panNumber = lines[3];
					String salaryEarned = lines[4];
					long salaryearned=Long.parseLong(salaryEarned);
					String allowances = lines[5];
					long Allowances=Long.parseLong(allowances);
					String typeOfPension = lines[6];
					String bankName = lines[7];
					String accountNumber = lines[8];
					String typeOfBank = lines[9];
					System.out.println(" " +name + " " + dob + " " + aadhaarNumber + " " +
							panNumber + " " + salaryEarned + " " + allowances );
					allPensionDetailList.add(new PensionerDetail(aadhaarNo, name,
							dob, panNumber, salaryearned,Allowances, typeOfPension,
							new BankDetails(bankName, accountNumber, typeOfBank)));
				}
				count++;

				
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block.
			throw new ResourceNotFoundException("File not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new ResourceNotFoundException("Pensioner detail is not added");
		}
		pensionrepository.saveAll(allPensionDetailList);
		
	}
	
	public  PensionerDetail getdetailsByAadhaarNumber(Long aadhaarNumber) throws Exception {
		PensionerDetail pensionerDetail=null;
		try {
		 pensionerDetail = pensionrepository.findByAadhaarNumber(aadhaarNumber);
		}
		catch(Exception e){
			throw  new ResourceNotFoundException("employee with aadhaar number not found");
		
		}
		return pensionerDetail;
		

	}




}
