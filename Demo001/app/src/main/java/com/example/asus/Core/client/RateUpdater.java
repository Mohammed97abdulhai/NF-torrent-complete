package com.example.asus.Core.client;

import android.os.Handler;
import android.util.Log;

import com.example.asus.Core.peer.Rate;
import com.example.asus.Core.peer.RatePerSec;

/**
 * Created by Asus on 7/21/2018.
 */

public class RateUpdater implements Runnable{

    private Thread thread;
    private boolean stop;

    Handler handler ;


    RatePerSec downloadRate;
    RatePerSec uploadRate;


    public RateUpdater(Handler handler){

        this.handler = handler;
        thread = null;
        downloadRate = new RatePerSec();
        uploadRate = new RatePerSec();

    }

    public void stop() {
        this.stop = true;

        if (this.thread != null && this.thread.isAlive()) {
            try {
                this.thread.join();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }

        this.thread = null;
    }

    public void start(){

        stop=false;

        if (this.thread == null || !this.thread.isAlive()) {
            this.thread = new Thread(this);
            this.thread.setName("bt-serve");
            this.thread.start();
        }
    }


    @Override
    public void run() {
        if(!stop){


            Log.i("info","OMG FUCK U ICHIGO U SUCK DICKS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + "upRate:" +String.valueOf(downloadRate.get()/1024)+"downRate:" );

            handler.postDelayed(this, 1000);

        }


    }
    public void updatedownRate(long bytes){

        synchronized (downloadRate) {

            downloadRate.add(bytes);

        }
    }

    public void updateUpRate(long bytes){

        synchronized (uploadRate) {

            uploadRate.add(bytes);

        }
    }
}
