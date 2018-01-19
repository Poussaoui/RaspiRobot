package com.raspirobot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import com.raspirobot.R;

public class MainActivity extends Activity {

	TextView textResponse;
	EditText editTextAddress, editTextPort;
	Button buttonConnect, buttonClear;
	private InterstitialAd interstitial;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		setContentView(R.layout.activity_main);
		editTextAddress = (EditText)findViewById(R.id.address);
		editTextPort = (EditText)findViewById(R.id.port);
		buttonConnect = (Button)findViewById(R.id.connect);
		buttonConnect.setOnClickListener(buttonConnectOnClickListener);

		AdView adView = (AdView)this.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);

		interstitial = new InterstitialAd(this);
		interstitial.setAdUnitId(getResources().getString(R.string.Interstitial_ad_unit_id));
		AdRequest adRequest2 = new AdRequest.Builder().build();
		interstitial.loadAd(adRequest2);

		interstitial.setAdListener(new AdListener() {
			public void onAdLoaded() {
				displayInterstitial();
			}
		});

	}
	OnClickListener buttonConnectOnClickListener =
			new OnClickListener(){

				@Override
				public void onClick(View v) {
					if(!(editTextAddress.getText().toString().equals("") && editTextPort.getText().toString().equals(""))){
						Intent act2 = new Intent(v.getContext(),Dashboard.class);
						act2.putExtra("ipAdress",editTextAddress.getText().toString());
						act2.putExtra("port", Integer.parseInt(editTextPort.getText().toString()));
						startActivity(act2);
					}else
						Toast.makeText(getApplication(), "Please enter a valid IP Adress and Port numbre", Toast.LENGTH_SHORT).show();

				}};

	public void displayInterstitial() {
		if (interstitial.isLoaded()) {
			interstitial.show();
		}
	}
}
