package com.ClientManagerFull;

import com.Clients.Clients;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ClientListView extends LinearLayout
{
	private TextView name = null;
	private TextView reference = null;
	private TextView phone = null;
	float scale1;
	boolean done;
	public ClientListView(Context context, float scale)
	{
		super(context);
		this.setOrientation(LinearLayout.VERTICAL);
		scale1=scale;
		done=false;
		name = new TextView(context);
		name.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC);
		name.setTextColor(Color.WHITE);
		name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
		name.setPadding(2, 5, 2, 5);
		
		 phone = new TextView(getContext());
			phone.setTypeface(Typeface.MONOSPACE, Typeface.NORMAL);
			phone.setTextColor(Color.WHITE);
			phone.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
			phone.setPadding((int)(10*scale1), 2, 2, 2);
		
		reference = new TextView(context);
		reference.setTypeface(Typeface.SERIF, Typeface.ITALIC);
		reference.setTextColor(Color.WHITE);
		reference.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
		reference.setPadding((int)(10*scale), 2, 2, 2);
		
		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		this.addView(name, params);
		this.addView(reference, params);
		this.addView(phone, params);
	}

	public void setData(Clients address)
	{
		
		name.setText(address.darNombre() );
		if (address.darType()){
			name.setTextColor(Color.RED);
		}
		else{
			name.setTextColor(Color.GREEN);
		}
		reference.setText("Reference: " + address.darReference());
		if (!address.darNumero().equals("")){
				if (!address.darEmail().equals(""))
					phone.setText("Phone: " + address.darNumero() +"\n"+"Email: "+ address.darEmail());
				else {phone.setText("Phone: " + address.darNumero());}
		}
		else phone.setText(" ");
	}
}
