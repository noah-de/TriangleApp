package ken.mse.triangleapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	//Activity's Tag for logs
	private static final String sTag = MainActivity.class.getSimpleName();
	
	private TextView mInitialText;

	private TextView mInputString;
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
		mInitialText = (TextView) findViewById(R.id.initialText);
		
		mInput = (EditText) findViewById(R.id.input1);
		
		
		//Output it's text
		String initialTextStr = mInitialText.getText().toString();
		Log.d(sTag, "Initial Text: " + initialTextStr);
			
		
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	// Additions by: Keir Trotter
	/** Called when the user clicks the Enter button */
	public void enterMessage(View view) {
	    // Do something in response to button
		
		// Pull in the text input string
		mInputString = (TextView) findViewById(R.id.input1);

		// Output lengths
		String lengthsTextStr = mInputString.getText().toString();
		
		// Parse input
		// take space, ',', or ';' as delimiter
		String delims = "[ ,;]";
		String[] tokens = lengthsTextStr.split(delims);
		
		int numberOfTriangleLengths = tokens.length;
		final int SidesOfTriangle = 3;
		
		
		// Check number of lengths
		//============================================================================
		if(SidesOfTriangle != numberOfTriangleLengths)
		{
			Log.d(sTag, "Triangle lengths Text: " + lengthsTextStr + " Bad Sides:" + numberOfTriangleLengths);
		}
		else
		{
			mLength1 = Float.parseFloat(tokens[0]);
			mLength2 = Float.parseFloat(tokens[1]);
			mLength3 = Float.parseFloat(tokens[2]);

			Log.d(sTag, "Triangle lengths Text: " + mLength1 + " " + mLength2 + " " + mLength3 +  " Sides:" + numberOfTriangleLengths);
		}
		//============================================================================

		
		// Check validity of lengths (1<= x <= 100)
		//============================================================================
		if((mLength1 < 1) || (mLength1 > 100))
		{
			Log.d(sTag, "Triangle Length 1 not between 1 and 100: " + mLength1);
		}
		if((mLength2 < 1) || (mLength2 > 100))
		{
			Log.d(sTag, "Triangle Length 2 not between 1 and 100: " + mLength2);
		}
		if((mLength3 < 1) || (mLength3 > 100))
		{
			Log.d(sTag, "Triangle Length 3 not between 1 and 100: " + mLength3);
		}
		//============================================================================

	}

}
