package com.nayaproject.e_cards;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;

public class Holi extends AppCompatActivity {

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
        setContentView(R.layout.activity_holi);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        video = findViewById(R.id.video);
        share =findViewById(R.id.share);
        sharing = findViewById(R.id.sharing);
        view = findViewById(R.id.view);
        back = findViewById(R.id.back);
        media = new MediaController(this);
        count=0;

        videopath = "android.resource://com.nayaproject.e_cards/"+ R.raw.holi_greeting;
        Uri uri = Uri.parse(videopath);
        video.setVideoURI(uri);
        video.setMediaController(media);
        media.setAnchorView(view);
        video.start();

        sharing.setVisibility(View.INVISIBLE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Holibg.class));
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

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showcustomdialog();
            }
        });
    }

    public void showcustomdialog()
    {
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Share");

        View view = LayoutInflater.from(this).inflate(R.layout.activity_customdialogbox,null);
        final TextView sharephoto = view.findViewById(R.id.sharephoto);
        TextView sharevideo = view.findViewById(R.id.sharevideo);

        sharevideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("video/mp4");
                File fileToShare = new File("sdcard/9016-4EF8/E-cards/to/christmas_greeting.mp4");
                Uri uri = Uri.fromFile(fileToShare);
                sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);
                startActivity(Intent.createChooser(sharingIntent, "Share Video"));
                dialog.dismiss();
            }
        });

        sharephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(Intent.ACTION_SEND);

                // If you want to share a png image only, you can do:
                // setType("image/png"); OR for jpeg: setType("image/jpeg");
                share.setType("image/*");

                // Make sure you put example png image named myImage.png in your
                // directory
                String imagePath = Environment.getExternalStorageDirectory()
                        + "/christmas.jpg";

                File imageFileToShare = new File(imagePath);

                Uri uri = Uri.fromFile(imageFileToShare);
                share.putExtra(Intent.EXTRA_STREAM, uri);

                startActivity(Intent.createChooser(share, "Share Image"));

                dialog.dismiss();
            }
        });

        dialog.setContentView(view);
        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videopath = "android.resource://com.nayaproject.e_cards/" + R.raw.holi_greeting;
        Uri uri = Uri.parse(videopath);
        video.setVideoURI(uri);
        video.start();
    }
}
