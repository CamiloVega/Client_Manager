package com.ClientManagerFull;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
//import com.google.ads.AdRequest;
//import com.google.ads.AdSize;
//import com.google.ads.AdView;


public class AddNote extends Activity implements OnClickListener{
	Button boton;
	 EditText subject;
	 EditText text;
	 TableLayout layout;
	 TableRow showSubject;
	 TableRow add;
	 TextView showText;
	char i=0;

    /** Called when the activity is first created. */
	
	   public void onCreate(Bundle savedInstanceState) {
	       super.onCreate(savedInstanceState);
	       setContentView(R.layout.add_note);
	       layout=(TableLayout)findViewById(R.id.add_note_tablelayout);
	       add=(TableRow)findViewById(R.id.add_note_newsubject);
	       showSubject=(TableRow)findViewById(R.id.add_note_viewsubject);
	       boton= (Button)findViewById(R.id.add_note_button);
	       boton.setOnClickListener(this);
	       subject = (EditText) findViewById(R.id.add_note_subjectadd);
	       showText = (TextView) findViewById(R.id.add_note_showtext);
	       text = (EditText) findViewById(R.id.add_note_text);
	       showText.setText("Prueba \n Prueba 2 \n Prueba 3");
	      layout.removeView(showSubject);
	      layout.removeView(showText);
	      

	            
	   }
	   
	public void onClick(View v) {

		
		String subject1=subject.getText().toString();
		String text1=text.getText().toString();
		String date=(Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+Calendar.getInstance().get(Calendar.YEAR);
		((App)getApplication()).darClientManager().darClientIndex(((App)getApplication()).darSelected()).newNote(date, text1, subject1);
	       ((App)(getApplication())).save();
	       Toast.makeText(getApplicationContext(), "The note was successfully added on the  "+date+" was successfully added",
     	          Toast.LENGTH_SHORT).show();
	      finish();
	}
	
	
	

}