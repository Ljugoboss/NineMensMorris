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
	private ArrayList<ImageView> whiteCheckers;
	private ArrayList<ImageView> blackCheckers;
	private ArrayList<FrameLayout> higBoxAreas;
	private ImageView selectedChecker;
	private FrameLayout areaToMoveTo;
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
		checkerPositions = new HashMap<ImageView, Integer>();
		playerTurn = (TextView) findViewById(R.id.TurnText);

		//Save all white checkers in a list
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

		//Save all black checkers in a list
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

		//Save all areas the checkers can move to in a list
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

		//Add a onClickListener to the white checkers
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

		//Add a onClickListener to the black checkers
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

		//add a clickListnerer to all the hit box areas
		for (FrameLayout v : higBoxAreas) {
			v.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					//If we have a selected checker, try to move it
					if (hasSelectedChecker) {
						int currentTurn = rules.getTurn();
						areaToMoveTo = (FrameLayout) v;

						
						//What areas are we moving from and to?
						int to = Integer.parseInt((String) areaToMoveTo.getContentDescription());
						int from = checkerPositions.get(selectedChecker);
						//Try to move the checker
						if (rules.validMove(from, to)) { // This line will change turn
							//Update the UI
							unMarkAllFields();
							moveChecker(currentTurn);

							checkerPositions.put((ImageView) selectedChecker, Integer.parseInt((String) areaToMoveTo.getContentDescription()));

							//Did the row create a row of 3?
							removeNextChecker = rules.canRemove(to);
							selectedChecker.setAlpha(1.0f);
							hasSelectedChecker = false;
							selectedChecker = null;
							
							//Update the turn text
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
		//Start a new game
		case R.id.item1:
			finish();
			startActivity(getIntent());
			return true;           
		default:
			return super.onOptionsItemSelected(item);
		} 
	} 

	/**
	 * Move the checker from the current position to a new position
	 * @param turn Constant.WHITE or Constant.BLACK according to whos turn it is
	 */
	private void moveChecker(int turn) {

		ImageView animChecker = null;
		//Get the position of the checker that will move and the area it will move to
		final int[] locationChecker = {0, 0};
		final int[] locationArea = {0, 0};
		selectedChecker.getLocationOnScreen(locationChecker);
		areaToMoveTo.getLocationOnScreen(locationArea);
		Log.i(TAG, "move from x: " + locationChecker[0] + " y: " + locationChecker[1]);
		Log.i(TAG, "move to x: " + locationArea[0] + " y: " + locationArea[1]);

		ViewGroup parent = ((ViewGroup)selectedChecker.getParent());

		//create a ghost checker which will be animated while the real on just moves.
		if(turn == Constants.WHITE) {
			animChecker = (ImageView) getLayoutInflater().inflate(R.layout.anim_white_checker, parent, false);
		} else {
			animChecker = (ImageView) getLayoutInflater().inflate(R.layout.anim_black_checker, parent, false);
		}

		//If the checker is in the side board, we need to update the side board as well
		if (parent != findViewById(R.id.board)) {
			//Remove the real checker and add the ghost where the real one was
			int index = parent.indexOfChild(selectedChecker);
			parent.removeView(selectedChecker);
			parent.addView(animChecker, index);
			//Move the real one to the side board
			((ViewGroup) findViewById(R.id.board)).addView(selectedChecker);
		} else {
			//Add the ghost checker at the real ones position
			parent.addView(animChecker);
			animChecker.setLayoutParams(selectedChecker.getLayoutParams());
		}

		//Make the real checker invisible and move it
		selectedChecker.setLayoutParams(areaToMoveTo.getLayoutParams());
		selectedChecker.setVisibility(View.INVISIBLE);

		//final copies to be used in the animation thread
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
				//Fix the side board so its children stays in position
				if (tmpAnimChecker.getParent() != findViewById(R.id.board)) {
					FrameLayout placeholder = (FrameLayout) getLayoutInflater().inflate(R.layout.layout_placeholder, parent, false);
					int index = parent.indexOfChild(tmpAnimChecker);
					parent.addView(placeholder, index);		
				}
				//remove the ghost and make the real one visible
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
	 * Lets the player select a checker to remove or move
	 * @param v The checker which was clicked on.
	 */
	private void selectChecker(View v) {
		//Is it a reamove click=
		if (removeNextChecker) {
			//Is it a valid remove click?
			if(rules.getTurn() == Constants.BLACK && rules.remove(checkerPositions.get(v), Constants.BLACK)) {
				//Unamrk all options and remove the selected checker
				unMarkAllFields();
				blackCheckers.remove(v);
				removeNextChecker = false;
				ViewGroup parent = ((ViewGroup)v.getParent());
				parent.removeView(v);
				playerTurn.setText("Black turn");
				
				//Did someone win?
				if (rules.isItAWin(Constants.BLACK)) {
					playerTurn.setText("White wins!");
				}
			} 
			else if(rules.getTurn() == Constants.WHITE && rules.remove(checkerPositions.get(v), Constants.WHITE)) {
				//Unmark all options and remove the selected checker
				unMarkAllFields();
				whiteCheckers.remove(v);
				removeNextChecker = false;
				ViewGroup parent = ((ViewGroup)v.getParent());
				parent.removeView(v);
				playerTurn.setText("White turn");
				
				//Did someone win?
				if (rules.isItAWin(Constants.WHITE)) {
					playerTurn.setText("Black wins!");
				}
			}
		} 
		//Try to select the checker for a move
		else if (!(checkerPositions.get(v) != 0 && checkerPositions.containsValue(0)) || (checkerPositions.get(v) == 0)) {
			//If a checker is already selected, unselect it
			if (selectedChecker != null) {
				selectedChecker.setAlpha(1.0f);
			}
			//If its the selected checker which is clicked, no checker is selected.
			if(selectedChecker == v) {
				hasSelectedChecker = false;
				selectedChecker = null;
				unMarkAllFields();
				return;
			}
			//Select a checker and mark available moves.
			markAvailableMoveFields(checkerPositions.get(v));
			hasSelectedChecker = true;
			selectedChecker = (ImageView) v;
			selectedChecker.setAlpha(0.5f);
		}
	}

	/**
	 * Mark all available moves that can be done.
	 * @param from The position of the checker which wants to move
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
