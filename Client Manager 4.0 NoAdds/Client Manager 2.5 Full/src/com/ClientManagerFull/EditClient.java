package com.ClientManagerFull;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredPostal;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;


import java.io.InputStream;
import java.lang.String;
import java.util.Properties;

import com.ClientManagerFull.R;
import com.ClientManagerFull.AddClient.MyOnItemSelectedListener;


public class EditClient extends Activity implements OnClickListener{
	Button boton;
	 EditText edit;
	 EditText nombre;
	 EditText reference;
	 EditText number;
	 EditText email;
	 EditText address;
	 private boolean type;
		private static final int CONTACT_PICKER_RESULT = 1001;  

	 AlertDialog alertDialog;
	char i=0;
	Editable k;
	int sel;
    /** Called when the activity is first created. */
	
	   public void onCreate(Bundle savedInstanceState) {
	       super.onCreate(savedInstanceState);
	       setContentView(R.layout.add_client);
	       Spinner spinner = (Spinner) findViewById(R.id.spinner);
	       ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	               this, R.array.type, android.R.layout.simple_spinner_item);
	       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	       spinner.setAdapter(adapter);
	       spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
	       sel=((App)getApplication()).darSelected();
	       boton= (Button)findViewById(R.id.add_client_createnewclientbutton);
	       boton.setOnClickListener(this);
	       boton.setText("Edit Client");
	       nombre = (EditText) findViewById(R.id.add_client_name);
	       reference = (EditText) findViewById(R.id.add_client_reference);
	       number = (EditText) findViewById(R.id.add_client_number);
	       email = (EditText) findViewById(R.id.add_client_email);
	       address = (EditText) findViewById(R.id.add_client_address);
	       nombre.setText(((App)getApplication()).darClientManager().darClientIndex(sel).darNombre());
	       reference.setText(((App)getApplication()).darClientManager().darClientIndex(sel).darReference());
	       number.setText(((App)getApplication()).darClientManager().darClientIndex(sel).darNumero());
	       email.setText(((App)getApplication()).darClientManager().darClientIndex(sel).darEmail());
	       address.setText(((App)getApplication()).darClientManager().darClientIndex(sel).darAddress());

       
	   }
	   
	public void onClick(View v) {

		
		String nombre1=nombre.getText().toString();
		String clas=reference.getText().toString();
		String phone=number.getText().toString();
		String mail=email.getText().toString();
		String address1=address.getText().toString();

		((App)getApplication()).darClientManager().editClient(nombre1,clas,phone,mail,address1,sel,type);
	       ((App)(getApplication())).save();
	       Toast.makeText(getApplicationContext(), "The client "+nombre1+" was successfully edited",
	     	          Toast.LENGTH_SHORT).show();
     	  finish();
	}
	public void onPickContact(View view) {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                Contacts.CONTENT_URI);
        startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);
    }
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
	    if (resultCode == RESULT_OK) {  
	        switch (requestCode) {  
	        case CONTACT_PICKER_RESULT:  
	            Cursor c=null;
	            try {  
	                Uri result = data.getData();  
	                // get the contact id from the Uri  
	                String id = result.getLastPathSegment();  
	                c = getContentResolver().query(Phone.CONTENT_URI, null, Phone.CONTACT_ID + "=?", new String[] { id }, null);  
	                 if (c.moveToFirst()) {  	                
	                     String nombre1 = c.getString(c.getColumnIndexOrThrow(Phone.DISPLAY_NAME));
	                     nombre.setText(nombre1.trim());  
	                     String number1=c.getString(c.getColumnIndexOrThrow(Phone.NUMBER)); 
	                     number1.replaceAll(" ", "");
	                     number.setText(number1.trim());
	                 }  
	                c = getContentResolver().query(Email.CONTENT_URI, null, Email.CONTACT_ID + "=?", new String[] { id }, null);  
	                 if (c.moveToFirst()) {  	                
		                     String mail1 = c.getString(c.getColumnIndexOrThrow(Email.DATA)).trim();
		                     email.setText(mail1);
		             }  
	                 c = getContentResolver().query(StructuredPostal.CONTENT_URI, null, StructuredPostal.CONTACT_ID + "=?", new String[] { id }, null);  
	                 if (c.moveToFirst()) {  	                
		                     String address1 = c.getString(c.getColumnIndexOrThrow(StructuredPostal.DATA)).trim();
		                     address.setText(address1);
		             }
	            }
	            catch (Exception e) {  
	                //Log.e(DEBUG_TAG, "Failed to get email data", e);  
	            }
	            finally {
	                if (c != null) {  
	                    c.close();  
	                } 
	            }  
	  
	            break;  
	        }  
	  
	    } else {  
	       // Log.w(DEBUG_TAG, "Warning: activity result not ok");  
	    }  
	} 
	public class MyOnItemSelectedListener implements OnItemSelectedListener {
		 
	    public void onItemSelected(AdapterView<?> parent,
	        View view, int pos, long id) {
	    	/*Dado que se esta realizando un update y inicialmente no se contaba kon la variable
	    	 * se da por hecho que los clientes iniciales en la version 2.8 eran clientes, por lo tanto 
	    	 * se deja como valor de defecto type=false para clientes y type=true para providers
	    	 */
	      if (pos==1){
	    	  type=true;
	    	  Toast.makeText(getApplicationContext(), "Provider ",
	     	          Toast.LENGTH_SHORT).show();
	      }
	      else {
	    	  Toast.makeText(getApplicationContext(), "Client ",
	     	          Toast.LENGTH_SHORT).show();
	    	  type=false;
	      }
	    }

	    public void onNothingSelected(AdapterView parent) {
	      // Do nothing.
	    }

		
	}
}