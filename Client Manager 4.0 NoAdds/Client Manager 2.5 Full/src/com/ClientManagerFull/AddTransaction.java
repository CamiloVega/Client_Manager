package com.ClientManagerFull;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.ClientManagerFull.R;
public class AddTransaction extends Activity implements OnClickListener {
	Button boton;
	TextView client;
	TextView reference;
	TextView historial;
	DatePicker datePicker;
	CheckBox check;
	int mDay;
	int mMonth;
	int mYear;
	EditText pagoTot;
	String fech;
	EditText service;
	boolean pago;
	int sel;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_transaction);
	       boton= (Button)findViewById(R.id.add_transaction_button_add);
	       boton.setOnClickListener(this);
	       pagoTot = (EditText) findViewById(R.id.add_transaction_payment);
	       service = (EditText) findViewById(R.id.add_transaction_service);
	       check = (CheckBox) findViewById(R.id.add_transaction_checkbox);
	       client = (TextView) findViewById(R.id.add_transaction_name);
	       //precio = (TextView) findViewById(R.id.TInfPrecio);
	       reference = (TextView) findViewById(R.id.add_transaction_reference);
	       datePicker=(DatePicker) findViewById(R.id.add_transaction_datepicker);
	       sel=((App)getApplication()).darSelected();
	       client.setText(((App)getApplication()).darClientManager().darClientIndex(sel).darNombre());
	       reference.setText(((App)getApplication()).darClientManager().darClientIndex(sel).darReference());
	      
    }
	public void onClick(View arg0) {
		switch(arg0.getId())
		  {
		  case R.id.add_transaction_button_add:
			  fech=""+(datePicker.getMonth()+1)+"/"+datePicker.getDayOfMonth()+"/"+datePicker.getYear();
			  Toast.makeText(getApplicationContext(), "Transaction Successfully Added",
        	          Toast.LENGTH_SHORT).show();
			 pago= check.isChecked();
		   //Do something
			 if (pagoTot.getText().toString().trim().equalsIgnoreCase("")){
				 pagoTot.setText("0");
			 }
			  ((App)getApplication()).darClientManager().darClientIndex(sel).addTransaction(fech.trim(), service.getText().toString().trim(),Double.parseDouble(pagoTot.getText().toString().trim()),pago);
			  
		   break;
		  
		  }
		((App)getApplication()).save();
			finish();
			//this.finish();
		// TODO Auto-generated method stub	
	}
/*
    <TableRow>
	 <TextView
   android:padding="3dip" android:text="Comments:  \n \n  \n \n "/>
   <EditText android:gravity="right"
   android:padding="6dip"
  	android:layout_width="fill_parent" 
   android:layout_height="fill_parent" 
   android:id="@+id/add_class_comments"
   ></EditText>
     </TableRow>
*/
}
