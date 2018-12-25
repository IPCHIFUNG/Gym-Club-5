package com.zifung.gymclub;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class CourseActivity extends Activity {

    private VideoView videoView;
    private TextView tv_tel_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        initViews();
        Intent intent = getIntent();
        tv_tel_number.setText(intent.getStringExtra("tel_number"));

        videoView.setVideoURI(Uri.parse("http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8"));
        videoView.requestFocus();
        videoView.start();
    }

    private void initViews() {
        videoView = findViewById(R.id.vv_course_video);
        tv_tel_number = findViewById(R.id.tv_tel_number);

        tv_tel_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(CourseActivity.this);
                builder.setTitle("确认拨打电话：" + tv_tel_number.getText());
                builder.setMessage("是否确认？");
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        Uri data = Uri.parse("tel:" + tv_tel_number.getText());
                        intent.setData(data);
                        if (ActivityCompat.checkSelfPermission(CourseActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(CourseActivity.this, "没有拨打权限", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("否", null);
                builder.show();
            }
        });
    }

}
