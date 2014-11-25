package com.ninemensmorris;

import Utils.Rules;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;



public class MainActivity extends Activity {
	private final String TAG = "Main activity";
	
	Rules rules = new Rules();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "Creating activity");
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_UP:
			Log.i(TAG, "Action up");
			//TODO Check position and do stuff
			break;
		}
		return super.onTouchEvent(event);
	}
}
