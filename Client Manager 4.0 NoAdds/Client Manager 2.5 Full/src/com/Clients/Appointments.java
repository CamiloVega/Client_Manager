package com.Clients;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Appointments implements Serializable{
	//-----------------------------------------------------------------
    // Atributes
    //-----------------------------------------------------------------
    private static final long serialVersionUID = 100L;

    private String fecha="";
    private String name="";
    private ArrayList <AppointmentsArray> appointmentsArray;


    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Construye una nueva instancia de un equipo
     * @param nombreEquipo Es el nombre del equipo. nombreEquipo != null
     */
    public Appointments( String nameClient , String reference, String fecha1, String hora1, int horas, boolean type1) 
    {
        fecha=fecha1.trim();
        name=nameClient.trim();
        appointmentsArray= new ArrayList<AppointmentsArray>();
        addAppointment(nameClient, reference, hora1, horas, type1);
        

    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Retorna el nombre del equipo
     * @return nombre
     */
    public void deleteAppointmentIndex(int pos){
    	appointmentsArray.remove(pos);		 
	 }
    public String darFecha( )
    {
    	if (fecha==null){
    		fecha="";
    	}
        return fecha.trim();
    }
    public String darName( )
    {
    	if (name==null){
    		name="";
    	}
        return name.trim();
    }
    public void addAppointment(String nombre,String clase, String hora, int nHoras, boolean type1){
    	appointmentsArray.add(new AppointmentsArray(nombre, clase, hora, nHoras, type1));
    	orderByTime();
    	
    }
    public AppointmentsArray darAppointmentsIndex(int pos){
    	AppointmentsArray s=(AppointmentsArray)appointmentsArray.get(pos);
		return s;
    }
    public String darDia(){
    	String[] date=fecha.split("/");
    	Calendar cal = new GregorianCalendar(Integer.parseInt(date[2]),Integer.parseInt(date[0])-1, Integer.parseInt(date[1]));
    	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    		if(dayOfWeek==1){
    			return "Sunday";
    		}
    		else if (dayOfWeek==2){
    			return "Monday";
    		}
    		else if (dayOfWeek==3){
    			return "Tuesday";
    		}
    		else if (dayOfWeek==4){
    			return "Wednesday";
    		}
    		else if (dayOfWeek==5){
    			return "Thursday";
    		}
    		else if (dayOfWeek==6){
    			return "Friday";
    		}
    		else  {
    			return "Saturday";
    		}
    		
    }
    public int darNumberOfAppointments(){
    	if (appointmentsArray==null){
    		appointmentsArray= new ArrayList<AppointmentsArray>();
    	}
    	return appointmentsArray.size();
    }
public void orderByTime(){
	    
        int inicial;
        for( inicial = 0; inicial < appointmentsArray.size( ); inicial++ )
        {
            for( int i = appointmentsArray.size( ) - 1; i > inicial; i-- )
            {
                AppointmentsArray p2 = ( AppointmentsArray)appointmentsArray.get( i );
                AppointmentsArray p1 = ( AppointmentsArray )appointmentsArray.get( i - 1 );

                if( p1.darHoraStart().compareToIgnoreCase(p2.darHoraStart()) > 0 )
                {
                	appointmentsArray.set( i, p1 );
                	appointmentsArray.set( i - 1, p2 );
                }
         }
    }	    
}

}
