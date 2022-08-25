package com.microservice.authorization.entitytest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.microservice.authorization.entity.UserData;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthorizationEntityTest {

	@Test
	public void testPensionerBean() {
		final BeanTester beanTester = new BeanTester();
		beanTester.getFactoryCollection();
		beanTester.testBean(UserData.class);
	}

}
