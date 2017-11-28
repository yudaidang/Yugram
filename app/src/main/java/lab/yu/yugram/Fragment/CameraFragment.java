package lab.yu.yugram.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import lab.yu.yugram.Activity.NextActivity;
import lab.yu.yugram.Activity.PostActivity;
import lab.yu.yugram.R;
import lab.yu.yugram.Utils.Permissions;



public class CameraFragment extends Fragment {
    private static final String TAG = "PhotoFragment";

    //constant
    private static final int PHOTO_FRAGMENT_NUM = 1;
    private static final int GALLERY_FRAGMENT_NUM = 2;
    private static final int  CAMERA_REQUEST_CODE = 5;
    private static final int CAMERA_PHOTO = 1337;
    private Uri imageToUploadUri;
    ImageView imageView;
    ImageView image2;

    File f;
    File folderSource;
    Intent chooserIntent;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera, container, false);
        Log.d(TAG, "onCreateView: started.");

        ImageView uploadClose = (ImageView) view.findViewById(R.id.ivCloseShare);
        uploadClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        folderSource = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) +
                File.separator + "Yugram");
        boolean success = true;
        if (!folderSource.exists()) {
            success = folderSource.mkdirs();
        }

        ImageView imageView = (ImageView) view.findViewById(R.id.imageCamera);
        String imgAddress = "https://cdn3.iconfinder.com/data/icons/follow-me/256/Instagram-512.png";
        Glide.with(getActivity()).load(imgAddress).apply(RequestOptions.placeholderOf(R.mipmap.ic_lens)).into(imageView);
/*        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 1.0f * 360.0f,Animation.RELATIVE_TO_SELF,   0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setRepeatMode(Animation.INFINITE);
        imageView.startAnimation(rotateAnimation);*/
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooserIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                        + File.separator + "Yugram" + File.separator + "Yu_" + timeStamp + ".jpg");
                chooserIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                startActivityForResult(chooserIntent, CAMERA_PHOTO);

            }
        });
        return view;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_PHOTO && resultCode == Activity.RESULT_OK) {

            String fTest = f.getAbsolutePath();
            Log.d(TAG, fTest);
            Intent intent = new Intent(getActivity(), NextActivity.class);
            intent.putExtra(getString(R.string.selected_bitmap), fTest);
            startActivity(intent);
        }

    }


}
