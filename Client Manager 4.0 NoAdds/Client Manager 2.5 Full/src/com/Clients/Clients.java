package com.Clients;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Clients implements Serializable{
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    private static final long serialVersionUID = 100L;
    /**
     * Es el nombre del equipo
     */
    private String name="";
    private String reference="";
    private String number="";
    private String email="";
    private String address="";
    private double pagos=0;
    private double balance=0;
    private boolean type;
	private ArrayList<Fechas> fechas;
	private ArrayList<Fechas> deudas;
	private ArrayList <Notes> notes;


    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Construye una nueva instancia de un equipo
     * @param nombreEquipo Es el nombre del equipo. nombreEquipo != null
     */
    public Clients( String nombreStudent , String reference1, String phone, String mail, String addres, boolean type1) 
    {
        name = nombreStudent.trim();
        reference=reference1.trim();
        email=mail.trim();
        number=phone.trim();
        address=addres.trim();
        type=type1;
        pagos=0;
        balance=0;
        deudas=new ArrayList<Fechas>();
        fechas=new ArrayList<Fechas>();
        notes= new ArrayList<Notes>();

    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Retorna el nombre del equipo
     * @return nombre
     */
    public String darNombre( )
	    {
    	if (name==null){
    		name="";
    	}
	        return name.trim();
	    }

    public String darReference( )
    {
    	if (reference==null){
    		reference="";
    	}
        return reference.trim();
    }
    public boolean darType( )
    {
    	
        return type;
    }
    public String darNumero( )
    {
    	if (number==null){
    		number="";
    	}
        return number.trim();
    }
    public String darEmail( )
    {
    	if (email==null){
    		email="";
    	}
        return email.trim();
    }public String darAddress( )
    {
    	if (address==null){
    		address="";
    	}
        return address.trim();
    }
    public void editName(String nombre1){
    	name=nombre1;
    }
    public void editReference(String reference1){
    	reference=reference1;
    }
    public void editNumber(String numero1){
    	number=numero1;
    }
    public void editMail(String email1){
    	email=email1;
    }
    public void editAddress(String address1){
    	address=address1;
    }
    public void editType(boolean type1){
    	type=type1;
    }
    public void addTransaction(String fecha,String service, double precio, boolean pago){
    	Fechas f= new Fechas(service,fecha,precio,pago);
    	fechas.add(f);
    	if (!f.darPagoRealizado()){
			deudas.add(f); 
			balance+=precio;
		}
    	else{
    		pagos+=precio;
    	}
    	ordenarPorFecha();
    }
    public void editTransaction(int pos,String fecha,String service, double precio, boolean pago ){
    	addTransaction(fecha, service, precio, pago);
    	deleteTransaction(pos);
    	
    }
    public void deleteTransaction(int pos){
        	Fechas oldFecha=fechas.get(pos);
        	if (deudas.contains(oldFecha)){
        		balance-=oldFecha.darPago();
        		deudas.remove(oldFecha);
        	}
        	else {
        		pagos-=oldFecha.darPago();
        	}
        	fechas.remove(oldFecha);
        	ordenarPorFecha();
    }
    public ArrayList<Notes> darNotes(){
    	if (notes==null){
    		notes=new ArrayList <Notes>();
    	}
    	return notes;
    }
    public Notes darNoteIndex(int index){
    	if (notes==null){
    		notes=new ArrayList <Notes>();
    	}
    	return notes.get(index);
    }
    public void newNote(String date, String text, String subject){
    	Notes note=new Notes(date,text, subject);
    	if (notes==null){
    		notes=new ArrayList <Notes>();
    	}
    	notes.add(note);
    }
    public void editNote(String date1, String text, String subject1, int pos){
    	notes.get(pos).editNote(text, date1, subject1);
    }
    public void deleteNote(int index){
    	notes.remove(index);
    }
    public Fechas darFechaIndex(int i){
		 Fechas f = ( Fechas )fechas.get( i );
		 return f;
	 }
    public Fechas darDeudaIndex(int i){
		 Fechas f = ( Fechas )deudas.get( i );
		 return f;
	 }
    public int darNumberOfTransactions(){
    	return fechas.size();
    }
    public int darNumberOfDebts(){
    	return deudas.size();
    }
    public ArrayList<Fechas> darFechas(){
    	return fechas;
    }
    public ArrayList<Fechas> darDeudas(){
    	return deudas;
    }

    public double darPagosTotales(){
    	return pagos;
    }
    public double darBalance(){
    	return balance;
    }
    public Fechas darFecha1( String fecha1, String service )
    {
        for( int i = 0; i < fechas.size( ); i++ )
        {
            Fechas f = ( Fechas )fechas.get( i );
            if( f.darFecha().matches(fecha1)&&f.darService().equalsIgnoreCase(service)){
                return f;
                }
        }
        return null;
    }
    public void deleteDebt( int fecha )
    {
       
            Fechas f = ( Fechas )deudas.get( fecha );
          
            	pagos+=f.darPago();
            	balance-=f.darPago();
            	deudas.remove(f);
                
        
    }
    public double darIncomeMesActual(){
    	double suma=0;
    	boolean esDeuda=false;
    	if (!type){
    	 for( int i = 0; i < fechas.size( ); i++ )
         {
    		 esDeuda=false;
    		 
             Fechas f = ( Fechas )fechas.get( i );
             int anio1=Calendar.getInstance().get(Calendar.YEAR);
             int mes1=Calendar.getInstance().get(Calendar.MONTH)+1;
             int anio=Integer.parseInt(f.darAnio());
             int mes=Integer.parseInt(f.darMes());
             if( (anio1==anio)&&(mes1==mes)){
               for (int j=0;j<deudas.size();j++){
            	   Fechas d=deudas.get(j);
            	   if(d.equals(f)){
            		   esDeuda=true;
            	   }
            	   
               }
               if(!esDeuda){
            	 suma+=f.darPago();
               	}
               }
    		 }
         }
    	return suma;
    }
    public double darExpensesMesActual(){
    	double suma=0;
    	boolean esDeuda=false;
    	if (type){
    	 for( int i = 0; i < fechas.size( ); i++ )
         {
    		 esDeuda=false;
             Fechas f = ( Fechas )fechas.get( i );
             if( Calendar.getInstance().get(Calendar.YEAR)==Integer.parseInt(f.darAnio())&&(Calendar.getInstance().get(Calendar.MONTH)+1)==Integer.parseInt(f.darMes())){
               for (int j=0;j<deudas.size();j++){
            	   Fechas d=deudas.get(j);
            	   if(d.equals(f)){
            		   esDeuda=true;
            	   }
            	   
               }
               if(!esDeuda){
            	 suma+=f.darPago();
               	}
               }
         }
    	}
    	return suma;
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
        for( inicial = 0; inicial < fechas.size( ); inicial++ )
        {
            for( int i = fechas.size( ) - 1; i > inicial; i-- )
            {
            	Fechas p2 = ( Fechas )fechas.get( i );
            	Fechas p1 = ( Fechas )fechas.get( i - 1 );

                if( compararFechas(p1.darFecha(),p2.darFecha()) > 0 )
                {
                    fechas.set( i, p1 );
                    fechas.set( i - 1, p2 );
                }
         }
    }	    
}
    
}
