package com.ClientManagerFull;

import java.util.ArrayList;

import com.Clients.Clients;
import com.Clients.Notes;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NotesView extends LinearLayout
{
	private TextView date = null;
	private TextView subject=null ;
	boolean again;
	float scale1;
	public NotesView(Context context, float scale)
	{
		super(context);
		this.setOrientation(LinearLayout.VERTICAL);
		again=true;
		scale1=scale;
		subject = new TextView(context);
		subject.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC);
		subject.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
		subject.setPadding(2, 5, 2, 5);	
		 LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
			this.addView(subject, params);
			 date = new TextView(getContext());
			date.setTypeface(Typeface.SERIF, Typeface.ITALIC);
			date.setTextColor(Color.WHITE);
			date.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
			date.setPadding((int)(10*scale1), 2, 2, 2);
			this.addView(date, params);
	}

	public void setData(Notes note)
	{
		subject.setText("Subject: "+note.darSubject());
		date.setText("Date: "+note.darDate());
	}
}
