package com.example.budgetnotebook.test;

import com.example.budgetnotebook.DBHelper;
import com.example.budgetnotebook.Transaction;

import android.test.AndroidTestCase;

public class TransactionTests extends AndroidTestCase {

	private DBHelper dbConn;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		dbConn = new DBHelper(this.getContext()); // Set up db connection with a test context.
	}
	
	private Transaction getTestTransaction(int id){
		Transaction transaction = new Transaction();
		transaction.setAId(id);;
		transaction.setAmount("100");
		transaction.setCategory("Test Category");
		transaction.setDate("01/02/2003");
		transaction.setDescription("Test description");
		transaction.setId(id);
		transaction.setInterval("1000");
		transaction.setName("Test Name");
		transaction.setType("Test type");
		return transaction;
	}
	
	public void testCreateTransaction() throws Throwable{
		Transaction transaction = this.getTestTransaction(1);
		dbConn.addTransaction(transaction);
		
		transaction = dbConn.getTransaction(transaction.getId());
		
		assertNotNull("Failed to retrieve transaction after inserting it.", transaction);		
	}
	
	public void testUpdateTransaction() throws Throwable{
		// Insert a transaction
		Transaction transaction = this.getTestTransaction(2);
		dbConn.addTransaction(transaction);
		
		// Get the transaction
		transaction = dbConn.getTransaction(2);
		
		// Update the transaction
		String amount = "200";
		String category = "new category";
		String date = "03/04/2005";
		String description = "new description";
		String interval = "555";
		String name = "new name";
		String type = "new type";
		
		transaction.setAmount(amount);
		transaction.setCategory(category);
		transaction.setDate(date);
		transaction.setDescription(description);
		transaction.setInterval(interval);
		transaction.setName(name);
		transaction.setType(type);
		
		dbConn.updateTransaction(transaction);
		
		// Get the updated transaction
		transaction = dbConn.getTransaction(2);
		
		// Validate the updated values
		assertEquals("Amount not updated.", transaction.getAmount(), amount);
		assertEquals("Category not updated.", transaction.getCategory(), category);
		assertEquals("Date not updated.", transaction.getDate(), date);
		assertEquals("Description not updated.", transaction.getDescription(), description);
		assertEquals("Interval not updated.", transaction.getInterval(), interval);
		assertEquals("Name not updated.", transaction.getName(), name);
		assertEquals("Type not updated.", transaction.getType(), type);
	}	
	
	public void testDeleteTransaction() throws Throwable{
		// Get the test transaction
		Transaction transaction = this.getTestTransaction(3);
		
		// Insert the test transaction
		dbConn.addTransaction(transaction);
		
		// Delete the transaction
		dbConn.deleteTransaction(transaction);
		
		// Make sure the transaction no longer exists		
		Exception e = null;
		try{
			transaction = dbConn.getTransaction(3);
		}
		catch(Exception exception){
			e = exception;
		}
		assertNotNull("Transaction was not deleted.", e);  // If no exception happened, then the get succeeded and this was not deleted.
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
}