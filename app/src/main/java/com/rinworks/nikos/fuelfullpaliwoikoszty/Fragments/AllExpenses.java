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

import com.rinworks.nikos.fuelfullpaliwoikoszty.Database.AppDatabase;
import com.rinworks.nikos.fuelfullpaliwoikoszty.Database.Data;
import com.rinworks.nikos.fuelfullpaliwoikoszty.MainActivity;
import com.rinworks.nikos.fuelfullpaliwoikoszty.R;
import com.rinworks.nikos.fuelfullpaliwoikoszty.Recycler.RVadapter;
import java.util.ArrayList;
import java.util.List;


public class AllExpenses extends Fragment {

    private RecyclerView recyclerView;


    //static
    public AllExpenses newInstance()
    {
        AllExpenses allExpenses=new AllExpenses();
        return allExpenses;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Wszystkie wydatki:");
        String[] passedData = getArguments().getStringArray("data");
        View rootView = inflater.inflate(R.layout.fragment_main_layout, container, false);

        if (passedData[0] != null) {
            AppDatabase db = Room.databaseBuilder(getActivity(), AppDatabase.class, "database")
                    .allowMainThreadQueries().build();
            Data data = new Data(0, passedData[0], passedData[1], passedData[2], passedData[3]);
            db.DataDao().insertAll(data);
            List<Data> data1 = db.DataDao().getAll();

            recyclerView = rootView.findViewById(R.id.card_recycler_main);
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            LinearLayoutManager lm = new LinearLayoutManager(getActivity());
            lm.setReverseLayout(true);
            lm.setStackFromEnd(true);
            recyclerView.setLayoutManager(lm);
            recyclerView.setAdapter(new RVadapter(data1));
        }
        return rootView;
    }

}
