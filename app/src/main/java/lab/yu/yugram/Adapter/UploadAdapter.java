package lab.yu.yugram.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lab.yu.yugram.Model.HinhAnh;
import lab.yu.yugram.Model.Post;
import lab.yu.yugram.R;

import static android.content.ContentValues.TAG;

/**
 * Created by Yu on 11/22/2017.
 */

public class UploadAdapter extends BaseAdapter {
    private Context context;
    private String mAppend = "file:/";
    private ArrayList<String> imagesList;
    int layout;

    public UploadAdapter(Context context, ArrayList<String> imagesList, int layout) {
        this.context = context;
        this.imagesList = imagesList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return imagesList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layout, null,
                    false);
        }




        ImageView imagePost = (ImageView) convertView.findViewById(R.id.grid_item_image);

        String fileName = "FB_IMG_1490436667634.jpg";
        String completePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/DCIM/facebook/"+fileName;

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "/DCIM/facebook/FB_IMG_1490436667634.jpg");
        Uri imageUri = Uri.fromFile(file);
        Log.d(TAG, "onItemClick: selected: " + completePath);
        // Set avater image
        String url=imagesList.get(position);
        Glide.with(context).load(new File(url)).apply(RequestOptions.placeholderOf(R.mipmap.ic_camera)).into(imagePost);




        return convertView;
    }
}
