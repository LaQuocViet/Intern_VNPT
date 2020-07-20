package uet.internandroid.music.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import uet.internandroid.music.Adapter.SongAdapter;
import uet.internandroid.music.Interface.ILoadMore;
import uet.internandroid.music.Model.Song;
import uet.internandroid.music.R;

public class PlaylistActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SongAdapter songAdapter;
    private List<Song> listSongs;
    private Handler handler;
    private int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        addListSongs();
        recyclerView();
    }

    private void recyclerView() {
        handler = new Handler();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        songAdapter = new SongAdapter(listSongs, getApplicationContext(), recyclerView);
        songAdapter.setLoadMore(new ILoadMore() {
            @Override
            public void onLoadMore() {
                position = recyclerView.getLayoutManager().getItemCount() - 1;//9
                listSongs.add( position ,null);
                songAdapter.notifyItemInserted(position);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listSongs.remove(position);
                        songAdapter.notifyItemRemoved(position);
                        int start = position + 1; // 10 20
                        int totalItemCount = position + 11;//20 30
                                //10               // 20
                        for (int i = start; i <= totalItemCount; i++) {
                            if (totalItemCount <= listSongs.size()) {
                                songAdapter.setTotalItemCount(totalItemCount);
                            }
                            else {
                                songAdapter.setIsEndList(true);
                                Toast.makeText(PlaylistActivity.this, "End List!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        songAdapter.notifyDataSetChanged();
                        songAdapter.setLoading();
                    }
                }, 3000);
            }
        });
        recyclerView.setAdapter(songAdapter);
    }

    private void addListSongs() {
        listSongs = new ArrayList<>();
        for (int i = 0; i < 50; i++){
            listSongs.add(new Song("Em không sai, chúng ta sai " + (i + 1), "Erik", R.drawable.adele));
        }
    }
}
