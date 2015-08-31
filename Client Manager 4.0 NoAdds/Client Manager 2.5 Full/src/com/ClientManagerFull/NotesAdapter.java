package com.ClientManagerFull;

import java.util.ArrayList;

import com.Clients.Appointments;
import com.Clients.Notes;
import com.Clients.Notes;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class NotesAdapter extends BaseAdapter
{
	private Context context;
	private ArrayList<Notes> data = null;
	private float scale = 1.0f;

	public NotesAdapter(Context context)
	{
		super();
		this.context = context;
		this.data = new ArrayList<Notes>();
        this.scale = context.getResources().getDisplayMetrics().density;

	}
	
	public void addNote(Notes note)
	{
		data.add(note);
	}
	
	public Notes getInfo(int pos)
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
		NotesView view = null;
		
		if (convertView != null)
			view = (NotesView)convertView;
		else
			view = new NotesView(context,scale);
		
		view.setData((data.get(pos)));
		
		return view;
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}	
}
