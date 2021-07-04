package com.example.samihtaskmngr2019;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;
//import android.support.v7.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity {

    private static final int PERMISSION_CODE = 100;

    @Override
    protected void onResume() {
        super.onResume();
        Thread th=new Thread()
        {
            @Override
            public void run() {
                try {
                    sleep(3*1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        th.start();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int PD = PackageManager.PERMISSION_DENIED;
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PD
                    || checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PD
                    || checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PD

            ) {
                //permission not granted, request it.
                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};
                //show popup for runtime permission
                requestPermissions(permissions, PERMISSION_CODE);
            } else {
                Intent i = new Intent(getApplication(), SignInActivity.class);
                startActivity(i);
                finish();
//                FirebaseAuth auth=FirebaseAuth.getInstance();

//                if(auth.getCurrentUser()==null || auth.getCurrentUser().getEmail()==null) {
//                    Intent i = new Intent(getApplication(), SignInActivity.class);
//                    startActivity(i);
//                    finish();
//                }
//                else
//                {
//                    Intent i = new Intent(getApplication(), MainTasksActivity.class);
//                    startActivity(i);
//                    finish();
//                }
            }

        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        switch (requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    //permission was granted
                    FirebaseAuth auth=FirebaseAuth.getInstance();

                    if(auth.getCurrentUser()==null || auth.getCurrentUser().getEmail()==null) {
                        Intent i = new Intent(getApplication(), SignInActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else
                    {
                        Intent i = new Intent(getApplication(), MainTasksActivity.class);
                        startActivity(i);
                        finish();
                    }
                }
                else {
                    //permission was denied
                    Toast.makeText(this,"Permission denied...!",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
    }




}
