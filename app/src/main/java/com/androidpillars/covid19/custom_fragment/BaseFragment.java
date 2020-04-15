package com.androidpillars.covid19.custom_fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.androidpillars.covid_19.R;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

/**
 * Created by Gowtham.R on 2020-04-07.
 */
public abstract class BaseFragment extends Fragment implements ScreenShotable {

    private View containerView;
    private Bitmap bitmap;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        initView(rootView);
        initData();
        return rootView;
    }


    public abstract int getLayoutId();


    public abstract void initView(View rootView);


    public abstract void initData();

    @Override
    public void takeScreenShot() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Bitmap bitmap = Bitmap.createBitmap(containerView.getWidth(),
                        containerView.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                containerView.draw(canvas);
                BaseFragment.this.bitmap = bitmap;
            }
        };
        thread.start();
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}
