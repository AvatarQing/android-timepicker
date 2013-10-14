package net.simonvt.timepicker.samples;

import java.util.Calendar;

import net.simonvt.timepicker.TimePicker;
import net.simonvt.timepicker.TimePickerDialog;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * @author Simon Vig Therkildsen <simonvt@gmail.com>
 */
public class DialogSample extends Activity {

	private TextView mTimeDisplay;

	private int mHour;
	private int mMinute;
	private int mSecond;

	static final int TIME_DIALOG_ID = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog);

		final Calendar c = Calendar.getInstance();
		mHour = c.get(Calendar.HOUR_OF_DAY);
		mMinute = c.get(Calendar.MINUTE);
		mSecond = c.get(Calendar.SECOND);

		mTimeDisplay = (TextView) findViewById(R.id.timeDisplay);

		findViewById(R.id.btnDialog).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View v) {
						showDialog(TIME_DIALOG_ID);
					}
				});

		updateDisplay();
	}

	// updates the time we display in the TextView
	private void updateDisplay() {
		mTimeDisplay.setText(new StringBuilder().append(pad(mHour)).append(":")
				.append(pad(mMinute)).append(":").append(pad(mSecond)));
	}

	private static String pad(int c) {
		if (c >= 10) {
			return String.valueOf(c);
		} else {
			return "0" + String.valueOf(c);
		}
	}

	// the callback received when the user "sets" the time in the dialog
	private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute,
				int second) {
			mHour = hourOfDay;
			mMinute = minute;
			mSecond = second;
			updateDisplay();
		}
	};

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case TIME_DIALOG_ID:
			return new TimePickerDialog(this, mTimeSetListener, mHour, mMinute,
					mSecond,
					false);
		}
		return null;
	}
}
