package com.ClientManagerFull;

import java.util.ArrayList;

import com.Clients.Appointments;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class AppointmentAdapter extends BaseAdapter
{
	private Context context;
	private ArrayList<Appointments> data = null;
	private float scale = 1.0f;

	public AppointmentAdapter(Context context)
	{
		super();
		this.context = context;
		this.data = new ArrayList<Appointments>();
        this.scale = context.getResources().getDisplayMetrics().density;

	}
	
	public void addUpcoming(Appointments appointment)
	{
		data.add(appointment);
	}
	
	public Appointments getInfo(int pos)
	{
		return data.get(pos);
	}
	
	public void remove(int pos)
	{
		data.remove(pos);
	}
	
	public int getCount()
	{
		return data.size();
	}
	
	public Object getItem(int pos)
	{
		return data.get(pos);
	}
	

	
	public View getView(int pos, View convertView, ViewGroup parent)
	{
		AppointmentView view = null;
		
		if (convertView != null)
			view = (AppointmentView)convertView;
		else
			view = new AppointmentView(context,scale,data.get(pos).darNumberOfAppointments());
		
		view.setData((data.get(pos)));
		
		return view;
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}	
}
