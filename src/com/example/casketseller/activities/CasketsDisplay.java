package com.example.casketseller.activities;

import com.example.casketseller.R;
import com.example.casketseller.R.layout;
import com.example.casketseller.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CasketsDisplay extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_caskets_display);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.activity_caskets_display, menu);
    return true;
  }

}
