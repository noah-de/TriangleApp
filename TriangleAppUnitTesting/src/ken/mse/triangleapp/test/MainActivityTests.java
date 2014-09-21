package ken.mse.triangleapp.test;

import ken.mse.triangleapp.MainActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

public class MainActivityTests extends ActivityInstrumentationTestCase2<MainActivity>{
	private TextView mInitialText;
	
	public MainActivityTests(){
		super(MainActivity.class);
	}
	
	/***
	 * Initial Setup before running tests
	 * State: Activity is already loaded with layout
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		//Reference initialText object in MainActivity
		mInitialText = (TextView) getActivity().findViewById(ken.mse.triangleapp.R.id.initialText);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void test_canGetInitialText(){
		//User sees the screen and looks at the initial message displayed
		assertNotNull("Could not get the object that displays 'Hello world!'", mInitialText);
		assertEquals("Hello world!", mInitialText.getText());
	}
}
