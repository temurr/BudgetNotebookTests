package com.example.budgetnotebook.test;

import com.example.budgetnotebook.DBHelper;
import com.example.budgetnotebook.Profile;

import android.test.AndroidTestCase;

public class A_ProfileTests extends AndroidTestCase {

	private DBHelper dbConn;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		dbConn = new DBHelper(this.getContext()); // Set up db connection with a test context.
	}
	
	private Profile getTestProfile(int id){
		Profile profile = new Profile();
		profile.setBirthday("01/02/2003");
		profile.setCity("Anytown");
		profile.setEmail("test@test.com");
		profile.setFirstName("Tester");
		profile.setGender("M");
		profile.setId(id);
		profile.setLastName("Testerson");

		return profile;		
	}
	
	public void testA_CreateProfile() throws Throwable{
		Profile profile = this.getTestProfile(1);
		dbConn.addProfile(profile);
		
		profile = dbConn.getProfile(profile.getId());
		
		assertNotNull("Failed to retrieve profile after inserting it.", profile);		
	}
	
	public void testB_UpdateProfile() throws Throwable{
		// Insert a profile
		Profile profile = this.getTestProfile(2);
		dbConn.addProfile(profile);;
		
		// Get the profile
		profile = dbConn.getProfile(2);
		
		// Update the goal
		String birthday = "02/03/2004";
		String city = "updatesville";
		String email = "update@update.com";
		String firstName = "updateName";
		String gender = "F";
		String lastName = "updateLastName";
		
		profile.setBirthday(birthday);
		profile.setCity(city);
		profile.setEmail(email);
		profile.setFirstName(firstName);
		profile.setGender(gender);
		profile.setLastName(lastName);		

		dbConn.updateProfile(profile);
		
		// Get the updated profile
		profile = dbConn.getProfile(2);
		
		// Validate the updated values
		assertEquals("Birthday not updated.", profile.getBirthday(), birthday);
		assertEquals("City not updated.", profile.getCity(), city);
		assertEquals("Email not updated.", profile.getEmail(), email);
		assertEquals("FirstName not updated.", profile.getFirstName(), firstName);
		assertEquals("Gender not updated.", profile.getGender(), gender);
		assertEquals("LastName not updated.", profile.getLastName(), lastName);		
	}	
	
	/*
	public void testDeleteProfile() throws Throwable{
		// Get the test profile
		Profile profile = this.getTestProfile(3);
		
		// Insert the test profile
		dbConn.addProfile(profile);
		
		// Delete the profile
		//dbConn.deleteProfile(profile); < -- Removed this test because this functionality is not needed.
		
		// Make sure the profile no longer exists		
		Exception e = null;
		try{
			profile = dbConn.getProfile(3);
		}
		catch(Exception exception){
			e = exception;
		}
		assertNotNull("Profile was not deleted.", e);  // If no exception happened, then the get succeeded and this was not deleted.
	}
	*/
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
}
