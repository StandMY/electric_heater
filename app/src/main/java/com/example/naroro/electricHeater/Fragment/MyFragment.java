package com.example.naroro.electricHeater.Fragment;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.naroro.electricHeater.Activity.LoginActivity;
import com.example.naroro.electricHeater.Activity.RegisterActivity;
import com.example.naroro.electricHeater.R;
import com.example.naroro.electricHeater.databinding.ActivityLoginBinding;
import com.example.naroro.electricHeater.utils.AlertDialogManager;

import java.io.File;

public class MyFragment extends Fragment {

    //private FragMainBinding mBinding;
    private MyViewModel mViewModel;
    private ImageView profile_photo;
    private ImageView contact_info_detail;
    private Button exit;

    private Uri imageUri;
    public static final int CHOOSE_PHOTO = 2 ;
    static String TAG = "Exit";

    public static MyFragment newInstance() {
        return new MyFragment();
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.my_fragment, container, false);
        profile_photo = view.findViewById(R.id.profile_photo);
        exit = view.findViewById(R.id.exit);
        contact_info_detail = view.findViewById(R.id.contact_info_detail);

        //选择相册中的文件进行上传
        profile_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,1);
            }
        });

        //显示客服联系方式
        contact_info_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showContactInfo();
            }
        });

        //发送广播，强制退出登录
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除sharedPreference中的东西，即token值
                clear(getActivity());
                //返回登录界面
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public static void clear(Context context) {

        //删除sharedPreference文件
        File directory = new File("/data/data/" + context.getPackageName() + "/shared_prefs");
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }

        //删除缓存
        SharedPreferences preferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }



    //打开相册
    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        // TODO: Use the ViewModel

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1){
            Toast.makeText(getActivity(),data.getData().toString(),Toast.LENGTH_LONG).show();
            Log.e("mainActivity",data.getData().toString());
            profile_photo.setImageURI(data.getData());
        }
    }

    private void showContactInfo() {
        AlertDialogManager.showDialog(getActivity(),
                "联系方式",
                "客服电话："+ "18813194197",
                "拨打",
                "确定",
                false,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callPhone("18813194197");
                        dialog.dismiss();
                    }
                },
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "确定", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
    }

    /**
     * 跳转到拨号界面，手动点击拨打电话
     * @param service_phoneNum
     */
    private void callPhone(String service_phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + service_phoneNum);
        intent.setData(data);
        startActivity(intent);
    }
}
