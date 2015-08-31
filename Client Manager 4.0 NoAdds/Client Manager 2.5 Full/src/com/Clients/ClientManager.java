package com.Clients;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

 
public class ClientManager {
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	private ArrayList<Clients> clients;
	private ArrayList<Appointments> appointments;
	private ArrayList monthlyIncome;
	   private String pathArchivoClient;
	   public ClientManager(String nameOfFileClient)throws StreamCorruptedException, FileNotFoundException, IOException, ClassNotFoundException 
	{
		pathArchivoClient = nameOfFileClient;
		String [] f= pathArchivoClient.split("/");
		String path="";
        appointments = new ArrayList<Appointments>( );
		for (int i=1;i<f.length-1;i++){
			path+="/"+f[i];
		}
        File fileClient = new File( nameOfFileClient );
        if( fileClient.exists( ) ) 
        {
            // El archivo existe: se debe recuperar de allí el estado del modelo del mundo
                ObjectInputStream ois = new ObjectInputStream( new FileInputStream( fileClient ) );
                clients = ( ArrayList<Clients> )ois.readObject( );
                appointments = ( ArrayList<Appointments> )ois.readObject( );
                ois.close( );
        }
        else
        {
            // El archivo no existe: es la primera vez que se ejecuta el programa
            File directorios = new File( path );
            directorios.mkdirs();
            clients = new ArrayList<Clients>( );
            appointments = new ArrayList<Appointments>( );

            
           // while(!archivoStudent.createNewFile());
        }
        monthlyIncome= new ArrayList();
	}
	public void saveClientManager( ) throws FileNotFoundException, IOException 
    {  
           ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( pathArchivoClient ) );
            oos.writeObject( clients );
            oos.writeObject( appointments );
            oos.close( );
 
    }


	 public void addClient(String nombre, String clas,String phone, String mail, String addres, boolean type){
		 clients.add(new Clients(nombre,clas,phone,mail,addres, type));	
		 ordenarAlfabeticamente();
	 }
	 public void addAppointment(String nombre, String clas,String fecha, String hora, int horas){
		 Appointments up=darAppointmentByDate(fecha);
		 boolean type1=darClientByName(nombre).darType();
		 if (up==null){
		 appointments.add(new Appointments(nombre,clas,fecha,hora, horas, type1));	
		 }
		 else{
			 up.addAppointment(nombre, clas, hora, horas, type1);
		 }
		 ordenarPorFecha();
	 }
	 public void editClient(String nombre, String clas,String phone, String mail,String address1, int pos, boolean type){
         Clients s = ( Clients )clients.get( pos );
         s.editReference(clas);
         s.editName(nombre);
         s.editNumber(phone);
         s.editMail(mail);
         s.editAddress(address1);
         s.editType(type);
     }
	 public Clients darClientIndex(int i){
		 Clients s = ( Clients )clients.get( i );
		 return s;
	 }
	 public Appointments darAppointmentIndex(int i){
		 Appointments s = ( Appointments )appointments.get( i );
		 return s;
	 }
	 public void deleteClientIndex(int pos){
		 
				for(int i=0;i<appointments.size();i++){
					if (appointments.get(i).darName().equalsIgnoreCase(clients.get(pos).darNombre())){
						appointments.remove(i);
						i--;
					}
				}				 
		 clients.remove(pos);		 
	 }
	 public void deleteAppointmentIndex(int pos){
		 appointments.remove(pos);		 
	 }
	 public ArrayList<Appointments> darAppointments(){
		 return appointments;
	 }
	 public Clients darClientByName( String nameClient )
	    {
		 
	        for( int i = 0; i < clients.size( ); i++ )
	        {
	            Clients s = ( Clients )clients.get( i );
	            String n=s.darNombre().trim();
	            if( n.equalsIgnoreCase(nameClient) )
	                return s;
	        }
	        return null;
	    }
	 public Appointments darAppointmentByDate( String fecha )
	    {
		 
	        for( int i = 0; i < appointments.size( ); i++ )
	        {
	            Appointments s = ( Appointments )appointments.get( i );
	            String n=s.darFecha().trim();
	            if( n.equalsIgnoreCase(fecha) )
	                return s;
	        }
	        return null;
	    }
		
	 public int darNumberOfClients(){
		 return clients.size();
	 }
	 public int darNumberOfAppoinments(){
		 int cont=0;
		 for (int i=0;i<appointments.size();i++){
			 cont+=appointments.get(i).darNumberOfAppointments();
		 }
		 return cont;
	 }
	 
	 public double darTotalPayments(){
		 double total=0;
		 for( int i = 0; i < clients.size( ); i++ )
	        {
	            Clients s = ( Clients )clients.get( i );
	           if (!s.darType()){
	            total+=s.darPagosTotales();
	           }
	        }
		 return total;
	 }
	 public double darTotalExpenses(){
		 double total=0;
		 for( int i = 0; i < clients.size( ); i++ )
	        {
	            Clients s = ( Clients )clients.get( i );
	           if (s.darType()){
	            total+=s.darPagosTotales();
	           }
	        }
		 return total;
	 }
	 public int darTotalNumberOfDebts(){
		 int total=0;
		 for( int i = 0; i < clients.size( ); i++ )
	        {
	            Clients s = ( Clients )clients.get( i );     
	            total+=s.darNumberOfDebts();
	        }
		 return total;
	 }
	 public int darTotalClientPayments(){
		 int total=0;
		 for( int i = 0; i < clients.size( ); i++ )
	        {
	            Clients s = ( Clients )clients.get( i );
	            total+=s.darPagosTotales();
	        }
		 return total;
	 }
	 
	 public double darBalance(){
		 double total=0;
		 for( int i = 0; i < clients.size( ); i++ )
	        {
	            Clients s = ( Clients )clients.get( i );
	            total+=s.darBalance();
	        }
		 return total;
	 }

	public void ordenarAlfabeticamente(){
		    
		        int inicial;
		        for( inicial = 0; inicial < clients.size( ); inicial++ )
		        {
		            for( int i = clients.size( ) - 1; i > inicial; i-- )
		            {
		                Clients p2 = ( Clients )clients.get( i );
		                Clients p1 = ( Clients )clients.get( i - 1 );

		                if( p1.darNombre().compareToIgnoreCase(p2.darNombre()) > 0 )
		                {
		                    clients.set( i, p1 );
		                    clients.set( i - 1, p2 );
		                }
		            }
		    }	    
	}
	public int compararFechas(String fecha1,String fecha2){
		String[] date1=fecha1.split("/");
		String[] date2=fecha2.split("/");
		if(date1[2].compareToIgnoreCase(date2[2])==0){
			if(date1[0].compareToIgnoreCase(date2[0])==0){
				return date1[1].compareToIgnoreCase(date2[1]);			
			}
			else {
				return date1[0].compareToIgnoreCase(date2[0]);			
			}
		}
		else{
			return date1[2].compareToIgnoreCase(date2[2]);			
		}
	}
	public void ordenarPorFecha(){
	    
        int inicial;
        for( inicial = 0; inicial < appointments.size( ); inicial++ )
        {
            for( int i = appointments.size( ) - 1; i > inicial; i-- )
            {
                Appointments p2 = ( Appointments )appointments.get( i );
                Appointments p1 = ( Appointments )appointments.get( i - 1 );

                if( compararFechas(p1.darFecha(),p2.darFecha()) > 0 )
                {
                    appointments.set( i, p1 );
                    appointments.set( i - 1, p2 );
                }
         }
    }	    
}


	public void deleteAppointmentByDate (String date){
		for(int i=0;i<appointments.size();i++){
			Appointments up=(Appointments)appointments.get(i);
			if (up.darFecha().equalsIgnoreCase(date)){
				appointments.remove(i);
				i=appointments.size();
			}
		}
			
	}
	public void deletePastAppointments(){
		String fecha="";
		Calendar cal2 = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),(Calendar.getInstance().get(Calendar.DATE)));

		for (int i=0;i<appointments.size();i++){
			fecha=appointments.get(i).darFecha();
		String[] date=fecha.split("/");

		Calendar cal = new GregorianCalendar(Integer.parseInt(date[2]),Integer.parseInt(date[0])-1, Integer.parseInt(date[1]));
			if(cal.before(cal2)){
				
				deleteAppointmentIndex(i);
			}
			else{
				i=appointments.size();
			}
		}

	}
	public double monthlyPayments(){
		
			 double total=0;
			 for( int i = 0; i < clients.size( ); i++ )
		        {
		            Clients s = ( Clients )clients.get( i );
		            total+=s.darIncomeMesActual();
		        }
			 return total;
		 
	}
	public double monthlyExpenses(){
		
		 double total=0;
		 for( int i = 0; i < clients.size( ); i++ )
	        {
	            Clients s = ( Clients )clients.get( i );
	            total+=s.darExpensesMesActual();
	        }
		 return total;
	 
}
}
