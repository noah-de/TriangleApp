package ken.mse.triangleapp.test;

import ken.mse.triangleapp.MainActivity;
import ken.mse.triangleapp.StringUtils;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.KeyEvent;
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
	

	public void test_views() {
		assertNotNull(getActivity());
		assertNotNull(mText);
		assertNotNull(mInput);
		assertNotNull(mOutput);
		assertNotNull(mButton1);
		
	}
	

	public void test_canGetText(){
		//User sees the screen and looks at the initial message displayed
		assertNotNull("Initial text did not read as expected", mText);
		assertEquals("Input: Values for TriangleApp?:", mText.getText());
	}
	

	public void test_canInputValues(){
		TouchUtils.tapView(this, mInput);                                                                                                                                
		sendKeys("1");                                                                                                                                    
		assertEquals("Input box did not get the expected value", "1", mInput.getText().toString());                                                                      

	}


	public void test_inputGeneratesOutput(){
		TouchUtils.tapView(this, mInput);
		String first_message = mOutput.getText().toString();
		getInstrumentation().sendStringSync("3");
		
		TouchUtils.tapView(this, mButton1);
		String second_message = mOutput.getText().toString();
		
		assertTrue("Message is not displayed", second_message.contains("invalid input"));
		
		// make sure the message is not the same
		assertNotSame("Output message has changed", first_message, second_message);
	}

	public void test_inputLessThanThree(){
		TouchUtils.tapView(this, mInput);
		String first_message = mOutput.getText().toString();
		assertFalse("No error before entering text", first_message.contains("need more input"));
		getInstrumentation().sendStringSync("3");
		
		TouchUtils.tapView(this, mButton1);
		String second_message = mOutput.getText().toString();
		
		assertTrue("Message is not displayed", second_message.contains("invalid input"));
		
		// make sure the message is not the same
		assertNotSame("Output message has changed", first_message, second_message);
	}
	
	public void test_inputExactlyThree(){
		TouchUtils.tapView(this, mInput);
		getInstrumentation().sendStringSync("3 5 7");
		
		TouchUtils.tapView(this, mButton1);
		
		assertTrue("Message does not contain expected string", mOutput.getText().toString().contains("3.0, 5.0, 7.0"));
		assertFalse("Alert displays false alert", mOutput.getText().toString().contains("invalid input"));
	}


	public void test_inputHighRangeChecked(){
		TouchUtils.tapView(this, mInput);
		getInstrumentation().sendStringSync("103 5 7");
		
		TouchUtils.tapView(this, mButton1);
		
		assertTrue("Message does not contain expected string", mOutput.getText().toString().contains("not between 1 and 100"));
		assertFalse("Alert displays false alert", mOutput.getText().toString().contains("invalid input"));
	}
	

	public void test_inputLowRangeChecked(){
		TouchUtils.tapView(this, mInput);
		getInstrumentation().sendStringSync("-2 5 7");
		
		TouchUtils.tapView(this, mButton1);
		
		assertTrue("Message does not contain expected string", mOutput.getText().toString().contains("not between 1 and 100"));
		assertFalse("Alert displays false alert", mOutput.getText().toString().contains("invalid input"));
	}
	
	public void test_inputZeroDisplaysMessage(){
		TouchUtils.tapView(this, mInput);
		getInstrumentation().sendStringSync("0");
		
		TouchUtils.tapView(this, mButton1);
		
		assertEquals("Alert displays false alert", "The End", mOutput.getText().toString());
	}
	
	public void test_inputZeroExitsApp(){
		TouchUtils.tapView(this, mInput);
		getInstrumentation().sendStringSync("0");
		
		TouchUtils.tapView(this, mButton1);
		
		assertTrue(getActivity().isFinishing());
	}
	
	public void test_inputEquilateral(){
		TouchUtils.tapView(this, mInput);
		getInstrumentation().sendStringSync("2 2 2");
		
		TouchUtils.tapView(this, mButton1);
		
		assertEquals("Alert confirms Equilateral", "Equilateral", mOutput.getText().toString());
	}
	
	public void test_inputScalene(){
		TouchUtils.tapView(this, mInput);
		getInstrumentation().sendStringSync("3 5 7");
		
		TouchUtils.tapView(this, mButton1);
		
		assertEquals("Alert confirms Scalene", "Scalene", mOutput.getText().toString());
	}
	
	public void test_inputIsosceles(){
		TouchUtils.tapView(this, mInput);
		getInstrumentation().sendStringSync("3 5 7");
		
		TouchUtils.tapView(this, mButton1);
		
		assertEquals("Alert confirms Isosceles", "Isosceles", mOutput.getText().toString());
	}
	
	// these form a line (2,4,6), (4,9,5), (8,4,4)
	public void test_inputLineFails(){
		TouchUtils.tapView(this, mInput);
		getInstrumentation().sendStringSync("2,4,6");
		
		TouchUtils.tapView(this, mButton1);
		
		assertEquals("Alert displays Error on bad line lengths", "Error", mOutput.getText().toString());
	}

	
	//Eric - Was Testing StringUtils
	public void test_inputHasThreePositiveNumericValues(){
		//Simulate user typing '2,5,7'
		TouchUtils.tapView(this, mInput);
		getInstrumentation().sendStringSync("2,5,7");
		
		//Check input String has 3 positive numbers
		int countNumeric = StringUtils.CountPositiveNumericValuesInString(mInput.getText().toString());
		assertEquals("Failed: More than 3 numbers parsed: Has " + Integer.toString(countNumeric), 3, countNumeric);
		
		//Clear Input
		int inputStrCount = mInput.getText().length();
		for(int i=0; i < inputStrCount; i++){
			getInstrumentation().sendCharacterSync(KeyEvent.KEYCODE_DEL);
		}
		
		
		//Simulate user typing '2;5;7'
		TouchUtils.tapView(this, mInput);
		getInstrumentation().sendStringSync("2;5;7");
		
		//Check input String has 3 positive numbers
		countNumeric = StringUtils.CountPositiveNumericValuesInString(mInput.getText().toString());
		assertEquals("Failed: More than 3 numbers parsed: Has " + Integer.toString(countNumeric), 3, countNumeric);
		
		//Clear Input
		inputStrCount = mInput.getText().length();
		for(int i=0; i < inputStrCount; i++){
			getInstrumentation().sendCharacterSync(KeyEvent.KEYCODE_DEL);
		}
		
		
		
		//Simulate user typing '2 5 7'
		TouchUtils.tapView(this, mInput);
		getInstrumentation().sendStringSync("2 5 7");
		
		//Check input String has 3 positive numbers
		countNumeric = StringUtils.CountPositiveNumericValuesInString(mInput.getText().toString());
		assertEquals("Failed: More than 3 numbers parsed: Has " + Integer.toString(countNumeric), 3, countNumeric);
		
		//Clear Input
		inputStrCount = mInput.getText().length();
		for(int i=0; i < inputStrCount; i++){
			getInstrumentation().sendCharacterSync(KeyEvent.KEYCODE_DEL);
		}
	}
}
