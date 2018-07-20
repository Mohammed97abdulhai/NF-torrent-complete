package com.example.ichigo.Gui;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.asus.Core.TorrentParser;
import com.example.asus.Core.base.Torrent;

import java.util.ArrayList;
import java.util.Set;

public class Torrent_Activity extends AppCompatActivity implements StatusFragment.OnFragmentInteractionListener,
        PiecesFragment.OnFragmentInteractionListener,
        TrackersFragment.OnFragmentInteractionListener,
        Files.OnFragmentInteractionListener,
        PeersFragment.OnFragmentInteractionListener,
        TorrentParser.ParserResultUpdateCallback{

    TabLayout tabLayout;
    ViewPager viewPager;

    Files filesFragment;
    StatusFragment statusFragment;
    TorrentParser test;
    ArrayList<String> files;
    Tracker_details [] trackers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torrent_);


        files = new ArrayList<>();
        files.add("motherfuck");

        Intent intent = getIntent();
        String name = intent.getExtras().getString("name");
        String path = intent.getExtras().getString("path");

        Uri uri = Uri.parse(path);
        Toolbar toolbar = (Toolbar) findViewById(R.id.torrent_toolbar);
        toolbar.setTitle(name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tabLayout = (TabLayout) findViewById(R.id.tablayout1);
        viewPager = (ViewPager) findViewById(R.id.secondpager);
        SecondPagerAdapter adapter = new SecondPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);


        test = new TorrentParser(this);
        test.RegisterParser(this);
        test.execute(uri);

        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.torrent_menu, menu);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void updateResults(Torrent torrent) {
        files = torrent.getFilenames();
        Set test = torrent.getAlltrackers();
        filesFragment.update(files);

    }

    public void setFilesFragment(Files listener){
        this.filesFragment= listener;
    }


    class Tracker_details{
        Set<String> tracker_name;
        boolean tracker_state;
    }
}