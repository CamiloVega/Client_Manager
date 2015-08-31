package com.Clients;

import java.io.Serializable;



public class AppointmentsArray implements Serializable{
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    private static final long serialVersionUID = 100L;

    private String name="";
    private String reference="";
    private String horaStart="";
    private String horaEnd="";
    private int nHoras;
    private boolean type;


    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Construye una nueva instancia de un equipo
     * @param nombreEquipo Es el nombre del equipo. nombreEquipo != null
     */
    public AppointmentsArray( String nombreStudent , String class1, String hora1, int horas, boolean type1) 
    {
        name = nombreStudent.trim();
        reference=class1.trim();
        horaStart=hora1.trim();
       nHoras=horas;
       type=type1;
        determinarHoraEnd();

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

    public String darClass( )
    {
    	if (reference==null){
    		reference="";
    	}
        return reference.trim();
    }
    
    public String darHoraStart( )
    {
    	if (horaStart==null){
    		horaStart="";
    	}
        return horaStart.trim();
    }
    public String darHoraEnd( )
    {
    	if (horaEnd==null){
    		horaEnd="";
    	}
        return horaEnd.trim();
    }
    public boolean darType(){
    	return type;
    }
   public void determinarHoraEnd(){
	   String temp=horaStart.split(":")[0];
	   int temp1= Integer.parseInt(temp);
	   temp1+=nHoras;
	   if (temp1>24){
		   temp1-=23;
	   }
	   horaEnd=temp1+":"+horaStart.split(":")[1];
   }
    
}
