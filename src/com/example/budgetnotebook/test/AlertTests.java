package com.example.budgetnotebook.test;

import com.example.budgetnotebook.DBHelper;
import com.example.budgetnotebook.Alert;

import android.test.AndroidTestCase;

public class AlertTests extends AndroidTestCase {

	private DBHelper dbConn;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		dbConn = new DBHelper(this.getContext()); // Set up db connection with a test context.
	}
	
	private Alert getTestAlert(int id){
		Alert alert = new Alert();
		alert.setAId(id);
		alert.setDescription("Test description");
		alert.setDueDate("01/02/2003");
		alert.setId(id);
		alert.setName("Test name");
		
		return alert;
	}
	
	public void testCreateAlert() throws Throwable{
		Alert alert = this.getTestAlert(1);
		dbConn.addAlert(alert);
		
		alert = dbConn.getAlert(alert.getId());
		
		assertNotNull("Failed to retrieve alert after inserting it.", alert);		
	}
	
	public void testUpdateAlert() throws Throwable{
		// Insert an alert
		Alert alert = this.getTestAlert(2);
		dbConn.addAlert(alert);
		
		// Get the alert
		alert = dbConn.getAlert(2);
		
		// Update the alert	
		String description = "new description";
		String dueDate = "02/03/2004";
		String name = "new name";

		alert.setDescription(description);
		alert.setDueDate(dueDate);
		alert.setName(name);

		dbConn.updateAlert(alert);
		
		// Get the updated alert
		alert = dbConn.getAlert(2);
		
		// Validate the updated values
		assertEquals("Description not updated.", alert.getDescription(), description);
		assertEquals("Due Date not updated.", alert.getDueDate(), dueDate);
		assertEquals("Name not updated.", alert.getName(), name);
	}	
	
	public void testDeleteAlert() throws Throwable{
		// Get the test Alert
		Alert alert = this.getTestAlert(3);
		
		// Insert the test alert
		dbConn.addAlert(alert);
		
		// Delete the alert
		dbConn.deleteAlert(alert);
		
		// Make sure the alert no longer exists		
		Exception e = null;
		try{
			alert = dbConn.getAlert(3);
		}
		catch(Exception exception){
			e = exception;
		}
		assertNotNull("Alert was not deleted.", e);  // If no exception happened, then the get succeeded and this was not deleted.
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
}