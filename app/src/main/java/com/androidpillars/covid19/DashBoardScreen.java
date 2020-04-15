package com.androidpillars.covid19;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.androidpillars.covid19.custom_fragment.FAQSelectionFragment;
import com.androidpillars.covid19.custom_fragment.HomeSelectionFragment;
import com.androidpillars.covid19.custom_fragment.HelpLinksFragment;
import com.androidpillars.covid19.custom_fragment.UpdateTimeFragment;
import com.androidpillars.covid19.utils.Constants;
import com.androidpillars.covid_19.R;

import java.util.ArrayList;
import java.util.List;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.model.SlideMenuItem;
import yalantis.com.sidemenu.util.ViewAnimator;


/**
 * Created by Gowtham.R on 2020-04-05.
 */
public class DashBoardScreen extends AppCompatActivity implements ViewAnimator.ViewAnimatorListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private List<SlideMenuItem> list = new ArrayList<>();
    private HomeSelectionFragment contentFragment;
    private ViewAnimator viewAnimator;
    private LinearLayout linearLayout;


    private void createMenuList() {
        SlideMenuItem menuItem0 = new SlideMenuItem(Constants.CLOSE, R.drawable.icn_close);
        list.add(menuItem0);
        SlideMenuItem menuItem = new SlideMenuItem(Constants.Home, R.drawable.icn_1);
        list.add(menuItem);
        SlideMenuItem menuItem2 = new SlideMenuItem(Constants.UPDATE_TIME, R.drawable.icn_7);
        list.add(menuItem2);
        SlideMenuItem menuItem5 = new SlideMenuItem(Constants.LINKS, R.drawable.icn_2);
        list.add(menuItem5);
        SlideMenuItem menuItem4 = new SlideMenuItem(Constants.FAQ, R.drawable.icn_4);
        list.add(menuItem4);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dash_board_screen);

        init();
    }

    private void init() {

        contentFragment = HomeSelectionFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, contentFragment)
                .commit();
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        linearLayout = findViewById(R.id.left_drawer);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });

        setActionBar();
        createMenuList();
        viewAnimator = new ViewAnimator<>(this, list, contentFragment, drawerLayout, this);

    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    private void setActionBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        ) {


            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                linearLayout.removeAllViews();
                linearLayout.invalidate();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset > 0.6 && linearLayout.getChildCount() == 0)
                    viewAnimator.showMenuContent();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    private ScreenShotable replaceFragment(ScreenShotable screenShotable, int topPosition,
                                           Fragment contentFragment) {
        View view = findViewById(R.id.content_frame);
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition,
                0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);

        findViewById(R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources
                (), screenShotable.getBitmap()));
        animator.start();

        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                contentFragment).commit();
        return (ScreenShotable) contentFragment;
    }

    @Override
    public ScreenShotable onSwitch(Resourceble slideMenuItem, ScreenShotable screenShotable, int
            position) {
        switch (slideMenuItem.getName()) {
            case Constants.CLOSE:
                return screenShotable;
            case Constants.Home:
                return replaceFragment(screenShotable, position, HomeSelectionFragment.newInstance());
            case Constants.UPDATE_TIME:
                return replaceFragment(screenShotable, position, UpdateTimeFragment.newInstance());
            case Constants.LINKS:
                return replaceFragment(screenShotable, position, HelpLinksFragment.newInstance());
            case Constants.FAQ:
                return replaceFragment(screenShotable, position, FAQSelectionFragment.newInstance());

            default:
                return screenShotable;
        }
    }


    @Override
    public void disableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(false);

    }

    @Override
    public void enableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout.closeDrawers();

    }

    @Override
    public void addViewToContainer(View view) {
        linearLayout.addView(view);
    }


}
