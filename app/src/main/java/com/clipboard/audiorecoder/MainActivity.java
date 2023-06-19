package com.clipboard.audiorecoder;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
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
        if (!isFlashAavilable) {
            flashNotFound();
        }

        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
                //flash on method call
                flashOn(isCheck);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)

    private void flashOn(boolean isCheck) {
        try {
            if (isCheck == true) {
                textView.setText("FLASHLIGHT ON");
            } else {
                textView.setText("FLASHLIGHT OFF");
            }
            cameraManager.setTorchMode(cameraId, isCheck);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void flashNotFound() {

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();

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