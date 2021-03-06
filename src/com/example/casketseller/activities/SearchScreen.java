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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class SearchScreen extends Activity {

  String selectedColor;

  String selectedExtMaterial;
  
  ProgressDialog dialog;
  
  protected void onStop(){
    
    super.onStop();
    
    if (dialog != null)
      dialog.dismiss();
  }
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search_screen);

    // Set up the Exterior Material selection spinner


    final String[] extMaterials = getResources().getStringArray(R.array.extMaterials);

    Spinner extMaterialSpinner = (Spinner) findViewById(R.id.extMaterialSpinner);

    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_item, extMaterials);

    extMaterialSpinner.setAdapter(adapter1);
    extMaterialSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        int index = arg0.getSelectedItemPosition();
        // Toast.makeText(getBaseContext(), "You have selected item : " + extMaterials[index],
        // Toast.LENGTH_SHORT).show();
        selectedExtMaterial = extMaterials[index];
      }

      @Override
      public void onNothingSelected(AdapterView<?> arg0) {
      }
    });

    // Set up the Color selection spinner

    final String[] casketColors = getResources().getStringArray(R.array.casketColors);

    Spinner colorSpinner = (Spinner) findViewById(R.id.colorSpinner);

    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_item, casketColors);

    colorSpinner.setAdapter(adapter2);
    colorSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        int index = arg0.getSelectedItemPosition();
        // Toast.makeText(getBaseContext(), "You have selected item : " + casketColors[index],
        // Toast.LENGTH_SHORT).show();
        selectedColor = casketColors[index];
      }

      @Override
      public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

      }

    });

  }

  public void onSearch(View v) {
    
//    EditText priceFrom = (EditText) findViewById(R.id.priceFrom);
//    String priceFromString = priceFrom.getText().toString();
//
//    EditText priceTo = (EditText) findViewById(R.id.priceTo);
//    String priceToString = priceTo.getText().toString();
//    
//    Intent displayCaskets = new Intent(SearchScreen.this, CasketsDisplay.class);
//    
//    displayCaskets.putExtra("exteriorMaterial", selectedExtMaterial);
//    displayCaskets.putExtra("color", selectedColor);
//    displayCaskets.putExtra("priceFrom", 
//            priceFromString.matches(".*\\d.*") ? Double.parseDouble(priceFromString) : (Double) 0.0);
//    displayCaskets.putExtra("priceTo", 
//            priceToString.matches(".*\\d.*") ? Double.parseDouble(priceToString) : (Double) 0.0);
//    displayCaskets.putExtra("casketName", ((EditText) findViewById(R.id.name)).getText().toString());
//
//    startActivity(displayCaskets);
    
    searchCaskets();
    
  }

  private void searchCaskets() {

    Double pTo = (double) 0;
    Double pFrom = (double) 0;
    String priceFromString = null;
    String priceToString = null;

    EditText casketName = (EditText) findViewById(R.id.name);
    String cName = casketName.getText().toString();

    EditText priceFrom = (EditText) findViewById(R.id.priceFrom);
    priceFromString = priceFrom.getText().toString();
    if (priceFromString.matches(".*\\d.*"))
      pFrom = Double.parseDouble(priceFromString);

    EditText priceTo = (EditText) findViewById(R.id.priceTo);
    priceToString = priceTo.getText().toString();
    if (priceToString.matches(".*\\d.*"))
      pTo = Double.parseDouble(priceToString);

    final CasketDownloader c = new CasketDownloader();
    c.setSearch(selectedExtMaterial, selectedColor, cName, pFrom, pTo);
    
    // get images from database
    dialog = ProgressDialog.show(this, "Retrieving Caskets",
            "Please wait ...", true);

    new Thread(new Runnable() {

      @Override
      public void run() {

        // c.downloadCaskets();

        if (c.downloadCaskets()) {
          while (!c.downloadComplete())
            ;

          dialog.dismiss();
          // direct to next view
          startActivity(new Intent(Constants.Activities.casketsDisplay.toString()));
        } else {
          dialog.dismiss();

        }
      }

    }).start();
    
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.activity_search_screen, menu);
    return true;
  }

}
