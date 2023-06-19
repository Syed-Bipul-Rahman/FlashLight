package com.clipboard.audiorecoder;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    ToggleButton toggleButton;
    private CameraManager cameraManager;
    private String cameraId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //variables initializations
        toggleButton = findViewById(R.id.toggleButton);
        textView = findViewById(R.id.textView);

        //text off
        textView.setText("FLASHLIGHT OFF");

        boolean isFlashAavilable = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        if (!isFlashAavilable){
            flashNotFound();
        }

    }

    private void flashNotFound() {

        AlertDialog alertDialog=new AlertDialog.Builder(this).create();

        alertDialog.setTitle("Opps");
        alertDialog.setMessage("Flash is not Available in this Device!");
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alertDialog.show();

    }
}