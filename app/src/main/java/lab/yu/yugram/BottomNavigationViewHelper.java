package lab.yu.yugram;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.lang.reflect.Field;

import lab.yu.yugram.Activity.MainActivity;
import lab.yu.yugram.Activity.NotificationActivity;
import lab.yu.yugram.Activity.PostActivity;
import lab.yu.yugram.Activity.ProfileActivity;
import lab.yu.yugram.Activity.SearchActivity;

/**
 * Created by Yu on 11/16/2017.
 */

public class BottomNavigationViewHelper {

    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);

            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                item.setPadding(0,25,0,0);
                item.setChecked(item.getItemData().isChecked());

            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }

    public static void setupBottomNavigationView(Activity context, int ACTIVITY_NUM){
        BottomNavigationView bottomNavigationView = (BottomNavigationView) context.findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        BottomNavigationViewHelper.enableNavigation(context, bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    public static void enableNavigation(final Context context, BottomNavigationView view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.ic_house:
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);

                        break;
                    case R.id.ic_search:
                        Intent intent1 = new Intent(context, SearchActivity.class);
                        context.startActivity(intent1);
                        break;
                    case R.id.ic_post:
                        Intent intent2 = new Intent(context, PostActivity.class);
                        context.startActivity(intent2);
                        break;
                    case R.id.ic_notification:
                        Intent intent3 = new Intent(context, NotificationActivity.class);
                        context.startActivity(intent3);
                        break;
                    case R.id.ic_profile:
                        Intent intent4 = new Intent(context, ProfileActivity.class);
                        context.startActivity(intent4);
                        break;
                }
                return false;
            }
        });
    }
}
