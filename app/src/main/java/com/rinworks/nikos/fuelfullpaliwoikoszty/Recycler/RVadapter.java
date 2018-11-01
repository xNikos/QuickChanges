package com.rinworks.nikos.fuelfullpaliwoikoszty.Recycler;

import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rinworks.nikos.fuelfullpaliwoikoszty.Database.AppDatabase;
import com.rinworks.nikos.fuelfullpaliwoikoszty.Database.Data;
import com.rinworks.nikos.fuelfullpaliwoikoszty.R;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class RVadapter extends RecyclerView.Adapter {

    private List<Data> mData;

    class TankowanieVH extends RecyclerView.ViewHolder {

        TextView zatankowano_zaplacono_tytulNotatki;
        TextView zaplacono_naprawiono;
        TextView przejechano;
        TextView spalanie;
        TextView data;

        public TankowanieVH(View itemView) {
            super(itemView);
            this.zatankowano_zaplacono_tytulNotatki = (TextView) itemView.findViewById(R.id
                    .cardOption1Value);
            this.zaplacono_naprawiono = (TextView) itemView.findViewById(R.id.cardOption2Value);
            this.przejechano = (TextView) itemView.findViewById(R.id.cardOption3Value);
            this.spalanie = (TextView) itemView.findViewById(R.id.cardOption4Value);
            this.data = (TextView) itemView.findViewById(R.id.Date);
        }
    }

    class NaprawaVH extends RecyclerView.ViewHolder {
        TextView zatankowano_zaplacono_tytulNotatki;
        TextView zaplacono_naprawiono;
        TextView data;
        public NaprawaVH(View itemView) {
            super(itemView);
            this.zatankowano_zaplacono_tytulNotatki = (TextView) itemView.findViewById(R.id
                    .cardOption1Value);
            this.zaplacono_naprawiono = (TextView) itemView.findViewById(R.id.cardOption2Value);
            this.data = (TextView) itemView.findViewById(R.id.Date);
        }
    }

    class PrzypomnienieVH extends RecyclerView.ViewHolder {
        TextView zatankowano_zaplacono_tytulNotatki;
        TextView zaplacono_naprawiono_notatka;
        TextView data;
        public PrzypomnienieVH(View itemView) {
            super(itemView);
            this.zatankowano_zaplacono_tytulNotatki = (TextView) itemView.findViewById(R.id
                    .cardTitleP);
            this.zaplacono_naprawiono_notatka = (TextView) itemView.findViewById(R.id
                    .cardOption1Value);
            this.data = (TextView) itemView.findViewById(R.id.Date);
        }
    }


    public RVadapter(List<Data> data) {
        mData = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
            switch (viewType) {
                case 0:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tankowanie, parent, false);
                    return new TankowanieVH(view);
                case 1:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_naprawa, parent,
                            false);
                    return new NaprawaVH(view);
                case 2:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                            .card_przypomnienie, parent, false);
                    return new PrzypomnienieVH(view);
            }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getType();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        String st = String.valueOf(mData.get(position).getZatankowano());
        float zaplaconoV = (float) Math.round((mData.get(position).getZaplacono())*(mData.get
                (position).getZatankowano()) *100)/100;
        String nd = String.valueOf(zaplaconoV);
        String rd = String.valueOf(mData.get(position).getPrzejechano());
        String data = mData.get(position).getData();


        switch (holder.getItemViewType()) {
            case 0:
                TankowanieVH tankowanieVH = (TankowanieVH) holder;
                tankowanieVH.zatankowano_zaplacono_tytulNotatki.setText(st + " L");
                tankowanieVH.zaplacono_naprawiono.setText(nd + " ZŁ");
                tankowanieVH.przejechano.setText(rd + " KM");
                tankowanieVH.data.setText(data);
                if(position!=0) {
                    float spalanieV = (float) Math.round((mData.get(position-1).getZatankowano())/
                            (mData
                            .get
                            (position).getPrzejechano())*10000)/100;
                    String th = String.valueOf(spalanieV);
                    tankowanieVH.spalanie.setText(th + " L/100");
                }
                else
                { tankowanieVH.spalanie.setText("--"); }
                break;
            case 1:
                NaprawaVH naprawaVH = (NaprawaVH) holder;
                naprawaVH.zatankowano_zaplacono_tytulNotatki.setText(String.valueOf(mData.get
                        (position).getZaplacono()) + " ZŁ");
                naprawaVH.zaplacono_naprawiono.setText(mData.get(position)
                        .getNotatka_naprawiono());
                naprawaVH.data.setText(mData.get(position).getData());
                break;
            case 2:
                PrzypomnienieVH przypomnienieVH = (PrzypomnienieVH) holder;
                przypomnienieVH.zatankowano_zaplacono_tytulNotatki.setText(mData.get(position)
                        .getTytulNotatki());
                przypomnienieVH.zaplacono_naprawiono_notatka.setText(mData.get(position)
                        .getNotatka_naprawiono());
                przypomnienieVH.data.setText(mData.get(position).getData());
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}

