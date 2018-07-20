package com.example.ichigo.Gui;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.example.asus.Core.base.Torrent;
import com.example.asus.Core.client.Client;
import com.example.asus.Core.client.SharedTorrent;
import com.example.asus.Core.util.Utils;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


public class TorrentManagingService extends Service implements ClientActivityListener{
    Map<Integer,Client>torrents = new HashMap<>();


    final static String GROUP_KEY_Messages = "group_key_messages";




    public void add_torrent(final Torrent torrent, final File desti,final int id) throws IOException, NoSuchAlgorithmException {

        final ClientActivityListener listener = this;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SharedTorrent sharedTorrent = new SharedTorrent(torrent,desti);
                    Client client = new Client(selfAdress,sharedTorrent,id);
                    client.registerObserver(listener);
                    client.download();
                    torrents.put(id,client);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

            }
        });
        t.start();





    }


    private IBinder mbinder = new MyBinder();

    InetAddress selfAdress=null;
    Thread IpFindThread;

    public TorrentManagingService() {

    }

    @Override
    public void onCreate()
    {

        IpFindThread = new Thread(new Runnable() {
            @Override
            public void run() {
                selfAdress= Utils.getLocalIpAddress();
                Log.i("info", selfAdress.getHostAddress().toString());
            }
        });
        IpFindThread.start();
    }
    private Notification getMyActivityNotification(String text){
        Intent notificationintent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 ,notificationintent,0);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("NF Torrent is running in the Background")
                .setContentText(text)
                .setGroup(GROUP_KEY_Messages)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentIntent(pendingIntent)
                .build();

        return notification;
    }
    private void updateNotification(String text){


        Notification notification=getMyActivityNotification(text);

        NotificationManager mNotificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1,notification);
    }
    @Override
    public int onStartCommand(Intent intent,int flags, int startID)
    {

       Notification notification = getMyActivityNotification("press here");

        startForeground(1,notification);

        NotificationManager d;


        return START_NOT_STICKY;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mbinder;
    }

    @Override
    synchronized public void handleMessage(String text) {

        updateNotification(text);
    }

    public class MyBinder extends Binder{
        public TorrentManagingService getservice()
        {
            return TorrentManagingService.this;
        }
    }
}
