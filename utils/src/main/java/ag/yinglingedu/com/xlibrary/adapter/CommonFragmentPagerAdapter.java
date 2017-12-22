package ag.yinglingedu.com.xlibrary.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.List;

/**
 * Created by M 4700 on 2017/6/1.
 */

public class CommonFragmentPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragmentList;


    public CommonFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        if(fragmentList != null){
            return fragmentList.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        if(fragmentList != null){
            return fragmentList.size();
        }
        return 0;
    }
}
