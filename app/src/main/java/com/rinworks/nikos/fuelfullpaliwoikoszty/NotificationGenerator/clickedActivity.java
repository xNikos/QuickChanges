package com.rinworks.nikos.fuelfullpaliwoikoszty.NotificationGenerator;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.rinworks.nikos.fuelfullpaliwoikoszty.Fragments.przypomnienieFragment;
import com.rinworks.nikos.fuelfullpaliwoikoszty.R;

public class clickedActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Fragment przypomnienie = new przypomnienieFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        Bundle data = new Bundle();
        String[] list = new String[2];
        data.putStringArray("data",list);
        setContentView(R.layout.fragment_main_layout);
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, przypomnienie).commit();
    }
}
