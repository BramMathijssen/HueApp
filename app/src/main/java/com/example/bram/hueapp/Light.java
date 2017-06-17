package com.example.bram.hueapp;

import android.util.Log;

/**
 * Created by Bram on 17-6-2017.
 */

public class Light {

    //Tag t.b.v logging
    private final String TAG = this.getClass().getName();

    private final Bridge bridge;
    private String id;
    private String description;
    private boolean on;
    private int hue;
    private int saturation;
    private int brightness;

    //Constructor
    public Light(Bridge bridge, String id){
        this.bridge = bridge;
        this.id = id;
    }

    /**
     * Retourneert het id (de String handle) van de lamp. N.b. dit is niet
     * het uniqueid zoals de Bridge dat retourneert!
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Retourneert de aan-toestand.
     *
     * @return aan/uit
     */
    public boolean isOn() {
        return on;
    }

    /**
     * Schakel de lamp in.
     */
    public void switchOn() {
        this.on = true;
        bridge.write(this.id, "on", true);
    }

    /**
     * Schakel de lamp uit.
     */
    public void switchOff() {
        this.on = false;
        bridge.write(this.id, "on", false);
    }

    /**
     * Retourneert de beschrijving van deze lamp.
     *
     * @return beschrijving
     */
    public String getDescription() {
        return description;
    }

    /**
     * Stelt de beschrijving van deze lamp in.
     *
     * @param description beschrijving
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Opvragen verzadiging.
     *
     * @return verzadiging
     */
    public int getSaturation() {
        return saturation;
    }

    /**
     * Instellen verzadiging.
     *
     * @param saturation verzadiging tussen 1 en 254.
     */
    public void setSaturation(int saturation) {
        if (saturation < 1)
            this.saturation = 1;
        else if (saturation > 254)
            this.saturation = 254;
        else
            this.saturation = saturation;
        Log.d(TAG, "Saturation of light " + id + " set to " + this.saturation + '.');
        bridge.write(this.id, "sat", this.saturation);
    }

    /**
     * Opvragen hue.
     *
     * @return hue
     */
    public int getHue() {
        return hue;
    }

    /**
     * Instellen hue.
     *
     * @param hue hue tussen 0 en 65535.
     */
    public void setHue(int hue) {
        if (hue < 0)
            this.hue = 0;
        else if (hue > 65535)
            this.hue = 65535;
        else
            this.hue = hue;
        Log.d(TAG, "Hue of light " + id + " set to " + this.hue + '.');
        bridge.write(this.id, "hue", this.hue);
    }

    /**
     * Opvragen helderheid.
     *
     * @return helderheid tussen 1 en 254.
     */
    public int getBrightness() {
        return brightness;
    }

    /**
     * Instellen helderheid.
     *
     * @param brightness helderheid tussen 1 en 254.
     */
    public void setBrightness(int brightness) {
        if (brightness < 1)
            this.brightness = 1;
        else if (brightness > 254)
            this.brightness = 254;
        else
            this.brightness = brightness;
        Log.d(TAG, "Brightness of light " + id + " set to " + this.brightness + '.');
        bridge.write(this.id, "bri", this.brightness);
    }

}
