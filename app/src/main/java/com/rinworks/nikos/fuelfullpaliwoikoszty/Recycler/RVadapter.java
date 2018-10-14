package com.rinworks.nikos.fuelfullpaliwoikoszty.Recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        public TankowanieVH(View itemView) {
            super(itemView);
            this.zatankowano_zaplacono_tytulNotatki = (TextView) itemView.findViewById(R.id
                    .cardOption1Value);
            this.zaplacono_naprawiono = (TextView) itemView.findViewById(R.id.cardOption2Value);
            this.przejechano = (TextView) itemView.findViewById(R.id.cardOption3Value);
            this.spalanie = (TextView) itemView.findViewById(R.id.cardOption4Value);
        }
    }

    class NaprawaVH extends RecyclerView.ViewHolder {
        TextView zatankowano_zaplacono_tytulNotatki;
        TextView zaplacono_naprawiono;
        public NaprawaVH(View itemView) {
            super(itemView);
            this.zatankowano_zaplacono_tytulNotatki = (TextView) itemView.findViewById(R.id
                    .cardOption1Value);
            this.zaplacono_naprawiono = (TextView) itemView.findViewById(R.id.cardOption2Value);
        }
    }

    class PrzypomnienieVH extends RecyclerView.ViewHolder {
        TextView zatankowano_zaplacono_tytulNotatki;
        TextView zaplacono_naprawiono_notatka;
        public PrzypomnienieVH(View itemView) {
            super(itemView);
            this.zatankowano_zaplacono_tytulNotatki = (TextView) itemView.findViewById(R.id
                    .cardTitleP);
            this.zaplacono_naprawiono_notatka = (TextView) itemView.findViewById(R.id
                    .cardOption1Value);
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
        switch (holder.getItemViewType()) {
            case 0:
                TankowanieVH tankowanieVH = (TankowanieVH) holder;
                tankowanieVH.zatankowano_zaplacono_tytulNotatki.setText(mData.get(position).getZatankowano_zaplacono_tytulNotatki());
                tankowanieVH.zaplacono_naprawiono.setText(mData.get(position).getZaplacono_naprawiono_notatka());
                tankowanieVH.przejechano.setText(mData.get(position).getPrzejechano());
                tankowanieVH.spalanie.setText(mData.get(position).getSpalanie());
                break;
            case 1:
                NaprawaVH naprawaVH = (NaprawaVH) holder;
                naprawaVH.zatankowano_zaplacono_tytulNotatki.setText(mData.get(position)
                        .getZatankowano_zaplacono_tytulNotatki());
                naprawaVH.zaplacono_naprawiono.setText(mData.get(position)
                        .getZaplacono_naprawiono_notatka());
                break;
            case 2:
                PrzypomnienieVH przypomnienieVH = (PrzypomnienieVH) holder;
                przypomnienieVH.zatankowano_zaplacono_tytulNotatki.setText(mData.get(position)
                        .getZatankowano_zaplacono_tytulNotatki());
                przypomnienieVH.zaplacono_naprawiono_notatka.setText(mData.get(position)
                        .getZaplacono_naprawiono_notatka());
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}

