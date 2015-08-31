  package com.ClientManagerFull;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.ClientManagerFull.R;
public class FinancesTab extends Activity implements OnClickListener {
	Button boton;
	TextView totalIncome; 
	TextView totalExpenses;
	TextView totalProfit;
	TextView saldo;
	TextView monthlyIncome;
	TextView monthlyExpenses;
	TextView monthlyProfit;
	AlertDialog alertDialog;
	
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabfinanzas);

 	   boton= (Button)findViewById(R.id.BAgPago);
       boton.setOnClickListener(this);
       totalIncome = (TextView) findViewById(R.id.tabfinanzas_totalincome);
       totalExpenses = (TextView) findViewById(R.id.tabfinanzas_totalexpenses);
       saldo = (TextView) findViewById(R.id.TSaldoPagos);
       monthlyIncome = (TextView) findViewById(R.id.tab_finanzas_monthlyincome);
       monthlyExpenses = (TextView) findViewById(R.id.tab_finanzas_monthlyexpenses);
       totalProfit = (TextView) findViewById(R.id.tab_finanzas_totalprofit);
       monthlyProfit = (TextView) findViewById(R.id.tab_finanzas_monthlyprofit);
	   alertDialog = new AlertDialog.Builder(this).create();
       alertDialog.setTitle("First Time");
       alertDialog.setMessage(" To add and income or an expense, long click a contact and go to Add Payment." +
       		" Green contacts are added as Income. Red contacts are added as Expenses. \n" +
       		"Sugestion: If you want to add expenses such as gas and food, add a contact as various " +
       		"expenses.");
       alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int which) { 
        	  alertDialog.dismiss();
          }
       });
	 
       if ((((App)getApplication()).darClientManager().darTotalPayments()==0)&&(((App)getApplication()).darClientManager().darTotalExpenses()==0)){
   		  alertDialog.show();
   	  }
		  
        refrescar();
    }

	public void onClick(View v) {
		//((App)getApplication()).darTutor().agregarPago(eFecha.getText().toString().trim(), Integer.parseInt(ePago.getText().toString().trim()));
   	 startActivity(new Intent(FinancesTab.this, DebtList.class));

		refrescar();
	       
		// TODO Auto-generated method stub
		
	}
	public void onResume(){
    	super.onResume();
    	
    		refrescar();
	}
	public void refrescar(){
		double profit=0;
		DecimalFormat df = new DecimalFormat("#.##");
	       double incomeTotal=((App)getApplication()).darClientManager().darTotalPayments();
	       double expensesTotal=((App)getApplication()).darClientManager().darTotalExpenses();
	        totalIncome.setText("    $ "+df.format(incomeTotal));
	        totalExpenses.setText("    $ "+df.format(expensesTotal));
	        if (incomeTotal<expensesTotal){
	        	profit=0;
	        	profit=expensesTotal-incomeTotal;
	        	totalProfit.setText("    $ "+df.format(profit));
	        	totalProfit.setTextColor(Color.RED);
	        }
	        else {
	        	profit=0;
	        	profit=incomeTotal-expensesTotal;
	        	totalProfit.setText("    $ "+df.format(profit));
	        	totalProfit.setTextColor(Color.GREEN);
	        }
	       
		       double incomeMonthly=((App)getApplication()).darClientManager().monthlyPayments();
		       double expensesMonthly=((App)getApplication()).darClientManager().monthlyExpenses();
		        monthlyIncome.setText("    $ "+df.format(incomeMonthly));
		        monthlyExpenses.setText("    $ "+df.format(expensesMonthly));
		        if (incomeMonthly<expensesMonthly){
		        	 profit=0;
		        	profit=expensesMonthly-incomeMonthly;
		            
		        	monthlyProfit.setText("    $ "+df.format(profit));
		        	monthlyProfit.setTextColor(Color.RED);
		        }
		        else {
		        	 profit=0;
		        	profit=incomeMonthly-expensesMonthly;
		        	monthlyProfit.setText("    $ "+df.format(profit));
		        	monthlyProfit.setTextColor(Color.GREEN);
		        }
	        saldo.setText("    $ "+Double.toString(((App)getApplication()).darClientManager().darBalance())+"0");
	        //monthlyIncome.setText("    $ "+Double.toString(((App)getApplication()).darClientManager().monthlyPayments())+"0");
	        //monthlyExpenses.setText("    $ "+Double.toString(((App)getApplication()).darClientManager().monthlyExpenses())+"0");
	}
	
}
