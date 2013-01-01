package com.example.casketseller.activities;

import com.example.casketseller.R;

import android.net.Uri;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import com.example.casketseller.R.id;
import com.example.casketseller.R.layout;
import com.example.casketseller.utilities.AndroidUtilities;
import com.example.casketseller.utilities.Constants;

public class MainActivity extends Activity {

  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ((TextView) findViewById(R.id.error)).setVisibility(View.INVISIBLE);

  }

  public void onLogin(View v) {

    String userName = AndroidUtilities
            .getStringFromEditText((EditText) findViewById(R.id.username));
    String password = AndroidUtilities
            .getStringFromEditText((EditText) findViewById(R.id.password));

    // access database to check if user exists
    if (checkUserExists(userName, password)) {
      // direct to next activity
      startActivity(new Intent(Constants.Activities.searchScreen.toString()));

    } else
      // else user does not exist, display error message
      ((TextView) findViewById(R.id.error)).setVisibility(View.VISIBLE);

  }

  private boolean checkUserExists(String string, String string2) {
    // TODO Auto-generated method stub
    return true;
  }

}
