package com.example.snapwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class MemoEditActivity extends AppCompatActivity {
    private EditText memo_edit;
    int memoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_edit);
        memo_edit = findViewById(R.id.memo_edit);

        Intent intent = getIntent();
        memoId = intent.getIntExtra("memoId", -1);

        if (memoId != -1){
            memo_edit.setText(MemoActivity.memo.get(memoId));
        }else{
            MemoActivity.memo.add("");
            memoId = MemoActivity.memo.size() -1;
            MemoActivity.arrayAdapter.notifyDataSetChanged();
        }

        memo_edit.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

                MemoActivity.memo.set(memoId, String.valueOf(charSequence));
                MemoActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.snapwatch", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(MemoActivity.memo);
                sharedPreferences.edit().putStringSet("memo", set).apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}