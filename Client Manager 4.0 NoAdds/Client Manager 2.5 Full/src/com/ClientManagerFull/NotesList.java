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
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
public class NotesList extends ListActivity {
	 protected static final int REQST_CODE = 0;
	 String nombre;
	 String fecha;
	 String horas;
	 AlertDialog alert;
	 AlertDialog pick;
	 AlertDialog alert3;
	 int poss;
	 int del;
	 NotesAdapter adapter = null;
	 LayoutAnimationController controller;
	 AlertDialog alertDialog;
	 int tot;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	refresh();
    	createDialogs();
		  if (((App)getApplication()).darClientManager().darClientIndex(((App)getApplication()).darSelected()).darNotes().size()==0){
			  alertDialog.show();
		  }
		  controller = AnimationUtils.loadLayoutAnimation( this, R.anim.list_animation_controller);

    }
    public void click(int position){
    	
     	 poss=position;	
     	((App)getApplication()).cambiarSelectedNote(poss);
     	startActivity(new Intent(NotesList.this, ShowNote.class));    		
    	
    }
    public void onResume(){
    	super.onResume();
        refresh();
    	
    }
    
    
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.context_menu_noteslist, menu);
}
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	  AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
    	  ((App)getApplication()).cambiarSelectedNote(info.position);
    	  switch (item.getItemId()) {
    	  case R.id.context_noteslist_editnote:
           	 startActivity(new Intent(NotesList.this, EditNote.class));
    	    return true;
    	  case R.id.context_noteslist_deletenote:
    		  alert.show();
    		  //((App)getApplication()).darClientManager().darClientIndex(((App)getApplication()).darSelected()).deleteNote(info.position);
    		  
    	    return true;
    	  
    	  default:
    	    return super.onContextItemSelected(item);
    	  }
    	}
    public void refresh(){
    	int cuantos= ((App)getApplication()).darClientManager().darClientIndex(((App)getApplication()).darSelected()).darNotes().size();
        adapter = new NotesAdapter(this); 
        setListAdapter(adapter);
       ListView lv = getListView();
       lv.setCacheColorHint(0);
       lv.setBackgroundResource(R.drawable.background2);
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
       		adapter.addNote(((App)getApplication()).darClientManager().darClientIndex(((App)getApplication()).darSelected()).darNotes().get(i));
       }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_noteslist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.menu_noteslist_addnewnote:
            // Launch the DeviceListActivity to see devices and do scan
          	 startActivity(new Intent(NotesList.this, AddNote.class));
            return true;
       
        }
        return false;
    }
    public void createDialogs(){
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
		  builder.setMessage("Are you sure you want to delete this note?")
		         .setCancelable(false)
		         .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		             public void onClick(DialogInterface dialog, int id) {
		            	 int pC=((App)getApplication()).darSelected();
		            	 int pN=((App)getApplication()).darSelectedNote();
		            	 ((App)getApplication()).darClientManager().darClientIndex(pC).deleteNote(pN);
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
	       alertDialog.setMessage("To Add a Note, press menu");
	       alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
	          public void onClick(DialogInterface dialog, int which) { 
	        	  alertDialog.dismiss();
	          }
	       });
		  alert = builder.create();
    }
}
