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
import com.Clients.Fechas;
public class EditTransaction extends Activity implements OnClickListener {
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
        Fechas f=((App)getApplication()).darSelectedClient().darFechaIndex(((App)getApplication()).darSelectedPayment());
	       boton= (Button)findViewById(R.id.add_transaction_button_add);
	       boton.setOnClickListener(this);
	       pagoTot = (EditText) findViewById(R.id.add_transaction_payment);
	       service = (EditText) findViewById(R.id.add_transaction_service);
	       check = (CheckBox) findViewById(R.id.add_transaction_checkbox);
	       client = (TextView) findViewById(R.id.add_transaction_name);
	       //precio = (TextView) findViewById(R.id.TInfPrecio);
	       reference = (TextView) findViewById(R.id.add_transaction_reference);
	       datePicker=(DatePicker) findViewById(R.id.add_transaction_datepicker);
	       datePicker.init(Integer.parseInt(f.darAnio()), Integer.parseInt(f.darMes())-1,Integer.parseInt( f.darDia()), null);
	       sel=((App)getApplication()).darSelected();
	       client.setText(((App)getApplication()).darClientManager().darClientIndex(sel).darNombre());
	       reference.setText(((App)getApplication()).darClientManager().darClientIndex(sel).darReference());
	       service.setText(f.darService());
	       pagoTot.setText(f.darPago()+"");
	       if (f.darPagoRealizado()){
	    	   check.setChecked(true);
	       }
	       else{
	    	   check.setChecked(false);
	       }
	       boton.setText("Edit Payment");
    }
	public void onClick(View arg0) {
		switch(arg0.getId())
		  {
		  case R.id.add_transaction_button_add:
			  fech=""+(datePicker.getMonth()+1)+"/"+datePicker.getDayOfMonth()+"/"+datePicker.getYear();

			 pago= check.isChecked();
		   //Do something
			  ((App)getApplication()).darClientManager().darClientIndex(sel).editTransaction(((App)getApplication()).darSelectedPayment(),fech.trim(), service.getText().toString().trim(),Double.parseDouble(pagoTot.getText().toString().trim()),pago);
			  Toast.makeText(getApplicationContext(), "Transaction Successfully Edited",
        	          Toast.LENGTH_SHORT).show();
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
