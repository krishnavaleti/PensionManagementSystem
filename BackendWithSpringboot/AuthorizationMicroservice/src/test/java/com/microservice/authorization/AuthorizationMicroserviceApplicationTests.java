package com.microservice.authorization;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.microservice.authorization.entitytest.AuthorizationEntityTest;
import com.microservice.authorization.servicetest.AuthorizationServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ AuthorizationEntityTest.class ,AuthorizationServiceTest.class})
class AuthorizationMicroserviceApplicationTests {

}
