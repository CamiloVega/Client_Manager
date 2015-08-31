package com.ClientManagerFull;


import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
public class PaymentsList extends ListActivity {
	 protected static final int REQST_CODE = 0;
//	 String nombre;
//	 String horas;
	 AlertDialog alert;
	 AlertDialog alert3;
	 LayoutAnimationController controller;
	 PaymentsListAdapter adapter = null;
	 int poss;
	 int fecha;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	refresh();
  	  controller = AnimationUtils.loadLayoutAnimation( this, R.anim.list_animation_controller);

      /* AlertDialog.Builder builder = new AlertDialog.Builder(this);
		  builder.setMessage("Was this debt paid?")
		         .setCancelable(false)
		         .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		             public void onClick(DialogInterface dialog, int id) {
		  
		            	 ((App)getApplication()).darClientManager().darClientByName(adapter.getInfo(poss).darNombre()).darFechaIndex(fecha).setPago(true);
		               	 ((App)getApplication()).darClientManager().darClientByName(adapter.getInfo(poss).darNombre()).deleteDebt(fecha);
		               	((App)getApplication()).save(); 
		            finish();
		             }
		         })
		         .setNegativeButton("No", new DialogInterface.OnClickListener() {
		             public void onClick(DialogInterface dialog, int id) {
		                  dialog.cancel();
		             }
		         });*/
		//  alert = builder.create();
      
    }
    public void click( int position){
    	((App)getApplication()).cambiarSelectedPayment(position);
    	startActivity(new Intent(PaymentsList.this, EditTransaction.class));
    		
    }
    public void onResume(){
    	super.onResume();
        refresh();
    	
    }
    
    public void refresh(){
    	int posCliente=((App)getApplication()).darSelected();
    	int cuantos= ((App)getApplication()).darClientManager().darClientIndex(posCliente).darNumberOfTransactions();
        adapter = new PaymentsListAdapter(this);
        this.setTitle("Payment History for: "+((App)getApplication()).darClientManager().darClientIndex(posCliente).darNombre());
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

       	adapter.addPayments(((App)getApplication()).darClientManager().darClientIndex(posCliente).darFechaIndex(i));
    	 
       }
    }
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.context_menu_paymentlist, menu);
}
    public boolean onContextItemSelected(MenuItem item) {
    	  AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
    	  ((App)getApplication()).cambiarSelectedPayment(info.position);
    	  switch (item.getItemId()) {
    	   case R.id.context_paymentlist_editpayment:
    	    	startActivity(new Intent(PaymentsList.this, EditTransaction.class));
    		  return true;
    	   case R.id.context_paymentlist_deletepayment:
    		   ((App)getApplication()).darSelectedClient().deleteTransaction(info.position);
    		   ((App)getApplication()).save();
    		   refresh();
    			  return true;
    	  default:
    	    return super.onContextItemSelected(item);
    	  }
    	}
    
}
