package com.bvbv.dogtraining;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bvbv.weather.AsyncWeatherRetrieve;
import com.bvbv.weather.IWeatherPostExecute;
import com.bvbv.weather.Weather;
import com.bvbv.weather.WeatherHttpClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setActivityBackgroundColor(Color.WHITE);
        IWeatherPostExecute iWeatherPostExecute = new IWeatherPostExecute() {
            @Override
            public void doDelegate(Weather weather) {
                float temperature = Float.parseFloat(weather.getTemperature());
                temperature = temperature - 273.15f;
                //Toast.makeText(MainActivity.this, "Looks like the temperature is " + temperature,
                       // Toast.LENGTH_LONG).show();
                ((TextView) findViewById(R.id.t_temperature)).setText("The current temperature is "+
                        temperature + "°C" + "\nLooks like " + weather.getDescription() + "\nPerfect day for training!");

                ((ImageView)findViewById(R.id.imageView1)).setImageDrawable(weather.getBitmapImage());

            }
        };

        Object []object = new Object[2];
        object[0] = "Coimbatore";
        object[1] = iWeatherPostExecute;
        new AsyncWeatherRetrieve().execute(object);
    }

    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, ActivitySettings.class);
            intent.putExtra("activityToOpen", "Listen");
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openPreparingActivity(View view) {
        vibrateForPress();
        Intent intent = new Intent(this, Preparing.class);
        intent.putExtra("activityToOpen", "Preparing");
        startActivity(intent);
    }

    public void openQuickTipsActivity(View view) {
        vibrateForPress();
        Intent intent = new Intent(this, Preparing.class);
        intent.putExtra("activityToOpen", "QuickTips");
        startActivity(intent);
    }

    public void openSitActivity(View view) {
        vibrateForPress();
        Intent intent = new Intent(this, Preparing.class);
        intent.putExtra("activityToOpen", "Sit");
        startActivity(intent);
    }

    public void openDownActivity(View view) {
        vibrateForPress();
        Intent intent = new Intent(this, Preparing.class);
        intent.putExtra("activityToOpen", "Down");
        startActivity(intent);
    }

    public void openStayActivity(View view) {
        vibrateForPress();
        Intent intent = new Intent(this, Preparing.class);
        intent.putExtra("activityToOpen", "Stay");
        startActivity(intent);
    }

    public void openListenActivity(View view) {
        vibrateForPress();
        Intent intent = new Intent(this, Preparing.class);
        intent.putExtra("activityToOpen", "Listen");
        startActivity(intent);
    }

    public void vibrateForPress()
    {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(16);
    }
}
