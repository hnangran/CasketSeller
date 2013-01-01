package com.example.casketseller.activities;

import com.example.casketseller.R;
import com.example.casketseller.R.layout;
import com.example.casketseller.R.menu;
import com.example.casketseller.utilities.Constants;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class SearchScreen extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search_screen);
  }

  public void onSearch(View v) {
    
    startActivity(new Intent(Constants.Activities.casketsDisplay.toString()));

    
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.activity_search_screen, menu);
    return true;
  }

}
