package com.ninemensmorris;

import java.util.ArrayList;
import java.util.HashMap;

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
	private HashMap<ImageView, Integer> checkerPositions;

	private boolean hasSelectedChecker = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "Creating activity");
		setContentView(R.layout.activity_main);

		imageViewSelectedChecker = null;
		imageViewAreaToMoveTo = null;
		imageViewAreaToMoveFrom = null;
		checkerPositions = new HashMap<ImageView, Integer>();

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
		arrayListBlackCheckers.add((ImageView) findViewById(R.id.blackChecker1));
		arrayListBlackCheckers.add((ImageView) findViewById(R.id.blackChecker2));
		arrayListBlackCheckers.add((ImageView) findViewById(R.id.blackChecker3));
		arrayListBlackCheckers.add((ImageView) findViewById(R.id.blackChecker4));
		arrayListBlackCheckers.add((ImageView) findViewById(R.id.blackChecker5));
		arrayListBlackCheckers.add((ImageView) findViewById(R.id.blackChecker6));
		arrayListBlackCheckers.add((ImageView) findViewById(R.id.blackChecker7));
		arrayListBlackCheckers.add((ImageView) findViewById(R.id.blackChecker8));
		arrayListBlackCheckers.add((ImageView) findViewById(R.id.blackChecker9));

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
			checkerPositions.put(v, 0);
			v.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (rules.getTurn() == Constants.WHITE) {
						if (!(checkerPositions.get(v) != 0 && checkerPositions.containsValue(0)) || (checkerPositions.get(v) == 0)) {
							if (imageViewSelectedChecker != null) {
								imageViewSelectedChecker.setAlpha(1.0f);
							}
							hasSelectedChecker = true;
							imageViewSelectedChecker = (ImageView) v;
							imageViewSelectedChecker.setAlpha(0.5f);
						}
					}
				}
			});
		}

		for (ImageView v : arrayListBlackCheckers) {
			checkerPositions.put(v, 0);
			v.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (rules.getTurn() == Constants.BLACK) {
						if (!(checkerPositions.get(v) != 0 && checkerPositions.containsValue(0)) || (checkerPositions.get(v) == 0)) {
							if (imageViewSelectedChecker != null) {
								imageViewSelectedChecker.setAlpha(1.0f);
							}
							hasSelectedChecker = true;
							imageViewSelectedChecker = (ImageView) v;
							imageViewSelectedChecker.setAlpha(0.5f);
						}
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

						if (imageViewAreaToMoveFrom != null) {
							imageViewSelectedChecker.setAlpha(1.0f);
						}
						int to = Integer.parseInt((String) imageViewAreaToMoveTo.getContentDescription());
						if (rules.validMove(checkerPositions.get(imageViewSelectedChecker), to)) {
							moveChecker();
							
							// Remember new position
							checkerPositions.put((ImageView) imageViewSelectedChecker, Integer.parseInt((String) imageViewAreaToMoveTo.getContentDescription()));
						}	
						imageViewSelectedChecker.setAlpha(1.0f);
						hasSelectedChecker = false;
						imageViewSelectedChecker = null;
					}
				}
			});
		}
	}

	private void moveChecker() {
		final ImageView tmpImageViewSelectedChecker = imageViewSelectedChecker;
		final ImageView tmpImageViewAreaToMoveTo = imageViewAreaToMoveTo;
		final int[] locationChecker = {0, 0};
		final int[] locationArea = {0, 0};
		tmpImageViewSelectedChecker.getLocationOnScreen(locationChecker);
		tmpImageViewAreaToMoveTo.getLocationOnScreen(locationArea);

		TranslateAnimation tAnimation = new TranslateAnimation(0, locationArea[0] - locationChecker[0], 0, locationArea[1] - locationChecker[1]);
		tAnimation.setFillEnabled(false);
		tAnimation.setFillAfter(false);
		tAnimation.setDuration(2000);
		tAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationEnd(Animation animation) {

				// Remove the checker from the side of the board onto the board and add a new view to hold its place.
				if (tmpImageViewSelectedChecker.getParent() != findViewById(R.id.board)) {
					ViewGroup parent = ((ViewGroup)tmpImageViewSelectedChecker.getParent());
					int index = parent.indexOfChild(tmpImageViewSelectedChecker);
					parent.removeView(tmpImageViewSelectedChecker);
					parent.addView(getLayoutInflater().inflate(R.layout.layout_placeholder, parent, false), index);
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
