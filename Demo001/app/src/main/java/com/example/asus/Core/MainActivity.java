/*
package com.example.asus.torrenttesting;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.PersistableBundle;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.asus.torrenttesting.announce.AnnounceResponseListener;
import com.example.asus.torrenttesting.base.Peer;
import com.example.asus.torrenttesting.base.Torrent;
import com.example.asus.torrenttesting.util.Utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
}
*/
/*        TorrentsManagingService.MainActivityCallBacks , TorrentParser.ParserResultUpdateCallback,AnnounceResponseListener{
    Button btn;
    TextView NameText,CreatedBy,intervalText;
    ListView listView;
    ArrayList<String> peers_list;
    ArrayAdapter<String> adapter;
    private TorrentsManagingService TorrentManager;
    boolean bound=false;
    TorrentParser parser;
    private  final int PICKFILE_RESULT_CODE = 1;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == PICKFILE_RESULT_CODE) {
                Uri uri = data.getData();
                String mimeType = getContentResolver().getType(uri);
                parser = new TorrentParser(this,TorrentManager);
                parser.RegisterParser(this);
                parser.execute(uri);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.nigger);
        NameText= (TextView) findViewById(R.id.Name);
        CreatedBy = (TextView) findViewById(R.id.CreatedBy);
        intervalText = (TextView) findViewById(R.id.interval);
         listView = (ListView) findViewById(R.id.listView1);
        peers_list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(MainActivity.this,R.layout.list_item,peers_list);
        listView.setAdapter(adapter);
        Intent intent0 = new Intent(getApplicationContext(),TorrentsManagingService.class);
        startService(intent0);
        Intent intent = new Intent(this,TorrentsManagingService.class);
        bindService(intent,this, Context.BIND_AUTO_CREATE);
        bound=true;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/x-bittorrent");
                startActivityForResult(intent, PICKFILE_RESULT_CODE);
            }
        });
    }
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(bound) {
            unbindService(this);
        }
    }
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        TorrentsManagingService.MyBinder tempBinder = (TorrentsManagingService.MyBinder) service;
        TorrentManager = tempBinder.getService();
        TorrentManager.RegisterActivity(this);
        TorrentManager.RegisterAnnounceListener(this);
    }
    @Override
    public void onServiceDisconnected(ComponentName name) {
        TorrentManager.UnregisterActivity();
        TorrentManager = null;
    }
    @Override
    public void UpdateText(final String text) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
    @Override
    public void updateResults(Torrent torrent) {
        NameText.setText(torrent.getName());
        CreatedBy.setText(torrent.getCreatedBy());
    }
    @Override
    public void handleAnnounceResponse(int interval, int complete, int incomplete) {
        final int interv = interval;
        final int compl = complete;
        final int incompl = incomplete;
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                intervalText.setText(String.valueOf(interv));
            }
        });
    }
    @Override
    public void handleDiscoveredPeers(List<Peer> peers) {
        final List<Peer> pers = peers;
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for(Peer peer : pers){
                    peers_list.add(peer.toString());
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}
*//*
*/