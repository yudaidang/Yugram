package lab.yu.yugram.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import lab.yu.yugram.Adapter.PostAdapter;
import lab.yu.yugram.BottomNavigationViewHelper;
import lab.yu.yugram.Fragment.CameraFragment;
import lab.yu.yugram.Fragment.HomeFragment;
import lab.yu.yugram.Fragment.MessagesFragment;
import lab.yu.yugram.Model.Post;
import lab.yu.yugram.R;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity";
    private Context mContext = ProfileActivity.this;
    private static final int ACTIVITY_NUM = 4;
    private ImageView imgCamera, imgSend;
    private Context context;
    private TextView textView;
    FragmentManager fm;
    private ArrayList<Post> postArray = new ArrayList<Post>() ;
    private PostAdapter postArrayAdapter;
    private ListView listView;
    ImageButton imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

/*        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.totalComment);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, CommentActivity.class);
                ProfileActivity.this.startActivity(intent);

            }
        });*/

        BottomNavigationViewHelper.setupBottomNavigationView(this, ACTIVITY_NUM);
        listView = (ListView) findViewById(R.id.listView);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View head= inflater.inflate(R.layout.header_profile_me_layout,listView,false);
        listView.addHeaderView(head);
        postArrayAdapter = new PostAdapter(this,R.layout.item_post,postArray);
        listView.setAdapter(postArrayAdapter);
        addPost();
        ImageView uploadClose = (ImageView) findViewById(R.id.backImage);
        uploadClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ImageView avatarUser = (ImageView) findViewById(R.id.avatarProfile);
        ImageView backgroundUser = (ImageView) findViewById(R.id.backGroundImage);
        //set header profile
        String url_image_user = "https://scontent.fsgn5-1.fna.fbcdn.net/v/t1.0-9/21687485_1133775720087600_8541101764693327944_n.jpg?oh=b0b927dee62f2cec22aeb14a687e6814&oe=5A9A38C1";
        String url_image_background="https://scontent.fsgn5-1.fna.fbcdn.net/v/t1.0-9/18222582_887073868098395_7159416397014555794_n.jpg?oh=6d68e61d83b11b5f812e1533904d2f93&oe=5AAFE4A2";

        Glide.with(this).load(url_image_background).apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher_round)).into(backgroundUser);
        Glide.with(this).load(url_image_user).apply(RequestOptions.circleCropTransform().placeholder(R.mipmap.ic_launcher_round)).into(avatarUser);

        imageView = (ImageButton) findViewById(R.id.menuProfile);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(ProfileActivity.this, imageView);

                popup.getMenuInflater().inflate(R.menu.menu_profile, popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch(item.getItemId()){
                            case R.id.editProfile:
                                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                                ProfileActivity.this.startActivity(intent);
                                return true;
                            case R.id.changePassword:
                                Intent intent1 = new Intent(ProfileActivity.this, ChangePasswordActivity.class);
                                ProfileActivity.this.startActivity(intent1);
                                return true;
                            case R.id.logOut:
                                Toast.makeText(ProfileActivity.this,"You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
            }
        });
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

    private void setupBottomNavigationView(){
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
    public void addPost(){


        String url1="https://scontent.fsgn5-1.fna.fbcdn.net/v/t1.0-9/21686381_967927180013063_1644726607964479174_n.jpg?oh=536dfab4b2d18e2d250fab9de6208365&oe=5A637CFB";
        String url_image="https://scontent.fsgn5-1.fna.fbcdn.net/v/t1.0-9/18222582_887073868098395_7159416397014555794_n.jpg?oh=6d68e61d83b11b5f812e1533904d2f93&oe=5AAFE4A2";


        String urll1="https://scontent.fsgn5-1.fna.fbcdn.net/v/t1.0-9/21687485_1133775720087600_8541101764693327944_n.jpg?oh=b0b927dee62f2cec22aeb14a687e6814&oe=5A9A38C1";
        String url_image1="https://scontent.fsgn5-1.fna.fbcdn.net/v/t1.0-9/18839387_1065062070292299_5078978297961725368_n.jpg?oh=c61d185a4a84d3dfee52b81e1b829614&oe=5A9EF278";
        String url_image2="https://scontent.fsgn5-1.fna.fbcdn.net/v/t1.0-9/18300842_1047017035430136_1002648503586412473_n.jpg?oh=56f050a7f1fd85be1f707f8ba4894a14&oe=5AAC5DAE";
        Post p = new Post();
        p.setUrlAvatar(urll1);
        p.setUrlImage(url_image1);
        p.setUserName("hoanghtk3108");
        postArray.add(p);

        Post pi = new Post();
        pi.setUrlAvatar(url1);
        pi.setUrlImage(url_image);
        pi.setUserName("yudaidang");
        postArray.add(pi);

        Post pio = new Post();
        pio.setUrlAvatar(urll1);
        pio.setUrlImage(url_image2);
        pio.setUserName("hoanghtk3108");
        postArray.add(pio);
    }
}
