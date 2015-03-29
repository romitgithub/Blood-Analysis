package com.example.bloodanalysis;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowImage extends Activity implements OnClickListener{
	
	ImageView capturedImage;
	String imagePath;
	TextView processImageButton;
	Bitmap bmp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ActionBar mActionBar = getActionBar();
		mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#C0392B")));
		mActionBar.setLogo(R.drawable.blood_white);
		
		setContentView(R.layout.activity_show_image);
		
		Intent intent = getIntent();
		this.imagePath = intent.getStringExtra("image_path");
		
		this.capturedImage = (ImageView)this.findViewById(R.id.capturedImage);
		this.processImageButton = (TextView)this.findViewById(R.id.processImageButton);
		
		this.processImageButton.setOnClickListener(this);
		
		this.bmp = BitmapFactory.decodeFile(this.imagePath);
		this.capturedImage.setImageBitmap(this.bmp);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_image, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View view) {
		if(view == this.processImageButton)
		{
			int width = this.bmp.getWidth();
			int height = this.bmp.getHeight();	
			
			int rgbval[] = new int[width*height];
			
			this.bmp.getPixels(rgbval, 0, width, 0, 0, width, height);
			
			int rgbval2[] = actual(rgbval);

			Bitmap bmp2=bmp.copy(Bitmap.Config.ARGB_8888,true);
			bmp2.setPixels(rgbval2, 0, width, 0, 0, width, height);
			
			this.capturedImage.setImageBitmap(bmp2);

		}
	}
	private int[] actual(int rgb[]) {
	    int rgb2[] = new int[rgb.length];

	    for(int i=0; i<rgb.length; ++i) {
	        rgb2[i] = Color.argb(Color.alpha(rgb[i]), Color.red(rgb[i])/2,  
	                      Color.green(rgb[i])/2, Color.blue(rgb[i])/2);
	    }

	    return rgb2;
	}
}
