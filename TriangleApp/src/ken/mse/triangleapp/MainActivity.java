package ken.mse.triangleapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.graphics.Color;
import android.os.SystemClock;

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
		
		mInput = (EditText) findViewById(R.id.input1);
		
		
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
			outputStr = outputStr + " Triangle Length 1 not between 1 and 100: " + mLength1;
			mOutputString.setTextColor(Color.rgb(200,0,0));
			mOutputString.setText(outputStr);
			Log.d(sTag, "Triangle Length 1 not between 1 and 100: " + mLength1);
			returnStatus = false;
		}

		if((mLength2 < 1) || (mLength2 > 100))
		{
			outputStr = outputStr + " Triangle Length 2 not between 1 and 100: " + mLength2;
			mOutputString.setTextColor(Color.rgb(200,0,0));
			mOutputString.setText(outputStr);
			Log.d(sTag, "Triangle Length 2 not between 1 and 100: " + mLength2);
			returnStatus = false;
		}

		if((mLength3 < 1) || (mLength3 > 100))
		{
			outputStr = outputStr + " Triangle Length 3 not between 1 and 100: " + mLength3;
			mOutputString.setTextColor(Color.rgb(200,0,0));
			mOutputString.setText(outputStr);
			Log.d(sTag, "Triangle Length 3 not between 1 and 100: " + mLength3);
			returnStatus = false;
		}
		//============================================================================

		mInputString.setText("");

		return returnStatus;
	}

	// Additions by: Keir Trotter
	/** Called when the user clicks the Enter button */
	public void enterMessage(View view) 
	{
	    // Do something in response to button

		// Pull in the text input string
		mInputString = (TextView) findViewById(R.id.input1);

		mOutputString = (TextView) findViewById(R.id.output);
		mOutputString.setTextColor(Color.rgb(155,0,0)); // default color

		// Output lengths
		String lengthsTextStr = mInputString.getText().toString();
		
		String outputStr = "";

		// Parse input
		// take space, ',', or ';' as delimiter
		String delims = "[ ,;]";
		String[] tokens = lengthsTextStr.split(delims);

		
		int numberOfTriangleLengths = tokens.length;
		
		if(numberOfTriangleLengths >= 1)
		{
			mLength1 = Float.parseFloat(tokens[0]);
		}
		
		if(numberOfTriangleLengths >= 2)
		{
			mLength2 = Float.parseFloat(tokens[1]);
		}
		
		if(numberOfTriangleLengths >= 3)
		{
			mLength3 = Float.parseFloat(tokens[2]);
		}

		// Exit app if 0 entered
		if(mLength1 == 0)
		{
			mOutputString.setText("The End");
			Log.d(sTag, "The End");

			// Wait 2 seconds before exiting to allow viewing
			// of exit message :TODO: may not be working right
			SystemClock.sleep(2000);

			// believe this is launching a separate thread
			finish();
		}

		final int SidesOfTriangle = 3;

		//:TODO: is this not a test in itself? not sure where this would be
		// implemented in TriangleAppUnitTesting
		// Check number of lengths
		//============================================================================
		if((numberOfTriangleLengths < SidesOfTriangle) && (mLength1 != 0))
		{

			if(numberOfTriangleLengths == 1)
			{
				outputStr = " [" + mLength1 + ", ] invalid input (need more input)";
			}
			
			if(numberOfTriangleLengths == 2)
			{
				outputStr = " [" + mLength1 + ", " + mLength2 + ", ] invalid input (need more input)";
			}
						
			
			mOutputString.setTextColor(Color.rgb(200,0,0));
			mOutputString.setText(outputStr);
			Log.d(sTag, "Triangle lengths Text: " + lengthsTextStr + " Bad Sides:" + numberOfTriangleLengths);
		}
		else if((numberOfTriangleLengths > SidesOfTriangle) && (mLength1 != 0))
		{
			outputStr = " [" + mLength1 + ", " + mLength2 + ", " + mLength3 + "...]  = invalid input (too much input)";
			mOutputString.setTextColor(Color.rgb(200,0,0));
			mOutputString.setText(outputStr);
			Log.d(sTag, "Triangle lengths Text: " + lengthsTextStr + " Bad Sides:" + numberOfTriangleLengths);
		}
		else if((numberOfTriangleLengths == SidesOfTriangle) && (mLength1 != 0))
		{
			mLength1 = Float.parseFloat(tokens[0]);
			mLength2 = Float.parseFloat(tokens[1]);
			mLength3 = Float.parseFloat(tokens[2]);

			if(checkLengths())
			{
				outputStr = " [" + mLength1 + ", " + mLength2 + ", " + mLength3 + "] = ";
				mOutputString.setText(outputStr);
				mInputString.setText("");

				Log.d(sTag, "Triangle lengths Text: " + mLength1 + " " + mLength2 + " " + mLength3 +  " Sides:" + numberOfTriangleLengths);
			}
		}
		//============================================================================

	}

}
