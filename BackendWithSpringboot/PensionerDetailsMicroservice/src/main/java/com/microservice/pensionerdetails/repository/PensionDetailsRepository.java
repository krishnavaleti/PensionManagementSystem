package com.microservice.pensionerdetails.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.microservice.pensionerdetails.entity.*;

public interface PensionDetailsRepository extends JpaRepository<PensionerDetail,Long>{

	PensionerDetail findByAadhaarNumber(Long aadhaarNumber);

}
