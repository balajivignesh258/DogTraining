package com.bvbv.dogtraining;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bvbv.location.AppLocationService;
import com.bvbv.location.ILocationAddressPostExecute;
import com.bvbv.weather.AsyncWeatherRetrieve;
import com.bvbv.weather.IWeatherPostExecute;
import com.bvbv.weather.Weather;
import com.bvbv.location.AsyncLocationAddressRetrieve;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    boolean isWeatherProcessComplete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setActivityBackgroundColor(Color.WHITE);
        if(!isWeatherProcessComplete && isNetworkAvailable())
            diplayWeatherInformation();

    }

    private boolean isNetworkAvailable() {
        System.out.println("Came to isNetworkAvailable");
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        System.out.println("Result:-->" + (activeNetworkInfo != null && activeNetworkInfo.isConnected()));
        if(!(activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()))
        {
            System.out.println("Result inside:-->" + (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()));
            ((TextView) findViewById(R.id.t_temperature)).setText("No network access..");
            Toast.makeText(getApplicationContext(), "No network access..",
                    Toast.LENGTH_SHORT).show();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void diplayWeatherInformation() {
        final IWeatherPostExecute iWeatherPostExecute = new IWeatherPostExecute() {
            @Override
            public void doDelegate(Weather weather) {
                float temperature = Float.parseFloat(weather.getTemperature());
                temperature = temperature - 273.15f;
                ((TextView) findViewById(R.id.t_temperature)).setText("" + weather.getCity() + " " + temperature + "°C" + "\nLooks like " + weather.getDescription() + "\nPlan your training!");

                int id = getResources().getIdentifier(getApplicationContext().getPackageName() + ":drawable/" + "weather_" + weather.getIconCode() , null, null);
                if(id==0)
                    ((ImageView)findViewById(R.id.imageView1)).setImageDrawable(weather.getBitmapImage());
                else
                  ((ImageView)findViewById(R.id.imageView1)).setImageResource(id);
                isWeatherProcessComplete = true;
            }
        };

        ILocationAddressPostExecute iLocationAddressPostExecute = new ILocationAddressPostExecute() {
            @Override
            public void doDelegate(String city) {
                Object []object = new Object[2];
                object[0] = city;
                System.out.println("The retrieved city from location is " + city);
                object[1] = iWeatherPostExecute;
                new AsyncWeatherRetrieve().execute(object);
            }
        };

        double latitude = 0f;
        double longitude  = 0f;

        AppLocationService appLocationService = new AppLocationService(getApplicationContext());
        Location gpsLocation = appLocationService
                .getLocation(LocationManager.NETWORK_PROVIDER);
        if (gpsLocation != null) {
            latitude = gpsLocation.getLatitude();
            longitude = gpsLocation.getLongitude();
            Object params[] = new Object[4];
            params[0] = latitude;
            params[1] = longitude;
            params[2] = getApplicationContext();
            params[3] = iLocationAddressPostExecute;
            new AsyncLocationAddressRetrieve().execute(params);
        } else {
            ((TextView) findViewById(R.id.t_temperature)).setText("Unable to fetch weather..\nEnable location services?");
            //Toast.makeText(getApplicationContext(), "Could not retrieve the location information!",
                    //Toast.LENGTH_SHORT).show();
            //showSettingsAlert();
            (findViewById(R.id.t_temperature)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(
                            Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    MainActivity.this.startActivity(intent);
                }
            });
        }
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

    public void openRollOverActivity(View view) {
        vibrateForPress();
        Intent intent = new Intent(this, Preparing.class);
        intent.putExtra("activityToOpen", "RollOver");
        startActivity(intent);
    }

    public void openFetchActivity(View view) {
        vibrateForPress();
        Intent intent = new Intent(this, Preparing.class);
        intent.putExtra("activityToOpen", "Fetch");
        startActivity(intent);
    }

    public void openHeelActivity(View view) {
        vibrateForPress();
        Intent intent = new Intent(this, Preparing.class);
        intent.putExtra("activityToOpen", "Heel");
        startActivity(intent);
    }

    public void openMoreActivity(final View view) {
        vibrateForPress();
        final Context context = view.getContext();
        new AlertDialog.Builder(context)
        .setTitle("Dog Training")
        .setMessage("We are pleased that you like this app. There will be more things coming soon." +
                "Please support us by rating our app in the play store.")
        .setPositiveButton("Later", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // do nothing
            }
        })
        .setNegativeButton("Rate", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
                }
            }
        })
        .setIcon(R.mipmap.ic_launcher)
        .show();
    }

    public void vibrateForPress()
    {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(16);
    }

    public void openAboutPage(MenuItem item) {
        Intent intent = new Intent(this, AboutPage.class);
        startActivity(intent);
        /*LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View inflatedView = layoutInflater.inflate(R.layout.activity_about_page, null,false);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        PopupWindow popWindow = new PopupWindow(inflatedView, size.x - 100,size.y - size.y/2, true );
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(true);
        popWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_home));
        popWindow.showAtLocation(findViewById(R.id.main_layout), Gravity.BOTTOM, 0,150);  // 0 - X postion and 150 - Y position*/

    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                MainActivity.this);
        alertDialog.setTitle("SETTINGS");
        alertDialog.setMessage("Enable location services! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        MainActivity.this.startActivity(intent);
                }
                });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        (findViewById(R.id.card_view_weather)).setVisibility(View.GONE);
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!isWeatherProcessComplete) {
            ((TextView)findViewById(R.id.t_temperature)).setText("Welcome....!");
            if(isNetworkAvailable())
            {
                (findViewById(R.id.t_temperature)).setOnClickListener(null);
                diplayWeatherInformation();
            }
        }
    }
}
