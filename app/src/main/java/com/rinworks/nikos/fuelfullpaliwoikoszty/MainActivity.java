package com.rinworks.nikos.fuelfullpaliwoikoszty;

import android.app.AlertDialog;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rinworks.nikos.fuelfullpaliwoikoszty.Database.AppDatabase;
import com.rinworks.nikos.fuelfullpaliwoikoszty.Database.Data;
import com.rinworks.nikos.fuelfullpaliwoikoszty.Fragments.AllExpenses;
import com.rinworks.nikos.fuelfullpaliwoikoszty.Fragments.naprawaFragment;
import com.rinworks.nikos.fuelfullpaliwoikoszty.Fragments.przypomnienieFragment;
import com.rinworks.nikos.fuelfullpaliwoikoszty.Fragments.tankowanieFragment;
import com.rinworks.nikos.fuelfullpaliwoikoszty.Recycler.RVadapter;

import java.util.ArrayList;
import java.util.List;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Navbar reference
        NavigationView navigationView = findViewById(R.id.NavigationView);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_all_expenses);

        //Drawer reference
        DrawerLayout drawer = findViewById(R.id.drawerLayout);

        //Adding hamburger icon to drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open_navigation_drawer, R.string.close_navigation_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //FAB reference
        final FabSpeedDial fab = findViewById(R.id.extendedFab);

        //FAB DIALOGS
        fab.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    //Tankowanie
                    case R.id.fab_tankowanie:
                        //Dialog create
                        AlertDialog.Builder popupBuilder = new AlertDialog.Builder(MainActivity.this);
                        View mView = getLayoutInflater().inflate(R.layout.popup_tankowanie, null);
                        TextView tankowanie_title = mView.findViewById(R.id.tankowanie_popup_title);
                        TextView zatankowano = mView.findViewById(R.id.Zatankowano);
                        final TextView cenaL = mView.findViewById(R.id.CenaL);
                        final TextView przejechano = mView.findViewById(R.id.PrzejechanoT);
                        final EditText zatankowanoV = mView.findViewById(R.id.ZatankowanioVALUE);
                        zatankowanoV.setFilters(new InputFilter[]{new DecimalDigitsInputFilter
                                (2, 2)});
                        final EditText CenaLV = mView.findViewById(R.id.CenaLValue);
                        CenaLV.setFilters(new InputFilter[]{new DecimalDigitsInputFilter
                                (1, 2)});
                        final EditText przejechanoV = mView.findViewById(R.id.PrzejechanoTValue);
                        przejechanoV.setFilters(new InputFilter[]{new DecimalDigitsInputFilter
                                (3, 2)});
                        Button okbtn = mView.findViewById(R.id.tankowanie_ok_btn);
                        Button cancelbtn = mView.findViewById(R.id.tankowanie_cancel_btn);

                        popupBuilder.setView(mView);
                        final AlertDialog dialog = popupBuilder.create();
                        dialog.show();

                        //dialog buttons on click
                        okbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!zatankowanoV.getText().toString().isEmpty() && !CenaLV.getText().toString
                                        ().isEmpty() && !przejechanoV.getText().toString().isEmpty()) {
                                    Fragment tankowanie = new tankowanieFragment();
                                    Bundle data = new Bundle();
                                    String[] list = new String[4];
                                    list[0] = zatankowanoV.getText().toString();
                                    list[1] = CenaLV.getText().toString();
                                    list[2] = przejechanoV.getText().toString();
                                    list[3] = "HARDODED ATM";
                                    data.putStringArray("data",list);
                                    tankowanie.setArguments(data);
                                    dialog.dismiss();
                                    MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id
                                            .fragmentContainer, tankowanie)
                                            .commit();; //ODŚWIEŻ
                                    Snackbar mSnack = Snackbar.make(findViewById(R.id.drawerLayout),
                                            "Dodano do bazy! :)", Snackbar
                                                    .LENGTH_LONG);
                                    mSnack.show();

                                } else {
                                    Toast mToast = Toast.makeText(MainActivity.this, "Proszę wypełnij " +
                                            "wszystkie pola!", Toast.LENGTH_SHORT);
                                    mToast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL,
                                            0, 0);
                                    mToast.show();

                                }
                            }
                        });

                        cancelbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                Snackbar mSnack = Snackbar.make(findViewById(R.id.drawerLayout),
                                        "Anulowano!", Snackbar.LENGTH_LONG);
                                mSnack.show();
                            }
                        });
                        break;
                        //Przypomnienie
                    case R.id.fab_przypomnienie:
                        //Dialog create
                        AlertDialog.Builder popupBuilderNotification = new AlertDialog.Builder
                                (MainActivity.this);
                        View mViewNotification = getLayoutInflater().inflate(R.layout.popup_przypomnienie,
                                null);
                        TextView przypomnienie_title = mViewNotification.findViewById(R.id.przypomnienie_popup_title);
                        TextView tytul = mViewNotification.findViewById(R.id.Tytuł);
                        TextView tresc = mViewNotification.findViewById(R.id.Tresc);
                        TextView kiedy = mViewNotification.findViewById(R.id.Kiedy);
                        final EditText tytulV = mViewNotification.findViewById(R.id.TytulValue);
                        final EditText trescV = mViewNotification.findViewById(R.id.TrescValue);
                        final EditText kiedyV = mViewNotification.findViewById(R.id.KiedyV);
                        okbtn = mViewNotification.findViewById(R.id.tankowanie_ok_btn);
                        cancelbtn = mViewNotification.findViewById(R.id.tankowanie_cancel_btn);

                        popupBuilderNotification.setView(mViewNotification);
                        final AlertDialog dialogNotification = popupBuilderNotification.create();
                        dialogNotification.show();

                        okbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!trescV.getText().toString().isEmpty() && !tytulV.getText()
                                        .toString().isEmpty() && !kiedyV.getText().toString().isEmpty()) {
                                    Fragment przypomnienie = new przypomnienieFragment();
                                    Bundle data = new Bundle();
                                    String[] list = new String[2];
                                    list[0] = tytulV.getText().toString();
                                    list[1] = trescV.getText().toString();
                                    data.putStringArray("data",list);
                                    przypomnienie.setArguments(data);
                                    dialogNotification.dismiss();
                                    MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id
                                            .fragmentContainer, przypomnienie)
                                            .commit();; //ODŚWIEŻ
                                    Snackbar mSnack = Snackbar.make(findViewById(R.id.drawerLayout),
                                            "Dodano do bazy! :)", Snackbar
                                                    .LENGTH_LONG);
                                    mSnack.show();

                                } else {
                                    Toast mToast = Toast.makeText(MainActivity.this, "Proszę wypełnij " +
                                            "wszystkie pola!", Toast.LENGTH_SHORT);
                                    mToast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL,
                                            0, 0);
                                    mToast.show();

                                }
                            }
                        });

                        cancelbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogNotification.dismiss();
                                Snackbar mSnack = Snackbar.make(findViewById(R.id.drawerLayout),
                                        "Anulowano!", Snackbar.LENGTH_LONG);
                                mSnack.show();
                            }
                        });
                        break;
                    //Naprawa
                    case R.id.fab_naprawa:
                        //Dialog create
                        AlertDialog.Builder popupBuilderRepair = new AlertDialog.Builder
                                (MainActivity.this);
                        View mViewRepair = getLayoutInflater().inflate(R.layout.popup_naprawa,
                                null);
                        TextView naprawa_title = mViewRepair.findViewById(R.id.naprawa_popup_title);
                        TextView Zaplacono = mViewRepair.findViewById(R.id.Zaplacono);
                        TextView Naprawiono = mViewRepair.findViewById(R.id.Naprawiono);
                        final EditText zaplaconoV = mViewRepair.findViewById(R.id.ZaplaconoValue);
                        zaplaconoV.setFilters(new InputFilter[]{new DecimalDigitsInputFilter
                                (4, 2)});
                        final EditText naprawionoV = mViewRepair.findViewById(R.id.naprawionoVALUE);
                        okbtn = mViewRepair.findViewById(R.id.tankowanie_ok_btn);
                        cancelbtn = mViewRepair.findViewById(R.id.tankowanie_cancel_btn);

                        popupBuilderRepair.setView(mViewRepair);
                        final AlertDialog dialogRepair = popupBuilderRepair.create();
                        dialogRepair.show();

                        okbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!zaplaconoV.getText().toString().isEmpty() && !naprawionoV.getText()
                                        .toString
                                                ().isEmpty()) {
                                    Fragment naprawa = new naprawaFragment();
                                    Bundle data = new Bundle();
                                    String[] list = new String[2];
                                    list[0] = zaplaconoV.getText().toString();
                                    list[1] = naprawionoV.getText().toString();
                                    data.putStringArray("data",list);
                                    naprawa.setArguments(data);
                                    dialogRepair.dismiss();
                                    MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id
                                            .fragmentContainer, naprawa)
                                            .commit();; //ODŚWIEŻ
                                    Snackbar mSnack = Snackbar.make(findViewById(R.id.drawerLayout),
                                            "Dodano do bazy! :)", Snackbar
                                                    .LENGTH_LONG);
                                    mSnack.show();

                                } else {
                                    Toast mToast = Toast.makeText(MainActivity.this, "Proszę wypełnij " +
                                            "wszystkie pola!", Toast.LENGTH_SHORT);
                                    mToast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL,
                                            0, 0);
                                    mToast.show();

                                }
                            }
                        });

                        cancelbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogRepair.dismiss();
                                Snackbar mSnack = Snackbar.make(findViewById(R.id.drawerLayout),
                                        "Anulowano!", Snackbar.LENGTH_LONG);
                                mSnack.show();
                            }
                        });
                        break;
                    //Default
                    default:
                        Toast.makeText(MainActivity.this, "Coś", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //Na potrzeby fragmentów:
        Bundle data = new Bundle();
        String[] list = new String[4];
        data.putStringArray("data",list);
        //
        switch (item.getItemId()) {
            case R.id.nav_all_expenses:
                Fragment allExpenses = new AllExpenses();
                allExpenses.setArguments(data);
                MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id
                        .fragmentContainer, allExpenses)
                        .commit();
                break;

            case R.id.nav_fuel_expenses:
                Fragment tankowania = new tankowanieFragment();
                tankowania.setArguments(data);
                MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id
                        .fragmentContainer, tankowania)
                        .commit();
                break;

            case R.id.nav_repair_expenses:
                Fragment naprawa = new naprawaFragment();
                naprawa.setArguments(data);
                MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id
                        .fragmentContainer, naprawa)
                        .commit();
                break;
            case R.id.nav_notifications:
                Fragment przypomnienie = new przypomnienieFragment();
                przypomnienie.setArguments(data);
                MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id
                        .fragmentContainer, przypomnienie)
                        .commit();
        }
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


//private class GetData extends AsyncTask<Void, Void, List<Data>> {
//
//    @Override
//    protected List<Data> doInBackground(Void... params) {
//        AppDatabase db = Room.databaseBuilder(MainActivity.this, AppDatabase.class, "database").build();
//
//        Data data = new Data(0, "36.6L", "166.67ZŁ","476.4KM","7.8L/100KM");
//
//        db.DataDao().insertAll(data);
//        List<Data> data1 = db.DataDao().getAll();
//        return data1;
//    }
//
//    @Override
//    protected void onPostExecute(List<Data> data) {
//        super.onPostExecute(data);
//        loadRecyclerView(data);
//    }
//}
//
//    private void loadRecyclerView(List<Data> data) {
//        // setup RecyclerView
//        mRecyclerView = (RecyclerView) findViewById(R.id.card_recycler_main);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mAdapter = new RVadapter(data);
//        mRecyclerView.setAdapter(mAdapter);
//    }
}