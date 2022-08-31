package com.example.mydiary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class Fragment2 extends Fragment implements View.OnClickListener {
    ImageView imageView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2,container,false);

        Button btn1 = view.findViewById(R.id.btn_small);
        Button btn2 = view.findViewById(R.id.btn_large);
        Button btn3 = view.findViewById(R.id.btn_reset);

        imageView = view.findViewById(R.id.image_main);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_small:
                imageView.setScaleX(imageView.getScaleX() - 0.1f);
                imageView.setScaleY(imageView.getScaleY() - 0.1f);
                break;
            case R.id.btn_large:
                imageView.setScaleX(imageView.getScaleX() + 0.1f);
                imageView.setScaleY(imageView.getScaleY() + 0.1f);
                break;
            case R.id.btn_reset:
                imageView.setScaleX(1);
                imageView.setScaleY(1);
                break;

        }
    }
}