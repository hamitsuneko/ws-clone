package com.example.ws_session1.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.ws_session1.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageViewerActivity extends AppCompatActivity implements View.OnClickListener {

    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        setOnClick();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setOnClick() {
        findViewById(R.id.viewer_close).setOnClickListener(this);
        findViewById(R.id.viewer_remove).setOnClickListener(this);

        String filePath = "/data/data/com.example.ws_session1/app_imageDir/" + getIntent().getStringExtra("imageName");
        file = new File(filePath);
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            ImageView iv = ((ImageView) findViewById(R.id.viewer_image));
            iv.setImageBitmap(bitmap);
            iv.setOnTouchListener(new OnSwipeTouchListener(getApplication()) {
                @Override
                public void onSwipeRight() {
                    finish();
                }

                @Override
                public void onSwipeLeft() {
                    file.delete();
                    finish();
                }
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.viewer_close: {
                finish();
            }
            break;
            case R.id.viewer_remove: {
                file.delete();
                finish();
            }
            break;
        }
    }
}

