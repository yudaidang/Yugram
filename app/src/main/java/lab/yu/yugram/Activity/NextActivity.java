package lab.yu.yugram.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import lab.yu.yugram.BottomNavigationViewHelper;
import lab.yu.yugram.R;
public class NextActivity extends AppCompatActivity {
    private String imgUrl;
    Bitmap bitmap;
    private static final int ACTIVITY_NUM = 2;
    ImageView image;
    ImageView image2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        BottomNavigationViewHelper.setupBottomNavigationView(this, ACTIVITY_NUM);
        image = (ImageView) findViewById(R.id.imageShare);
        image2 = (ImageView) findViewById(R.id.imageUserCamera);
        String url = "https://scontent.fsgn5-1.fna.fbcdn.net/v/t1.0-9/21686381_967927180013063_1644726607964479174_n.jpg?oh=536dfab4b2d18e2d250fab9de6208365&oe=5A637CFB";
        Glide.with(this).load(url).apply(RequestOptions.circleCropTransform().placeholder(R.mipmap.ic_camera)).into(image2);
        ImageView backArrow = (ImageView) findViewById(R.id.ivBackShare);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView share = (TextView) findViewById(R.id.tvShare);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //upload the image to firebase
            }
        });
        setImage();
    }

    private void setImage(){
        Intent intent = getIntent();


        if(intent.hasExtra(getString(R.string.selected_image))){


            Glide.with(this).load(intent.getStringExtra("selected_image")).apply(RequestOptions.placeholderOf(R.mipmap.ic_camera)).into(image);

        }
        else if(intent.hasExtra(getString(R.string.selected_bitmap))){
            Glide.with(this).load(intent.getStringExtra("selected_bitmap")).apply(RequestOptions.placeholderOf(R.mipmap.ic_camera)).into(image);

        }



    }
}
