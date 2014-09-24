package ken.mse.triangleapp.test;

import ken.mse.triangleapp.MainActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivityTests extends ActivityInstrumentationTestCase2<MainActivity>{
	private TextView mText;
	private EditText mInput;
	private Button mButton1;
	private TextView mOutput;
	

	
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
		mOutput = (TextView) getActivity().findViewById(ken.mse.triangleapp.R.id.output);
		mButton1 = (Button) getActivity().findViewById(ken.mse.triangleapp.R.id.button1);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	@SmallTest
	public void test_views() {
	assertNotNull(getActivity());
	assertNotNull(mText);
	assertNotNull(mInput);
	}
	
	public void test_canGetText(){
		//User sees the screen and looks at the initial message displayed
		assertNotNull("Initial text did not read as expected", mText);
		assertEquals("Input: Values for TriangleApp?:", mText.getText());
	}
	                                                                                                                                                                                                                                                                                                                                                         
	public void test_canInputValues(){                                                                                                                                                                                                                                         

		TouchUtils.tapView(this, mInput);                                                                                                                                
		sendKeys("1");                                                                                                                                                   

		// submit the entered values                                                                                                                                     
		assertEquals("Input box did not get the expected value", "1", mInput.getText().toString());                                                                      

	}

	public void test_inputGeneratesOutput(){
		TouchUtils.tapView(this, mInput);
		
		//assertNotSame("Output message has changed", "expected", mOutput.getText().toString());
		
		
		getInstrumentation().sendStringSync("3");
		TouchUtils.tapView(this, mButton1);
		
		assertTrue("Message is not displayed", mOutput.getText().toString().contains("invalid input"));
		//("Message is not displayed", " [3.0, ] invalid input (need more input)", );	
	}
	
	public void test_inputExactlyThree(){
		//Input 3 values is accepted
		TouchUtils.tapView(this, mInput);
//		String inputStr = "3 5 7";
//		sendKeys(inputStr);
		getInstrumentation().sendStringSync("3 5 7");
		TouchUtils.tapView(this, mButton1);
		assertTrue("Message does not contain expected string", mOutput.getText().toString().contains("3.0, 5.0, 7.0"));
		assertFalse("Alert displays false alert", mOutput.getText().toString().contains("invalid input"));
		// assertEquals("Error string matches expectation", "[3.0, 5.0, 7.0] = ", mOutput.getText().toString());
		// output text: textView1
		// button: button1
		
		
		
	}
	
//	@SmallTest
//	public void testKilosToPounds() {
//	   // clearing the mKilos edit text
//	   mKilos.clearComposingText();
//	   // tapping the mKilos edit text through TouchUtils class
//	   TouchUtils.tapView(this, mKilos);
//	   // sending input to mKilos as 1
//	   sendKeys("1");
//	   // Clikcing on the button
//	   TouchUtils.clickView(this, mCPounds);
//	   double pounds;
//	   try {
//	   // getting the input from the mTextView reference
//	     pounds = Double.parseDouble(mTextView.getText().toString());
//	   } catch (NumberFormatException e) {
//	     pounds = -1;
//	   }
//	   // checking the pounds value with the target value.
//	   assertTrue("1 kilo is 2.20462262 pounds", pounds > 2.2 && pounds < 2.3);
//	}

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
