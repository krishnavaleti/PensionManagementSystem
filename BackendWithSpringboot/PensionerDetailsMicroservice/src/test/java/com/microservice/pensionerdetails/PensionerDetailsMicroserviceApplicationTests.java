package com.microservice.pensionerdetails;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.microservice.pensionerdetails.controllertest.PensionerControllerTest;
import com.microservice.pensionerdetails.servicetest.PensionerDetailsServiceTest;


@RunWith(Suite.class)
@SuiteClasses({ PensionerDetailsServiceTest.class , PensionerControllerTest.class})

class PensionerDetailsMicroserviceApplicationTests {

}
