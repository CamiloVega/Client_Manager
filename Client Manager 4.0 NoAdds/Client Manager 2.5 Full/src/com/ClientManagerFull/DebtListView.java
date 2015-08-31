package com.ClientManagerFull;

import java.util.ArrayList;

import com.Clients.Clients;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DebtListView extends LinearLayout
{
	private TextView name = null;
	private ArrayList <TextView> arrayDates ;
	private ArrayList <TextView> arrayService ;
	private ArrayList <TextView> arraySeparator ;

	boolean again;
	float scale1;
	public DebtListView(Context context, float scale, int cuantos)
	{
		super(context);
		this.setOrientation(LinearLayout.VERTICAL);
		again=true;
		scale1=scale;
		name = new TextView(context);
		name.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC);
		name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
		name.setPadding(2, 5, 2, 5);	
		 arrayService=new ArrayList<TextView>();
		 arrayDates=new ArrayList<TextView>();
		 arraySeparator=new ArrayList<TextView>();
		 LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
			this.addView(name, params);
		for (int i=0;i<cuantos;i++){ 
			TextView fecha = new TextView(getContext());
			fecha.setTypeface(Typeface.SERIF, Typeface.ITALIC);
			fecha.setTextColor(Color.WHITE);
			fecha.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
			fecha.setPadding((int)(10*scale1), 2, 2, 2);
			arrayDates.add(fecha);
			this.addView(arrayDates.get(i), params);
			TextView service = new TextView(getContext());
			service.setTypeface(Typeface.SERIF, Typeface.ITALIC);
			service.setTextColor(Color.WHITE);
			service.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
			service.setPadding((int)(10*scale1), 2, 2, 2);
			arrayService.add(service);
			this.addView(arrayService.get(i), params);
			TextView separator = new TextView(getContext());
			separator.setTypeface(Typeface.SERIF, Typeface.ITALIC);
			separator.setTextColor(Color.DKGRAY);
			separator.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
			separator.setPadding((int)(10*scale1), 2, 2, 2);
			arraySeparator.add(separator);
			this.addView(arraySeparator.get(i), params);

		}
	}

	public void setData(Clients clients)
	{
		name.setText(clients.darNombre() );
		if (clients.darType()){
			name.setTextColor(Color.RED);
		}
		else{
			name.setTextColor(Color.GREEN);
		}
		if (again){ 
			for (int i=0;i<clients.darNumberOfDebts();i++){
				arrayDates.get(i).setText("   Date: "+clients.darDeudaIndex(i).darFecha());
				arrayService.get(i).setText("   For: "+clients.darDeudaIndex(i).darService()+"     Amount Due: $"+clients.darDeudaIndex(i).darPago()+"0");
				arraySeparator.get(i).setText("-------------------------------------------------------");
	
			}
			again=false;
		}
	}
}
