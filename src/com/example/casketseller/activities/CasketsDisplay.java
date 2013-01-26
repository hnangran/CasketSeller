package com.example.casketseller.activities;

import java.util.ArrayList;

import com.example.casketseller.R;
import com.example.casketseller.backend.Casket;
import com.example.casketseller.utilities.AndroidUtilities;
import com.example.casketseller.utilities.CasketDownloader;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class CasketsDisplay extends Activity {

	ArrayList<Casket> caskets;

	final CasketDownloader c = new CasketDownloader();

	LinearLayout myGallery;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_caskets_display);

		caskets = CasketDownloader.getCaskets();

		myGallery = (LinearLayout) findViewById(R.id.mygallery);
		for (Casket cas : CasketDownloader.getCaskets()) {

			myGallery.addView(insertPhoto(cas));

		}
		LinearLayout layout = new LinearLayout(getApplicationContext());
		layout.setOrientation(1);
		layout.setLayoutParams(new LayoutParams(70, 50));
		layout.setGravity(Gravity.CENTER);
		Button b = new Button(getApplicationContext());
		b.setText("...");
		b.setGravity(Gravity.CENTER);
		b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            }
        });
		layout.addView(b);
		myGallery.addView(layout);		
		initializeImageView(caskets.get(0));
	}

	private void initializeImageView(Casket cas) {
		AndroidUtilities.setBitmapToImageView(
				(ImageView) findViewById(R.id.centerCasket), cas.getImage());
    	TextView centerCasketDetails = (TextView) findViewById(R.id.centerCasketDesc);
    	centerCasketDetails.setText(cas.getName() + ", " + 
				cas.getExteriorMaterial() + ", $" + cas.getPrice());
	}

	View insertPhoto(final Casket cas) {
		final Bitmap bm = cas.getImage();
		LinearLayout layout = new LinearLayout(getApplicationContext());
		layout.setOrientation(1);
		layout.setLayoutParams(new LayoutParams(120, 120));
		layout.setGravity(Gravity.CENTER);

		ImageView imageView = new ImageView(getApplicationContext());
		imageView.setLayoutParams(new LayoutParams(110, 80));
		imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		imageView.setImageBitmap(bm);
		imageView.setPadding(imageView.getPaddingLeft(), 5, imageView.getPaddingRight(),1 );
		layout.addView(imageView);
		TextView textView = new TextView(getApplicationContext());
		textView.setLayoutParams(new LayoutParams(120, 40));
		
		textView.setTextColor(Color.BLUE);
		textView.setText("$"+cas.getPrice());
		textView.setIncludeFontPadding(false);
		layout.addView(textView);
		
		layout.setOnClickListener( new OnClickListener() {

            @Override
            public void onClick(View imageView) {
            	initializeImageView(cas);
            }
        });
		return layout;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_caskets_display, menu);
		return true;
	}
	
	public void onClick(View view){
		AndroidUtilities.setBitmapToImageView(
				(ImageView) findViewById(R.id.centerCasket), Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888));
		
	}

}
