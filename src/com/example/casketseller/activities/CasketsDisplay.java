//package com.example.casketseller.activities;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//
//import com.example.casketseller.R;
//import com.example.casketseller.R.layout;
//import com.example.casketseller.R.menu;
//import com.example.casketseller.utilities.AndroidUtilities;
//import com.example.casketseller.utilities.CasketDownloader;
//import com.example.casketseller.utilities.Constants;
//import com.example.casketseller.utilities.ImageDownloader;
//
//import android.net.Uri;
//import android.os.Bundle;
//import android.app.Activity;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//import android.view.Menu;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.ViewGroup.LayoutParams;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.BaseAdapter;
//import android.widget.Gallery;
//import android.widget.ImageSwitcher;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.SpinnerAdapter;
//import android.widget.TextView;
//import android.widget.ViewSwitcher.ViewFactory;
//
//public class CasketsDisplay extends Activity implements ViewFactory {
//
//  ArrayList<Bitmap> casketImages;
//
//  final CasketDownloader c = new CasketDownloader();
//
//  @Override
//  protected void onCreate(Bundle savedInstanceState) {
//
//    super.onCreate(savedInstanceState);
//
//    setContentView(R.layout.activity_caskets_display);
//
//    casketImages = CasketDownloader.getCasketImages();
//
//    TextView xCasketsFound = (TextView) findViewById(R.id.xCasketsFound);
//    xCasketsFound.setText("" + casketImages.size() + " " + Constants.xCasketsFound);
//    
//    initializeGallery();
//    initializeImageView();
//  }
//
//  private void initializeGallery() {
//
//    // get reference to gallery
//    Gallery casketsList = (Gallery) findViewById(R.id.casketsList);
//
//    casketImages = c.getCasketImages();
//
//    // set adapter to handle images
//    casketsList.setAdapter(new ImageAdapter(this));
//
//    casketsList.setSpacing(10);
//
//    // set event handler
//    casketsList.setOnItemClickListener(new OnItemClickListener() {
//      public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//
//        ImageView centerCasket = (ImageView) findViewById(R.id.centerCasket);
//        centerCasket.setImageBitmap(casketImages.get(position));
//
//      }
//    });
//
//  }
//
//  private void initializeImageView() {
//    ImageView centerCasket = (ImageView) findViewById(R.id.centerCasket);
//    centerCasket.setImageBitmap(casketImages.get(0));
//  }
//
//  @Override
//  public boolean onCreateOptionsMenu(Menu menu) {
//    // Inflate the menu; this adds items to the action bar if it is present.
//    getMenuInflater().inflate(R.menu.activity_caskets_display, menu);
//    return true;
//  }
//
//  @Override
//  public View makeView() {
//
//    ImageView centerImage = new ImageView(this);
//    centerImage.setBackgroundColor(0xFF000000);
//    centerImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
//    centerImage.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT,
//            LayoutParams.FILL_PARENT));
//
//    return centerImage;
//
//  }
//
//  class ImageAdapter extends BaseAdapter {
//
//    private Context context;
//
//    ImageAdapter(Context c) {
//      super();
//      context = c;
//    }
//
//    @Override
//    public int getCount() {
//      return casketImages.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//      return position;
//    }
//
//    @Override
//    public long getItemId(int position) {
//      return position;
//    }
//
//    @Override
//    public View getView(int position, View v, ViewGroup vg) {
//
//      ImageView view = null;
//
//      if (v == null) {
//        view = new ImageView(context);
//        view.setImageBitmap(casketImages.get(position));
//        view.setLayoutParams(new Gallery.LayoutParams(150, 120));
//        view.setScaleType(ImageView.ScaleType.FIT_XY);
//        RelativeLayout borderImg = new RelativeLayout(context);
//        borderImg.setPadding(1, 1, 1, 1);
//        borderImg.setBackgroundColor(0xff000000);
//        borderImg.addView(view);
//        return borderImg;
//      } else
//        view = (ImageView) v;
//
//      return view;
//    }
//  }
//}

package com.example.casketseller.activities;

import java.util.ArrayList;

import com.example.casketseller.R;
import com.example.casketseller.backend.Casket;
import com.example.casketseller.utilities.AndroidUtilities;
import com.example.casketseller.utilities.CasketDownloader;
import com.example.casketseller.utilities.Constants;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

@SuppressWarnings("deprecation")
public class CasketsDisplay extends Activity implements ViewFactory {

  ArrayList<Casket> caskets;

  final CasketDownloader c = new CasketDownloader();

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_caskets_display);

    caskets = CasketDownloader.getCaskets();

    TextView xCasketsFound = (TextView) findViewById(R.id.xCasketsFound);
    xCasketsFound.setText("" + caskets.size() + " " + Constants.xCasketsFound);
    
    initializeGallery();
    initializeImageView();
  }

  private void initializeGallery() {

    // get reference to gallery
    Gallery casketsList = (Gallery) findViewById(R.id.casketsList);

    // set adapter to handle images
    casketsList.setAdapter(new ImageAdapter(this));

    casketsList.setSpacing(10);

    // set event handler
    casketsList.setOnItemClickListener(new OnItemClickListener() {
      public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        AndroidUtilities.setBitmapToImageView((ImageView) findViewById(R.id.centerCasket), caskets.get(position).getImage());
        TextView centerCasketDetails = (TextView) findViewById(R.id.centerCasketDetails);
        centerCasketDetails.setText(caskets.get(position).getExteriorMaterial() + " " + caskets.get(position).getPrice());
      }
    });

  }

  private void initializeImageView() {
    AndroidUtilities.setBitmapToImageView((ImageView) findViewById(R.id.centerCasket), caskets.get(0).getImage());
    TextView centerCasketDetails = (TextView) findViewById(R.id.centerCasketDetails);
    centerCasketDetails.setText(caskets.get(0).getExteriorMaterial() + " " + caskets.get(0).getPrice());
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.activity_caskets_display, menu);
    return true;
  }

  @Override
  public View makeView() {

    ImageView centerImage = new ImageView(this);
    centerImage.setBackgroundColor(0xFF000000);
    centerImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
    centerImage.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT,
            LayoutParams.FILL_PARENT));

    return centerImage;

  }

  class ImageAdapter extends BaseAdapter {

    private Context context;

    ImageAdapter(Context c) {
      super();
      context = c;
    }

    @Override
    public int getCount() {
      return caskets.size();
    }

    @Override
    public Object getItem(int position) {
      return position;
    }

    @Override
    public long getItemId(int position) {
      return position;
    }

    @Override
    public View getView(int position, View v, ViewGroup vg) {

      ImageView view = null;

      if (v == null) {
        view = new ImageView(context);
        AndroidUtilities.setBitmapToImageView(view, caskets.get(position).getImage());
        view.setLayoutParams(new Gallery.LayoutParams(150, 120));
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        RelativeLayout borderImg = new RelativeLayout(context);
        borderImg.setPadding(1, 1, 1, 1);
        borderImg.setBackgroundColor(0xff000000);
        borderImg.addView(view);
        return borderImg;
      } else
        view = (ImageView) v;

      return view;
    }
  }
}

