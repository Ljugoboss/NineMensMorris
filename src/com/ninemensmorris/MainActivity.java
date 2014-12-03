package com.ninemensmorris;

import java.util.ArrayList;

import Utils.Constants;
import Utils.Rules;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;



public class MainActivity extends Activity {
	private final String TAG = "Main activity";

	Rules rules = new Rules();

	private ArrayList<ImageView> arrayListWhiteCheckers;
	private ArrayList<ImageView> arrayListBlackCheckers;
	private ArrayList<ImageView> arrayListAreas;
	private ImageView imageViewCheckerToMove;
	private ImageView imageViewAreaToMoveTo;

	private boolean hasSelectedChecker = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "Creating activity");
		setContentView(R.layout.activity_main);

		imageViewCheckerToMove = (ImageView) findViewById(R.id.whiteChecker1);
		imageViewAreaToMoveTo = (ImageView) findViewById(R.id.area1);

		arrayListWhiteCheckers = new ArrayList<ImageView>();
		arrayListWhiteCheckers.add((ImageView) findViewById(R.id.whiteChecker1));
		arrayListWhiteCheckers.add((ImageView) findViewById(R.id.whiteChecker2));
		arrayListWhiteCheckers.add((ImageView) findViewById(R.id.whiteChecker3));
		arrayListWhiteCheckers.add((ImageView) findViewById(R.id.whiteChecker4));
		arrayListWhiteCheckers.add((ImageView) findViewById(R.id.whiteChecker5));
		arrayListWhiteCheckers.add((ImageView) findViewById(R.id.whiteChecker6));
		arrayListWhiteCheckers.add((ImageView) findViewById(R.id.whiteChecker7));
		arrayListWhiteCheckers.add((ImageView) findViewById(R.id.whiteChecker8));
		arrayListWhiteCheckers.add((ImageView) findViewById(R.id.whiteChecker9));

		arrayListBlackCheckers = new ArrayList<ImageView>();
		arrayListBlackCheckers.add((ImageView) findViewById(R.id.whiteChecker1));
		arrayListWhiteCheckers.add((ImageView) findViewById(R.id.whiteChecker2));
		arrayListWhiteCheckers.add((ImageView) findViewById(R.id.whiteChecker3));
		arrayListWhiteCheckers.add((ImageView) findViewById(R.id.whiteChecker4));
		arrayListWhiteCheckers.add((ImageView) findViewById(R.id.whiteChecker5));
		arrayListWhiteCheckers.add((ImageView) findViewById(R.id.whiteChecker6));
		arrayListWhiteCheckers.add((ImageView) findViewById(R.id.whiteChecker7));
		arrayListWhiteCheckers.add((ImageView) findViewById(R.id.whiteChecker8));
		arrayListWhiteCheckers.add((ImageView) findViewById(R.id.whiteChecker9));

		arrayListAreas = new ArrayList<ImageView>();
		arrayListAreas.add((ImageView) findViewById(R.id.area1));
		arrayListAreas.add((ImageView) findViewById(R.id.area2));
		arrayListAreas.add((ImageView) findViewById(R.id.area3));
		arrayListAreas.add((ImageView) findViewById(R.id.area4));
		arrayListAreas.add((ImageView) findViewById(R.id.area5));
		arrayListAreas.add((ImageView) findViewById(R.id.area6));
		arrayListAreas.add((ImageView) findViewById(R.id.area7));
		arrayListAreas.add((ImageView) findViewById(R.id.area8));
		arrayListAreas.add((ImageView) findViewById(R.id.area9));
		arrayListAreas.add((ImageView) findViewById(R.id.area10));
		arrayListAreas.add((ImageView) findViewById(R.id.area11));
		arrayListAreas.add((ImageView) findViewById(R.id.area12));
		arrayListAreas.add((ImageView) findViewById(R.id.area13));
		arrayListAreas.add((ImageView) findViewById(R.id.area14));
		arrayListAreas.add((ImageView) findViewById(R.id.area15));
		arrayListAreas.add((ImageView) findViewById(R.id.area16));
		arrayListAreas.add((ImageView) findViewById(R.id.area17));
		arrayListAreas.add((ImageView) findViewById(R.id.area18));
		arrayListAreas.add((ImageView) findViewById(R.id.area19));
		arrayListAreas.add((ImageView) findViewById(R.id.area20));
		arrayListAreas.add((ImageView) findViewById(R.id.area21));
		arrayListAreas.add((ImageView) findViewById(R.id.area22));
		arrayListAreas.add((ImageView) findViewById(R.id.area23));
		arrayListAreas.add((ImageView) findViewById(R.id.area24));
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			Log.i(TAG, "Action down");
			//TODO Check position and do stuff
			break;
		case MotionEvent.ACTION_UP:
			Log.i(TAG, "Action up");
			//TODO Check position and do stuff

			if (!hasSelectedChecker) {
				if (checkerAtPosition(event.getX(), event.getY())) {
					hasSelectedChecker = true;
					AlphaAnimation alphaAnimation = new AlphaAnimation(1, (float) 0.5);
					alphaAnimation.setFillAfter(true);
					imageViewCheckerToMove.startAnimation(alphaAnimation);
				}
			} else {
				if (areaAtPosition(event.getX(), event.getY())) {
					hasSelectedChecker = false;
					moveChecker();
				}
			}
			break;
		}

		return super.onTouchEvent(event);
	}

	private boolean checkerAtPosition(float x, float y) {

		// TODO Check for every checker in the game
		if (rules.getTurn() == Constants.WHITE) {
			for (ImageView v : arrayListWhiteCheckers) {
				if (getBorders(v).contains((int) x, (int) y)) {
					Log.i(TAG, "Clicked checker");
					imageViewCheckerToMove = v;
					return true;
				}
			}
		} else {
			for (ImageView v : arrayListBlackCheckers) {
				if (getBorders(v).contains((int) x, (int) y)) {
					Log.i(TAG, "Clicked checker");
					imageViewCheckerToMove = v;
					return true;
				}
			}
		}
		return false;
	}

	private boolean areaAtPosition(float x, float y) {

		// TODO Check for every checker in the game
		for (ImageView v : arrayListAreas) {
			if (getBorders(v).contains((int) x, (int) y)) {
				Log.i(TAG, "Clicked area");
				imageViewAreaToMoveTo = v;
				return true;
			}
		}
		return false;
	}

	private Rect getBorders(ImageView v) {
		if (v != null) {
			int[] location = {0, 0};
			v.getLocationOnScreen(location);

			int width = v.getWidth();
			int height = v.getHeight();

			int left = location[0] - width/2;
			int right = location[0] + width/2;
			int top = location[1] - height/2;
			int bottom = location[1] + height/2;

			return new Rect(left, top, right, bottom);		
		}
		return new Rect();
	}
	
	private void moveChecker() {
		final int[] locationChecker = {0, 0};
		final int[] locationArea = {0, 0};
		imageViewCheckerToMove.getLocationOnScreen(locationChecker);
		imageViewAreaToMoveTo.getLocationOnScreen(locationArea);
		TranslateAnimation tAnimation = new TranslateAnimation(0, locationArea[0] - locationChecker[0], 0, locationArea[1] - locationChecker[1]);
		tAnimation.setFillEnabled(false);
		tAnimation.setFillAfter(false);
		tAnimation.setDuration(2000);
		tAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Ändra till rätt koordinater
//				int width = imageViewCheckerToMove.getWidth();
//				int height = imageViewCheckerToMove.getHeight();
//
//				int left = locationArea[0] - locationChecker[0];
//				int right = locationArea[0] - locationChecker[0] + imageViewCheckerToMove.getWidth();
//				int top = locationArea[1] - locationChecker[1];
//				int bottom = locationArea[1] - locationChecker[1] + imageViewCheckerToMove.getHeight();
//			
//				int left = imageViewAreaToMoveTo.getLeft();
//				int right = imageViewAreaToMoveTo.getRight();
//				int top = imageViewAreaToMoveTo.getTop();
//				int bottom = imageViewAreaToMoveTo.getBottom();
				
				// Remove the checker from the side of the board onto the board
				if (imageViewCheckerToMove.getParent() != findViewById(R.id.board)) {
					((ViewGroup)imageViewCheckerToMove.getParent()).removeView(imageViewCheckerToMove);
					((ViewGroup) findViewById(R.id.board)).addView(imageViewCheckerToMove);
				}
				
				imageViewCheckerToMove.setLayoutParams(imageViewAreaToMoveTo.getLayoutParams());
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
			}
		});
		imageViewCheckerToMove.startAnimation(tAnimation);
	}
}
