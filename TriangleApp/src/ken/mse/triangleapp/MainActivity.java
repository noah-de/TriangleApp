package ken.mse.triangleapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	//Activity's Tag for logs
	private static final String sTag = MainActivity.class.getSimpleName();
	
	private TextView mInitialText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Setup Layout
		setContentView(R.layout.activity_main);
		
		//*** Screen is now loaded with a layout, manipulate objects in layout below ***
		
		//Example: Get the initialText Object in the layout
		mInitialText = (TextView) findViewById(R.id.initialText);
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

}
