package com.rinworks.nikos.fuelfullpaliwoikoszty.Fragments;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rinworks.nikos.fuelfullpaliwoikoszty.Database.AppDatabase;
import com.rinworks.nikos.fuelfullpaliwoikoszty.Database.Data;
import com.rinworks.nikos.fuelfullpaliwoikoszty.R;
import com.rinworks.nikos.fuelfullpaliwoikoszty.Recycler.RVadapter;

import java.util.List;


public class naprawaFragment extends Fragment {

    private RecyclerView recyclerView;


    //static
    public naprawaFragment newInstance()
    {
        naprawaFragment naprawa =new naprawaFragment();
        return naprawa;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Wykonane naprawy:");
        String[] passedData = getArguments().getStringArray("data");
        View rootView = inflater.inflate(R.layout.fragment_main_layout, container, false);

        //Initialize DB
        AppDatabase db = Room.databaseBuilder(getActivity(), AppDatabase.class, "database")
                .allowMainThreadQueries().build();

        //Initialize Recycler
        recyclerView = rootView.findViewById(R.id.card_recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        lm.setReverseLayout(true); //odwrócenie Layoutu (najnowsze u góry)
        lm.setStackFromEnd(true); //odwrócenie początku (początek u najnowszych)
        recyclerView.setLayoutManager(lm);

        if (passedData[0] != null) {
            Data data = new Data(1, passedData[0], passedData[1], null,null);
            db.DataDao().insertAll(data);
        }
        List<Data> loadData = db.DataDao().selectType(1);
        recyclerView.setAdapter(new RVadapter(loadData));
        return rootView;
    }

}
