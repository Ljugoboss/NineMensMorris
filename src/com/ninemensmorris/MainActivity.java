package com.ninemensmorris;

import java.util.ArrayList;
import java.util.HashMap;

import Utils.Constants;
import Utils.Rules;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;



public class MainActivity extends Activity {
	private final String TAG = "Main activity";

	Rules rules = new Rules();

	private TextView playerTurn;
	private ArrayList<ImageView> arrayListWhiteCheckers;
	private ArrayList<ImageView> arrayListBlackCheckers;
	private ArrayList<FrameLayout> arrayListAreas;
	private ImageView imageViewSelectedChecker;
	private FrameLayout imageViewAreaToMoveTo;
	private ImageView imageViewAreaToMoveFrom;
	private HashMap<ImageView, Integer> checkerPositions;

	private boolean hasSelectedChecker = false;
	private boolean removeNextChecker = false;
	private boolean isWin = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "Creating activity");
		setContentView(R.layout.activity_main);

		imageViewSelectedChecker = null;
		imageViewAreaToMoveTo = null;
		imageViewAreaToMoveFrom = null;
		checkerPositions = new HashMap<ImageView, Integer>();
		playerTurn = (TextView) findViewById(R.id.TurnText);

		// Initialize all views
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

		arrayListAreas = new ArrayList<FrameLayout>();
		arrayListAreas.add((FrameLayout) findViewById(R.id.area1));
		arrayListAreas.add((FrameLayout) findViewById(R.id.area2));
		arrayListAreas.add((FrameLayout) findViewById(R.id.area3));
		arrayListAreas.add((FrameLayout) findViewById(R.id.area4));
		arrayListAreas.add((FrameLayout) findViewById(R.id.area5));
		arrayListAreas.add((FrameLayout) findViewById(R.id.area6));
		arrayListAreas.add((FrameLayout) findViewById(R.id.area7));
		arrayListAreas.add((FrameLayout) findViewById(R.id.area8));
		arrayListAreas.add((FrameLayout) findViewById(R.id.area9));
		arrayListAreas.add((FrameLayout) findViewById(R.id.area10));
		arrayListAreas.add((FrameLayout) findViewById(R.id.area11));
		arrayListAreas.add((FrameLayout) findViewById(R.id.area12));
		arrayListAreas.add((FrameLayout) findViewById(R.id.area13));
		arrayListAreas.add((FrameLayout) findViewById(R.id.area14));
		arrayListAreas.add((FrameLayout) findViewById(R.id.area15));
		arrayListAreas.add((FrameLayout) findViewById(R.id.area16));
		arrayListAreas.add((FrameLayout) findViewById(R.id.area17));
		arrayListAreas.add((FrameLayout) findViewById(R.id.area18));
		arrayListAreas.add((FrameLayout) findViewById(R.id.area19));
		arrayListAreas.add((FrameLayout) findViewById(R.id.area20));
		arrayListAreas.add((FrameLayout) findViewById(R.id.area21));
		arrayListAreas.add((FrameLayout) findViewById(R.id.area22));
		arrayListAreas.add((FrameLayout) findViewById(R.id.area23));
		arrayListAreas.add((FrameLayout) findViewById(R.id.area24));

		for (ImageView v : arrayListWhiteCheckers) {
			checkerPositions.put(v, 0);
			v.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (rules.getTurn() == Constants.WHITE && !isWin) {
						selectChecker(v);
					}
				}
			});
		}

		for (ImageView v : arrayListBlackCheckers) {
			checkerPositions.put(v, 0);
			v.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (rules.getTurn() == Constants.BLACK && !isWin) {
						selectChecker(v);
					}
				}
			});
		}

		// Determines if the move is valid and changes turns
		for (FrameLayout v : arrayListAreas) {
			v.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (hasSelectedChecker) {
						imageViewAreaToMoveTo = (FrameLayout) v;

						if (imageViewAreaToMoveFrom != null) {
							imageViewSelectedChecker.setAlpha(1.0f);
						}
						int to = Integer.parseInt((String) imageViewAreaToMoveTo.getContentDescription());
						int from = checkerPositions.get(imageViewSelectedChecker);
						if (rules.validMove(from, to)) { // This line will change turn
							unMarkAllFields();
							moveChecker();
							
							checkerPositions.put((ImageView) imageViewSelectedChecker, Integer.parseInt((String) imageViewAreaToMoveTo.getContentDescription()));

							removeNextChecker = rules.canRemove(to);
						}

						imageViewSelectedChecker.setAlpha(1.0f);
						hasSelectedChecker = false;
						imageViewSelectedChecker = null;
						if (removeNextChecker) {
							if (rules.getTurn() == Constants.WHITE) {
								playerTurn.setText("Remove White");
							} else {
								playerTurn.setText("Remove Black");
							}
						} else {
							if (rules.getTurn() == Constants.WHITE) {
								playerTurn.setText("White turn");
							} else {
								playerTurn.setText("Black turn");
							}
						}
					}
				}
			});
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Standard menu creating
		switch (item.getItemId()) {
		case R.id.item1:
			finish();
			startActivity(getIntent());
			return true;           
		default:
			return super.onOptionsItemSelected(item);
		} 
	} 

	/**
	 * Creates an animation that will move the checker from its current position to the new. Repositions the checker after animation is done.
	 */
	private void moveChecker() {
		// Create two temporary final variables so that the onAnimationEnd method uses the right values
		final ImageView tmpImageViewSelectedChecker = imageViewSelectedChecker;
		final FrameLayout tmpImageViewAreaToMoveTo = imageViewAreaToMoveTo;
		final int[] locationChecker = {0, 0};
		final int[] locationArea = {0, 0};
		tmpImageViewSelectedChecker.getLocationOnScreen(locationChecker);
		tmpImageViewAreaToMoveTo.getLocationOnScreen(locationArea);

		//Prepare animation with x and y movement
		TranslateAnimation tAnimation = new TranslateAnimation(0, locationArea[0] - locationChecker[0], 0, locationArea[1] - locationChecker[1]);
		tAnimation.setFillEnabled(true);
		tAnimation.setDuration(1500);

		tAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationEnd(Animation animation) {
				tmpImageViewSelectedChecker.setLayoutParams(tmpImageViewAreaToMoveTo.getLayoutParams());

				tmpImageViewSelectedChecker.setDrawingCacheEnabled(true);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationStart(Animation animation) {
				tmpImageViewSelectedChecker.setDrawingCacheEnabled(false);
			}
		});
		//Move to the other layout
		if (imageViewSelectedChecker.getParent() != findViewById(R.id.board)) {
			ViewGroup parent = ((ViewGroup)imageViewSelectedChecker.getParent());
			FrameLayout placeholder = (FrameLayout) getLayoutInflater().inflate(R.layout.layout_placeholder, parent, false);
			int index = parent.indexOfChild(imageViewSelectedChecker);
			parent.removeView(imageViewSelectedChecker);
			parent.addView(placeholder, index);
			((ViewGroup) findViewById(R.id.board)).addView(imageViewSelectedChecker);

		}
		
		imageViewSelectedChecker.startAnimation(tAnimation);
	}

	/**
	 * Tries to select the clicked checker. If the previous player hade 3-in-a-row, the checker will be removed instead. Also determines if someone has won.
	 * @param v
	 */
	private void selectChecker(View v) {
		if (removeNextChecker) {
			// If the previous player got 3-in-a-row, remove a choosen checker from the other player			
			if(rules.getTurn() == Constants.BLACK && rules.remove(checkerPositions.get(v), Constants.BLACK)) {
				unMarkAllFields();
				arrayListBlackCheckers.remove(v);
				removeNextChecker = false;
				ViewGroup parent = ((ViewGroup)v.getParent());
				parent.removeView(v);
				playerTurn.setText("Black turn");
				if (rules.isItAWin(Constants.BLACK)) {
					playerTurn.setText("White wins!");
				}
			} 
			else if(rules.getTurn() == Constants.WHITE && rules.remove(checkerPositions.get(v), Constants.WHITE)) {
				unMarkAllFields();
				arrayListWhiteCheckers.remove(v);
				removeNextChecker = false;
				ViewGroup parent = ((ViewGroup)v.getParent());
				parent.removeView(v);
				playerTurn.setText("White turn");
				if (rules.isItAWin(Constants.WHITE)) {
					playerTurn.setText("Black wins!");
				}
			}
		} else if (!(checkerPositions.get(v) != 0 && checkerPositions.containsValue(0)) || (checkerPositions.get(v) == 0)) {
			// Select checker and mark it. Remove marking from other selected checker if there is one
			if (imageViewSelectedChecker != null) {
				imageViewSelectedChecker.setAlpha(1.0f);
			}
			if(imageViewSelectedChecker == v) {
				hasSelectedChecker = false;
				imageViewSelectedChecker = null;
				unMarkAllFields();
				return;
			}
			markAvailableMoveFields(checkerPositions.get(v));
			hasSelectedChecker = true;
			imageViewSelectedChecker = (ImageView) v;
			imageViewSelectedChecker.setAlpha(0.5f);
		}
	}
	
	/**
	 * Mark all avalible moves that can be done.
	 * @param from
	 */
	private void markAvailableMoveFields(int from) {
		for(int i = 0; i < 24; i++) {
			if(rules.isValidMove(from, i+1)) {
				arrayListAreas.get(i).setBackgroundResource(R.drawable.valid_move);
			}
		}
	}
	
	/**
	 * Unmark all fields.
	 */
	private void unMarkAllFields() {
		for(FrameLayout f : arrayListAreas) {
			f.setBackgroundResource(0);
		}
	}
}
