package com.ClientManagerFull;

import java.util.ArrayList;

import com.Clients.Clients;
import com.Clients.Fechas;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class PaymentsListAdapter extends BaseAdapter
{
	private Context context;
	private ArrayList<Fechas> data = null;
	private float scale = 1.0f;

	public PaymentsListAdapter(Context context)
	{
		super();
		this.context = context;
		this.data = new ArrayList<Fechas>();
        this.scale = context.getResources().getDisplayMetrics().density;

	}
	
	public void addPayments(Fechas fechas)
	{
		data.add(fechas);
	}
	
	public Fechas getInfo(int pos)
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
	
	public Fechas getItem(int pos)
	{
		return data.get(pos);
	}
	

	
	public View getView(int pos, View convertView, ViewGroup parent)
	{
		PaymentsListView view = null;
		
		if (convertView != null)
			view = (PaymentsListView)convertView;
		else
			view = new PaymentsListView(context,scale);
		
		view.setData((data.get(pos)));
		
		return view;
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}	
}
