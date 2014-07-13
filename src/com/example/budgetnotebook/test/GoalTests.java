package com.example.budgetnotebook.test;

import com.example.budgetnotebook.DBHelper;
import com.example.budgetnotebook.Goal;

import android.test.AndroidTestCase;

public class GoalTests extends AndroidTestCase {

	private DBHelper dbConn;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		dbConn = new DBHelper(this.getContext()); // Set up db connection with a test context.
	}
	
	private Goal getTestGoal(int id){
		Goal goal = new Goal();
		goal.setAId(id);
		goal.setDeltaAmount("100");
		goal.setDescription("This is a test goal");
		goal.setEndDate("01/02/2003");
		goal.setId(id);
		goal.setName("TestGoal");
		goal.setStartAmount("0");
		goal.setType("TestType");
			
		return goal;
	}
	
	public void testCreateGoal() throws Throwable{
		Goal goal = this.getTestGoal(1);
		dbConn.addGoal(goal);
		
		Goal dbGoal = dbConn.getGoal(goal.getId());
		
		assertNotNull("Failed to retrieve goal after inserting it.", dbGoal);		
	}
	
	public void testUpdateGoal() throws Throwable{
		// Insert a goal
		Goal goal = this.getTestGoal(2);
		dbConn.addGoal(goal);;
		
		// Get the goal
		goal = dbConn.getGoal(2);
		
		// Update the goal
		String deltaAmount = "1";
		String description = "update";
		String endDate = "02/03/2004";
		String name = "updateName";
		String startAmount = "1";
		String type = "updateType";
		
		goal.setDeltaAmount(deltaAmount);
		goal.setDescription(description);
		goal.setEndDate(endDate);
		goal.setName(name);
		goal.setStartAmount(startAmount);
		goal.setType(type);
		
		dbConn.updateGoal(goal);
		
		// Get the updated goal
		goal = dbConn.getGoal(2);
		
		// Validate the updated values
		assertEquals("Delta amount not updated.", goal.getDeltaAmount(), deltaAmount);
		assertEquals("Description not updated.", goal.getDescription(), description);
		assertEquals("End Date not updated.", goal.getEndDate(), endDate);
		assertEquals("Name not updated.", goal.getName(), name);
		assertEquals("Start Amount not updated.", goal.getStartAmount(), startAmount);
		assertEquals("Type not updated.", goal.getType(), type);		
	}	
	
	public void testDeleteGoal() throws Throwable{
		// Get the test goal
		Goal goal = this.getTestGoal(3);
		
		// Insert the test goal
		dbConn.addGoal(goal);
		
		// Delete the goal
		dbConn.deleteGoal(goal);
		
		// Make sure the goal no longer exists		
		Exception e = null;
		try{
			goal = dbConn.getGoal(3);
		}
		catch(Exception exception){
			e = exception;
		}
		assertNotNull("Goal was not deleted.", e);  // If no exception happened, then the get succeeded and this was not deleted.
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
}
