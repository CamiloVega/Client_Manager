package com.ClientManagerFull;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.ClientManagerFull.R;
public class ClientList extends ListActivity {
	 protected static final int REQST_CODE = 0;
	 AlertDialog alert;
	 AlertDialog alertDialog;
	 ClientListAdapter adapter = null;
	 LayoutAnimationController controller;
	 int sel;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        refresh();
       sel=0;  
      createDialogs();
	  if (((App)getApplication()).darClientManager().darNumberOfClients()==0){
		  alertDialog.show();
	  }
	  controller = AnimationUtils.loadLayoutAnimation( this, R.anim.list_animation_controller);
	  
    }
    public void onResume(){
    	super.onResume();
       refresh();
    	
    }
    
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.context_menu_clientlist, menu);
}
@Override
public boolean onContextItemSelected(MenuItem item) {
	  AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
	  ((App)getApplication()).cambiarSelected(info.position);
	  switch (item.getItemId()) {
	  case R.id.context_clientlist_addtransaction:
       	 startActivity(new Intent(ClientList.this, AddTransaction.class));
	    return true;
	  case R.id.context_clientlist_transaction_history:
		 startActivity(new Intent(ClientList.this, PaymentsList.class));
		  
	    return true;
	  case R.id.context_clientlist_editclient:
			 startActivity(new Intent(ClientList.this, EditClient.class));
			  
		    return true;
	  case R.id.context_clientlist_addappointment:
			 startActivity(new Intent(ClientList.this, AddAppointment.class));
			  
		    return true;
	  case R.id.context_clientlist_callclient:
		  String phone=((App)getApplication()).darClientManager().darClientIndex(info.position).darNumero();
		  if(phone.equalsIgnoreCase("")){
			  Toast.makeText(getApplicationContext(), "The Contact does not have a registered phone Number",
         	          Toast.LENGTH_SHORT).show();
		  }
		  else{
		  		startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone)));
		  }
		    return true;
		    
	  case R.id.context_clientlist_showmap:
		  String address=((App)getApplication()).darClientManager().darClientIndex(info.position).darAddress().replace("#", " ");
		  if(address.equalsIgnoreCase("")){
			  Toast.makeText(getApplicationContext(), "The Contact does not have a registered Address",
         	          Toast.LENGTH_SHORT).show();
		  }
		  else{
		  String geoUriString = "geo:0,0?q="+ address;  
		  Uri geoUri = Uri.parse(geoUriString);  
		  Intent mapCall = new Intent(Intent.ACTION_VIEW, geoUri);  
		  startActivity(mapCall); 
		  }
		  return true;
	   case R.id.context_clientlist_emailclient:
		  Uri uri = Uri.parse("mailto:"+ ((App)getApplication()).darClientManager().darClientIndex(info.position).darEmail());
		  Intent it = new Intent(Intent.ACTION_SENDTO, uri);
		  startActivity(it);
		  return true;
	   case R.id.context_clientlist_notesclient:
			 startActivity(new Intent(ClientList.this, NotesList.class));
		  return true;
	   case R.id.context_clientlist_deleteclient:
			   alert.show();
			  return true;
	  default:
	    return super.onContextItemSelected(item);
	  }
	}
public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_clientlist, menu);
    return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
    case R.id.menu_clientlist_addnewclient:
        // Launch the DeviceListActivity to see devices and do scan
      	 startActivity(new Intent(ClientList.this, AddClient.class));

        return true;
    case R.id.menu_clientlist_rate:
     	 final String items [] = {"Rate in Market","Send a Suggestion"};
     	 AlertDialog alert3;
		  AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
		  builder1.setTitle("Pick an Action:");
		  builder1.setItems(items, new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog, int item) {
		          if (item==0){
		        	  String url = "market://details?id=com.ClientManagerFull";
		     		 Intent i = new Intent(Intent.ACTION_VIEW);
		     		 i.setData(Uri.parse(url));
		     		 startActivity(i);
		          }
		          else {
		        	  Uri uri = Uri.parse("mailto:"+ "cvdeveloperscorp@gmail.com");
		    		  Intent it = new Intent(Intent.ACTION_SENDTO, uri);
		    		  startActivity(it);
		          }
		          
		      }
		  });
		   alert3 = builder1.create();
     	alert3.show();
        
	    return true;
    }
    return false;
}
	public void refresh(){
		int cuantos= ((App)getApplication()).darClientManager().darNumberOfClients();
        adapter = new ClientListAdapter(this);
        
        setListAdapter(adapter);
       ListView lv = getListView();
       lv.setCacheColorHint(0);
       lv.setOnItemClickListener(new OnItemClickListener() {
           public void onItemClick(AdapterView<?> parent, View view,
               int position, long id) {
        	   ((App)getApplication()).cambiarSelected(position);
        	   startActivity(new Intent(ClientList.this, ShowInfo.class));
           }
         });
       //lv.setTextFilterEnabled(true);
       lv.setLayoutAnimation(controller);
       registerForContextMenu(lv);
       for (int i=0;i<cuantos;i++){
       	adapter.addAddress(((App)getApplication()).darClientManager().darClientIndex(i));
       }
     
	}
	public void createDialogs(){
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
		   				onResume();
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
	       alertDialog.setMessage("Start by pressing the menu button to add a new contact." +
	       		" Contacts in Green are clients. Contacts in red are providers. " +
	       		"To interact with a contact long press the name to expand the context menu." +
	       		"If you want a feature to be added or you liked the App, please rate and comment.");
	       alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
	          public void onClick(DialogInterface dialog, int which) { 
	        	  alertDialog.dismiss();
	          }
	       });
	}
}
