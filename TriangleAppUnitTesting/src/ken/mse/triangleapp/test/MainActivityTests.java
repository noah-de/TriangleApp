package ken.mse.triangleapp.test;

import ken.mse.triangleapp.MainActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivityTests extends ActivityInstrumentationTestCase2<MainActivity>{
	private TextView mText;
	private EditText mInput;
	
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
		mText = (TextView) getActivity().findViewById(ken.mse.triangleapp.R.id.initialText);
		mInput = (EditText) getActivity().findViewById(ken.mse.triangleapp.R.id.input1);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void test_canGetText(){
		//User sees the screen and looks at the initial message displayed
		assertNotNull("Could not get the object that displays 'Hello world!'", mText);
		assertEquals("Enter Lengths:", mText.getText());
	}

	public void test_canInputValues(){
		//Find an input box
		assertNotNull("Could not get the input text object'", mInput);
		
		TouchUtils.tapView(this, mInput);
		sendKeys("1");

		// submit the entered values
		assertEquals("Input box did not get the expected value", "1", mInput.getText().toString());

		

	}
	
//	public void test_inputExactlyThree(){
//		//Input 3 values is accepted
//		assertNotNull("Could not get the object that displays 'Hello world!'", mInitialText);
//		assertEquals("Hello world!", mInitialText.getText());
//	}
//	
//	public void test_inputLessThanThree(){
//		//less than 3 causes error
//		assertNotNull("Could not get the object that displays 'Hello world!'", mInitialText);
//		assertEquals("Hello world!", mInitialText.getText());
//	}
//	
//	public void test_inputMoreThanThree(){
//		//more than 3 values causes error
//		assertNotNull("Could not get the object that displays 'Hello world!'", mInitialText);
//		assertEquals("Hello world!", mInitialText.getText());
//	}
}
