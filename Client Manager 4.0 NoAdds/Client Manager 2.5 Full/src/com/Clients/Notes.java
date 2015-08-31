package com.Clients;

import java.io.Serializable;

public class Notes implements Serializable {
	  private static final long serialVersionUID = 100L;
	  private String date;
	  private String note;
	  private String subject;
	  public Notes(  String date1,String note1, String subject1 ) 
	    {	
		  date=date1;
		  note=note1;
		  subject=subject1;
	    }
	  public String darText(){
		  return note;
	  }
	  public String darDate(){
		  return date;
	  }
	  public String darSubject(){
		  return subject;
	  }
	  public void editNote(String text, String date1, String subject1){
		  note=text;
		  date=date1;
		  subject=subject1;
	  }
}
