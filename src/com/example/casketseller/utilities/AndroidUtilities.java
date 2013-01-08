package com.example.casketseller.utilities;

import java.util.ArrayList;

import com.example.casketseller.R;

import android.widget.EditText;
import android.widget.ImageView;
import android.app.ProgressDialog;
import android.graphics.Bitmap;

public class AndroidUtilities {
  
  public static String getStringFromEditText(EditText e){
    return e.getText().toString();
    
  }

  public static void setBitmapToImageView(ImageView view, Bitmap image) {
    view.setImageBitmap(image);    
  }
  
  
}
