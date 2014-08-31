package com.wangjie.theoldreaderforandroid.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.astuetz.PagerSlidingTabStrip;
import com.wangjie.androidbucket.log.Logger;
import com.wangjie.androidbucket.objs.DelayObj;
import com.wangjie.androidbucket.utils.ABPrefsUtil;
import com.wangjie.androidinject.annotation.annotations.base.AILayout;
import com.wangjie.androidinject.annotation.annotations.base.AIView;
import com.wangjie.theoldreaderforandroid.R;
import com.wangjie.theoldreaderforandroid.database.DbExecutor;
import com.wangjie.theoldreaderforandroid.entity.CustomDelayTab;
import com.wangjie.theoldreaderforandroid.entity.FeedItem;
import com.wangjie.theoldreaderforandroid.prefs.PrefsKey;
import com.wangjie.theoldreaderforandroid.ui.base.BaseActivity;
import com.wangjie.theoldreaderforandroid.ui.base.BaseFragment;
import com.wangjie.theoldreaderforandroid.ui.fragment.FeedFragment;
import com.wangjie.theoldreaderforandroid.ui.fragment.SettingFragment;
import com.wangjie.theoldreaderforandroid.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

@AILayout(R.layout.main)
public class MainActivity extends BaseActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @AIView(R.id.main_tab_strips)
    private PagerSlidingTabStrip tabStrip;
    @AIView(R.id.main_tab_vp)
    private ViewPager tabVp;

    private List<CustomDelayTab<BaseFragment>> tabFragments = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tabFragments.add(
                new CustomDelayTab<BaseFragment>()
                        .setClazz(FeedFragment.class)
                        .setTitle("feed")
        );
        tabFragments.add(
                new CustomDelayTab<BaseFragment>()
                        .setClazz(SettingFragment.class)
                        .setTitle("setting")
        );

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("The Old Reader");

        tabVp.setAdapter(tabAdapter);
        tabStrip.setViewPager(tabVp);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        menu.findItem(R.id.menu_main_account).setTitle(ABPrefsUtil.getInstance().getString(PrefsKey.KEY_USERNAME));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_main_account:
                Toast.makeText(context, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_main_setting:
                Toast.makeText(context, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_main_loginout:
                new AlertDialog.Builder(context)
                        .setTitle("Warn")
                        .setMessage("Login Out?")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AppUtil.loginOut(context);
                                finish();
                            }
                        })
                        .show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private FragmentStatePagerAdapter tabAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int i) {
            CustomDelayTab<BaseFragment> fragmentDelayObj = tabFragments.get(i);
            BaseFragment fragment = fragmentDelayObj.getObj();
            if(null == fragment){
                try {
                    fragment = fragmentDelayObj.getClazz().newInstance();
                    fragmentDelayObj.setObj(fragment);
                } catch (Exception e) {
                    Logger.e(TAG, e);
                }
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return tabFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabFragments.get(position).getTitle();
        }
    };



}
