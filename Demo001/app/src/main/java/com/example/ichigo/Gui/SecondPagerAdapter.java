package com.example.ichigo.Gui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class SecondPagerAdapter extends FragmentPagerAdapter {
    public SecondPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return StatusFragment.newInstance("","");
            case 1:
                return Files.newInstance("torrent","");
            case 2:
                return TrackersFragment.newInstance("","");
            case 3:
                return PeersFragment.newInstance("","");
            case 4:
                return PiecesFragment.newInstance("","");
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
