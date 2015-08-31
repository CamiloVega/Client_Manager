package com.Clients;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Fechas implements Serializable {
	  private static final long serialVersionUID = 100L;
	    /**
	     * 
	     */

	    private String service;
	    private String fechas;
	    private String dia;
	    private String mes;
	    private String anio;
	    private double precio;
	    private boolean pago;

	    //-----------------------------------------------------------------
	    // Constructores
	    //-----------------------------------------------------------------

	    /**
	     *
	     */
	    public Fechas(  String reference1,String fechasStudent, double precio1, boolean pago1 ) 
	    {	       
	        service= reference1;
	        fechas=fechasStudent.trim();
	        precio=precio1;
	        pago=pago1;
	        String[] splited=fechas.split("/");
	        dia=splited[1];
	        mes=splited[0];
	        anio=splited[2];
	    }

	    //-----------------------------------------------------------------
	    // Métodos
	    //-----------------------------------------------------------------

	    /**
	     * Retorna el nombre del equipo
	     * @return nombre
	     */

	    public String darFecha( )
		    {
		        return fechas;
		    }
	    public void setFecha(String fecha){
	    	fechas=fecha;
	    }
	    public String darService( )
		    {
		        return service;
		    }
	    public double darPago( )
	    {
	    	

	        return precio;
	    }
	    public boolean darPagoRealizado( )
	    {
	        return pago;
	    }
	    public void setPago(boolean pago1)
	    {
	        pago=pago1;
	    }
	    public String darDia(){
	    	return dia;
	    }
	    public String darMes(){
	    	return mes;
	    }
	    public String darAnio(){
	    	return anio;
	    }
	    /**
	     * Retorna una representación como cadena de caracteres del equipo
	     * @return nombre
	     */

}
