package com.ClientManagerFull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;

import com.Clients.ClientManager;
import com.Clients.Clients;

import android.app.AlertDialog;
import android.app.Application;

public class App extends Application{
	 ClientManager clientManager;
	 AlertDialog alertDialog;
	 int selected;
	 int selectedAppointment;
	 int selectedNote;
	 int selectedPayment;

	public void loadClientManager()
	   {
		selected=0;
		selectedAppointment=0;
		selectedNote=0;
		       // FileInputStream fis = new FileInputStream( "/sdcard/hoja.properties" );
					try {
						//tutor=new Tutor ("/sdcard/Students.tutor");
						clientManager=new ClientManager("/sdcard/ClientManagerApp/Clients.cvd");
					} catch (StreamCorruptedException e) {
						alertDialog.setMessage("Error Loading File 1");
						alertDialog.show();
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						alertDialog.setMessage("Error Loading File 2");
						alertDialog.show();
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						alertDialog.setMessage("Error Loading File 3");
						alertDialog.show();
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						alertDialog.setMessage("Error Loading File 4");
						alertDialog.show();
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					

	   }
	public void save()
	   {
		try {
			clientManager.saveClientManager();
			//tutor.salvarPagos();
		} catch (FileNotFoundException e) {
			alertDialog.setMessage("Error Saving File, File Not Found");
			alertDialog.show();
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			alertDialog.setMessage("Error Saving File 2"+e.getMessage());
			alertDialog.show();
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	   }
	public ClientManager darClientManager(){
		return clientManager;
	}
	public void cambiarSelected(int sel){
		selected=sel;
	}
	public int darSelected(){
		return selected;
	}
	public Clients darSelectedClient(){
		return clientManager.darClientIndex(selected);
	}
	public void cambiarSelectedNote(int sel){
		selectedNote=sel;
	}
	public int darSelectedNote(){
		return selectedNote;
	}
	public void cambiarSelectedPayment(int sel){
		selectedPayment=sel;
	}
	public int darSelectedPayment(){
		return selectedPayment;
	}
	public void changeSelectedAppointment(int sel){
		selectedAppointment=sel;
	}
	public int darSelectedAppointment(){
		return selectedAppointment;
	}
}
