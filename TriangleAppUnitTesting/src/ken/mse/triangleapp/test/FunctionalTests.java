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
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void test_canLoadActivity(){
		//User launches TriangleApp and sees the main screen
		assertNotNull("Cannot get MainActivity", mMainActivity);
	}

}
