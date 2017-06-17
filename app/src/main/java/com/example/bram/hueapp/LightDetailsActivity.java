package com.example.bram.hueapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Bram on 17-6-2017.
 */

public class LightDetailsActivity extends AppCompatActivity {

    /**
     * Tag t.b.v. logging.
     */
    public final String TAG = this.getClass().getName();

    /**
     * Referentie naar de (singleton) Bridge.
     */
    private Bridge bridge = Bridge.getInstance();

    /**
     * Referentie naar de betreffende Light-instantie die we gaan bewerken.
     */
    private Light light;

    /**
     * Label met de omschrijving van de lamp.
     */
    private TextView label;

    /**
     * Regelaar voor de hue.
     */
    private SeekBar hueSeekBar;

    /**
     * Regelaar voor de verzadiging.
     */
    private SeekBar saturationSeekBar;

    /**
     * Regelaar voor de helderheid.
     */
    private SeekBar brightnessSeekBar;

    /**
     * Lifecycle method onCreate()
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_details);

        // Bundle uitlezen en de index van de juiste Lights-instantie vinden. Hiermee
        // de juiste Lights-instantie ophalen bij de Bridge.
        Bundle extras = getIntent().getExtras();
        int i = extras.getInt("INDEX");
        light = bridge.getLights().get(i);
        label = (TextView) findViewById(R.id.labelTextView);
        label.setText(light.getDescription());

        // Seek bar voor hue.
        hueSeekBar = (SeekBar) findViewById(R.id.hueSeekBar);
        hueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                light.setHue(hueSeekBar.getProgress());
            }
        });

        // Seek bar voor verzadiging.
        saturationSeekBar = (SeekBar) findViewById(R.id.saturationSeekBar);
        saturationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                light.setSaturation(saturationSeekBar.getProgress());
            }
        });

        // Seek bar voor helderheid.
        brightnessSeekBar = (SeekBar) findViewById(R.id.brightnessSeekBar);
        brightnessSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                light.setBrightness(brightnessSeekBar.getProgress());
            }
        });
    }

}