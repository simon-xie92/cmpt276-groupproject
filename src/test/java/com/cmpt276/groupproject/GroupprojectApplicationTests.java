package com.cmpt276.groupproject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import com.cmpt276.groupproject.models.User;

@SpringBootTest
class GroupprojectApplicationTests {

	@Test
	void testSignup() {
		User user = new User("testing", "testing", 123, 456, 78, 910);
		String name = user.getName();
		assertEquals("testing",name);
	}

}
