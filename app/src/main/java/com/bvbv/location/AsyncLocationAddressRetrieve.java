package com.bvbv.location;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Balaji Vignesh on 12-Nov-16.
 */
public class AsyncLocationAddressRetrieve extends AsyncTask<Object, Void, Object[]> {
    @Override
    protected Object[] doInBackground(Object... params) {
        String city = "";
        double latitude = (double) params[0];
        double longitude  = (double) params[1];

        Geocoder geocoder = new Geocoder((Context) params[2], Locale.getDefault());
        String result = null;
        try {
            List<Address> addressList = geocoder.getFromLocation(
                    latitude, longitude, 1);
            if (addressList != null && addressList.size() > 0) {
                Address address = addressList.get(0);
                city = address.getLocality();
            }
        } catch (IOException e) {
            System.out.print("Async Location Address Retrieve : " + e.getMessage());
        }
        Object object[] = new Object[2];
        object[0] = city;
        object[1] = params[3];
        return object;
    }

    protected void onPostExecute(Object[] object) {
        ((ILocationAddressPostExecute)object[1]).doDelegate((String)object[0]);
    }
}
