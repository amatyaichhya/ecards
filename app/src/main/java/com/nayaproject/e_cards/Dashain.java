package com.nayaproject.e_cards;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;

public class Dashain extends AppCompatActivity {

    MediaPlayer dashainsong;
    ImageView back,object,share;
    Animation scale,slide,slide1,slide2;
    TextView s1,s2,s3;
    LinearLayout sharing;
    RelativeLayout view;
    int count;
    String videopath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashain);

        share =findViewById(R.id.share);
        sharing = findViewById(R.id.sharing);
        view = findViewById(R.id.view);
        count=0;

        videopath = "android.resource://com.nayaproject.e_cards/"+ R.raw.dashain_greeting;
        Uri uri = Uri.parse(videopath);

        sharing.setVisibility(View.INVISIBLE);

        object = findViewById(R.id.object);
        s1 = findViewById(R.id.s1);
        s2 = findViewById(R.id.s2);
        s3 = findViewById(R.id.s3);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashain.this,Dashainbg.class);
                startActivity(intent);
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

        dashainsong = MediaPlayer.create(getApplicationContext(),R.raw.hype);

        dashainsong.start();

        scale = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scaleup);
        slide1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slideup);
        slide2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slideup);
        slide = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slideup);

        object.startAnimation(scale);

        scale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                s1.setVisibility(s1.GONE);
                s2.setVisibility(s2.GONE);
                s3.setVisibility(s3.GONE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                s1.startAnimation(slide);
                s1.setVisibility(s1.VISIBLE);

            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        slide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                s2.setVisibility(s2.GONE);
                s3.setVisibility(s3.GONE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                s2.startAnimation(slide1);
                s2.setVisibility(s2.VISIBLE);

            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        slide1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                s3.setVisibility(s3.GONE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                s3.startAnimation(slide2);
                s3.setVisibility(s3.VISIBLE);

            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void showcustomdialog()
    {
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Share");

        View view = LayoutInflater.from(this).inflate(R.layout.activity_customdialogbox,null);
        TextView sharephoto = view.findViewById(R.id.sharephoto);
        TextView sharevideo = view.findViewById(R.id.sharevideo);

        sharevideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("video/mp4");
                File fileToShare = new File("sdcard/9016-4EF8/E-cards/to/dashain_greeting.mp4");
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
                        + "/dashainimage.jpg";

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
    protected void onPause() {
        super.onPause();
        dashainsong.release();

    }
    @Override
    protected void onResume() {
        super.onResume();
        dashainsong.start();
    }

}
