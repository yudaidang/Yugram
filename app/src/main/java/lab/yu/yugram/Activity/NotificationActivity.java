package lab.yu.yugram.Activity;

import android.content.Context;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import lab.yu.yugram.BottomNavigationViewHelper;
import lab.yu.yugram.Fragment.CameraFragment;
import lab.yu.yugram.Fragment.HomeFragment;
import lab.yu.yugram.Fragment.MessagesFragment;
import lab.yu.yugram.R;

public class NotificationActivity extends AppCompatActivity {
    private static final String TAG = "NotificationActivity";
    private Context mContext = NotificationActivity.this;
    private static final int ACTIVITY_NUM = 3;
    private ImageView imgCamera, imgSend;
    private Context context;
    private TextView textView;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        BottomNavigationViewHelper.setupBottomNavigationView(this, ACTIVITY_NUM);
        setupToolbar();
    }

    @Override
    public void onPause(){
        super.onPause();
        overridePendingTransition(0, 0);
    }

    private void setupToolbar(){


        Toolbar toolbar = (Toolbar) findViewById(R.id.tabs);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        fm = getSupportFragmentManager();


        context = getApplicationContext();
        imgCamera = (ImageView) findViewById(R.id.ic_camera);
        imgCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FragmentTransaction fragmentTransaction = fm.beginTransaction();
                CameraFragment cmFragment = new CameraFragment();
                fragmentTransaction.replace(R.id.container, cmFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        imgSend = (ImageView) findViewById(R.id.ic_send);
        imgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FragmentTransaction fragmentTransaction = fm.beginTransaction();
                MessagesFragment meFragment = new MessagesFragment();
                fragmentTransaction.replace(R.id.container, meFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        textView = (TextView) findViewById(R.id.ic_logo);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FragmentTransaction fragmentTransaction = fm.beginTransaction();
                HomeFragment homeFragment = new HomeFragment();
                fragmentTransaction.replace(R.id.container, homeFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }


}
