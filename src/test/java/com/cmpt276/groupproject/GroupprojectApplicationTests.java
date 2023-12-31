package com.cmpt276.groupproject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import com.cmpt276.groupproject.models.Transaction;
import com.cmpt276.groupproject.models.User;
import com.cmpt276.groupproject.models.Expense;

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

	@Test
	void testTransaction(){
		Transaction transaction = new Transaction(12,null, "hi", 34, "hello");
		int uid = transaction.getUid();
		assertEquals(12,uid);
		String reason = transaction.getReason();
		assertEquals("hi", reason);
		double amount = transaction.getAmount();
		assertEquals(34,amount);
		String type = transaction.getType();
		assertEquals("hello",type);
	}
	
	@Test
	void testExpense(){
		Expense expense = new Expense(10,"hello",12);
		int uid = expense.getUid();
		assertEquals(10,uid);
		String reason = expense.getReason();
		assertEquals("hello",reason);
		double amount = expense.getAmount();
		assertEquals(12,amount);
	}

}
