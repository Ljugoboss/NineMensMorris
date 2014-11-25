package com.ninemensmorris;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;



public class MainActivity extends Activity {
	private final String TAG = "Main activity";
	private HashMap<String, Double> rates; //Used to store the rates with currency as a key for easy access.
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "Creating activity");
		setContentView(R.layout.activity_main);
	}

}
