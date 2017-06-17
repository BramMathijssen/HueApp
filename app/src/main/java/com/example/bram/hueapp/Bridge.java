package com.example.bram.hueapp;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Bram on 17-6-2017.
 */

public class Bridge {

    /**
     * Singleton-referentie.
     */
    private static Bridge INSTANCE;

    /**
     * Tag t.b.v. logging.
     */
    public final String TAG = this.getClass().getName();

    /**
     * Basisdeel van url naar Philips Hue Bridge.
     */
    private static final String BASE_URL = "http://philips-hue/api/";

    /**
     * Gebruikersnaam.
     */
    private static final String USERNAME = "gpk1-oAKQTmYIuasZOaUlvL9qwTDfpZ23waW2SL2";

    /**
     * Private constructor (Singleton pattern).
     */
    private Bridge() {
    }

    /**
     * Singleton-referentie.
     *
     * @return steeds dezelfde Bridge-instantie.
     */
    public static final Bridge getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Bridge();
        return INSTANCE;
    }

    /**
     * Retourneer een dummy lijst met lampen. In de volledige versie zouden we hier
     * de Bridge gaan ondervragen over de werkelijk aangesloten lampen (via een
     * HTTP GET op /api/lights).
     *
     * @return lijst van alle aangesloten lampen.
     */
    public ArrayList<Light> getLights() {
        ArrayList<Light> l = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            int id = i + 1;
            Light light = new Light(this, Integer.toString(id));
            light.setDescription("LED " + id);
            l.add(i, light);
        }
        return l;
    }

    /**
     * Overloaded methode voor schrijven van een boolean-waarde.
     *
     * @param id    lamp id
     * @param key   JSON key
     * @param value JSON boolean-waarde
     */
    public void write(String id, String key, boolean value) {
        try {
            JSONObject o = new JSONObject();
            o.put(key, value);
            write(id, o);
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    /**
     * Overloaded methode voor schrijven van een integer-waarde.
     *
     * @param id    lamp id
     * @param key   JSON key
     * @param value JSON integer-waarde
     */
    public void write(String id, String key, int value) {
        try {
            JSONObject o = new JSONObject();
            o.put(key, value);
            write(id, o);
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    /**
     * Overloaded methode voor schrijven van een String-waarde.
     *
     * @param id    lamp id
     * @param key   JSON key
     * @param value JSON String-waarde
     */
    public void write(String id, String key, String value) {
        try {
            JSONObject o = new JSONObject();
            o.put(key, value);
            write(id, o);
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    /**
     * Private methode om het schrijven van de HTTP REST requests te laten gebeuren
     * door een subclass van AsyncTask.
     *
     * @param id   lamp id
     * @param data JSON data-object
     */
    private void write(String id, JSONObject data) {
        try {
            URL url = new URL(BASE_URL + USERNAME + "/lights/" + id + "/state/");
            LightWriter w = new LightWriter(data);
            w.execute(url);
        } catch (IOException e) {
            //TODO: nette foutafhandeling
        }
    }

    /**
     * LightWriter is van AsyncTask afgeleid, en verzorgt de communicatie met de Bridge
     * voor één instantie van Light. Dit gebeurt op een andere thread dan de UI-thread.
     */
    private class LightWriter extends AsyncTask<URL, Void, String> {

        private final JSONObject data;

        public LightWriter(JSONObject data) {
            this.data = data;
        }

        @Override
        protected String doInBackground(URL... params) {
            try {
                // Bouw de HTTP-connectie op en bereid een HTTP PUT request voor.
                HttpURLConnection c = (HttpURLConnection) params[0].openConnection();
                c.setRequestMethod("PUT");
                c.setDoOutput(true);
                c.connect();

                // Schrijf de JSON data in de request body.
                DataOutputStream d = new DataOutputStream(c.getOutputStream());
                d.writeBytes(data.toString());
                Log.d(TAG, data.toString());

                // Lees het resultaat dat de Bridge retourneert.
                BufferedReader b = new BufferedReader(new InputStreamReader(c.getInputStream()));
                String r = b.readLine();
                Log.d(TAG, r);
                d.close();
                c.disconnect();
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {

        }
    }
}
