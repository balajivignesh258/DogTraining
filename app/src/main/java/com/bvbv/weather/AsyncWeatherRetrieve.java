package com.bvbv.weather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Balaji Vignesh on 07-Nov-16.
 */

public class AsyncWeatherRetrieve extends AsyncTask<Object, Void, Object[]> {

    private Exception exception;

    protected Object[] doInBackground(Object... params) {
        WeatherHttpClient weatherHttpClient = new WeatherHttpClient();
        String data =  weatherHttpClient.getWeatherData((String)params[0]);
        IWeatherPostExecute iWeatherPostExecute = (IWeatherPostExecute)params[1];
        String iconCode = null;
        Drawable imageData = null;
        try {
            JSONObject reader = new JSONObject(data);
            System.out.println("Reader data :" +  reader);
            iconCode = reader.getJSONArray("weather").getJSONObject(0).getString("icon");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(iconCode!=null)
        {
            imageData= weatherHttpClient.getImage(iconCode);
        }
        else {
            System.out.println("iconCode is null");
        }

        Object []object = new Object[3];
        object[0] = data;
        object[1] = iWeatherPostExecute;
        object[2] = imageData;
        return object;
    }

    protected void onPostExecute(Object[] object) {
        Weather weather = new Weather();
        weather.setTemperature("");
        weather.setDescription("");
        weather.setBitmapImage(null);
        try {
            JSONObject reader = new JSONObject((String)object[0]);
            JSONObject main  = reader.getJSONObject("main");
            weather.setTemperature(main.getString("temp"));
            JSONArray weatherJson = reader.getJSONArray("weather");
            weather.setDescription(weatherJson.getJSONObject(0).getString("description"));
            weather.setBitmapImage((Drawable)object[2]);
            ((IWeatherPostExecute)object[1]).doDelegate(weather);
        }catch (Exception e)
        {
            System.out.println("Exception in parsing JSon object " + e.getMessage());
        }
    }
}