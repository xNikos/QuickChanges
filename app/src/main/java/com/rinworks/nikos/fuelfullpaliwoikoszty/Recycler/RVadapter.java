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
        //TODO: Filter recyclerView?!
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

    public void setFilter(List<Data> newData)
    {
        mData = new List<Data>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Data> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Data data) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Data> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, @NonNull Collection<? extends Data> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Data get(int index) {
                return null;
            }

            @Override
            public Data set(int index, Data element) {
                return null;
            }

            @Override
            public void add(int index, Data element) {

            }

            @Override
            public Data remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @NonNull
            @Override
            public ListIterator<Data> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<Data> listIterator(int index) {
                return null;
            }

            @NonNull
            @Override
            public List<Data> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
        mData.addAll(newData);
        notifyDataSetChanged();
    }

}

