package uet.internandroid.music.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import uet.internandroid.music.Adapter.SongAdapter;
import uet.internandroid.music.Model.Song;
import uet.internandroid.music.R;

public class MainActivity extends AppCompatActivity {
//    private RecyclerView recyclerView;
//    private SongAdapter songAdapter;
//    private List<Song> songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        addSongtoSongList();
//        songAdapter = new SongAdapter(songList, getApplicationContext());
//        recyclerView.setAdapter(songAdapter);
    }

//    private void addSongtoSongList() {
//        songList = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            songList.add(new Song(R.drawable.ic_launcher, "Song " + (i + 1), "Singer "+ (i + 1)));
//        }
//    }

}
