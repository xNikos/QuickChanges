package com.rinworks.nikos.fuelfullpaliwoikoszty;
//TODO: Powiadomienia | Fragment "O autorze" | Optymalizacja? | Smaczki?
//TODO: nie strzelić sobie w łeb...
import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.text.InputFilter;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.rinworks.nikos.fuelfullpaliwoikoszty.Database.AppDatabase;
import com.rinworks.nikos.fuelfullpaliwoikoszty.Database.Data;
import com.rinworks.nikos.fuelfullpaliwoikoszty.Database.SharedPreferences;
import com.rinworks.nikos.fuelfullpaliwoikoszty.Fragments.AllExpenses;
import com.rinworks.nikos.fuelfullpaliwoikoszty.Fragments.naprawaFragment;
import com.rinworks.nikos.fuelfullpaliwoikoszty.Fragments.przypomnienieFragment;
import com.rinworks.nikos.fuelfullpaliwoikoszty.Fragments.tankowanieFragment;
import com.rinworks.nikos.fuelfullpaliwoikoszty.Recycler.RVadapter;

import java.io.IOException;
import java.net.URI;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    SharedPreferences savedData = new SharedPreferences(this);
    SwitchCompat switchCompat;
    int updateLicznik; //update drawer licznik
    Button selectImg;
    ImageView imgSelected;
    private int REQUEST_CODE=1;
    private DatePickerDialog.OnDateSetListener DataListener;
    int mhour;
    int mminute;
    int mday;
    int mmonth;
    int myear;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //View Shared&Room
        Stetho.initializeWithDefaults(this);

        //Initialize DB
        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "database").allowMainThreadQueries().build();
        updateLicznik = db.DataDao().selectType(0).size();

        //Chceck theme status
        if (SharedPreferences.getBool("Theme")) {
            setTheme(R.style.AppThemeDark);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //TEST SECTION | START
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);


        if (SharedPreferences.getBool("Theme"))
        {
            //Dark Theme
            DatePickerDialog dataDialog = new DatePickerDialog(
                    MainActivity.this, R.style.DialogDark , new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, final int year, final int month, final int dayOfMonth) {

                    TimePickerDialog timeDialog = new TimePickerDialog(MainActivity.this, R.style
                            .DialogTDark, new
                            TimePickerDialog
                            .OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                            mhour = hourOfDay;
                            mminute = minute;
                            mday = dayOfMonth;
                            mmonth = month +1;
                            myear = year;


                            Snackbar.make(findViewById(R.id.drawerLayout),+mday+"" +
                                    "."+mmonth+"" +
                                            "."+myear+" || "+mhour+":"+mminute, Snackbar
                                    .LENGTH_LONG).show();
                        }
                    }, hour,minute,true);
                    timeDialog.show();

                }
            },year, month,day);
            dataDialog.show();

        }
        else
        {
            //Light Theme
            DatePickerDialog dataDialog = new DatePickerDialog(
                    MainActivity.this, R.style.DialogLight , new DatePickerDialog.OnDateSetListener
                    () {
                @Override
                public void onDateSet(DatePicker view, final int year, final int month, final int dayOfMonth) {

                    TimePickerDialog timeDialog = new TimePickerDialog(MainActivity.this, R.style
                            .DialogTLight, new
                            TimePickerDialog
                                    .OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                    mhour = hourOfDay;
                                    mminute = minute;
                                    mday = dayOfMonth;
                                    mmonth = month +1;
                                    myear = year;


                                    Snackbar.make(findViewById(R.id.drawerLayout),+mday+"" +
                                            "."+mmonth+"" +
                                            "."+myear+" || "+mhour+":"+mminute, Snackbar
                                            .LENGTH_LONG).show();
                                }
                            }, hour,minute,true);
                    timeDialog.show();

                }
            },year, month,day);
            dataDialog.show();
        }

//        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        alarmManager.setExact(AlarmManager.RTC_WAKEUP,);

        //TEST SECTION | END

        //Check if car added
        //starting popup
        //Dialog create
        if (!SharedPreferences.getBool("CarAdded?")) {
            AlertDialog.Builder popupBuilder = new AlertDialog.Builder(MainActivity.this);

            //Łapanie za odpowiedni Layout
            View mView = getLayoutInflater().inflate(R.layout.popup_add_car, null);
            final EditText markaV = mView.findViewById(R.id.markaValue);
            final EditText modelV = mView.findViewById(R.id.modelValue);
            final EditText rocznikV = mView.findViewById(R.id.rocznikValue);
            final EditText przebiegV = mView.findViewById(R.id.przebiegValue);
            rocznikV.setFilters(new InputFilter[]{new DecimalDigitsInputFilter
                    (4, 0)});
            Button okbtn = mView.findViewById(R.id.tankowanie_ok_btn);

            popupBuilder.setView(mView);
            final AlertDialog dialog = popupBuilder.create();
            dialog.show();
            dialog.setCancelable(false);

            //dialog buttons on click
            okbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!markaV.getText().toString().isEmpty() && !modelV.getText().toString
                            ().isEmpty() && !rocznikV.getText().toString().isEmpty() &&
                            !przebiegV.getText().toString().isEmpty())
                    {
                        //FUN
                        if(rocznikV.getText().toString().length()<4)
                        {
                            Toast mToast = Toast.makeText(MainActivity.this, "Aż taki z niego " +
                                    "staruszek?! :D", Toast
                                    .LENGTH_SHORT);
                            mToast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL,
                                    0, 0);
                            mToast.show();
                        }
                        else {
                            dialog.dismiss();
                            Snackbar mSnack = Snackbar.make(findViewById(R.id.fragmentContainer),
                                    "Dodano do bazy! :)", Snackbar
                                            .LENGTH_LONG);
                            SharedPreferences.setBool("CarAdded?",true);
                            mSnack.show();}

                        SharedPreferences.setStr("Marka",markaV.getText().toString());
                        SharedPreferences.setStr("Model",modelV.getText().toString());
                        SharedPreferences.setStr("Rocznik",rocznikV.getText().toString());
                        SharedPreferences.setFloat("Przebieg",Float.valueOf(przebiegV.getText().toString()));


                    } else {
                        Toast mToast = Toast.makeText(MainActivity.this, "Proszę wypełnij " +
                                "wszystkie pola!", Toast.LENGTH_SHORT);
                        mToast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL,
                                0, 0);
                        mToast.show();

                    }
                }
            });}

        //Default View
        Fragment allExpenses = new AllExpenses();
        MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, allExpenses).commit();

        //Navbar reference
        NavigationView navigationView = findViewById(R.id.NavigationView);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_all_expenses);

        //Drawer reference
        DrawerLayout drawer = findViewById(R.id.drawerLayout);

        //Adding hamburger icon to drawer
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open_navigation_drawer, R.string.close_navigation_drawer);
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                updateValues();
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //***CAR IMAGE SELECT | START
        //*Add Image
        //Łapanie za Navbar
        NavigationView nav = findViewById(R.id.NavigationView);
        View navBar = nav.getHeaderView(0);
        selectImg = navBar.findViewById(R.id.nav_header_add_photo);
        imgSelected = navBar.findViewById(R.id.addedImage);

        //Wczytaj zdjęcie jeśli było wybrane
        if(SharedPreferences.getStr("AddedIMG")!="Empty")
        {
            Uri uri = Uri.parse(SharedPreferences.getStr("AddedIMG"));
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                imgSelected.setImageBitmap(bitmap);
                imgSelected.setVisibility(View.VISIBLE);
                selectImg.setBackgroundColor(Color.TRANSPARENT);
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }

        selectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            }
        });

        //***CAR IMAGE SELECT| END

        //**THEME SWITCHER** | START
        //Menu reference
        Menu menu = navigationView.getMenu();
        //Menu item reference
        MenuItem item = menu.findItem(R.id.nav_theme);
        View switcherView = MenuItemCompat.getActionView(item);
        //Toogle switch theme reference
        switchCompat = switcherView.findViewById(R.id.switcher);
        //Radio buton position
        switchCompat.setChecked(SharedPreferences.getBool("Theme"));
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switchCompat.toggle();
                return false;
            }
        });
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                toogleTheme(isChecked);
            }
        });
        //**THEME SWITCHER** | END

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
                                    float [] val = new float[3];
                                    val[0] = Float.valueOf(zatankowanoV.getText().toString());
                                    val[1] = Float.valueOf(CenaLV.getText().toString());
                                    val[2] = Float.valueOf(przejechanoV.getText().toString());
                                    data.putFloatArray("data",val);
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
                                    float val = Float.valueOf(zaplaconoV.getText().toString());
                                    data.putFloat("ZapłaconoV", val);
                                    String str = naprawionoV.getText().toString();
                                    data.putString("NaprawionoV", str);
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
                break;
        }
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //Theme toogler
    private void toogleTheme(boolean darkTheme) {
        SharedPreferences.setBool("Theme", darkTheme);
        //restart activity
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    //Odświeżanie wartości na drawerze
    private void updateValues() {
        NavigationView nav = findViewById(R.id.NavigationView);
        View navBar = nav.getHeaderView(0);

        final TextView przebieg = navBar.findViewById(R.id.nav_header_przebiegVal);
        final TextView spalanie = navBar.findViewById(R.id.nav_header_spalanieVal);
        final TextView marka = navBar.findViewById(R.id.nav_header_name);
        final TextView model = navBar.findViewById(R.id.nav_header_name2);
        final TextView rocznik = navBar.findViewById(R.id.nav_header_rocznik);
        TextView spalanietext = navBar.findViewById(R.id.nav_header_hardcodedL);

        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "database").allowMainThreadQueries().build();

        float przeDb = db.DataDao().przebieg(0);
        float przeSH = SharedPreferences.getFloat("Przebieg");
        float tankDb = db.DataDao().zatankowano(db.DataDao().selectType(0).size()-1,0);
        float spaV = tankDb/przeDb*100;
        float przef = przeDb+przeSH;

        if(db.DataDao().selectType(0).size()>updateLicznik){
            updateLicznik+=1;

            przebieg.setText(String.format("%.2f",przef));
            spalanie.setText(String.format("%.2f",spaV));

            SharedPreferences.setFloat("Przebieg",przef);

        }
        else
        {
            przebieg.setText(String.format("%.2f",przeSH));
            marka.setText(SharedPreferences.getStr("Marka"));
            model.setText(SharedPreferences.getStr("Model"));
            rocznik.setText(SharedPreferences.getStr("Rocznik"));
            if(db.DataDao().selectType(0).size() > 1)
            {
                spalanie.setVisibility(View.VISIBLE);
                spalanietext.setVisibility(View.VISIBLE);
                spalanie.setText(String.format("%.2f",spaV));
            }
            else
                {
                    spalanie.setVisibility(View.GONE);
                    spalanietext.setVisibility(View.GONE);
                }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK && data!=null && data.getData()
                !=null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                imgSelected.setImageBitmap(bitmap);
                imgSelected.setVisibility(View.VISIBLE);
                selectImg.setBackgroundColor(Color.TRANSPARENT);
                SharedPreferences.setStr("AddedIMG",uri.toString());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                    startActivityForResult(Intent.createChooser(intent,"Wybierz zdjęcie swojego " +
                            "wehikułu:"),REQUEST_CODE);
                } else {
                    Snackbar.make(findViewById(R.id.drawerLayout),"Odmówiono dostępu do pamięci " +
                            "wewnętrznej!",Snackbar.LENGTH_LONG).show();
                }
                return;
            }

        }
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