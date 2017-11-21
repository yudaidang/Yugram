package lab.yu.yugram.Activity;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import lab.yu.yugram.Adapter.PostAdapter;
import lab.yu.yugram.Adapter.UserAdapter;
import lab.yu.yugram.BottomNavigationViewHelper;
import lab.yu.yugram.Fragment.CameraFragment;
import lab.yu.yugram.Fragment.HomeFragment;
import lab.yu.yugram.Fragment.MessagesFragment;
import lab.yu.yugram.Model.Post;
import lab.yu.yugram.Model.UserAccount;
import lab.yu.yugram.R;

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = "SearchActivity";
    private Context mContext = SearchActivity.this;
    private static final int ACTIVITY_NUM = 1;
    private ImageView imgCamera, imgSend;
    private Context context;
    private TextView textView;
    FragmentManager fm;

    private ArrayList<UserAccount> accountArray= new ArrayList<UserAccount>() ;
    private UserAdapter userAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        BottomNavigationViewHelper.setupBottomNavigationView(this, ACTIVITY_NUM);
        setupToolbar();

        addSearch();

        // set adapter for list view
        listView = (ListView) findViewById(R.id.listViewSearch);
        userAdapter = new UserAdapter(this,R.layout.item_search,accountArray);
        listView.setAdapter(userAdapter);

        // add post for newsfeed


    }

    public void addSearch(){
        String url1="https://scontent.fsgn5-1.fna.fbcdn.net/v/t1.0-9/21686381_967927180013063_1644726607964479174_n.jpg?oh=536dfab4b2d18e2d250fab9de6208365&oe=5A637CFB";
        String urll1="https://scontent.fsgn5-1.fna.fbcdn.net/v/t1.0-9/21687485_1133775720087600_8541101764693327944_n.jpg?oh=b0b927dee62f2cec22aeb14a687e6814&oe=5A9A38C1";

        UserAccount p = new UserAccount();
        p.setUrlAvatar(urll1);
        p.setUsername("hoanghtk3108");
        p.setDescription("Hồ Thị Kim Hoàng");
        accountArray.add(p);

        UserAccount pi = new UserAccount();
        pi.setUrlAvatar(url1);
        pi.setUsername("yudaidang");
        pi.setDescription("Đặng Văn Đại");
        accountArray.add(pi);

        UserAccount pio = new UserAccount();
        pio.setUrlAvatar(urll1);
        pio.setUsername("sika_babe");
        pio.setDescription("Hồ Thị Kim Hoàng");
        accountArray.add(pi);
    }


    @Override
    public void onPause(){
        super.onPause();
        overridePendingTransition(0, 0);
    }

    private void setupToolbar(){


        Toolbar toolbar = (Toolbar) findViewById(R.id.tabSearch);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        fm = getSupportFragmentManager();


        context = getApplicationContext();
       /* imgCamera = (ImageView) findViewById(R.id.ic_camera);
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
        });*/
    }

}
