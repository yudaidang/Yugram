package lab.yu.yugram.Adapter;

/**
 * Created by Yu on 11/17/2017.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import lab.yu.yugram.Model.Post;
import lab.yu.yugram.R;
public class PostAdapter extends ArrayAdapter<Post> {
    private Activity context;
    String currentUser;

    public PostAdapter(Activity context, int layoutID, List<Post> objects) {
        super(context, layoutID, objects);
        this.context = context;
        // Get current user
        SharedPreferences sharedPreferences= context.getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        currentUser = sharedPreferences.getString("userName","");

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_post, null,
                    false);

        }


        // Get item
        final Post post = getItem(position);

        // Get view


        TextView userName = (TextView) convertView.findViewById(R.id.userPost);
        TextView dateTime = (TextView) convertView.findViewById(R.id.timePost);
        TextView description = (TextView) convertView.findViewById(R.id.contentPost);
        TextView likeNumber = (TextView) convertView.findViewById(R.id.textLike);
        TextView commentNumber = (TextView) convertView.findViewById(R.id.textComment);
        ImageView avatar = (ImageView) convertView.findViewById(R.id.imageView);
        ImageView imagePost = (ImageView) convertView.findViewById(R.id.imgPost);
        final ImageView menu = (ImageView) convertView.findViewById(R.id.menuImage);





        // Set username
        userName.setText(post.getUserName());

        // Set avater image
        String url=post.getUrlAvatar();
        Glide.with(context).load(url).apply(RequestOptions.circleCropTransform().placeholder(R.mipmap.ic_camera)).into(avatar);

        //Set track image
        String url_image=post.getUrlImage();
        Glide.with(context).load(url_image).apply(RequestOptions.placeholderOf(R.mipmap.ic_camera)).into(imagePost);

        // Set other attribute of post ...


        return convertView;
    }

}
