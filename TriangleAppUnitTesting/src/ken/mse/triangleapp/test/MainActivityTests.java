/******************************************************************************************
 * Product: TriangleApp
 * 
 * Source File: MainActivityTests.java
 * 
 * Description: Implements primary functionality to fulfill TDD in TriangleApp.
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
		getInstrumentation().sendStringSync("1");                
		
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
		String test = mOutput.getText().toString();
		assertTrue("Message does not contain expected string", mOutput.getText().toString().contains("not between 1 and 100"));
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
		getActivity().getWindow().getDecorView().postDelayed(new Runnable() {			
			@Override
			public void run() {
				assertTrue(getActivity().isFinishing());
			}
		}, 3000);
		
	}
	
	public void test_inputEquilateral(){
		TouchUtils.tapView(this, mInput);
		getInstrumentation().sendStringSync("2 2 2");
		
		TouchUtils.tapView(this, mButton1);
		
		assertTrue("Alert confirms Equilateral", mOutput.getText().toString().contains("Equilateral"));
	}
	
	public void test_inputScalene(){
		TouchUtils.tapView(this, mInput);
		getInstrumentation().sendStringSync("3 5 7");
		
		TouchUtils.tapView(this, mButton1);

		assertTrue("Alert confirms Scalene", mOutput.getText().toString().contains("Scalene"));
	}
	
	public void test_inputIsosceles(){
		TouchUtils.tapView(this, mInput);
		getInstrumentation().sendStringSync("3 3 5");
		
		TouchUtils.tapView(this, mButton1);
		
		assertTrue("Alert confirms Isosceles", mOutput.getText().toString().contains("Isosceles"));
	}
	
	// these form a line (2,4,6), (4,9,5), (8,4,4)
	public void test_inputLineFails(){
		TouchUtils.tapView(this, mInput);
		getInstrumentation().sendStringSync("2,4,6");
		
		TouchUtils.tapView(this, mButton1);
		
		assertTrue("Alert displays Error on bad line lengths", mOutput.getText().toString().contains("Invalid"));
	}
}
