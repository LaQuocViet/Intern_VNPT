package uet.internandroid.music.Model;

public class Song {
    private String mNameSong;
    private int mImgSong;
    private int mFile;
    private String mNameSinger;

    public Song(String mNameSong, int mImgSong, int mFile) {
        this.mNameSong = mNameSong;
        this.mImgSong = mImgSong;
        this.mFile = mFile;
    }

    public Song (String mNameSong, String mNameSinger,int mImgSong ) {
        this.mNameSong = mNameSong;
        this.mNameSinger = mNameSinger;
        this.mImgSong = mImgSong;
    }

    public String getmNameSinger() {
        return mNameSinger;
    }

    public void setmNameSinger(String mNameSinger) {
        this.mNameSinger = mNameSinger;
    }

    public String getmNameSong() {
        return mNameSong;
    }

    public void setmNameSong(String mNameSong) {
        this.mNameSong = mNameSong;
    }

    public int getmImgSong() {
        return mImgSong;
    }

    public void setmImgSong(int mImgSong) {
        this.mImgSong = mImgSong;
    }

    public int getmFile() {
        return mFile;
    }

    public void setmFile(int mFile) {
        this.mFile = mFile;
    }
}
