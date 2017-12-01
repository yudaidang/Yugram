package lab.yu.yugram.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import lab.yu.yugram.R;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ImageView backgroundUser = (ImageView) findViewById(R.id.backGroundImage);
        ImageView avatarUser = (ImageView) findViewById(R.id.avatarProfile);
        String url_image_background="https://scontent.fsgn5-1.fna.fbcdn.net/v/t1.0-9/18222582_887073868098395_7159416397014555794_n.jpg?oh=6d68e61d83b11b5f812e1533904d2f93&oe=5AAFE4A2";
        String url_image_user = "https://scontent.fsgn5-1.fna.fbcdn.net/v/t1.0-9/21687485_1133775720087600_8541101764693327944_n.jpg?oh=b0b927dee62f2cec22aeb14a687e6814&oe=5A9A38C1";
        Glide.with(this).load(url_image_user).apply(RequestOptions.circleCropTransform().placeholder(R.mipmap.ic_launcher_round)).into(avatarUser);
        Glide.with(this).load(url_image_background).apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher_round)).into(backgroundUser);

        ImageView imageView = (ImageView) findViewById(R.id.ivCancel);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Toast.makeText(EditProfileActivity.this,"Hủy chỉnh sửa thông tin", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView imageViewSave = (ImageView) findViewById(R.id.ivSaveProfile);
        imageViewSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Toast.makeText(EditProfileActivity.this,"Lưu thông tin thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
