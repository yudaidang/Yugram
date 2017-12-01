package lab.yu.yugram.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import lab.yu.yugram.Adapter.CommentAdapter;
import lab.yu.yugram.Adapter.NotificationAdapter;
import lab.yu.yugram.Model.Notification;
import lab.yu.yugram.R;

public class CommentActivity extends AppCompatActivity {
    private ArrayList<Notification> notificationArray= new ArrayList<Notification>() ;
    private CommentAdapter notificationAdapter;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        ImageView imageView = (ImageView) findViewById(R.id.ivBack);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        addNotification();
        // set adapter for list view
        listView = (ListView) findViewById(R.id.listView);
        notificationAdapter = new CommentAdapter(this,R.layout.item_comment,notificationArray);
        listView.setAdapter(notificationAdapter);
    }
    public void addNotification(){
        String url1="https://scontent.fsgn5-1.fna.fbcdn.net/v/t1.0-9/21686381_967927180013063_1644726607964479174_n.jpg?oh=536dfab4b2d18e2d250fab9de6208365&oe=5A637CFB";
        String urll1="https://scontent.fsgn5-1.fna.fbcdn.net/v/t1.0-9/21687485_1133775720087600_8541101764693327944_n.jpg?oh=b0b927dee62f2cec22aeb14a687e6814&oe=5A9A38C1";

        Notification p = new Notification();
        p.setUrlAvatar(urll1);
        p.setUsername("hoanghtk3108");
        p.setDescription("hình đẹp quá. hihi");
        p.setUserFriend("yudaidang");
        notificationArray.add(p);

        Notification pi = new Notification();
        pi.setUrlAvatar(url1);
        pi.setUsername("yudaidang");
        pi.setDescription("từ hôm nay em sẽ chẳng mong chờ");
        pi.setUserFriend("hoanghtk3108");
        notificationArray.add(pi);

        Notification pio = new Notification();
        pio.setUrlAvatar(urll1);
        pio.setUsername("sika_babe");
        pio.setDescription("người theo hương hoa mây mù giăng lối");
        pio.setUserFriend("hoanghtk3108");
        notificationArray.add(pi);
    }
}
