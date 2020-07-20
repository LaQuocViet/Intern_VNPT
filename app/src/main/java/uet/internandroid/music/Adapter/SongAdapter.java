package uet.internandroid.music.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uet.internandroid.music.Interface.ILoadMore;
import uet.internandroid.music.Model.Song;
import uet.internandroid.music.R;

public class SongAdapter extends RecyclerView.Adapter {
    private List<Song> listSongs;
    private Context context;
    private final int VIEW_ITEM_PLAYLIST = 1;
    private final int VIEW_PROGRESSBAR = 0;
    private int visibleThreshold = 5;
    private int lastVisibleItem;
    private int totalItemCount = 10;
    private Boolean loading = false;
    private ILoadMore loadMore;
    private boolean isEndList = false;

    public SongAdapter(List<Song> listSongs, Context context, RecyclerView recyclerView) {
        this.listSongs = listSongs;
        this.context = context;
        final LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if (!loading && totalItemCount <= (visibleThreshold + lastVisibleItem) &&  ! isEndList) {
                    if (loadMore != null) {
                        loadMore.onLoadMore();
                    }
                    loading = true;
                }
            }
        });
    }

    public void setIsEndList (boolean isEndList) {
        this.isEndList = isEndList;
    }

    public void setTotalItemCount(int totalItemCount) {
        this.totalItemCount = totalItemCount;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public void setLoading() {
        this.loading = false;
    }

    public void setLoadMore(ILoadMore loadMore) {
        this.loadMore = loadMore;
    }

    public class SongViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgSong;
        private TextView txtNameSong;
        private TextView txtNameSinger;
        private ImageButton imgButtonDetail;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSong = itemView.findViewById(R.id.imgSong);
            txtNameSong = itemView.findViewById(R.id.txtnameSong);
            txtNameSinger = itemView.findViewById(R.id.txtnameSinger);
            imgButtonDetail = itemView.findViewById(R.id.imgViewDetail);
        }
    }

    public class LoadmoreViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar progressBar;

        public LoadmoreViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == VIEW_ITEM_PLAYLIST) {
            View itemView = inflater.inflate(R.layout.item_playlist_layout, parent, false);
            viewHolder = new SongViewHolder(itemView);
        }
        else if (viewType == VIEW_PROGRESSBAR) {
            View itemView = inflater.inflate(R.layout.loadmore_layout, parent, false);
            viewHolder = new LoadmoreViewHolder(itemView);
        }
        return viewHolder;
}

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SongViewHolder) {
            ((SongViewHolder) holder).imgSong.setImageResource(listSongs.get(position).getmImgSong());
            ((SongViewHolder) holder).txtNameSong.setText(listSongs.get(position).getmNameSong());
            ((SongViewHolder) holder).txtNameSinger.setText(listSongs.get(position).getmNameSinger());
        } else if (holder instanceof LoadmoreViewHolder) {
            ((LoadmoreViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return totalItemCount;
    }

    @Override
    public int getItemViewType(int position) {
        return (listSongs.get(position) == null) ? VIEW_PROGRESSBAR : VIEW_ITEM_PLAYLIST;
    }
}
