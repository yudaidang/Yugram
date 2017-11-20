package lab.yu.yugram.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import lab.yu.yugram.Model.Post;
import lab.yu.yugram.Model.UserAccount;
import lab.yu.yugram.R;

/**
 * Created by Yu on 11/19/2017.
 */

public class UserAdapter extends ArrayAdapter<UserAccount> {
    private Activity context;
    String currentUser;

    public UserAdapter(Activity context, int layoutID, List<UserAccount> objects) {
        super(context, layoutID, objects);
        this.context = context;
        // Get current user
        SharedPreferences sharedPreferences= context.getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        currentUser = sharedPreferences.getString("userName","");

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_search, null,
                    false);

        }


        // Get item
        final UserAccount account = getItem(position);

        // Get view


        TextView userName = (TextView) convertView.findViewById(R.id.userSearch);
        TextView description = (TextView) convertView.findViewById(R.id.descriptionSearch);
        ImageView avatar = (ImageView) convertView.findViewById(R.id.imageViewSearch);





        // Set username
        userName.setText(account.getUsername());

        // Set avater image
        String url=account.getUrlAvatar();
        Glide.with(context).load(url).apply(RequestOptions.circleCropTransform().placeholder(R.mipmap.ic_launcher_round)).into(avatar);


        // Set other attribute of post ...


        return convertView;
    }

}
