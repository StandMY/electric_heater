package com.example.naroro.electric_heater.Activity;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.naroro.electric_heater.R;

public class MyFragment extends Fragment {

    private MyViewModel mViewModel;
    private ImageView profile_photo;
    private Uri imageUri;
    public static final int CHOOSE_PHOTO = 2 ;

    public static MyFragment newInstance() {
        return new MyFragment();
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.my_fragment, container, false);
        profile_photo = view.findViewById(R.id.profile_photo);

        //选择相册中的文件进行上传
        profile_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,1);
            }
        });

        return view;
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
}
