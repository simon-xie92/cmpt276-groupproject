package com.cmpt276.groupproject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import com.cmpt276.groupproject.models.User;

@SpringBootTest
class GroupprojectApplicationTests {

	@Test
	void testUser() {
		User user = new User("testing", "testing", 123, 456, 78, 910);
		String name = user.getName();
		assertEquals("testing",name);
		double balance = user.getBalance();
		assertEquals(123, balance);
		double monthlyincome = user.getMonthlyincome();
		assertEquals(456, monthlyincome);
		double monthlyexpenses = user.getMonthlyexpenses();
		assertEquals(78, monthlyexpenses);
		double monthlysavings = user.getMonthlysavings();
		assertEquals(910, monthlysavings);
	}



	

}
