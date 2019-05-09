package com.anu.devlopers3k.androidthingsrainbowhat;

/**
 * Created by anurajr on 27/12/2017.
 */

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.things.contrib.driver.rainbowhat.RainbowHat;
import com.google.android.things.pio.Gpio;

import java.io.IOException;

/**
 * The project is very simple.
 * Just blink the led in the RainbowHAT
 */
public class MainActivity extends Activity {

    Handler handler;
    Gpio led;
    boolean value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            //choose the green led in the board.
            led = RainbowHat.openLedGreen();
            led.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
            handler = new Handler(getMainLooper());
            value =true;

            //loop for blinking the led
            runAndSchedule();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runAndSchedule() {
        try {
            led.setValue(value);
            value =!value;
        } catch (IOException e) {
            e.printStackTrace();
        }
        handler.postDelayed(this::runAndSchedule,300);

    }
}


