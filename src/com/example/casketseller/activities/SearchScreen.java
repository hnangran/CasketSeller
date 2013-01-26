package com.example.casketseller.activities;

import com.example.casketseller.R;
import com.example.casketseller.R.layout;
import com.example.casketseller.R.menu;
import com.example.casketseller.utilities.CasketDownloader;
import com.example.casketseller.utilities.Constants;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class SearchScreen extends Activity {

	String[] casketColors;
	String[] extMaterials;
	String selectedColor;
	String selectedExtMaterial;
	
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search_screen);
    
    //Set up the Exterior Material selection spinner
    
    extMaterials = getResources().getStringArray(R.array.extMaterials);
    
    Spinner extMaterialSpinner = (Spinner)findViewById(R.id.extMaterialSpinner);
    
    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, 
    		android.R.layout.simple_spinner_item, extMaterials);
   
    extMaterialSpinner.setAdapter(adapter1);
    extMaterialSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
    {
    	@Override
    	public void onItemSelected(AdapterView<?> arg0, View arg1,
    			int arg2, long arg3)
    	{
    		int index = arg0.getSelectedItemPosition();
    		Toast.makeText(getBaseContext(), "You have selected item : " + extMaterials[index], 
    				Toast.LENGTH_SHORT).show();
    		selectedExtMaterial = extMaterials[index];
    	}
    	
    	@Override
    	public void onNothingSelected(AdapterView<?> arg0) {}
    });     
    
    //Set up the Color selection spinner
    
    casketColors = getResources().getStringArray(R.array.casketColors);
    
    Spinner colorSpinner = (Spinner)findViewById(R.id.colorSpinner);
    
    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, 
    		android.R.layout.simple_spinner_item, casketColors);
   
    colorSpinner.setAdapter(adapter2);
    colorSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
    {
    	@Override
    	public void onItemSelected(AdapterView<?> arg0, View arg1,
    			int arg2, long arg3)
    	{
    		int index = arg0.getSelectedItemPosition();
    		Toast.makeText(getBaseContext(), "You have selected item : " + casketColors[index], 
    				Toast.LENGTH_SHORT).show();
    		selectedColor = casketColors[index];
    	}
    	
    	@Override
    	public void onNothingSelected(AdapterView<?> arg0) {}
    });     
    
  }

  public void onSearch(View v) {

    final CasketDownloader c = new CasketDownloader();
    // c.setSearch();

    // get images from database
    final ProgressDialog dialog = ProgressDialog.show(this, "Doing something", "Please wait ...",
            true);

    new Thread(new Runnable() {

      @Override
      public void run() {

        c.downloadCaskets();

        while (!c.downloadComplete())
          ;

        dialog.dismiss();

        startActivity(new Intent(Constants.Activities.casketsDisplay.toString()));
      }

    }).start();

    // direct to next view

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.activity_search_screen, menu);
    return true;
  }

}
