package com.androidpillars.covid19;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.androidpillars.covid19.Model.UpdateVersion;
import com.androidpillars.covid19.utils.CustomPopupDialog;
import com.androidpillars.covid19.utils.CustomPreference;
import com.androidpillars.covid19.utils.NetworkManager;
import com.androidpillars.covid19.utils.VersionUpdatemDialog;
import com.androidpillars.covid_19.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gowtham.R on 2020-04-05.
 */
public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;

    public FirebaseFirestore db;
    private List<UpdateVersion> mUpdateVersionList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.splash_screen_activity);

        if (!NetworkManager.isInterNetConnection(SplashScreenActivity.this)) {
            new CustomPopupDialog(SplashScreenActivity.this);
        } else {

            db = FirebaseFirestore.getInstance();
            mUpdateVersionList = new ArrayList<>();


            db.collection("UpdateVersion").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot d : list) {
                            UpdateVersion mUpdateVersion = d.toObject(UpdateVersion.class);
                            mUpdateVersionList.add(mUpdateVersion);
                            checkVersion();
                        }
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    init();
                }
            });
        }


    }

    void init() {

        if (!NetworkManager.isInterNetConnection(SplashScreenActivity.this)) {
            new CustomPopupDialog(SplashScreenActivity.this);
        } else {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {

                    Intent i = new Intent(SplashScreenActivity.this, DashBoardScreen.class);
                    startActivity(i);
                    finish();

                }

            }, SPLASH_TIME_OUT);
        }

    }


    void checkVersion() {

        PackageInfo pInfo;

        try {

            pInfo = this.getPackageManager().getPackageInfo(
                    this.getPackageName(), 0);

            double version = pInfo.versionCode;

            String code = mUpdateVersionList.get(0).getmVersion();

            if (code != null) {
                if (version < Double.parseDouble(code)) {
                    new VersionUpdatemDialog(this, mUpdateVersionList.get(0).getmDate(),
                            mUpdateVersionList.get(0).getmVersion(), mUpdateVersionList.get(0).getmSize(),
                            mUpdateVersionList.get(0).getmDescription());
                } else {
                    if (version >= Double.parseDouble(code)) {
                        init();

                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            init();
            e.printStackTrace();
        }
    }

}

