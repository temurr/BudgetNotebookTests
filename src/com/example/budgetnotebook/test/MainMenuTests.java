package com.example.budgetnotebook.test;

import junit.framework.TestCase;
import android.accounts.Account;
import android.content.Intent;
import android.os.SystemClock;
import android.sax.StartElementListener;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.budgetnotebook.MainMenu;
import com.example.budgetnotebook.Transaction;
 

	public class MainMenuTests extends ActivityInstrumentationTestCase2 {
		public MainMenuTests(Class activityClass) {
			super(activityClass);
			// TODO Auto-generated constructor stub
		}

		Button VIEW_ACCOUNTS;
		Button ADD_TRANSACTION;
		Button MY_PROFILE;
		Button VIEW_GOALS;
		Button ADVANCED_MENU;
		Button EXIT;
		MainMenu activity;

		public MainMenuTests(String name) {
			super("com.example", MainMenu.class);
			setName(name);
		}
		
		protected void setUp() throws Exception {
			super.setUp();

			activity = (MainMenu)this.getActivity();
			assertNotNull(activity);
		}
		@SmallTest
		public void testViewAccountsClick() {
			// Test the ACCOUNT activity is started when VIEW ACCOUNTS is clicked
			Intent launchIntent = new Intent(getInstrumentation().getTargetContext(), activity.getClass());
			activity.startActivity(launchIntent);

			VIEW_ACCOUNTS = (Button) activity.findViewById(com.example.budgetnotebook.R.id.view_account);
			TouchUtils.clickView(this, VIEW_ACCOUNTS);
			SystemClock.sleep(1000);
			super.assertEquals("Activity is of wrong type", MainMenu.class, activity.getClass());
		}
		
		@SmallTest
		public void testAddTransactionClick() {
			// Test the ADD TRANSACTION activity is started when ADD TRANSACTION is clicked
			Intent launchIntent = new Intent(getInstrumentation().getTargetContext(), activity.getClass());
			activity.startActivity(launchIntent);

			ADD_TRANSACTION = (Button)activity.findViewById(com.example.budgetnotebook.R.id.add_transaction);
			TouchUtils.clickView(this,  ADD_TRANSACTION);
			SystemClock.sleep(1000);
			super.assertEquals("Activity is of wrong type",  MainMenu.class, activity.getClass());
		}
		
		@SmallTest
		public void testMyProfileClick() {
			Intent launchIntent = new Intent(getInstrumentation().getTargetContext(), activity.getClass());
			activity.startActivity(launchIntent);

			// Test the MY PROFILE activity is started when MY PROFILE is clicked
			MY_PROFILE = (Button)activity.findViewById(com.example.budgetnotebook.R.id.view_profile);
			TouchUtils.clickView(this, MY_PROFILE);
			SystemClock.sleep(1000);
			super.assertEquals("Activity is of wrong type", MainMenu.class, activity.getClass());
		}
		
		@SmallTest
		public void testViewGoalsClick() {
			Intent launchIntent = new Intent(getInstrumentation().getTargetContext(), activity.getClass());
			activity.startActivity(launchIntent);

			// Test the VIEW GOALS activity is started when VIEW GOALS is clicked
			VIEW_GOALS = (Button)activity.findViewById(com.example.budgetnotebook.R.id.view_goals);
			TouchUtils.clickView(this,  VIEW_GOALS);
			SystemClock.sleep(1000);
			super.assertEquals("Activity is of wrong type", MainMenu.class, activity.getClass());
		}
		
		/*
		public void testAdvancedMenuClick(){
			Intent launchIntent = new Intent(getInstrumentation().getTargetContext(), activity.getClass());
			activity.startActivity(launchIntent);

			// Test the ADVANCED MENU activity is started when ADVANCED MENU is clicked
			ADVANCED_MENU = (Button)activity.findViewById(com.example.budgetnotebook.R.id.view_recommendations);
			TouchUtils.clickView(this, ADVANCED_MENU);
			SystemClock.sleep(1000);
			super.assertEquals("Activity is of wrong type", MainMenu.class, activity.getClass());
		}*/
		
		/*public void testExitClick() {
			EXIT = (Button)activity.findViewById(com.example.budgetnotebook.R.id.exit);
			TouchUtils.clickView(this, EXIT);
			SystemClock.sleep(1000);
			// Not sure what to test for here
		}*/

		@Override
		protected void tearDown() throws Exception {
			// TODO Auto-generated method stub
			super.tearDown();
		}
	}
