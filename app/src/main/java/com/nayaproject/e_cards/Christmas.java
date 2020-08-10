package com.nayaproject.e_cards;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.FileOutputStream;

public class Christmas extends AppCompatActivity {

    VideoView video;
    String videopath;
    ImageView share,back;
    LinearLayout sharing;
    RelativeLayout view;
    MediaController media;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_christmas);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        video = findViewById(R.id.video);
        share =findViewById(R.id.share);
        sharing = findViewById(R.id.sharing);
        view = findViewById(R.id.view);
        back = findViewById(R.id.back);
        media = new MediaController(this);
        count=0;

        videopath = "android.resource://com.nayaproject.e_cards/"+ R.raw.christmas_greeting;
        Uri uri = Uri.parse(videopath);
        video.setVideoURI(uri);
        video.setMediaController(media);
        media.setAnchorView(view);
        video.start();

        sharing.setVisibility(View.INVISIBLE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Christmas.this,Christmasbg.class));
                finish();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count=count+1;
                if(count%2==1){
                    sharing.setVisibility(View.VISIBLE);
                }
                else
                {
                    sharing.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        videopath = "android.resource://com.nayaproject.e_cards/" + R.raw.christmas_greeting;
        Uri uri = Uri.parse(videopath);
        video.setVideoURI(uri);
        video.start();
    }
}

