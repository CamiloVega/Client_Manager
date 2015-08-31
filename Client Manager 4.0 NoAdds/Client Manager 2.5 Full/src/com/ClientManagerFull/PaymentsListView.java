package com.ClientManagerFull;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import com.Clients.Clients;
import com.Clients.Fechas;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PaymentsListView extends LinearLayout
{
	private TextView date = null;
	private TextView service = null;
	private TextView separator = null;
	
	float scale1;
	

	public PaymentsListView(Context context, float scale) {
		super(context);
		this.setOrientation(LinearLayout.VERTICAL);
		 LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		scale1=scale;
		date = new TextView(context);
		date.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC);
		date.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
		date.setPadding(2, 5, 2, 5);	
			this.addView(date, params);
			 service = new TextView(getContext());
			service.setTypeface(Typeface.SERIF, Typeface.ITALIC);
			service.setTextColor(Color.WHITE);
			service.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
			service.setPadding((int)(10*scale1), 2, 2, 2);
			this.addView(service, params);
			separator = new TextView(getContext());
			separator.setTypeface(Typeface.SERIF, Typeface.ITALIC);
			separator.setTextColor(Color.DKGRAY);
			separator.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
			separator.setPadding((int)(10*scale1), 2, 2, 2);
			this.addView(separator, params);

	}

	public void setData(Fechas fecha)
	{
		date.setText(fecha.darFecha());
		if (!fecha.darPagoRealizado()){
			date.setTextColor(Color.RED);
		}
		else{
			date.setTextColor(Color.GREEN);
		}
		NumberFormat df = new DecimalFormat("#0.00");
		service.setText("   For: "+fecha.darService()+"     Payment: $"+df.format(fecha.darPago()));
		separator.setText("-------------------------------------------------------");
	
		
		
	}
}
