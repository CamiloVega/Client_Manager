package com.ClientManagerFull;

import java.util.ArrayList;

import com.Clients.Appointments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AppointmentView extends LinearLayout
{
	private TextView fecha = null;
	private ArrayList <TextView> arrayNames ;
	private ArrayList <TextView> arrayTime ;
	private ArrayList <TextView> arraySeparator ;

	boolean again;
	float scale1;
	public AppointmentView(Context context, float scale, int cuantos)
	{
		super(context);
		this.setOrientation(LinearLayout.VERTICAL);
		again=true;
		scale1=scale;
		fecha = new TextView(context);
		fecha.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC);
		fecha.setTextColor(Color.WHITE);
		fecha.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
		fecha.setPadding(2, 5, 2, 5);
		fecha.setGravity(1);
		arrayNames=new ArrayList<TextView>();
		 arrayTime=new ArrayList<TextView>();
		 arraySeparator=new ArrayList<TextView>();
		 LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
			this.addView(fecha, params);
		for (int i=0;i<cuantos;i++){ 
			TextView name = new TextView(getContext());
			name.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
			name.setTextColor(Color.WHITE);
			name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
			name.setPadding((int)(10*scale1), 2, 2, 2);
			arrayNames.add(name);
			this.addView(arrayNames.get(i), params);
			TextView time = new TextView(getContext());
			time.setTypeface(Typeface.SERIF, Typeface.ITALIC);
			time.setTextColor(Color.WHITE);
			time.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
			time.setPadding((int)(10*scale1), 2, 2, 2);
			arrayTime.add(time);
			this.addView(arrayTime.get(i), params);
			TextView separator = new TextView(getContext());
			separator.setTypeface(Typeface.SERIF, Typeface.ITALIC);
			separator.setTextColor(Color.DKGRAY);
			separator.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
			separator.setPadding((int)(10*scale1), 2, 2, 2);
			arraySeparator.add(separator);
			this.addView(arraySeparator.get(i), params);

		}
		
		

	}

	public void setData(Appointments appointment)
	{
		fecha.setText(appointment.darDia()+"  "+appointment.darFecha() );
		if (again){ 
			for (int i=0;i<appointment.darNumberOfAppointments();i++){
				
				arrayNames.get(i).setText(appointment.darAppointmentsIndex(i).darNombre());
				boolean color=appointment.darAppointmentsIndex(i).darType();
				if(color){
					arrayNames.get(i).setTextColor(Color.RED);	
				}
				else{
					arrayNames.get(i).setTextColor(Color.GREEN);
				}
				
				arrayTime.get(i).setText("      From: "+appointment.darAppointmentsIndex(i).darHoraStart()+"  To: "+appointment.darAppointmentsIndex(i).darHoraEnd());
				arraySeparator.get(i).setText("-------------------------------------------------------");
	
			}
			again=false;
		}
	}
}
