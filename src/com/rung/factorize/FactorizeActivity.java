package com.rung.factorize;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class FactorizeActivity extends Activity {
	private Button factorizeButton;
	private EditText numText;
	private TextView resultLabel;
	private ProgressBar progressBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		findViews();

		factorizeButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				final long myLong;
				factorizeButton.setVisibility(Button.INVISIBLE);
				progressBar.setVisibility(ProgressBar.VISIBLE);
				progressBar.setProgress(0);
				progressBar.setMax(100);
				
				myLong = getInput();
				
				new Thread() {
					@Override
					public void run() {
						doFactorize(myLong);
					}
				}.start();
			}
		});
	}

	/**
	 * Validates and returns the users input (in the numText EditText)
	 * 
	 * @return the number to factorize
	 */
	private long getInput() {
		long tempMyLong;

		if (numText.getText().toString().isEmpty()) {
			Toast.makeText(FactorizeActivity.this, "Please enter a number.", Toast.LENGTH_SHORT).show();
			return 0;
		}

		tempMyLong = Long.valueOf(numText.getText().toString());

		if (tempMyLong < 2) {
			Toast.makeText(FactorizeActivity.this, "Please enter a number greater than 1.", Toast.LENGTH_SHORT).show();
			return 0;
		}

		return tempMyLong;
	}

	private void doFactorize(long b) {
		Log.d("$$$$$$", "Entering doFactorize with b = " + String.valueOf(b));

		long myNumberSoFar = b;
		int myArrayLimit = (int) Math.min(1000000, myNumberSoFar);
		// indicates factors to skip. false means skip
		boolean myArray[] = new boolean[myArrayLimit];
		double mySquareRoot = Math.sqrt((double) myNumberSoFar);

		for (int qq = 0; qq < myArrayLimit; qq++) {
			myArray[qq] = true;
		}

		String myString = "";

		for (long x = 2; (double) x <= mySquareRoot; x++) {
			Log.d("$$$$$$", "Entering for loop with x = " + String.valueOf(x));

			while ((int) x < myArrayLimit && myArray[(int) x] == false) {
				x++;
			}

			Log.d("$$$$$$", "  x bumped up to " + String.valueOf(x));
			// Check whether x divides b
			Log.d("$$$$$$", "x = " + String.valueOf(x));
			Log.d("$$$$$$", "myNumberSoFar = " + String.valueOf(myNumberSoFar));

			if (myNumberSoFar % x == 0) {
				Log.d("$$$$$$", "Factor found with x = " + String.valueOf(x) + ", myNumberSoFar = " + String.valueOf(myNumberSoFar));
				myString += Long.toString(x) + " * ";
				Log.d("$$$$$$", "myString changed to " + myString);
				myNumberSoFar = myNumberSoFar / x;
				mySquareRoot = Math.sqrt((double) myNumberSoFar);
				Log.d("$$$$$$", "myNumberSoFar changed to " + String.valueOf(myNumberSoFar) + " and x set to 1");
				x = 1;

				Message msg = resultLabelHandler.obtainMessage();
				msg.obj = myString + "(" + String.valueOf(myNumberSoFar) + ")...";
				resultLabelHandler.sendMessage(msg);
			} else {
				Log.d("$$$$$$", "Factor not found with x = " + String.valueOf(x) + ", myNumberSoFar = " + String.valueOf(myNumberSoFar));
				for (int qq = (int) x; qq + x < myArrayLimit; qq += x) {
					myArray[qq] = false;
				}
			}

			Message progMsg = progressBarHandler.obtainMessage((int) ((x / mySquareRoot) * 100));
			Log.d("$$$$$$", "\tProgressbar value: " + (int) ((x / mySquareRoot) * 100));
			progressBarHandler.sendMessage(progMsg);
		}
		Log.d("$$$$$$", "Exited for loop");

		if (myNumberSoFar == 1) {
			myString = myString.substring(0, myString.length() - 2);
		} else {
			myString += Long.toString(myNumberSoFar);
		}

		Message msg = resultLabelHandler.obtainMessage();
		msg.obj = myString;
		resultLabelHandler.sendMessage(msg);
		finishHandler.sendEmptyMessage(0);
	}

	/**
	 * Ties the UI to the instance variables.
	 */
	private void findViews() {
		factorizeButton = (Button) findViewById(R.id.factorizeButton);
		numText = (EditText) findViewById(R.id.numText);
		resultLabel = (TextView) findViewById(R.id.resultLabel);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
	}

	private Handler resultLabelHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			resultLabel.setText(msg.obj.toString());
		}
	};

	private Handler progressBarHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			progressBar.setProgress(msg.what);
		}
	};

	private Handler finishHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			factorizeButton.setVisibility(Button.VISIBLE);
			progressBar.setVisibility(ProgressBar.INVISIBLE);
		}
	};
}
