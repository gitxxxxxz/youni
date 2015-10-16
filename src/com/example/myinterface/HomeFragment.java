package com.example.myinterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import com.example.myinterface.me;




import model.Search_out;

import com.example.myinterface.MeFragment.NewButtonListener;
import com.example.search.searchpanel;
import com.example.sqlite.Younidb;
import com.example.zz.R;
import com.example.zz.first_inside_Adapter;
import com.example.zz.R.drawable;
import com.example.zz.R.id;
import com.example.zz.R.layout;


//import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class HomeFragment extends Fragment {
	private Younidb younidb;
	public  final static String SER_KEY = "com.tutor.objecttran.ser"; 
	private List<String>dataList=new ArrayList<String>();
	private List<Search_out> searchoutList;
	private List<firstinside> firstinsideList=new ArrayList<firstinside>();
	private firstinside info;
	private String to;
	private String[] values = new String[] { "Item1","Item2","Item3","Item4" };  
	private int[] images = new int[] { R.drawable.showpic,  R.drawable.showpic, R.drawable.showpic,R.drawable.showpic }; 
    
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState) {  
        View view = inflater.inflate(R.layout.home, container, false);  
        Button button1 = (Button) view.findViewById(R.id.search);
        button1.setOnClickListener(new NewButtonListener()); 
        return view;  
	}
	
	  class NewButtonListener implements OnClickListener{  
		   
	        @Override  
	        public void onClick(View arg0) {  
	            // TODO Auto-generated method stub  
	        	Intent intent = new Intent();  
	            intent.setClass(getActivity(), searchpanel.class);  
	            getActivity().startActivity(intent); 
	        }  
	          
	    } 
	  
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		  List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();  
	        for (int i = 0; i < values.length; i++) {  
	            Map<String, Object> listItem = new HashMap<String, Object>();  
	            listItem.put("values", values[i]);  
	            listItem.put("images", images[i]);  
	            listItems.add(listItem);  
	        }  

		initfirstinsides();

		first_inside_Adapter adapter = new first_inside_Adapter(getActivity(), listItems,  
	                R.layout.first_inside, new String[] { "values" , "images"},  
	                new int[] { R.id.id, R.id.show });  
	        setListAdapter(adapter);  
	        
	        //  �����Ҳ�֪��adapter��Ҫ����ʲô  
	        
		
		
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				info= firstinsideList.get(position);
				to=info.getDetailed();
//				younidb.loadSearch_out(detailed, to);
				Search_out search_out1 = new Search_out();  
				search_out1.setDetailed(to); 
				//Intent intent=new Intent (HomeFragment.this,detailed_information.class);
				
				Intent intent = new Intent();  
	            intent.setClass(getActivity(), detailed_information.class);  
	            
	            
//		        Intent mIntent = new Intent(this,ObjectTranDemo1.class);  
		        Bundle mBundle = new Bundle();  
		        mBundle.putSerializable(SER_KEY,search_out1);  
		        intent.putExtras(mBundle);  
				//startActivity(intent);
				getActivity().startActivity(intent); 
			}
		}
		
//		Button me=(Button)findViewById(R.id.me);
//		me.setOnClickListener(new OnClickListener(){
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent intent=new Intent(HomeFragment.this,com.example.myinterface.MeFragment.class);
//				
//				startActivity(intent);
//				Intent intent = new Intent();  
//	           
//			}
//			
//		});
	

	private void initfirstinsides() {
		younidb=Younidb.getInstance(this);
		searchoutList=younidb.loadSearch_out();
		// TODO Auto-generated method stub
		if(searchoutList.size()>0){
			dataList.clear();
			for(Search_out search_out:searchoutList){
				dataList.add(search_out.getName());
				firstinside fi1=new firstinside(search_out.getName(),R.drawable.showpic,search_out.getDetailed());
				firstinsideList.add(fi1);
				
			}
		}}
	
	

}