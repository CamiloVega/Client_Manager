package com.ClientManagerFull;


import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
public class AppointmentList extends ListActivity {
	 protected static final int REQST_CODE = 0;
	 String nombre;
	 String fecha;
	 String horas;
	 AlertDialog alert;
	 AlertDialog pick;
	 AlertDialog alert3;
	 int poss;
	 int del;
	 LayoutAnimationController controller;
	 AppointmentAdapter adapter = null;
	 AlertDialog alertDialog;
	 int tot;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	refresh();
    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
		  builder.setMessage("Are you sure you want to delete this appointment?")
		         .setCancelable(false)
		         .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		             public void onClick(DialogInterface dialog, int id) {
		            	 ((App)getApplication()).darClientManager().darAppointmentIndex(poss).deleteAppointmentIndex(del);
		            	 if (((App)getApplication()).darClientManager().darAppointmentIndex(poss).darNumberOfAppointments()==0){
		            		 ((App)getApplication()).darClientManager().deleteAppointmentIndex(poss);
		            	 }
		            	 ((App)getApplication()).save();
			   				refresh();
}
		         })
		         .setNegativeButton("No", new DialogInterface.OnClickListener() {
		             public void onClick(DialogInterface dialog, int id) {
		                  dialog.cancel();
		             }
		         });
		 
		  alert = builder.create();
		  alertDialog = new AlertDialog.Builder(this).create();
	       alertDialog.setTitle("First Time");
	       alertDialog.setMessage("To Add an appointment, long click the client's name and Add an Appointment");
	       alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
	          public void onClick(DialogInterface dialog, int which) { 
	        	  alertDialog.dismiss();
	          }
	       });
		  alert = builder.create();
		  if (((App)getApplication()).darClientManager().darAppointments().size()==0){
			  alertDialog.show();
		  }
		  controller = AnimationUtils.loadLayoutAnimation( this, R.anim.appointment_animation_controller);

		  
    }
    public void click(int position){
    	
     	 poss=position;	 
     	 final CharSequence[] items = darAppointments();

		  AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
		  builder1.setTitle("Pick an Appointment");
		  builder1.setItems(items, new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog, int item) {
		          del=item;
		          alert.show();
		      }
		  });
		   alert3 = builder1.create();
     	alert3.show();
    		
    	
    }
    public void onResume(){
    	super.onResume();
        refresh();
    	
    }
    
    public void refresh(){
    	((App)getApplication()).darClientManager().deletePastAppointments();
    	int cuantos= ((App)getApplication()).darClientManager().darAppointments().size();
        adapter = new AppointmentAdapter(this); 
        setListAdapter(adapter);
       ListView lv = getListView();
       lv.setCacheColorHint(0);

       lv.setOnItemClickListener(new OnItemClickListener() {
           public void onItemClick(AdapterView<?> parent, View view,
               int position, long id) {
          	 click( position);
           }
         });
       lv.setLayoutAnimation(controller);
       //lv.setTextFilterEnabled(true);
       registerForContextMenu(lv);
       for (int i=0;i<cuantos;i++){
       		adapter.addUpcoming(((App)getApplication()).darClientManager().darAppointmentIndex(i));
       }

    }
    public String [] darAppointments(){
    	int all=((App)getApplication()).darClientManager().darAppointmentIndex(poss).darNumberOfAppointments();
    	String [] appointments= new String[all];
    	for (int i=0;i<all;i++){
    		appointments[i]=((App)getApplication()).darClientManager().darAppointmentIndex(poss).darAppointmentsIndex(i).darNombre() + "   At:"+((App)getApplication()).darClientManager().darAppointmentIndex(poss).darAppointmentsIndex(i).darHoraStart();
    	}
    	return appointments;
    }

}
