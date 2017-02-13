package com.ykk.mediaPlay;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;
import java.io.File;
import java.io.IOException;
public class MainActivity extends Activity implements View.OnClickListener{
    private Button music_play;
    private  Button music_pause;
    private  Button music_stop;
    private  Button video_play;
    private  Button video_pause;
    private  Button video_reply;
    private VideoView videoView;
    private MediaPlayer mediaPlayer=new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        music_play= (Button) findViewById(R.id.music_play_Id);
        music_pause= (Button) findViewById(R.id.music_pause_Id);
        music_stop= (Button) findViewById(R.id.music_stop_Id);
        video_play= (Button) findViewById(R.id.video_play_Id);
        video_pause= (Button) findViewById(R.id.music_pause_Id);
        video_reply= (Button) findViewById(R.id.video_reply_Id);
        videoView= (VideoView) findViewById(R.id.video_Id);
        music_play.setOnClickListener(this);
        music_pause.setOnClickListener(this);
        music_stop.setOnClickListener(this);
        video_play.setOnClickListener(this);
        video_pause.setOnClickListener(this);
        video_reply.setOnClickListener(this);
        initMediaPlayer();
        initVideoPlayer();
    }
     //初始化视频.
    private void initVideoPlayer() {
        File file=new File(Environment.getExternalStorageDirectory(),"tian.mp4");
        videoView.setVideoPath(file.getPath());
    }
    //初始化音乐
    private void initMediaPlayer() {
        try {
            //绝对路径:Environment.getExternalStorageDirectory().getAbsoluteFile()+文件路径
            //SD卡根目录下的文件
            File file=new File(Environment.getExternalStorageDirectory(),"yan.mp3");
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.music_play_Id:
                if(!mediaPlayer.isPlaying())
                {
                    mediaPlayer.start();
                }
                break;
            case R.id.music_pause_Id:
                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.pause();
                }
                break;
            case R.id.music_stop_Id:
                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.reset();
                    initMediaPlayer();
                }
                break;
            case R.id.video_play_Id:
                if(!videoView.isPlaying())
                {
                    videoView.start();
                }
                break;
            case R.id.video_pause_Id:
                if(videoView.isPlaying())
                {
                    videoView.pause();
                }
                break;
            case R.id.video_reply_Id:
                if(videoView.isPlaying())
                {
                    videoView.resume();
                }
                break;
            default:
                break;
        }
    }
    @Override
    protected void onDestroy() {
        if(mediaPlayer!=null)
        {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        if(videoView!=null)
        {
            videoView.suspend();
        }
        super.onDestroy();
    }
}
