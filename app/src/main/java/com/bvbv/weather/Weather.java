package com.bvbv.weather;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.location.Location;

/**
 * Created by Balaji Vignesh on 07-Nov-16.
 */
public class Weather {
    private String temperature = "";
    private String description = "";
    private Drawable drawableImage = null;
    private String city = "";
    private String iconCode = "";

    public void setTemperature(String temperature)
    {
        this.temperature = temperature;
    }
    public String getTemperature()
    {
        return this.temperature;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getDescription()
    {
        return this.description;
    }
    public void setBitmapImage(Drawable drawableImage)
    {
        this.drawableImage = drawableImage;
    }
    public Drawable getBitmapImage()
    {
        return this.drawableImage;
    }

    public void setCity(String city)
    {
        this.city = city;
    }
    public String getCity()
    {
        return this.city;
    }
    public void setIconCode(String iconCode)
    {
        this.iconCode = iconCode;
    }
    public String getIconCode()
    {
        return this.iconCode;
    }

}
