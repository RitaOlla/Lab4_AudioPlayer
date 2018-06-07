package com.example.rita_ola.audioplayer;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Rita_Ola on 22.05.2018.
 */

public class PlayListActivity extends ListActivity {
    //Songs list
    public ArrayList<HashMap<String, String>> songsList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlist);

        ArrayList<HashMap<String, String>> songsListData = new ArrayList<>();

        SongsManager plm = new SongsManager();
        //get all songs from sdcard
        this.songsList = plm.getPlayList();

        //looping through playlist
        for(int i = 0; i < songsListData.size(); i++){
            //crating new HashMap
            HashMap<String, String> song = songsList.get(i);

            //adding HashMap to Array
            songsListData.add(song);
        }
        //Adding menuItems to ListView
        ListAdapter adapter = new SimpleAdapter(this, songsListData, R.layout.playlist_item, new String[]{"song title"}, new int[]{ R.id.songTitle});


        setListAdapter(adapter);

        //selecting single ListVIew item
        ListView lv = this.getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(getApplicationContext(), MusicPlayerActivity.class);
                in.putExtra("songIndex", position);
                setResult(100, in);
                finish();
            }
        });
    }
}
