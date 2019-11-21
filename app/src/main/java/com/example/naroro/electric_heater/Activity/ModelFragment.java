package com.example.naroro.electric_heater.Activity;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.naroro.electric_heater.Fragment.auto_mode_fragment;
import com.example.naroro.electric_heater.Fragment.time_mode_fragment;
import com.example.naroro.electric_heater.R;

import java.util.ArrayList;
import java.util.List;

public class ModelFragment extends Fragment {

    private ModelViewModel mViewModel;
    private TextView model_text;
    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;
    private List<Fragment> pages;
    private RadioButton rb_auto_mode;
    private RadioButton rb_time_mode;

    public static ModelFragment newInstance() {
        return new ModelFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.model_fragment, container, false);

        //绑定控件
        mRadioGroup = view.findViewById(R.id.radio_group);
        mViewPager = view.findViewById(R.id.model_viewpager);
        rb_auto_mode = view.findViewById(R.id.auto_mode);
        rb_time_mode = view.findViewById(R.id.time_mode);

        //实现了滑动时显示不同的页面
        pages = new ArrayList<>();
        pages.add(new auto_mode_fragment());
        pages.add(new time_mode_fragment());
        mViewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return pages.get(position);
            }

            @Override
            public int getCount() {
                return pages.size();
            }
        });

        //滑动页面时改变RadioButton(被动改变)
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override//页面滑动后的状态
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        rb_auto_mode.setChecked(true);
                        break;
                    case 1:
                        rb_time_mode.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //点击RadioButton，实现切换ViewPager效果
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.auto_mode:
                        mViewPager.setCurrentItem(0,true);
                        break;
                    case R.id.time_mode:
                        mViewPager.setCurrentItem(1,true);
                        break;
                }
            }
        });


        //设备名称下拉框的选择
        String[] devices_list = new String[]{"客厅", "餐厅", "主卧", "次卧", "书房"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, devices_list);  //创建一个数组适配器
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     //设置下拉列表框的下拉选项样式
        Spinner spinner = view.findViewById(R.id.device_spinner);
        spinner.setAdapter(adapter);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ModelViewModel.class);
        // TODO: Use the ViewModel
        //owner:有lifecycle的对象，监听到变化后，view自动刷新

    }

}
