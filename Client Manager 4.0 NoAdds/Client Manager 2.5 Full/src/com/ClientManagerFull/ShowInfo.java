package com.ClientManagerFull;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

import com.ClientManagerFull.R;
public class ShowInfo extends Activity  {
	TextView name;
	TextView reference;
	TextView phone;
	TextView address;
	TextView email;
	AlertDialog alert;
float scale;
	int sel;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_info);
        scale = this.getResources().getDisplayMetrics().density;

	       name = (TextView) findViewById(R.id.info_name);
	       reference = (TextView) findViewById(R.id.info_reference);
	       phone = (TextView) findViewById(R.id.info_phone);
	       address = (TextView) findViewById(R.id.info_address);
	       email = (TextView) findViewById(R.id.info_email);
	       name.setTypeface(Typeface.MONOSPACE, Typeface.NORMAL);
			name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
			name.setPadding((int)(10*scale), 2, 2, 2);
			name.setGravity(Gravity.LEFT);
	       reference.setTypeface(Typeface.MONOSPACE, Typeface.NORMAL);
			reference.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
			reference.setPadding((int)(10*scale), 2, 2, 2);
			reference.setGravity(Gravity.LEFT);
	       phone.setTypeface(Typeface.MONOSPACE, Typeface.NORMAL);
	       phone.setGravity(Gravity.LEFT);
			phone.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
			phone.setPadding((int)(10*scale), 2, 2, 2);
		       email.setTypeface(Typeface.MONOSPACE, Typeface.NORMAL);
				email.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
				email.setPadding((int)(10*scale), 2, 2, 2);	
				email.setGravity(Gravity.LEFT);     
			       address.setTypeface(Typeface.MONOSPACE, Typeface.NORMAL);
					address.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
					address.setPadding((int)(10*scale), 2, 2, 2);
					address.setGravity(Gravity.LEFT);
	       sel=0;
	       AlertDialog.Builder builder = new AlertDialog.Builder(this);
			  builder.setMessage("Are you sure you want to delete this client?")
			         .setCancelable(false)
			         .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			             public void onClick(DialogInterface dialog, int id) {
			            ((App)getApplication()).darClientManager().deleteClientIndex(((App)getApplication()).darSelected());
			   			if (sel>0){
			   				((App)getApplication()).cambiarSelected((sel-1));
			   			}
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
	       refrescar();
	      
    }
	
	public void onResume(){
    	super.onResume();
    		refrescar();
	}
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.context_menu_clientlist, menu);
	    return true;
	}
	public boolean onOptionsItemSelected(MenuItem item) {
		  switch (item.getItemId()) {
		  case R.id.context_clientlist_addtransaction:
	       	 startActivity(new Intent(ShowInfo.this, AddTransaction.class));
		    return true;
		  case R.id.context_clientlist_transaction_history:
			 startActivity(new Intent(ShowInfo.this, PaymentsList.class));
		    return true;
		  case R.id.context_clientlist_editclient:
				 startActivity(new Intent(ShowInfo.this, EditClient.class));
				  
			    return true;
		  case R.id.context_clientlist_addappointment:
				 startActivity(new Intent(ShowInfo.this, AddAppointment.class));
				  
			    return true;
		  case R.id.context_clientlist_callclient:
			  String phone=((App)getApplication()).darClientManager().darClientIndex(((App)getApplication()).darSelected()).darNumero();
			  if(phone.equalsIgnoreCase("")){
				  Toast.makeText(getApplicationContext(), "The Client does not have a registered phone Number",
	        	          Toast.LENGTH_SHORT).show();
			  }
			  else{
			  		startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone)));
			  }
			    return true;
		  case R.id.context_clientlist_showmap:
			  //alert.show();
			  String address=((App)getApplication()).darClientManager().darClientIndex(((App)getApplication()).darSelected()).darAddress().replace("#", " ");
			  //String address="starbucks";
			  Toast.makeText(getApplicationContext(), address,
	    	          Toast.LENGTH_SHORT).show();
			  String geoUriString = "geo:0,0?q="+ address;  
			  Uri geoUri = Uri.parse(geoUriString);  
			  Intent mapCall = new Intent(Intent.ACTION_VIEW, geoUri);  
			  startActivity(mapCall); 
			  return true;
		  case R.id.context_clientlist_emailclient:
			  Uri uri = Uri.parse("mailto:"+ ((App)getApplication()).darClientManager().darClientIndex(((App)getApplication()).darSelected()).darEmail());
			  Intent it = new Intent(Intent.ACTION_SENDTO, uri);
			  startActivity(it);
			  return true;
		  case R.id.context_clientlist_notesclient:
				 startActivity(new Intent(ShowInfo.this, NotesList.class));
			  return true;
		  case R.id.context_clientlist_deleteclient:
			   alert.show();
			  
			   
	  	    return true;
		  default:
		    return super.onContextItemSelected(item);
		  }
		}
	public void refrescar(){
		//alert.show();
		sel=((App)getApplication()).darSelected();
	       name.setText(((App)getApplication()).darClientManager().darClientIndex(sel).darNombre());
	       reference.setText(((App)getApplication()).darClientManager().darClientIndex(sel).darReference());
	       phone.setText(((App)getApplication()).darClientManager().darClientIndex(sel).darNumero());
	       email.setText(((App)getApplication()).darClientManager().darClientIndex(sel).darEmail());
	       address.setText(((App)getApplication()).darClientManager().darClientIndex(sel).darAddress());

	     // precio.setText(Integer.toString(((App)getApplication()).darTutor().darStudentIndex(sel).darPrecio()));
	       //precio.setText(fech);
	      	
	        //fecha.setText("");
	        
	}

}
