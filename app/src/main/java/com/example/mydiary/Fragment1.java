package com.example.mydiary;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Fragment1 extends Fragment {
    EditText editDiary;
    Button btnWrite;
    String fileName;
    String str;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        editDiary = view.findViewById(R.id.editDiary);
        btnWrite = view.findViewById(R.id.btn_write);
        DatePicker dp = view.findViewById(R.id.datepicker);
        dp.init(dp.getYear(), dp.getMonth(), dp.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Log.d("DATE",year+"_"+(monthOfYear+1)+"_"+dayOfMonth);
                fileName = year + "_" +monthOfYear + "_" + dayOfMonth+".txt";
                str = readDiary(fileName);
                editDiary.setText(str);
            }
        });
        view.findViewById(R.id.btn_write).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream outfs = getContext().openFileOutput(fileName, Context.MODE_PRIVATE);
                    str = editDiary.getText().toString();
                    outfs.write(str.getBytes());
                    outfs.close();
                    Toast.makeText(getContext(), fileName+"저장했습니다", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }

    private String readDiary(String fileName) {
        String diaryStr = null;
        try {
            FileInputStream inFs = getContext().openFileInput(fileName);
            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();

            diaryStr = (new String(txt));
            btnWrite.setText("수정하기");
        } catch (IOException e) {
            editDiary.setHint("일기 없음");
            btnWrite.setText("새로 저장");
        }
        return diaryStr;
    }
}