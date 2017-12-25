package soft.robert.com.githubdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import soft.robert.com.xlibrary.base.BaseFragment;
import soft.robert.com.xlibrary.utils.SupportMultipleScreensUtil;

/**
 * Created on 2017/12/25.
 *
 * @author cpf
 * @2227039052@qq.com
 */

public class FirstPageFragment extends BaseFragment {

    private View view;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_first_page, null);
            SupportMultipleScreensUtil.scale(view);
            unbinder = ButterKnife.bind(this, view);
            initData(savedInstanceState);
        }
        //判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    public void initData(Bundle savedInstanceState) {

    }
}
