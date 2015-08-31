package com.ClientManagerFull;


import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
public class DebtList extends ListActivity {
	 protected static final int REQST_CODE = 0;
	 String nombre;
	 String horas;
	 AlertDialog alert;
	 AlertDialog alert3;
	 LayoutAnimationController controller;
	 PaymentListAdapter adapter = null;
	 int poss;
	 int fecha;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	refresh();
       AlertDialog.Builder builder = new AlertDialog.Builder(this);
		  builder.setMessage("Was this debt paid?")
		         .setCancelable(false)
		         .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		             public void onClick(DialogInterface dialog, int id) {
		  
		            	 ((App)getApplication()).darClientManager().darClientByName(adapter.getInfo(poss).darNombre()).darDeudaIndex(fecha).setPago(true);
		               	 ((App)getApplication()).darClientManager().darClientByName(adapter.getInfo(poss).darNombre()).deleteDebt(fecha);
		               	((App)getApplication()).save(); 
		            finish();
		             }
		         })
		         .setNegativeButton("No", new DialogInterface.OnClickListener() {
		             public void onClick(DialogInterface dialog, int id) {
		                  dialog.cancel();
		             }
		         });
		  alert = builder.create();
		  controller = AnimationUtils.loadLayoutAnimation( this, R.anim.list_animation_controller);

      
    }
    public void click( int position){
    	
    	 poss=position;	 
     	 final CharSequence[] items = darDeudas();

		  AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
		  builder1.setTitle("Pick a Debt");
		  builder1.setItems(items, new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog, int item) {
		          fecha=item;
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

    	int cuantos= ((App)getApplication()).darClientManager().darNumberOfClients();
        adapter = new PaymentListAdapter(this);
        
        setListAdapter(adapter);
       ListView lv = getListView();
       lv.setCacheColorHint(0);
       lv.setBackgroundResource(R.drawable.background2);

       //lv.setTextFilterEnabled(true);
       lv.setOnItemClickListener(new OnItemClickListener() {
           public void onItemClick(AdapterView<?> parent, View view,
               int position, long id) {
          	 click( position);
           }
         });
       lv.setLayoutAnimation(controller);

       registerForContextMenu(lv);
       for (int i=0;i<cuantos;i++){
    	   if (((App)getApplication()).darClientManager().darClientIndex(i).darNumberOfDebts()>0){
       	adapter.addDebt(((App)getApplication()).darClientManager().darClientIndex(i));
    	   }
       }
    }
    public String [] darDeudas(){
    	int all=adapter.getInfo(poss).darNumberOfDebts();
    	String [] debts= new String[all];
    	for (int i=0;i<all;i++){
    		debts[i]="Date: "+adapter.getInfo(poss).darDeudaIndex(i).darFecha() + "   Amount Due: "+adapter.getInfo(poss).darDeudaIndex(i).darPago()+"0";
    	}
    	return debts;
    }
    
 
}
