package com.example.chouqu;

import android.app.NativeActivity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.chouqu.adapter.VpAdapter;
import com.example.chouqu.base.BaseActivity;
import com.example.chouqu.fragment.WanandroidFragment;
import com.example.chouqu.fragment.WebFragment;
import com.example.chouqu.fragment.XiangjiFragment;
import com.example.chouqu.presenter.MainP;
import com.example.chouqu.view.MainV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Date
 * //                            _ooOoo_
 * //                           o8888888o
 * //                           88" . "88
 * //                           (| -_- |)
 * //                           O\  =  /O
 * //                        ____/`---'\____
 * //                      .'  \\|     |//  `.
 * //                     /  \\|||  :  |||//  \
 * //                     /  _||||| -:- |||||-  \
 * //                     |   | \\\  -  /// |   |
 * //                    | \_|  ''\---/''  |   |
 * //                    \  .-\__  `-`  ___/-. /
 * //                  ___`. .'  /--.--\  `. . __
 * //                ."" '<  `.___\_<|>_/___.'  >'"".
 * //              | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * //               \  \ `-.   \_ __\ /__ _/   .-` /  /
 * //          ======`-.____`-.___\_____/___.-`____.-'======
 * //                             `=---='
 * //         ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 * //                    佛祖保佑        永无BUG
 * //            佛曰:
 * //                  写字楼里写字间，写字间里程序员；
 * //                  程序人员写程序，又拿程序换酒钱。
 * //                  酒醒只在网上坐，酒醉还来网下眠；
 * //                  酒醉酒醒日复日，网上网下年复年。
 * //                  但愿老死电脑间，不愿鞠躬老板前；
 * //                  奔驰宝马贵者趣，公交自行程序员。
 * //                  别人笑我忒疯癫，我笑自己命太贱；
 * //                  不见满街漂亮妹，哪个归得程序员？
 * //                                        --白嘎力
 */

public class MainActivity extends BaseActivity<MainV, MainP> implements MainV, NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.tab)
    TabLayout mtab;
    @BindView(R.id.too)
    Toolbar mToo;
    @BindView(R.id.Na)
    NavigationView mNa;
    @BindView(R.id.Dl)
    DrawerLayout mDl;

    @Override
    protected MainP initPresenter() {
        return new MainP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
    //侧滑栏和tooldar

    @Override
    protected void initView() {
        super.initView();
        mToo.setTitle("popu");
        setSupportActionBar(mToo);
        mNa.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDl ,mToo, R.string.app_name, R.string.app_name);
        mDl.addDrawerListener(toggle);
        toggle.syncState();
    }
//123123
    //tab栏
    @Override
    protected void initData() {
        super.initData();
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new WanandroidFragment());
        fragments.add(new WebFragment());
        fragments.add(new XiangjiFragment());

        mtab.addTab(mtab.newTab().setText("玩安卓"));
        mtab.addTab(mtab.newTab().setText("福利"));
        mtab.addTab(mtab.newTab().setText("发现"));
        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager(), fragments);
        mVp.setAdapter(vpAdapter);
        mtab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mVp.setCurrentItem(tab.getPosition());
                mToo.setTitle(tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mtab));
    }
    //侧滑监听
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                pop();
                mDl.closeDrawer(Gravity.LEFT);
                break;
            case R.id.item2:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                mDl.closeDrawer(Gravity.LEFT);
                break;
            case R.id.item3:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                mDl.closeDrawer(Gravity.LEFT);
                break;
        }
        return true;

    }
    //选项菜单

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1,1,1,"通知");
        return super.onCreateOptionsMenu(menu);

    }
    //选项菜单监听
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                send2();
        }
        return super.onOptionsItemSelected(item);
    }
         private void send2() {
             NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
             String channelId = "1";
             String channelName = "default";
             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                 NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
                 channel.enableLights(true);
                 channel.setLightColor(Color.RED);
                 channel.setShowBadge(true);
                 manager.createNotificationChannel(channel);
             }
                 //延时意图
                 Intent intent = new Intent(this,NativeActivity.class);
                 PendingIntent pendingIntent = PendingIntent.getActivity(this,100,intent,PendingIntent.FLAG_CANCEL_CURRENT);
                 //2.创建构建器
                 NotificationCompat.Builder builder = new NotificationCompat.Builder(this,channelId);
                 builder.setSmallIcon(R.mipmap.ic_launcher);
                 builder.setContentTitle("你大爷");
                 builder.setContentText("Dasdsadas");
                 builder.setContentIntent(pendingIntent);
                 builder.setAutoCancel(true);//设置大图
                 builder.setDefaults(Notification.DEFAULT_ALL);
                 //3.生成通知
                 Notification notification = builder.build();
                 //4.发送通知
                 manager.notify(100,notification);
             }


    //popu
    private void pop() {
        //1.创建PoP
        View inflate = LayoutInflater.from(this).inflate(R.layout.item, null);


        final PopupWindow popupWindow1 = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow1.setOutsideTouchable(true);
        popupWindow1.setFocusable(true);
        popupWindow1.setBackgroundDrawable(new ColorDrawable());
        popupWindow1.showAtLocation(inflate, Gravity.CENTER, 0, 0);
    //            //3.事件处理
        Button yes = inflate.findViewById(R.id.btn_yes);
        Button on = inflate.findViewById(R.id.btn_on);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow1.dismiss();
            }
        });
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow1.dismiss();
            }
        });
    }


}
