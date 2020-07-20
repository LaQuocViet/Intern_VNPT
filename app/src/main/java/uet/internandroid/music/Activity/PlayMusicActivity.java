package uet.internandroid.music.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import uet.internandroid.music.Model.Song;
import uet.internandroid.music.R;
import uet.internandroid.music.Service.MyService;

public class PlayMusicActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtSongTitle;
    private TextView txtSongCurrentDurationLabel;
    private TextView txtSongTotalDurationLabel;
    private ImageButton btnPlaylist;
    private ImageButton btnShuffle;
    private ImageButton btnRepeat;
    private ImageButton btnPrevious;
    private ImageButton btnBackward;
    private ImageButton btnPlay;
    private ImageButton btnForward;
    private ImageButton btnNext;
    private SeekBar sbSongProgressBar;
    private ImageView imgSong;
    private int positon = 0;
    private List<Song> listSong;
    private MediaPlayer mediaPlayer;
    private Intent serviceIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        serviceIntent = new Intent(getApplicationContext(), MyService.class);
       //startService(serviceIntent);
        mapping();
        addTolistSong();
        initMusic(positon);
        OnClickListener();
    }

    private void initMusic(int positon) {
        mediaPlayer = MediaPlayer.create(PlayMusicActivity.this, listSong.get(positon).getmFile());
        txtSongTitle.setText(listSong.get(positon).getmNameSong());
        imgSong.setImageResource(listSong.get(positon).getmImgSong());
        SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");
        txtSongTotalDurationLabel.setText(timeFormat.format(mediaPlayer.getDuration()));
        sbSongProgressBar.setMax(mediaPlayer.getDuration());
    }

    private void currentDuration () {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");
                txtSongCurrentDurationLabel.setText(timeFormat.format(mediaPlayer.getCurrentPosition()));
                sbSongProgressBar.setProgress(mediaPlayer.getCurrentPosition());
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        positon++;
                        updatePositon(positon);
                    }
                });
                handler.postDelayed(this, 500);

            }
        }, 100);
    }

    private void addTolistSong() {
        listSong = new ArrayList<>();
        listSong.add (new Song ("ai_la_nguoi_thuong_em", R.drawable.ic_launcher, R.raw.ai_la_nguoi_thuong_em));
        listSong.add (new Song ("am_tham_ben_em", R.drawable.ic_launcher, R.raw.am_tham_ben_em));
        listSong.add (new Song ("anh_nha_o_dau_the", R.drawable.ic_launcher, R.raw.anh_nha_o_dau_the));
        listSong.add (new Song ("anh_oi_o_lai", R.drawable.ic_launcher, R.raw.anh_oi_o_lai));
        listSong.add (new Song ("anh_sai_roi", R.drawable.ic_launcher, R.raw.anh_sai_roi));
        listSong.add (new Song ("bac_phan", R.drawable.ic_launcher, R.raw.bac_phan));
        listSong.add (new Song ("banh_mi_khong", R.drawable.ic_launcher, R.raw.banh_mi_khong));
        listSong.add (new Song ("binh_yeu_noi_dau", R.drawable.ic_launcher, R.raw.binh_yeu_noi_dau));
        listSong.add (new Song ("buon_lam_em_oi", R.drawable.ic_launcher, R.raw.buon_lam_em_oi));
        listSong.add (new Song ("chac_ai_do_se_ve", R.drawable.ic_launcher, R.raw.chac_ai_do_se_ve));
        listSong.add (new Song ("cho_anh_say", R.drawable.ic_launcher, R.raw.cho_anh_say));
        listSong.add (new Song ("chuyen_anh_van_chua_ke", R.drawable.ic_launcher, R.raw.chuyen_anh_van_chua_ke));
    }

    private void OnClickListener() {
        btnPlaylist.setOnClickListener(this);
        btnShuffle.setOnClickListener(this);
        btnRepeat.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);
        btnBackward.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        btnForward.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    private void mapping() {
        txtSongTitle = findViewById(R.id.songTitle);
        txtSongCurrentDurationLabel = findViewById(R.id.songCurrentDurationLabel);
        txtSongTotalDurationLabel = findViewById(R.id.songTotalDurationLabel);
        btnPlaylist = findViewById(R.id.btnPlaylist);
        btnShuffle = findViewById(R.id.btnShuffle);
        btnRepeat = findViewById(R.id.btnRepeat);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnBackward = findViewById(R.id.btnBackward);
        btnPlay = findViewById(R.id.btnPlay);
        btnForward = findViewById(R.id.btnForward);
        btnNext = findViewById(R.id.btnNext);
        sbSongProgressBar = findViewById(R.id.songProgressBar);
        imgSong = findViewById(R.id.imgSong);

    }

    private void playMusic () {
        if (mediaPlayer.isPlaying()) { //dang phat -> pause -> doi icon play
            mediaPlayer.pause();
            btnPlay.setImageResource(R.drawable.btn_play);
        }else { // dang dung -> phat -> doi icon pause
            mediaPlayer.start();
            btnPlay.setImageResource(R.drawable.btn_pause);
            currentDuration();
            sbSongProgressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    mediaPlayer.seekTo(sbSongProgressBar.getProgress());
                }
            });
        }
    }

    private void updatePositon (int positon) {
        if (positon > listSong.size() - 1) {
            positon = 0;
        } else if (positon < 0){
            positon = listSong.size() - 1;
        }

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        initMusic(positon);
        playMusic();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPlaylist:
                //Toast.makeText(PlayMusicActivity.this, "PlayList", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PlayMusicActivity.this, PlaylistActivity.class);
                startActivity(intent);
                break;
            case R.id.btnShuffle:
                Toast.makeText(PlayMusicActivity.this, "btnShuffle", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnRepeat:
                Toast.makeText(PlayMusicActivity.this, "btnRepeat", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnPrevious:
                positon--;
                updatePositon(positon);
                break;
            case R.id.btnBackward:
                Toast.makeText(PlayMusicActivity.this, "btnBackward", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnPlay:
                playMusic();
                break;
            case R.id.btnForward:
                Toast.makeText(PlayMusicActivity.this, "btnForward", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnNext:
                positon++;
                updatePositon(positon);
                break;
        }
    }
}
