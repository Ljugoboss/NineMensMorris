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
import android.widget.LinearLayout;
import android.widget.TextView;



public class MainActivity extends Activity {
	private final String TAG = "Main activity";

	Rules rules = new Rules();

	private TextView playerTurn;
	private ArrayList<ImageView> whiteCheckers;
	private ArrayList<ImageView> blackCheckers;
	private ArrayList<FrameLayout> higBoxAreas;
	private ImageView selectedChecker;
	private FrameLayout areaToMoveTo;
	private ImageView areaToMoveFrom;
	private HashMap<ImageView, Integer> checkerPositions;

	private boolean hasSelectedChecker = false;
	private boolean removeNextChecker = false;
	private boolean isWin = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "Creating activity");
		setContentView(R.layout.activity_main);

		selectedChecker = null;
		areaToMoveTo = null;
		areaToMoveFrom = null;
		checkerPositions = new HashMap<ImageView, Integer>();
		playerTurn = (TextView) findViewById(R.id.TurnText);

		// Initialize all views
		whiteCheckers = new ArrayList<ImageView>();
		whiteCheckers.add((ImageView) findViewById(R.id.whiteChecker1));
		whiteCheckers.add((ImageView) findViewById(R.id.whiteChecker2));
		whiteCheckers.add((ImageView) findViewById(R.id.whiteChecker3));
		whiteCheckers.add((ImageView) findViewById(R.id.whiteChecker4));
		whiteCheckers.add((ImageView) findViewById(R.id.whiteChecker5));
		whiteCheckers.add((ImageView) findViewById(R.id.whiteChecker6));
		whiteCheckers.add((ImageView) findViewById(R.id.whiteChecker7));
		whiteCheckers.add((ImageView) findViewById(R.id.whiteChecker8));
		whiteCheckers.add((ImageView) findViewById(R.id.whiteChecker9));

		blackCheckers = new ArrayList<ImageView>();
		blackCheckers.add((ImageView) findViewById(R.id.blackChecker1));
		blackCheckers.add((ImageView) findViewById(R.id.blackChecker2));
		blackCheckers.add((ImageView) findViewById(R.id.blackChecker3));
		blackCheckers.add((ImageView) findViewById(R.id.blackChecker4));
		blackCheckers.add((ImageView) findViewById(R.id.blackChecker5));
		blackCheckers.add((ImageView) findViewById(R.id.blackChecker6));
		blackCheckers.add((ImageView) findViewById(R.id.blackChecker7));
		blackCheckers.add((ImageView) findViewById(R.id.blackChecker8));
		blackCheckers.add((ImageView) findViewById(R.id.blackChecker9));

		higBoxAreas = new ArrayList<FrameLayout>();
		higBoxAreas.add((FrameLayout) findViewById(R.id.area1));
		higBoxAreas.add((FrameLayout) findViewById(R.id.area2));
		higBoxAreas.add((FrameLayout) findViewById(R.id.area3));
		higBoxAreas.add((FrameLayout) findViewById(R.id.area4));
		higBoxAreas.add((FrameLayout) findViewById(R.id.area5));
		higBoxAreas.add((FrameLayout) findViewById(R.id.area6));
		higBoxAreas.add((FrameLayout) findViewById(R.id.area7));
		higBoxAreas.add((FrameLayout) findViewById(R.id.area8));
		higBoxAreas.add((FrameLayout) findViewById(R.id.area9));
		higBoxAreas.add((FrameLayout) findViewById(R.id.area10));
		higBoxAreas.add((FrameLayout) findViewById(R.id.area11));
		higBoxAreas.add((FrameLayout) findViewById(R.id.area12));
		higBoxAreas.add((FrameLayout) findViewById(R.id.area13));
		higBoxAreas.add((FrameLayout) findViewById(R.id.area14));
		higBoxAreas.add((FrameLayout) findViewById(R.id.area15));
		higBoxAreas.add((FrameLayout) findViewById(R.id.area16));
		higBoxAreas.add((FrameLayout) findViewById(R.id.area17));
		higBoxAreas.add((FrameLayout) findViewById(R.id.area18));
		higBoxAreas.add((FrameLayout) findViewById(R.id.area19));
		higBoxAreas.add((FrameLayout) findViewById(R.id.area20));
		higBoxAreas.add((FrameLayout) findViewById(R.id.area21));
		higBoxAreas.add((FrameLayout) findViewById(R.id.area22));
		higBoxAreas.add((FrameLayout) findViewById(R.id.area23));
		higBoxAreas.add((FrameLayout) findViewById(R.id.area24));

		for (ImageView v : whiteCheckers) {
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

		for (ImageView v : blackCheckers) {
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
		for (FrameLayout v : higBoxAreas) {
			v.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (hasSelectedChecker) {
						int currentTurn = rules.getTurn();
						areaToMoveTo = (FrameLayout) v;

						if (areaToMoveFrom != null) {
							selectedChecker.setAlpha(1.0f);
						}
						int to = Integer.parseInt((String) areaToMoveTo.getContentDescription());
						int from = checkerPositions.get(selectedChecker);
						if (rules.validMove(from, to)) { // This line will change turn
							unMarkAllFields();
							moveChecker(currentTurn);

							checkerPositions.put((ImageView) selectedChecker, Integer.parseInt((String) areaToMoveTo.getContentDescription()));

							removeNextChecker = rules.canRemove(to);
							selectedChecker.setAlpha(1.0f);
							hasSelectedChecker = false;
							selectedChecker = null;
							
							if (removeNextChecker) {
								if (currentTurn == Constants.BLACK) {
									playerTurn.setText("Remove White");
								} else {
									playerTurn.setText("Remove Black");
								}
							} else {
								if (currentTurn == Constants.BLACK) {
									playerTurn.setText("White turn");
								} else {
									playerTurn.setText("Black turn");
								}
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
	private void moveChecker(int turn) {
		// Create two temporary final variables so that the onAnimationEnd method uses the right values
		ImageView animChecker = selectedChecker;
		final int[] locationChecker = {0, 0};
		final int[] locationArea = {0, 0};
		selectedChecker.getLocationOnScreen(locationChecker);
		areaToMoveTo.getLocationOnScreen(locationArea);
		Log.i(TAG, "move from x: " + locationChecker[0] + " y: " + locationChecker[1]);
		Log.i(TAG, "move to x: " + locationArea[0] + " y: " + locationArea[1]);

		ViewGroup parent = ((ViewGroup)selectedChecker.getParent());

		if(turn == Constants.WHITE) {
			animChecker = (ImageView) getLayoutInflater().inflate(R.layout.anim_white_checker, parent, false);
		} else {
			animChecker = (ImageView) getLayoutInflater().inflate(R.layout.anim_black_checker, parent, false);
		}

		//Move to the other layout
		if (parent != findViewById(R.id.board)) {
			//FrameLayout placeholder = (FrameLayout) getLayoutInflater().inflate(R.layout.layout_placeholder, parent, false);
			int index = parent.indexOfChild(selectedChecker);
			parent.removeView(selectedChecker);
			//parent.addView(placeholder, index);

			parent.addView(animChecker, index);
			//Move the checker and make it invisible
			((ViewGroup) findViewById(R.id.board)).addView(selectedChecker);
		} else {
			parent.addView(animChecker);
			animChecker.setLayoutParams(selectedChecker.getLayoutParams());
		}

		selectedChecker.setLayoutParams(areaToMoveTo.getLayoutParams());
		selectedChecker.setVisibility(View.INVISIBLE);

		final ImageView tmpAnimChecker = animChecker;
		final ImageView tmpSelectedChecker = selectedChecker;

		//Prepare animation with x and y movement
		TranslateAnimation tAnimation = new TranslateAnimation(0, locationArea[0] - locationChecker[0], 0, locationArea[1] - locationChecker[1]);
		tAnimation.setFillEnabled(true);
		tAnimation.setFillAfter(true);
		tAnimation.setDuration(1500);

		tAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationEnd(Animation animation) {
				ViewGroup parent = ((ViewGroup)tmpAnimChecker.getParent());
				if (tmpAnimChecker.getParent() != findViewById(R.id.board)) {
					FrameLayout placeholder = (FrameLayout) getLayoutInflater().inflate(R.layout.layout_placeholder, parent, false);
					int index = parent.indexOfChild(tmpAnimChecker);
					parent.addView(placeholder, index);		
				}
				parent.removeView(tmpAnimChecker);
				tmpSelectedChecker.setVisibility(View.VISIBLE);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationStart(Animation animation) {
			}
		});

		tmpAnimChecker.startAnimation(tAnimation);
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
				blackCheckers.remove(v);
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
				whiteCheckers.remove(v);
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
			if (selectedChecker != null) {
				selectedChecker.setAlpha(1.0f);
			}
			if(selectedChecker == v) {
				hasSelectedChecker = false;
				selectedChecker = null;
				unMarkAllFields();
				return;
			}
			markAvailableMoveFields(checkerPositions.get(v));
			hasSelectedChecker = true;
			selectedChecker = (ImageView) v;
			selectedChecker.setAlpha(0.5f);
		}
	}

	/**
	 * Mark all avalible moves that can be done.
	 * @param from
	 */
	private void markAvailableMoveFields(int from) {
		for(int i = 0; i < 24; i++) {
			if(rules.isValidMove(from, i+1)) {
				higBoxAreas.get(i).setBackgroundResource(R.drawable.valid_move);
			}
		}
	}

	/**
	 * Unmark all fields.
	 */
	private void unMarkAllFields() {
		for(FrameLayout f : higBoxAreas) {
			f.setBackgroundResource(0);
		}
	}
}
