
package com.example.asus.Core;

/*import com.example.asus.torrenttesting.announce.Announce;
import com.example.asus.torrenttesting.announce.AnnounceResponseListener;*//*
import com.example.asus.torrenttesting.base.Peer;
import com.example.asus.torrenttesting.base.Torrent;
//import com.example.asus.torrenttesting.client.SharedTorrent;
import com.example.asus.torrenttesting.util.Utils;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.UUID;
public class TorrentsManagingService extends Service {
    private final Binder mBinder = new MyBinder();
    private MainActivityCallBacks mListener;
    //private AnnounceResponseListener announceResponseListener;
    private static final int NOTIFICATION_ID = 1;
    private static final String ACTION_STOP_SERVICE = "stopPlease";
    InetAddress selfAdress=null;
    Thread IpFindThread;
    //Announce announcer=null;
    private static final String BITTORRENT_ID_PREFIX = "-TO0042-";
    @Override
    public void onCreate() {
        super.onCreate();
        Intent activityIntent = new Intent(this,MainActivity.class);
        activityIntent.setAction(Intent.ACTION_MAIN);
        activityIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PendingIntent activityPintent = PendingIntent.getActivity(this,0,activityIntent,0);
        Intent  stopSelf = new Intent(this,TorrentsManagingService.class);
        stopSelf.setAction(ACTION_STOP_SERVICE);
        PendingIntent stopselfPintent =  PendingIntent.getService(this,0,stopSelf,0);
        NotificationCompat.Builder builder = new  NotificationCompat.Builder(this);
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        builder.setContentText("ContentTex");
        builder.setContentTitle("ContentTitle");
        builder.setContentIntent(activityPintent);
      //  builder.setSmallIcon(R.mipmap.ic_launcher);
       // builder.addAction(R.mipmap.ic_launcher_round,"stop",stopselfPintent);
        startForeground(NOTIFICATION_ID,builder.build());
        IpFindThread = new Thread(new Runnable() {
            @Override
            public void run() {
               selfAdress= Utils.getLocalIpAddress();
            }
        });
        IpFindThread.start();
    }
    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {
        if(ACTION_STOP_SERVICE.equals(intent.getAction())){
            stopSelf();
        }
        return Service.START_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    if(mListener!=null) {
                        mListener.UpdateText(getText());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        T = new Thread(r);
        T.start();
        return mBinder;
    }
    public class MyBinder extends Binder{
        TorrentsManagingService getService() {
            return TorrentsManagingService.this;
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(IpFindThread!=null)
            IpFindThread.interrupt();
        //announcer.stop();
    }
    public void RegisterActivity(MainActivityCallBacks listener){
        mListener=listener;
    }
  */
/*  public  void RegisterAnnounceListener(AnnounceResponseListener announceResponseListener){
        this.announceResponseListener = announceResponseListener;
    }*//*
    public void UnregisterActivity(){
        mListener=null;
    }
    public InetAddress getIp(){
        return selfAdress;
    }
 */
/*   public void start_announcer(SharedTorrent torrent){
        String id = BITTORRENT_ID_PREFIX + UUID.randomUUID()
                .toString().split("-")[4];
        try {
            ByteBuffer buffer = ByteBuffer.wrap(id.getBytes(Torrent.BYTE_ENCODING));
            Peer peer = new Peer(selfAdress.getHostAddress(),49152, buffer);
            announcer = new Announce(torrent,peer);
            announcer.register(announceResponseListener);
            announcer.start();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }*//*
    public static interface  MainActivityCallBacks {
        public abstract void UpdateText(String text);
    }
}
*/