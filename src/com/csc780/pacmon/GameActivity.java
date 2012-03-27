package com.csc780.pacmon;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class GameActivity extends Activity implements SensorEventListener{
	
	
	private GameSurfaceView gameView;
	private SensorManager mySensorManager;
	private Sensor myAccelerometer;
	
	//change in x and y of pac-mon
	private float xAccel;
	private float yAccel;
	private GameEngine gameEngine;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mySensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        myAccelerometer = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mySensorManager.registerListener(this, myAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        gameEngine = new GameEngine();
        gameView = new GameSurfaceView(this, gameEngine.pacmon, gameEngine);

        setContentView(gameView);
        
    }

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		gameView.pause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		gameView.resume();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//gameView.pause();
	}


	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	//get values of accelerometer
	public void onSensorChanged(SensorEvent event) {
		
		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		xAccel = event.values[0];
		yAccel = event.values[1];
		//float z = event.values[2];
		
		if(yAccel < -1.8F && yAccel*yAccel > xAccel*xAccel){ // tilt up
			gameEngine.setInputDir(1);
			//gameView.setDir(1);
		}
		if(yAccel > 1.8F && yAccel*yAccel > xAccel*xAccel){ // tilt down
			gameEngine.setInputDir(2);
			//gameView.setDir(2);
		}
		if (xAccel < -1.8F && xAccel * xAccel > yAccel * yAccel) { // tilt to
																	// right
			gameEngine.setInputDir(3);
			//gameView.setDir(3);
		}
		if (xAccel > 1.8F && xAccel * xAccel > yAccel * yAccel) { // tilt to
																	// left
			gameEngine.setInputDir(4);
			//gameView.setDir(4);
		}

		
		
	}
    
    
    
    
}