package com.ClientManagerFull;

import java.util.ArrayList;

import com.Clients.Clients;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

public class ClientListAdapter extends BaseAdapter
{
	private Context context;
	private ArrayList<Clients> data = null;
	private float scale = 1.0f;

	public ClientListAdapter(Context context)
	{
		super();
		this.context = context;
		this.data = new ArrayList<Clients>();
        this.scale = context.getResources().getDisplayMetrics().density;

	}
	
	public void addAddress(Clients client)
	{
		data.add(client);
	}
	
	public Clients getInfo(int pos)
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
		ClientListView view = null;
		
		if (convertView != null)
			view = (ClientListView)convertView;
		else
			view = new ClientListView(context,scale);
		
		view.setData((data.get(pos)));
		
		return view;
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}	
}
