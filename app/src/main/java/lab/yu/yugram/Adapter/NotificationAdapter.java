package lab.yu.yugram.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import lab.yu.yugram.Activity.MainActivity;
import lab.yu.yugram.Activity.NotificationActivity;
import lab.yu.yugram.Model.Notification;
import lab.yu.yugram.Model.Post;
import lab.yu.yugram.Model.UserAccount;
import lab.yu.yugram.R;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by Yu on 11/20/2017.
 */

public class NotificationAdapter extends ArrayAdapter<Notification> {
    private Activity context;
    String currentUser;

    public NotificationAdapter(Activity context, int layoutID, List<Notification> objects) {
        super(context, layoutID, objects);
        this.context = context;
        // Get current user
        SharedPreferences sharedPreferences= context.getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        currentUser = sharedPreferences.getString("userName","");



    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_notification, null,
                    false);

        }


        // Get item
        final Notification account = getItem(position);

        // Get view
        String userFriend = account.getUserFriend();
        String descriptionString = account.getDescription();
        String content = userFriend + " " + descriptionString;
        int userFriendLength = userFriend.length();

        TextView userName = (TextView) convertView.findViewById(R.id.userNotification);
        TextView description = (TextView) convertView.findViewById(R.id.descriptionNotification);
        ImageView avatar = (ImageView) convertView.findViewById(R.id.imageViewNotification);

        SpannableString ss = new SpannableString(content);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan, 0, userFriendLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new android.text.style.StyleSpan(Typeface.BOLD),0,userFriendLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(Color.BLACK), 0, userFriendLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        userName.setText(ss);
        userName.setMovementMethod(LinkMovementMethod.getInstance());
        userName.setHighlightColor(Color.TRANSPARENT);



/*        // Set username
        userName.setText(account.getUsername());*/

        // Set avater image
        String url=account.getUrlAvatar();
        Glide.with(context).load(url).apply(RequestOptions.circleCropTransform().placeholder(R.mipmap.ic_launcher_round)).into(avatar);


        // Set other attribute of post ...


        return convertView;
    }

}

