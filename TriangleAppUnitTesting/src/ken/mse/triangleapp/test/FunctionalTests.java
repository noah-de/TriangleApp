/******************************************************************************************
 * Product: TriangleApp
 * 
 * Source File: FunctionalTest.java
 * 
 * Description: Implements functional test for TDD in TriangleApp.
 * 
 * Developers: Noah Spahn, Keir Trotter, Eric Yan
 * 
 * Release History:
 * 10/03/2014  Sprint 3 Release, V3
 * 09/26/2014  Sprint 2 Release, V2
 * 09/19/2014  Sprint 1 Release, V1
 * 
*******************************************************************************************/


package ken.mse.triangleapp.test;

import ken.mse.triangleapp.MainActivity;
import android.test.ActivityInstrumentationTestCase2;

public class FunctionalTests extends ActivityInstrumentationTestCase2<MainActivity>{
	private MainActivity mMainActivity;
	
	public FunctionalTests(){
		super(MainActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		//Reference loaded Activity
		mMainActivity = getActivity();		
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void test_canLoadActivity(){
		//User launches TriangleApp and sees the main screen
		assertNotNull("Cannot get MainActivity", mMainActivity);
		assertSame("Loaded Activity class is not MainActivity", MainActivity.class, mMainActivity.getClass());
	}

}
