package com.zifung.gymclub.model;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.zifung.gymclub.R;
import com.zifung.gymclub.fragment.DiscoveryFragment;
import com.zifung.gymclub.fragment.HomeFragment;
import com.zifung.gymclub.fragment.MeFragment;
import com.zifung.gymclub.user.UserInfo;

public class MainActivity extends AppCompatActivity
        implements HomeFragment.OnFragmentInteractionListener, DiscoveryFragment.OnFragmentInteractionListener, MeFragment.OnFragmentInteractionListener {

    private HomeFragment home_fragment;
    private DiscoveryFragment discovery_fragment;
    private MeFragment me_fragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_main, home_fragment).commit();
                    return true;
                case R.id.navigation_dashboard:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_main, discovery_fragment).commit();
                    return true;
                case R.id.navigation_notifications:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_main, me_fragment).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        UserInfo user_info = (UserInfo) getApplication();

        home_fragment = new HomeFragment();
        discovery_fragment = new DiscoveryFragment();
        me_fragment = new MeFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.fl_main, home_fragment).commit();
        if (user_info.getUsername() == null) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(this,"",Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK: case KeyEvent.KEYCODE_HOME:
                moveTaskToBack(false);
                return true;
            default:
                return super.onKeyDown(keyCode, event);
        }
    }
}
