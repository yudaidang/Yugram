package lab.yu.yugram.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import lab.yu.yugram.R;

public class ChangePasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);


        ImageView avatarApp = (ImageView) findViewById(R.id.avatarProfile);
        Glide.with(this).load(R.mipmap.ic_logoyu).apply(RequestOptions.circleCropTransform().placeholder(R.mipmap.ic_launcher_round)).into(avatarApp);
        ImageView imageView = (ImageView) findViewById(R.id.ivCancel);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Toast.makeText(ChangePasswordActivity.this, "Hủy chỉnh sửa thông tin", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView imageViewSave = (ImageView) findViewById(R.id.ivSaveProfile);
        imageViewSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Toast.makeText(ChangePasswordActivity.this,"Lưu thông tin thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
