package com.example.budgetnotebook.test;

import com.example.budgetnotebook.DBHelper;
import com.example.budgetnotebook.Recommendation;

import android.test.AndroidTestCase;

public class RecommendationTests extends AndroidTestCase {

	private DBHelper dbConn;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		dbConn = new DBHelper(this.getContext()); // Set up db connection with a test context.
		
	}
	
	private Recommendation getTestRecommendation(int id){
		Recommendation recommend = new Recommendation();
		recommend.setId(id);
		recommend.setCategory1("first criteria");
		recommend.setCategory2("second criteria");
		recommend.setCategory3("third criteria");
		recommend.setCategory4("fourth criteria");
		recommend.setCategory5("fifth criteria");
		recommend.setIsValid(true);

		return recommend;		
	}
	
	public void testCreateRecommendation() throws Throwable{
		Recommendation recommend = this.getTestRecommendation(1);
		dbConn.addRecommendation(recommend);
		
		recommend = dbConn.getRecommendation(recommend.getId());
		
		assertNotNull("Failed to retrieve recommendation after inserting it.", recommend);		
	}
	
	public void testUpdateRecommendation() throws Throwable{
		// Insert an account
		Recommendation recommend = this.getTestRecommendation(2);
		//Add Recommendations to the database table.
		dbConn.addRecommendation(recommend);
		
		// Get the recommendation
		recommend = dbConn.getRecommendation(2);
		
		// Update the recommendation		
		String criteria1 = "new first";
		String criteria2 = "new second";
		String criteria3 = "new third";
		String criteria4 = "new fourth";
		String criteria5 = "new fifth";
		boolean validity = false;

		recommend.setCategory1(criteria1);
		recommend.setCategory2(criteria2);
		recommend.setCategory3(criteria3);
		recommend.setCategory4(criteria4);
		recommend.setCategory5(criteria5);
		recommend.setIsValid(validity);
		
		dbConn.updateRecommendation(recommend);
		
		// Get the updated recommendation
		recommend = dbConn.getRecommendation(2);
		
		// Validate the updated values
		assertEquals("Criteria1 not updated.", recommend.getCategory1(), criteria1);
		assertEquals("Criteria2 not updated.", recommend.getCategory2(), criteria2);
		assertEquals("Criteria3 not updated.", recommend.getCategory3(), criteria3);
		assertEquals("Criteria4 not updated.", recommend.getCategory4(), criteria4);
		assertEquals("Criteria5 not updated.", recommend.getCategory5(), criteria5);
		assertEquals("Validity not updated.", recommend.getIsValid(), validity);
	}	

	protected void tearDown() throws Exception {
		super.tearDown();
	}
}