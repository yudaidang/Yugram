package lab.yu.yugram.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import lab.yu.yugram.Adapter.NotificationAdapter;
import lab.yu.yugram.Adapter.UploadAdapter;
import lab.yu.yugram.BottomNavigationViewHelper;
import lab.yu.yugram.Fragment.CameraFragment;
import lab.yu.yugram.Fragment.GalleryFragment;
import lab.yu.yugram.Fragment.HomeFragment;
import lab.yu.yugram.Fragment.MessagesFragment;
import lab.yu.yugram.Model.HinhAnh;
import lab.yu.yugram.Model.Notification;
import lab.yu.yugram.R;
import lab.yu.yugram.Utils.FileSearch;
import lab.yu.yugram.Utils.SectionsPagerAdapter;

public class PostActivity extends AppCompatActivity {
    private static final String TAG = "PostActivity";
    private Context mContext = PostActivity.this;
    private static final int ACTIVITY_NUM = 2;
    private ImageView imgCamera, imgSend;
    private Context context;
    private TextView textView;
    FragmentManager fm;
    private static final int NUM_GRID_COLUMNS = 3;
    private GridView gridView;

    private UploadAdapter uploadAdapter;
    private ListView listView;
    private  ArrayList<String> listImage = new ArrayList<String>();
    public String ROOT_DIR = Environment.getExternalStorageDirectory().getPath();

    public String PICTURES = ROOT_DIR + "/Pictures/instagram";
    public String CAMERA = ROOT_DIR + "/DCIM/camera";
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);


        initPermission();
        setupViewPager();




    }


    private void setupViewPager(){
        SectionsPagerAdapter adapter =  new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new GalleryFragment());
        adapter.addFragment(new CameraFragment());

        mViewPager = (ViewPager) findViewById(R.id.containerupload);
        mViewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabsBottom);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.getTabAt(0).setText("Gallery");
        tabLayout.getTabAt(1).setText("Photo");

    }

    @Override
    public void onPause(){
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(PostActivity.this, "Permision Write File is Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(PostActivity.this, "Permision Write File is Denied", Toast.LENGTH_SHORT).show();

            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void initPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                //Permisson don't granted
                if (shouldShowRequestPermissionRationale(
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    Toast.makeText(PostActivity.this, "Permission isn't granted ", Toast.LENGTH_SHORT).show();
                }
                // Permisson don't granted and dont show dialog again.
                else {
                    Toast.makeText(PostActivity.this, "Permisson don't granted and dont show dialog again ", Toast.LENGTH_SHORT).show();
                }
                //Register permission
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

            }
        }
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
                fragmentTransaction.replace(R.id.containerNotification, cmFragment);
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
                fragmentTransaction.replace(R.id.containerNotification, meFragment);
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
                fragmentTransaction.replace(R.id.containerNotification, homeFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }


}
