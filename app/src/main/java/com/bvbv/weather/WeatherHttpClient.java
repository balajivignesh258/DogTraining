package com.bvbv.weather;

import android.graphics.drawable.Drawable;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Balaji Vignesh on 07-Nov-16.
 */
public class WeatherHttpClient {

    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static String IMG_URL = "http://openweathermap.org/img/w/";
    private static final String weatherAppID = "b40fafea6db264d0274d4decd4c68e6c";

    public String getWeatherData(String location) {
        System.out.println("Get weather data has been called");
        HttpURLConnection con = null ;
        InputStream is = null;
        String appID = "&APPID=" + weatherAppID;

        try {
            con = (HttpURLConnection) ( new URL(BASE_URL + location + appID)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            // Let's read the response
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();


            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ( (line = br.readLine()) != null )
                buffer.append(line + "rn");

            is.close();
            con.disconnect();
            return buffer.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }

        return null;

    }

    public Drawable getImage(String code) {
        HttpURLConnection con = null ;
        InputStream is = null;
        try {
            System.out.println("Get image has been called");
            con = (HttpURLConnection) ( new URL(IMG_URL + code + ".png")).openConnection();
            con.setRequestMethod("GET");
            con.connect();
            is = con.getInputStream();
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Drawable d = Drawable.createFromStream(is,"Image Name");
            while ( is.read(buffer) != -1)
                baos.write(buffer);
            return d;
        }
        catch(Throwable t) {
            System.out.println("Exception in getting the image icon----------->");
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }

        return null;

    }
}