package com.zyyoona7.easypopup.easypop;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.zyyoona7.easypopup.R;

/**
 * Author: zcj
 * Date: 18-8-29
 * Email: zhengcj01@vanke.com
 */
public class VideoAct extends AppCompatActivity {

    //create class reference
    VideoView vid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_video);

        vid = findViewById(R.id.videoView);

    }

    public void playVideo(View v) {
        MediaController m = new MediaController(this);
        vid.setMediaController(m);

//        String path = "https://www.w3schools.com/html/movie.mp4";
        String path = "http://alcdn.hls.xiaoka.tv/2017427/14b/7b3/Jzq08Sl8BbyELNTo/index.m3u8";

        Uri u = Uri.parse(path);

        vid.setVideoURI(u);

        vid.start();

    }
}