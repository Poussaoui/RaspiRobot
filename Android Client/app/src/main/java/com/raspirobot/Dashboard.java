package com.raspirobot;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import com.raspirobot.R;

public class Dashboard extends Activity {

    ImageButton left,right,forward,backward,backimbtn ;
    Button buttonFullControl;
    Switch mySwitchObst,mySwitchLight,mySwitchBack;
    String ipAdress;
    int port;
    private InterstitialAd interstitial;
    public static Camera cam = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        left = (ImageButton)findViewById(R.id.leftBtn);
        right = (ImageButton)findViewById(R.id.rightBtn);
        forward = (ImageButton)findViewById(R.id.forwardBttn);
        backward = (ImageButton)findViewById(R.id.backwardBtn);
        mySwitchObst = (Switch) findViewById(R.id.switchObstacle);
        mySwitchLight = (Switch) findViewById(R.id.switchLight);
        mySwitchBack = (Switch) findViewById(R.id.switchBack);
        buttonFullControl = (Button)findViewById(R.id.buttonFullControl);
        backimbtn = (ImageButton)findViewById(R.id.backimbtn);
        Intent myIntent = getIntent();
        ipAdress = myIntent.getStringExtra("ipAdress");
        port= myIntent.getIntExtra("port", 0);
        Toast.makeText(getApplication(), "Connected To pi@" + ipAdress + ":" + port, Toast.LENGTH_SHORT).show();

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


        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "left", Toast.LENGTH_LONG).show();

                if (mySwitchBack.isChecked()) {
                    Client myClient = new Client(ipAdress, port, "leftB");
                    displayInterstitial();
                    myClient.execute();

                }else{
                    Client myClient = new Client(ipAdress, port, "left");
                    displayInterstitial();
                    myClient.execute();
                }

            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "right", Toast.LENGTH_LONG).show();

                if (mySwitchBack.isChecked()) {
                    Client myClient = new Client(ipAdress, port, "rightB");
                    displayInterstitial();
                    myClient.execute();

                } else {
                    Client myClient = new Client(ipAdress, port, "right");
                    displayInterstitial();
                    myClient.execute();

                }

            }
        });
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "forward", Toast.LENGTH_LONG).show();

                if (mySwitchBack.isChecked()) {

                    Client myClient = new Client(ipAdress,port,"forwardB");
                    myClient.execute();

                }else{

                    Client myClient = new Client(ipAdress,port,"forward");
                    displayInterstitial();
                    myClient.execute();
                }
            }
        });
        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "backward", Toast.LENGTH_LONG).show();
                if (mySwitchBack.isChecked()) {
                    Client myClient = new Client(ipAdress,port,"backwardB");
                    myClient.execute();
                }else{
                    Client myClient = new Client(ipAdress,port,"backward");
                    displayInterstitial();
                    myClient.execute();
                }

            }
        });

        mySwitchObst.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    new CountDownTimer(15000, 1000) {
                        public void onTick(long millisUntilFinished) {
                            final Toast toast1 = Toast.makeText(getApplicationContext(), "seconds remaining: " + millisUntilFinished / 1000, Toast.LENGTH_SHORT);
                            toast1.show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    toast1.cancel();
                                }
                            }, 400);
                        }

                        public void onFinish() {
                            mySwitchObst.setChecked(false);
                        }
                    }.start();
                    final Toast toast1 = Toast.makeText(getApplicationContext(), "Obstacle Sensor ON", Toast.LENGTH_LONG);
                    toast1.show();
                    displayInterstitial();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast1.cancel();
                        }
                    }, 600);
                    Client myClient = new Client(ipAdress, port, "obstOn");
                    myClient.execute();
                } else {
                    final Toast toast = Toast.makeText(getApplicationContext(), "Obstacle Sensor OFF", Toast.LENGTH_SHORT);
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 600);
                    Client myClient = new Client(ipAdress, port, "obstOff");
                    myClient.execute();
                }

            }
        });

        mySwitchLight.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    try {
                        if (getPackageManager().hasSystemFeature(
                                PackageManager.FEATURE_CAMERA_FLASH)) {
                            cam = Camera.open();
                            Parameters p = cam.getParameters();
                            p.setFlashMode(Parameters.FLASH_MODE_TORCH);
                            cam.setParameters(p);
                            cam.startPreview();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getBaseContext(), "Exception flashLightOn()",
                                Toast.LENGTH_SHORT).show();
                    }

                    new CountDownTimer(15000, 1000) {
                        public void onTick(long millisUntilFinished) {
                            final Toast toast = Toast.makeText(getApplicationContext(), "seconds remaining: " + millisUntilFinished / 1000, Toast.LENGTH_SHORT);
                            toast.show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    toast.cancel();
                                }
                            }, 400);
                        }

                        public void onFinish() {
                            mySwitchLight.setChecked(false);
                        }
                    }.start();

                    final Toast toast = Toast.makeText(getApplicationContext(), "Light Sensor ON", Toast.LENGTH_SHORT);
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 600);
                    Client myClient = new Client(ipAdress, port, "lightOn");
                    myClient.execute();
                } else {
                    try {
                        if (getPackageManager().hasSystemFeature(
                                PackageManager.FEATURE_CAMERA_FLASH)) {
                            cam.stopPreview();
                            cam.release();
                            cam = null;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getBaseContext(), "Exception flashLightOff",
                                Toast.LENGTH_SHORT).show();
                    }
                    final Toast toast = Toast.makeText(getApplicationContext(), "Light Sensor OFF", Toast.LENGTH_SHORT);
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 600);
                    Client myClient = new Client(ipAdress, port, "lightOff");
                    myClient.execute();
                }


            }
        });

        mySwitchBack.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    Toast.makeText(getApplicationContext(), "Back Action ON", Toast.LENGTH_SHORT).show();
                    mySwitchObst.setEnabled(false);
                    mySwitchObst.setChecked(false);
                    mySwitchLight.setEnabled(false);
                    mySwitchLight.setChecked(false);
                    backimbtn.setBackgroundColor(Color.TRANSPARENT);
                    backimbtn.setImageResource(R.drawable.back_btn);
                    backimbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mySwitchBack.isChecked()) {
                                Toast.makeText(getApplicationContext(), "Is going back", Toast.LENGTH_SHORT).show();
                                Client myClient = new Client(ipAdress, port, "viewpath");
                                myClient.execute();

                                mySwitchBack.setChecked(false);
                                backimbtn.setBackgroundColor(Color.TRANSPARENT);
                                backimbtn.setImageResource(R.drawable.circuit_imvw);
                            }
                        }
                    });

                } else {
                    Toast.makeText(getApplicationContext(), "Back Action OFF", Toast.LENGTH_LONG).show();
                    mySwitchObst.setEnabled(true);
                    mySwitchLight.setEnabled(true);
                    backimbtn.setBackgroundColor(Color.TRANSPARENT);
                    backimbtn.setImageResource(R.drawable.circuit_imvw);


                }

            }
        });



    }

    public void displayInterstitial() {
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }
}
