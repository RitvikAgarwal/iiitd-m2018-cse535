package com.mobcomp.ritvik.assignment4;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



//References
// https://developer.android.com/guide/topics/sensors/sensors_overview
// https://developer.android.com/guide/topics/sensors/sensors_motion
// https://code.tutsplus.com/tutorials/using-the-accelerometer-on-android--mobile-22125
// https://code.tutsplus.com/tutorials/android-sensors-in-depth-proximity-and-gyroscope--cms-28084
// https://developer.android.com/guide/topics/sensors/sensors_position
// https://stuff.mit.edu/afs/sipb/project/android/docs/guide/topics/sensors/sensors_position.html
// https://developer.android.com/guide/topics/location/strategies

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager senMan;
    private Sensor accellero;
    private Sensor gyro;
    private Sensor orient;
    LocationManager locationManager;
    private Sensor proxima;

    private long timeLast = 0;
    private float last_x, last_y, last_z;
    private static final int threshShake = 600;

    TextView acc_x;
    TextView acc_y;
    TextView acc_z;

    TextView gyro_x;
    TextView gyro_y;
    TextView gyro_z;

    TextView orient_x;
    TextView orient_y;
    TextView orient_z;

    TextView longi;
    TextView lati;

    TextView prox1;

    Button loggingButton;
    boolean logOrNot = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        acc_x = findViewById(R.id.acc_x);
        acc_y = findViewById(R.id.acc_y);
        acc_z = findViewById(R.id.acc_z);

        gyro_x = findViewById(R.id.gyro_x);
        gyro_y = findViewById(R.id.gyro_y);
        gyro_z = findViewById(R.id.gyro_z);

        orient_x = findViewById(R.id.orient_x);
        orient_y = findViewById(R.id.orient_y);
        orient_z = findViewById(R.id.orient_z);

        longi = findViewById(R.id.gps_longi);
        lati = findViewById(R.id.gps_lati);

        prox1 = findViewById(R.id.proxVal);

        loggingButton = findViewById(R.id.LogOrNot);
        loggingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                logOrNot = !logOrNot;
                if (logOrNot) {
                    Toast.makeText(MainActivity.this, "Started logging", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Stopped logging!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        senMan = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accellero = senMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyro = senMan.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        orient = senMan.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                double longitude = location.getLongitude();
                double latitude = location.getLatitude();

                longi.setText(Double.toString(longitude));
                lati.setText(Double.toString(latitude));

            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

        proxima = senMan.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if(proxima == null) {
            Log.e("Sensor acquiring!", "Proximity sensor not available.");
            finish(); // Close app
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sense = event.sensor;

        if (sense.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            acc_x.setText(Float.toString(x));
            acc_y.setText(Float.toString(y));
            acc_z.setText(Float.toString(z));


            long timeNow = System.currentTimeMillis();
            if (timeNow - timeLast > 100) {
                long timeDiff = timeNow - timeLast;
                timeLast = timeNow;
                float speed = Math.abs(x + y + z - last_x - last_y - last_z)/timeDiff * 8000;
                if (speed > threshShake){
                    Toast.makeText(this, "Shake detected!!", Toast.LENGTH_SHORT).show();

                }
                last_x = x;
                last_y = y;
                last_z = z;
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    SensorEventListener gyroscopeSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            // More code goes here
            float val1 = sensorEvent.values[0];
            float val2 = sensorEvent.values[1];
            float val3 = sensorEvent.values[2];

            gyro_x.setText(Float.toString(val1));
            gyro_y.setText(Float.toString(val2));
            gyro_z.setText(Float.toString(val3));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };


    SensorEventListener OrientSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            // More code goes here
            float val1 = sensorEvent.values[0];
            float val2 = sensorEvent.values[1];
            float val3 = sensorEvent.values[2];

            orient_x.setText(Float.toString(val1));
            orient_y.setText(Float.toString(val2));
            orient_z.setText(Float.toString(val3));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };


    SensorEventListener proximitySensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float val1 = sensorEvent.values[0];
            if (val1 <= 5) {
                Toast.makeText(MainActivity.this, "Orientation\nx: " + orient_x.getText().toString()
                        + " y: " + orient_y.getText().toString() + " z: " + orient_z.getText().toString()
                        , Toast.LENGTH_SHORT).show();
            }

            prox1.setText(Float.toString(val1));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };

    // Register it, specifying the polling interval in
    // microseconds


    @Override
    protected void onPause() {
        super.onPause();
        senMan.unregisterListener(this);
        senMan.unregisterListener(proximitySensorListener);
        senMan.unregisterListener(OrientSensorListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        senMan.registerListener(this, accellero, senMan.SENSOR_DELAY_NORMAL);
        senMan.registerListener(gyroscopeSensorListener,
                gyro, SensorManager.SENSOR_DELAY_NORMAL);
        senMan.registerListener(OrientSensorListener, orient, SensorManager.SENSOR_DELAY_NORMAL);


        senMan.registerListener(proximitySensorListener, proxima, 2 * 1000 * 1000);
    }
}
