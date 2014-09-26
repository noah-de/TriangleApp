/******************************************************************************************
 * Product: TriangleApp
 * 
 * Source File: MainActivity.java
 * 
 * Description: Implements primary functionality to fulfill product goals of TriangleApp.
 * 
 * Developers: Noah Spahn, Keir Trotter, Eric Yan
 * 
 * Release History:
 * 10/03/2014  Sprint 3 Release, V3
 * 09/26/2014  Sprint 2 Release, V2
 * 09/19/2014  Sprint 1 Release, V1
 * 
*******************************************************************************************/

package ken.mse.triangleapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.graphics.Color;


public class MainActivity extends Activity {
	//Activity's Tag for logs
	private static final String sTag = MainActivity.class.getSimpleName();
	
	private TextView mText;

	private TextView mInputString;
	private TextView mOutputString;
	private float mLength1;
	private float mLength2;
	private float mLength3;
	
	private EditText mInput;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//Setup Layout
		setContentView(R.layout.activity_main);

		//*** Screen is now loaded with a layout, manipulate objects in layout below ***

		//Example: Get the initialText Object in the layout
		mText = (TextView) findViewById(R.id.initialText);
		
		mInputString = (TextView) findViewById(R.id.input1);
		mInput = (EditText) findViewById(R.id.input1);
		
		mOutputString = (TextView) findViewById(R.id.output);
		
		
		//Output it's text
		String initialTextStr = mText.getText().toString();
		Log.d(sTag, "Initial Text: " + initialTextStr);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// Additions by: Keir Trotter
	private boolean checkLengths()
	{
		String outputStr;
		boolean returnStatus = true;

		// Check validity of lengths (1<= x <= 100)
		//============================================================================
		outputStr = " [" + mLength1 + ", " + mLength2 + ", " + mLength3 + "] = ";

		if((mLength1 < 1) || (mLength1 > 100))
		{
			outputStr = outputStr + " Triangle Length 1 not between 1 and 100: " + mLength1 + "\n";
			mOutputString.setTextColor(Color.rgb(200,0,0));
			mOutputString.setText(outputStr);
			Log.d(sTag, "Triangle Length 1 not between 1 and 100: " + mLength1);
			returnStatus = false;
		}

		if((mLength2 < 1) || (mLength2 > 100))
		{
			outputStr = outputStr + " Triangle Length 2 not between 1 and 100: " + mLength2 + "\n";
			mOutputString.setTextColor(Color.rgb(200,0,0));
			mOutputString.setText(outputStr);
			Log.d(sTag, "Triangle Length 2 not between 1 and 100: " + mLength2);
			returnStatus = false;
		}

		if((mLength3 < 1) || (mLength3 > 100))
		{
			outputStr = outputStr + " Triangle Length 3 not between 1 and 100: " + mLength3 + "\n";
			mOutputString.setTextColor(Color.rgb(200,0,0));
			mOutputString.setText(outputStr);
			Log.d(sTag, "Triangle Length 3 not between 1 and 100: " + mLength3);
			returnStatus = false;
		}
		
		//Eric - Add Additional Validity (After individual sides have valid lengths)
		//Validate sum of Two Sides are not greater than the last side
		if(returnStatus){
			if(mLength1 > mLength2 + mLength3){
				outputStr = outputStr + " Invalid!\n"
							+ " Any side of a triangle must be shorter than the sum of the other two sides: " 
							+ mLength1 + " > " + mLength2 + " + " + mLength3;
				mOutputString.setTextColor(Color.rgb(200,0,0));
				mOutputString.setText(outputStr);
				returnStatus = false;
			}else if(mLength2 > mLength1 + mLength3){
				outputStr = outputStr + " Invalid!\n"
						+ " Any side of a triangle must be shorter than the sum of the other two sides: "
						+ mLength2 + " > " + mLength1 + " + " + mLength3;
				mOutputString.setTextColor(Color.rgb(200,0,0));
				mOutputString.setText(outputStr);
				returnStatus = false;
			}else if(mLength3 > mLength1 + mLength2){
				outputStr = outputStr + " Invalid!\n"
						+ " Any side of a triangle must be shorter than the sum of the other two sides: "
						+ mLength3 + " > " + mLength1 + " + " + mLength2;
				mOutputString.setTextColor(Color.rgb(200,0,0));
				mOutputString.setText(outputStr);
				returnStatus = false;
			}else if(mLength1 == mLength2 + mLength3 || mLength2 == mLength1 + mLength3 || mLength3 == mLength1 + mLength2){
				outputStr = outputStr + " Invalid!\n"
						+ " Any side of a triangle must be shorter than the sum of the other two sides: "
						+ mLength1 + " = " + mLength2 + " + " + mLength3;
				mOutputString.setTextColor(Color.rgb(200,0,0));
				mOutputString.setText(outputStr);
				returnStatus = false;
			}
		}
		
		
		//============================================================================

		mInputString.setText("");
		
		if(returnStatus)
		{
			mOutputString.setTextColor(Color.rgb(0,0,0));
		}

		return returnStatus;
	}

	// Additions by: Keir Trotter
	/** Called when the user clicks the Enter button */
	public void enterMessage(View view)
	{
		//Get Input String
		String inputStr = mInput.getText().toString();
		
		//Check if user wants to exit
		// Exit app if 0 entered
		if(inputStr.trim().equals("0")){
			mOutputString.setTextColor(Color.rgb(0,0,0));
			mOutputString.setText("The End");
			Log.d(sTag, "The End");

			// Wait 3 seconds before exiting to allow viewing
			getWindow().getDecorView().postDelayed(new Runnable() {				
				@Override
				public void run() {
					finish();
				}
			}, 3000);
			
			return;
		}
		
		
		//Get Triangle Lengths
		float[] triangleLengthArr = StringUtils.ParseStringToNumericValues(inputStr);
		
		//*** Validate Lengths and Output Results; Code Based on previous code below ***
		//Verify we have 3 lengths
		int numberOfTriangleLengths = triangleLengthArr.length;
		String outputStr = "";
		if(numberOfTriangleLengths == 1){
			//Set Triangle Lengths
			mLength1 = triangleLengthArr[0];
			
			//Set Error Msg for OutputStr
			outputStr = " [" + mLength1 + ", ] invalid input (need more input)";
			
			//Set Output Message
			mOutputString.setTextColor(Color.rgb(200,0,0));
			mOutputString.setText(outputStr);
			mInputString.setText("");
		}else if(numberOfTriangleLengths == 2){
			//Set Triangle Lengths
			mLength1 = triangleLengthArr[0];
			mLength2 = triangleLengthArr[1];
			
			//Set Error Msg for OutputStr
			outputStr = " [" + mLength1 + ", " + mLength2 + ", ] invalid input (need more input)";
			
			//Set Output Message
			mOutputString.setTextColor(Color.rgb(200,0,0));
			mOutputString.setText(outputStr);
			mInputString.setText("");
		}else if(numberOfTriangleLengths == 3){
			//Set Triangle Lengths
			mLength1 = triangleLengthArr[0];
			mLength2 = triangleLengthArr[1];
			mLength3 = triangleLengthArr[2];

			if(checkLengths()){
				String triangleType = getTriangleType(mLength1, mLength2, mLength3);
				//Set Success Msg for OutputStr
				outputStr = " [" + mLength1 + ", " + mLength2 + ", " + mLength3 + "] = " + triangleType;
				Log.d(sTag, "Triangle lengths Text: " + mLength1 + " " + mLength2 + " " + mLength3 +  " Sides:" + triangleLengthArr.length);
				
				//Set Output Message
				mOutputString.setTextColor(Color.rgb(0,0,0)); // default color
				mOutputString.setText(outputStr);
				mInputString.setText("");
			}
		}else{
			//Set Error Msg for OutputStr
			outputStr = "invalid input";
			
			//Set Output Message
			mOutputString.setTextColor(Color.rgb(200,0,0));
			mOutputString.setText(outputStr);
			mInputString.setText("");
		}

		return;
	}
	
	//Determine Triangle Type Based on side lengths
	private String getTriangleType(float sideALength, float sideBLength, float sideCLength){
		if(sideALength == sideBLength && sideALength == sideCLength){
			return "Equilateral";
		}else if(sideALength == sideBLength || sideALength == sideCLength || sideBLength == sideCLength){
			return "Isosceles";
		}
		return "Scalene";
	}
	
}
