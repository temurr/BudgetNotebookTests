package com.example.budgetnotebook.test;

import junit.framework.TestCase;
import android.accounts.Account;
import android.os.SystemClock;
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
			VIEW_ACCOUNTS = (Button) activity.findViewById(com.example.budgetnotebook.R.id.view_account);
			TouchUtils.clickView(this, VIEW_ACCOUNTS);
			SystemClock.sleep(1000);
			super.assertEquals("Activity is of wrong type", MainMenu.class, activity.getClass());
		}

		@Override
		protected void tearDown() throws Exception {
			// TODO Auto-generated method stub
			super.tearDown();
		}
	}
