package com.ClientManagerFull;


import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

import com.Clients.ClientManager;
//import com.google.ads.AdRequest;
//import com.google.ads.AdSize;
//import com.google.ads.AdView;

 
public class Main extends TabActivity  {
	 ClientManager tutor;
	 AlertDialog alertDialog;
//	 AdView adView; TODO
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	    ((App)getApplication()).loadClientManager();

	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab
	    
	    intent = new Intent(this, FinancesTab.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("Finances").setIndicator("Finances",
	                      res.getDrawable(R.drawable.money))
	                  .setContent(intent);
	    tabHost.addTab(spec);
	    // Create an Intent to launch an Activity for the tab (to be reused)
	   
	    intent = new Intent(this, ClientList.class);
	    
	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("Contact List").setIndicator("Contact List",
	                      res.getDrawable(R.drawable.clients1))
	                  .setContent(intent);
	    tabHost.addTab(spec);
	   

		//Froyo or greater (mind you I just tested this on CM7 and the less than froyo one worked so it depends on the phone...)
		//cn = new ComponentName("com.google.android.calendar", "com.android.calendar.LaunchActivity");

		//less than Froyo
		
	    intent = new Intent(this, AppointmentList.class);
	    
	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("Appointments").setIndicator("Appointments",
	                      res.getDrawable(R.drawable.calendar_icon))
	                  .setContent(intent);
	    tabHost.addTab(spec);	   // 
	    tabHost.setCurrentTabByTag("Contact List");
	    
	}
	
}