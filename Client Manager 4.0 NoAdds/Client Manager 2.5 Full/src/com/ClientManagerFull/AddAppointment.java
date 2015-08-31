package com.ClientManagerFull;



import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.ClientManagerFull.R;
import com.ClientManagerFull.AddClient.MyOnItemSelectedListener;
public class AddAppointment extends Activity implements OnClickListener {
	Button boton;
	Button botonRec;
	TextView client;
	TextView reference;
	TextView times;
	TextView days;
	Spinner selection;

	DatePicker datePicker;
	TimePicker timePicker;
	  AlertDialog alertDialog;

	int mDay;
	int mMonth;
	int mYear;
	String fech;
	String hora;
	EditText horas;
	int veces;
	int cadaCuanto;
	int tipo;
	int sel;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_appointment);
        veces=1;
        cadaCuanto=0;
        tipo=Calendar.DAY_OF_YEAR;
	       boton= (Button)findViewById(R.id.add_appointment_button);
	       boton.setOnClickListener(this);
	       botonRec= (Button)findViewById(R.id.add_appointment_recurrence);
	       botonRec.setOnClickListener(this);
	       horas = (EditText) findViewById(R.id.add_appointment_hours);
	       client = (TextView) findViewById(R.id.add_appointment_name);
	       //precio = (TextView) findViewById(R.id.TInfPrecio);
	       reference = (TextView) findViewById(R.id.add_appointment_reference);
	         
	       datePicker=(DatePicker) findViewById(R.id.add_appointment_date);
	       timePicker=(TimePicker) findViewById(R.id.add_appointment_time);	       
	       sel=((App)getApplication()).darSelected();
	       client.setText(((App)getApplication()).darClientManager().darClientIndex(sel).darNombre());
	       reference.setText(((App)getApplication()).darClientManager().darClientIndex(sel).darReference());
	       createDialog();
	       
	      
    }
	public void onClick(View arg0) {
		switch(arg0.getId())
		  {
		  case R.id.add_appointment_button:
			  timePicker.getCurrentHour();
			  Calendar cal = new GregorianCalendar();
			  cal.set(datePicker.getYear(),(datePicker.getMonth()+1), datePicker.getDayOfMonth());
			 
			  String min=timePicker.getCurrentMinute().toString().trim();
			  if(min.length()==1){
				  min="0"+min;
			  }
			  String hour=timePicker.getCurrentHour().toString().trim();
			  if(hour.length()==1){
				  hour="0"+hour;
			  }
			  hora=hour+":"+min;
			  
			  int nHoras=0;
			  if (!horas.getText().toString().trim().equalsIgnoreCase("")){
				  nHoras=Integer.parseInt(horas.getText().toString().trim());
			  }
			 
			  
		   //Do something
			  for (int i=0;i<veces;i++){
				  String day=cal.get(Calendar.DAY_OF_MONTH)+"";
				  String month=(cal.get(Calendar.MONTH))+"";
				  String year=cal.get(Calendar.YEAR)+"";
				  if(day.trim().length()==1){
					  day="0"+day;
				  }
				  if(month.trim().length()==1){
					  month="0"+month;
				  }
				  fech=""+month+"/"+day+"/"+year;
				  ((App)getApplication()).darClientManager().addAppointment(client.getText().toString(), reference.getText().toString(), fech, hora, nHoras);
				  cal.roll(tipo, cadaCuanto);
			  }
			  
			  ((App)getApplication()).save();
			  Toast.makeText(getApplicationContext(), "Appointment Successfully Added for "+fech+" with "+client.getText().toString(),Toast.LENGTH_SHORT).show();
				finish();
			  break;
		  case R.id.add_appointment_recurrence:
			 
			  alertDialog.show();
			  break;
		  }
		
			//this.finish();
		// TODO Auto-generated method stub	
	}

	public void createDialog(){
		 AlertDialog.Builder builder;

		  
		  LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
		  final View layout = inflater.inflate(R.layout.recurrence_dialog,
		                                 (ViewGroup) findViewById(R.id.recurrencedialog_layout));

		  EditText text = (EditText) layout.findViewById(R.id.recurrencedialog_times);
		 		  
		  final Context context=this.getApplicationContext();
		  builder = new AlertDialog.Builder(this);
		  String[] items = new String[] {"Days", "Weeks", "Months"};
     	 Spinner spinner1 = (Spinner) layout.findViewById(R.id.recurrencedialog_spinner);
     	 ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
     	             android.R.layout.simple_spinner_item, items);
     	 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     	 spinner1.setAdapter(adapter);
     	spinner1.setOnItemSelectedListener(new MyOnItemSelectedListener());
		  builder.setView(layout);
		  builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	             public void onClick(DialogInterface dialog, int id) {
	            /*selection = (Spinner) layout.findViewById(R.id.spinner);
	      	       ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	      	               context, R.array.type, android.R.layout.simple_spinner_item);
	      	       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	      	       selection.setAdapter(adapter);
	      	       selection.setOnItemSelectedListener(new MyOnItemSelectedListener());*/
	            	 
	           times = (EditText) layout.findViewById(R.id.recurrencedialog_times);
	      	   days = (EditText) layout.findViewById(R.id.recurrencedialog_days);	 
	      	 if (times.getText().toString().trim().equalsIgnoreCase("")){
				 times.setText("0");
			 }
	      	if (days.getText().toString().trim().equalsIgnoreCase("")){
				 days.setText("0");
			 }
	           veces=Integer.parseInt(times.getText()+"")+1;
	           cadaCuanto=Integer.parseInt(days.getText()+"");
	           String tipo1="";
	           if (tipo==Calendar.DAY_OF_YEAR) tipo1="days";
	           else if (tipo==Calendar.WEEK_OF_YEAR) tipo1="weeks";
	           else tipo1="months";
	           Toast.makeText(getApplicationContext(),"Recurrence: "+veces+" times, every "+cadaCuanto+" "+tipo1 ,Toast.LENGTH_SHORT).show();
	             }
	         })
	         .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	             public void onClick(DialogInterface dialog, int id) {
	                  dialog.cancel();
	             }
	         });	
		  alertDialog = builder.create();
	}
	public class MyOnItemSelectedListener implements OnItemSelectedListener {
		 
	    public void onItemSelected(AdapterView<?> parent,
	        View view, int pos, long id) {
	    	/*Dado que se esta realizando un update y inicialmente no se contaba kon la variable
	    	 * se da por hecho que los clientes iniciales en la version 2.8 eran clientes, por lo tanto 
	    	 * se deja como valor de defecto type=false para clientes y type=true para providers
	    	 */
	      if (pos==0){
	    	  tipo=Calendar.DAY_OF_YEAR;

	      }
	      else if (pos==1) {
	    	  tipo=Calendar.WEEK_OF_YEAR;
	    	  
	      }
	      else  {
	    	  tipo=Calendar.MONTH;
	    	 
	      }
	    }

	    public void onNothingSelected(AdapterView parent) {
	      // Do nothing.
	    }

		
	}
}
