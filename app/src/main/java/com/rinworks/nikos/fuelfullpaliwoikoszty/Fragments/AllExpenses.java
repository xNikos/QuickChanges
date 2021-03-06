package com.rinworks.nikos.fuelfullpaliwoikoszty.Fragments;

import android.arch.persistence.room.Room;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rinworks.nikos.fuelfullpaliwoikoszty.Database.AppDatabase;
import com.rinworks.nikos.fuelfullpaliwoikoszty.Database.Data;
import com.rinworks.nikos.fuelfullpaliwoikoszty.MainActivity;
import com.rinworks.nikos.fuelfullpaliwoikoszty.R;
import com.rinworks.nikos.fuelfullpaliwoikoszty.Recycler.RVadapter;
import java.util.ArrayList;
import java.util.List;


public class AllExpenses extends Fragment {

    private RecyclerView recyclerView;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Wszystkie zdarzenia:");
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

        //Initialize "Brak danych!!"
        textView = rootView.findViewById(R.id.data_string);

        //Wczytaj dane z bazy
        List<Data> loadData = db.DataDao().getAll();

        //Napis Brak danych!!
        if (loadData.size() != 0)
            { textView.setVisibility(View.GONE);}
        else
            { textView.setVisibility(View.VISIBLE); }

        recyclerView.setAdapter(new RVadapter(loadData));

        return rootView;
    }

}
