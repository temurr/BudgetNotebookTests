package com.example.budgetnotebook.test;

import com.example.budgetnotebook.DBHelper;
import com.example.budgetnotebook.Account;

import android.test.AndroidTestCase;

public class B_AccountTests extends AndroidTestCase {

	private DBHelper dbConn;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		dbConn = new DBHelper(this.getContext()); // Set up db connection with a test context.
	}
	
	private Account getTestAccount(int id){
		Account account = new Account();
		account.setId(id);
		account.setName("TestName");
		account.setNumber("123");
		account.setType("TestType");
		account.setBalance("100");

		return account;		
	}
	
	public void testAccountExists() throws Throwable {
		Account account = this.getTestAccount(1);
		boolean check;
		check = dbConn.checkAccountExists();
		
		assertFalse("Accounts already exist!", check);
		
		dbConn.addAccount(account);
		check = dbConn.checkAccountExists();
		assertTrue("Account does not exist!", check);		
		
	}
	
	public void testCreateAccount() throws Throwable{
		Account account = this.getTestAccount(2);
		dbConn.addAccount(account);
		
		account = dbConn.getAccount(account.getId());
		
		assertNotNull("Failed to retrieve account after inserting it.", account);		
	}
	
	public void testUpdateAccount() throws Throwable{
		// Insert an account
		Account account = this.getTestAccount(3);
		dbConn.addAccount(account);
		
		// Get the account
		account = dbConn.getAccount(3);
		
		// Update the account		
		String balance = "200";
		String name = "updatename";
		String number = "555";
		String type = "updateType";

		account.setBalance(balance);
		account.setName(name);
		account.setNumber(number);
		account.setType(type);

		dbConn.updateAccount(account);
		
		// Get the updated account
		account = dbConn.getAccount(3);
		
		// Validate the updated values
		assertEquals("Balance not updated.", account.getBalance(), balance);
		assertEquals("Name not updated.", account.getName(), name);
		assertEquals("Number not updated.", account.getNumber(), number);
		assertEquals("Type not updated.", account.getType(), type);
	}	
	
	public void testDeleteAccount() throws Throwable{
		// Get the test account
		Account account = this.getTestAccount(4);
		
		// Insert the test account
		dbConn.addAccount(account);
		
		// Delete the account
		dbConn.deleteAccount(account);
		
		// Make sure the account no longer exists		
		Exception e = null;
		try{
			account = dbConn.getAccount(4);
		}
		catch(Exception exception){
			e = exception;
		}
		assertNotNull("Account was not deleted.", e);  // If no exception happened, then the get succeeded and this was not deleted.
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
}