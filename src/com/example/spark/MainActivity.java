package com.example.spark;

import android.app.Activity;
import android.os.Bundle;
import org.json.*;
import java.io.*;
import android.widget.*;
import android.view.*;
import android.text.*;
import android.graphics.*;
import android.os.*;
import android.net.*;
import android.content.*;

public class MainActivity extends Activity {

	TextView t1,t2;
	String json = null;
	JasonParser jp;
	boolean con = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		t1 = (TextView)findViewById(R.id.t1);
		t2 = (TextView)findViewById(R.id.t2);
		//beginJsonParsing();
		t2.setText("Devloped By : \n        Rishav Singh \n\n Co Devloped By : \n        Soyam Singh \n   Aman singh");
		t2.setTextColor(Color.RED);
		json = loadJSONFromAsset();
		/*
		query();
		try{
		if(isOnline())
		{
			jp = new JasonParser();
			//json = jp.request("http://friended-correspond.000webhostapp.com/app/userlist.json");	
			json = jp.request("http://localhost:8080/userlist.json");
			con = true;
			
			
		}else{
			Toast.makeText(getApplicationContext(),"Internet is not Available",Toast.LENGTH_SHORT).show();
			con = false;
		}}catch(Exception e)
		{
			e.printStackTrace();
		}
		*/
	}
	private void beginJsonParsing(String array,String title) {
		try {
			t1.setText(title);
			t2.setText("");
			// get JSONObject from JSON file
			JSONObject obj = new JSONObject(json);

			// fetch JSONArray named users
			JSONArray userArray = obj.getJSONArray(array);

			// implement for loop for getting users list data
			for (int i = 0; i < userArray.length(); i++) {
				try {
					// create a JSONObject for fetching single user data
					JSONObject userDetail = userArray.getJSONObject(i);
					// fetch email and name and store it in arraylist
					String name = userDetail.getString("ques");
					String email = userDetail.getString("answ");
					//String con = String.valueOf( Html.fromHtml("<font color = \"yellow\">"+email+"</font>"));
					t2.append(name+"   :   "+email+"\n\n");
					t2.setTextColor(Color.WHITE);
				}catch (JSONException e){
					e.printStackTrace();
				}
			}}catch (JSONException e) {
			e.printStackTrace();
		}}
	public String loadJSONFromAsset() {
		
		try {
			InputStream is = getAssets().open("userlist.json");
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			json = new String(buffer, "UTF-8");
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		return json;
	}
	public void query()
	{
		if(android.os.Build.VERSION.SDK_INT > 9)
		{
			StrictMode.ThreadPolicy p = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(p);
		}
	}
	private boolean isOnline()
	{
		ConnectivityManager cm = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] nf = cm.getAllNetworkInfo();
		for(NetworkInfo info:nf)
		{
			
			{}
			if(info.isConnected()==true)
			//if(info.getTypeName().equalsIgnoreCase("mobile data"))if(info.isConnected())
				{
					Toast.makeText(getApplicationContext(),"Internet is Available",Toast.LENGTH_SHORT).show();
					return true;
				}
			//if(info.getTypeName().equalsIgnoreCase("wifi data"))if(info.isConnected())
					//return true;
			
		}
		//if(nf!=null && nf){
			//loadJSONFromAsset();
			//return true;
			
			//}
				//jp = new JasonParser();
				//json = jp.request("http://localhost:8080/userlist.json");	
			return false;
		//for(NetworkInfo info:nf){if(info.getTypeName().equalsIgnoreCase("mobile data"))if(info.isConnected())}
	}
	@Override 
	public boolean onCreateOptionsMenu(Menu menu) { 
		//inflate the menu resource file in your activity 
		getMenuInflater().inflate(R.menu.menu, menu); 
		return true; 
	} 

	@Override 
	public boolean onOptionsItemSelected(MenuItem item) { 
		//code for item select event handling 
		switch(item.getItemId()){ 
			case R.id.item1 : 
				beginJsonParsing("users","Country And Their Parliament");
				
			break; 
			case R.id.item2 : 
				if(con)beginJsonParsing("country","Country And Their Capital");
				else
					Toast.makeText(getApplicationContext(),"Please connect to internet",Toast.LENGTH_SHORT).show();
				break;
			case R.id.item3 :
				if(con)beginJsonParsing("song","Country And Their National Anthems");
				else
					Toast.makeText(getApplicationContext(),"Please connect to internet",Toast.LENGTH_SHORT).show();
				break;
			case R.id.item4 : 
				if(con)beginJsonParsing("revolution","Production And Their Revolutions");
				else
					Toast.makeText(getApplicationContext(),"Please connect to internet",Toast.LENGTH_SHORT).show();
				break;
			case R.id.item5 : 
				if(con)
					beginJsonParsing("book","Books And Their Authors");
				else
					Toast.makeText(getApplicationContext(),"Please connect to internet",Toast.LENGTH_SHORT).show();
				break;
			case R.id.item6 : 
				if(con)beginJsonParsing("invention","Invention And Their Inventors");
				else
					Toast.makeText(getApplicationContext(),"Please connect to internet",Toast.LENGTH_SHORT).show();
				break;
		} 
		return super.onOptionsItemSelected(item); 
	} 
}
