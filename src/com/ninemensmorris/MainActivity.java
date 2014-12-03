package com.ninemensmorris;

import java.util.ArrayList;

import Utils.Constants;
import Utils.Rules;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;



public class MainActivity extends Activity {
	private final String TAG = "Main activity";

	Rules rules = new Rules();

	private ArrayList<ImageView> arrayListWhiteCheckers;
	private ArrayList<ImageView> arrayListBlackCheckers;
	private ArrayList<ImageView> arrayListAreas;
	private ImageView imageViewSelectedChecker;
	private ImageView imageViewAreaToMoveTo;
	private ImageView imageViewAreaToMoveFrom;

	private boolean hasSelectedChecker = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "Creating activity");
		setContentView(R.layout.activity_main);

		imageViewSelectedChecker = null;
		imageViewAreaToMoveTo = null;
		imageViewAreaToMoveFrom = null;

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

		for (ImageView v : arrayListWhiteCheckers) {
			v.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (rules.getTurn() == Constants.WHITE) {
						if (imageViewSelectedChecker != null) {
							imageViewSelectedChecker.setAlpha(1.0f);
						}
						hasSelectedChecker = true;
						imageViewSelectedChecker = (ImageView) v;
						imageViewSelectedChecker.setAlpha(0.5f);				
					}
				}
			});
		}

		for (ImageView v : arrayListBlackCheckers) {
			v.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (rules.getTurn() == Constants.BLACK) {
						if (imageViewSelectedChecker != null) {
							imageViewSelectedChecker.setAlpha(1.0f);
						}
						hasSelectedChecker = true;
						imageViewSelectedChecker = (ImageView) v;
						imageViewSelectedChecker.setAlpha(0.5f);					
					}
				}
			});
		}

		for (ImageView v : arrayListAreas) {
			v.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (hasSelectedChecker) {
						imageViewAreaToMoveTo = (ImageView) v;
						int from = 0;
						if (imageViewAreaToMoveFrom != null) {
							imageViewSelectedChecker.setAlpha(1.0f);
							from = Integer.parseInt((String) imageViewAreaToMoveFrom.getContentDescription());
						}
//						if (rules.validMove(from, Integer.parseInt((String) imageViewAreaToMoveTo.getContentDescription()))) {
							moveChecker();
//						}	
						moveChecker();
						imageViewSelectedChecker.setAlpha(1.0f);
						hasSelectedChecker = false;
						imageViewSelectedChecker = null;
					}
				}
			});
		}
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
					//					AlphaAnimation alphaAnimation = new AlphaAnimation(1, (float) 0.5);
					//					alphaAnimation.setFillAfter(true);
					//					imageViewSelectedChecker.startAnimation(alphaAnimation);
				}
			} else {
				if (areaAtPosition(event.getX(), event.getY())) {
					hasSelectedChecker = false;
					//					int from, to;
					//					if (imageViewAreaToMoveFrom != null) {
					//						from = Integer.parseInt((String) imageViewAreaToMoveFrom.getContentDescription());
					//					} else {
					//						from = 0;
					//					}
					//					rules.validMove(from, Integer.parseInt((String) imageViewAreaToMoveTo.getContentDescription()));
//					moveChecker();
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
					imageViewSelectedChecker = v;
					return true;
				}
			}
		} else {
			for (ImageView v : arrayListBlackCheckers) {
				if (getBorders(v).contains((int) x, (int) y)) {
					Log.i(TAG, "Clicked checker");
					imageViewSelectedChecker = v;
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
		imageViewSelectedChecker.getLocationOnScreen(locationChecker);
		imageViewAreaToMoveTo.getLocationOnScreen(locationArea);
		TranslateAnimation tAnimation = new TranslateAnimation(0, locationArea[0] - locationChecker[0], 0, locationArea[1] - locationChecker[1]);
		tAnimation.setFillEnabled(false);
		tAnimation.setFillAfter(false);
		tAnimation.setDuration(2000);
		final ImageView tmpImageViewSelectedChecker = imageViewSelectedChecker;
		final ImageView tmpImageViewAreaToMoveTo = imageViewAreaToMoveTo;
		tAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Ändra till rätt koordinater
				//				int width = imageViewSelectedChecker.getWidth();
				//				int height = imageViewSelectedChecker.getHeight();
				//
				//				int left = locationArea[0] - locationChecker[0];
				//				int right = locationArea[0] - locationChecker[0] + imageViewSelectedChecker.getWidth();
				//				int top = locationArea[1] - locationChecker[1];
				//				int bottom = locationArea[1] - locationChecker[1] + imageViewSelectedChecker.getHeight();
				//			
				//				int left = imageViewAreaToMoveTo.getLeft();
				//				int right = imageViewAreaToMoveTo.getRight();
				//				int top = imageViewAreaToMoveTo.getTop();
				//				int bottom = imageViewAreaToMoveTo.getBottom();

				// Remove the checker from the side of the board onto the board
				if (tmpImageViewSelectedChecker.getParent() != findViewById(R.id.board)) {
					((ViewGroup)tmpImageViewSelectedChecker.getParent()).removeView(tmpImageViewSelectedChecker);
					((ViewGroup) findViewById(R.id.board)).addView(tmpImageViewSelectedChecker);
				}

				tmpImageViewSelectedChecker.setLayoutParams(tmpImageViewAreaToMoveTo.getLayoutParams());
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
		imageViewSelectedChecker.startAnimation(tAnimation);
	}
}
