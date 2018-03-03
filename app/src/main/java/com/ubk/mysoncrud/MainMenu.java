package com.ubk.mysoncrud;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mindorks.placeholderview.PlaceHolderView;
import com.ubk.mysoncrud.classes.adapter.MainMenuAdapter;
import com.ubk.mysoncrud.classes.drawer.DrawerHeader;
import com.ubk.mysoncrud.classes.drawer.DrawerMenuItem;
import com.ubk.mysoncrud.classes.helper.RecyclerTouchListener;
import com.ubk.mysoncrud.classes.helper.SessionManager;
import com.ubk.mysoncrud.classes.list.MainMenuList;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

public class MainMenu extends AppCompatActivity {

    private PlaceHolderView placeHolderView;
    private DrawerLayout drawer;
    private RecyclerView recyclerView;
    private List<MainMenuList> mainMenuList = new ArrayList<>();
    private Toolbar toolbar;
    boolean doubleBackToExitPressedOnce = false;
    SessionManager session;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_menu);

        session = new SessionManager(this);
        session.checkLogin();

        drawer = findViewById(R.id.drawer_layout);
        placeHolderView = findViewById(R.id.drawer_view);
        toolbar = findViewById(R.id.toolbar);
        Button btnLogOut = findViewById(R.id.btn_logout);
        recyclerView = findViewById(R.id.main_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new SlideInLeftAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        //recyclerView.setHasFixedSize(true);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.logoutUser();
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                switch (position){
                    case 0:
                        Intent groupmember = new Intent(MainMenu.this, GroupMember.class);
                        startActivity(groupmember);
                        break;

                    /*case 1:
                        Intent cctv = new Intent(MainMenu.this, Cctv.class);
                        startActivity(cctv);
                        break;

                    case 2:
                        Intent registration = new Intent(MainMenu.this, Registration.class);
                        startActivity(registration);
                        break;

                    case 3:
                        Intent email = new Intent(MainMenu.this, SendEmail.class);
                        startActivity(email);
                        break;

                    case 4:
                        Intent guestmedia = new Intent(MainMenu.this, GuestMedia.class);
                        startActivity(guestmedia);
                        break;


                    case 6:
                        Intent schedule = new Intent(MainMenu.this, Schedule.class);
                        startActivity(schedule);
                        break;

*/
                    case 5:
                        Intent teacher = new Intent(MainMenu.this, Teacher.class);
                        startActivity(teacher);
                        break;

/*
                    case 10:
                        Intent classes = new Intent(MainMenu.this, Classes.class);
                        startActivity(classes);
                        break;

                    case 11:
                        Intent groups = new Intent(MainMenu.this, Groups.class);
                        startActivity(groups);
                        break;

                    case 7:
                        Intent paymenttype = new Intent(MainMenu.this, PaymentType.class);
                        startActivity(paymenttype);
                        break;

                    case 15:
                        Intent photo = new Intent(MainMenu.this, Photo.class);
                        startActivity(photo);
                        break;

                    case 16:
                        Intent plp = new Intent(MainMenu.this, Plp.class);
                        startActivity(plp);
                        break;

                    case 8:
                        Intent scoretype = new Intent(MainMenu.this, ScoreType.class);
                        startActivity(scoretype);
                        break;

                    case 14:
                        Intent sickness = new Intent(MainMenu.this, Sickness.class);
                        startActivity(sickness);
                        break;

                    case 9:
                        Intent tahfizhstep = new Intent(MainMenu.this, TahfizhStep.class);
                        startActivity(tahfizhstep);
                        break;

                    case 12:
                        Intent subject = new Intent(MainMenu.this, Subject.class);
                        startActivity(subject);
                        break;
*/
                    case 13:
                        Intent violation = new Intent(MainMenu.this, Violation.class);
                        startActivity(violation);
                        break;
/*
                    case 17:
                        Intent reportcard = new Intent(MainMenu.this, ReportCard.class);
                        startActivity(reportcard);
                        break;

                    case 18:
                        Intent santri = new Intent(MainMenu.this, Santri.class);
                        startActivity(santri);
                        break;

                    case 19:
                        Intent smartcard = new Intent(MainMenu.this, SmartCard.class);
                        startActivity(smartcard);
                        break;

                    case 20:
                        Intent action = new Intent(MainMenu.this, Action.class);
                        startActivity(action);
                        break;

                    case 21:
                        Intent plpvideo = new Intent(MainMenu.this, PlpVideo.class);
                        startActivity(plpvideo);
                        break;

                    case 22:
                        Intent parents = new Intent(MainMenu.this, Parents.class);
                        startActivity(parents);
                        break;*/
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        viewList();

        setupDrawer();

    }

    private void viewList() {
        MainMenuList main = new MainMenuList("Anggota Kelompok",R.drawable.ic_key);
        mainMenuList.add(main);

        main = new MainMenuList("CCTV", R.drawable.ic_user);
        mainMenuList.add(main);

        main = new MainMenuList("Pendaftaran", R.drawable.ic_user);
        mainMenuList.add(main);

        main = new MainMenuList("Kirim Email", R.drawable.ic_user);
        mainMenuList.add(main);

        main = new MainMenuList("Guest Media", R.drawable.ic_user);
        mainMenuList.add(main);

        main = new MainMenuList("Guru", R.drawable.ic_user);
        mainMenuList.add(main);

        main = new MainMenuList("Jadwal Pelajaran", R.drawable.ic_user);
        mainMenuList.add(main);

        main = new MainMenuList("Jenis Pembayaran", R.drawable.ic_user);
        mainMenuList.add(main);

        main = new MainMenuList("Jenis Nilai", R.drawable.ic_user);
        mainMenuList.add(main);

        main = new MainMenuList("Tahapan Tahfizh", R.drawable.ic_user);
        mainMenuList.add(main);

        main = new MainMenuList("Kelas", R.drawable.ic_user);
        mainMenuList.add(main);

        main = new MainMenuList("Kelompok", R.drawable.ic_user);
        mainMenuList.add(main);

        main = new MainMenuList("Mata Pelajaran", R.drawable.ic_user);
        mainMenuList.add(main);

        main = new MainMenuList("Pelanggaran", R.drawable.ic_user);
        mainMenuList.add(main);

        main = new MainMenuList("Penyakit", R.drawable.ic_user);
        mainMenuList.add(main);

        main = new MainMenuList("Photo", R.drawable.ic_user);
        mainMenuList.add(main);

        main = new MainMenuList("PLP", R.drawable.ic_user);
        mainMenuList.add(main);

        main = new MainMenuList("Rapor", R.drawable.ic_user);
        mainMenuList.add(main);

        main = new MainMenuList("Santri", R.drawable.ic_user);
        mainMenuList.add(main);

        main = new MainMenuList("Smart Card", R.drawable.ic_user);
        mainMenuList.add(main);

        main = new MainMenuList("Tindakan", R.drawable.ic_user);
        mainMenuList.add(main);

        main = new MainMenuList("Video PLP", R.drawable.ic_user);
        mainMenuList.add(main);

        main = new MainMenuList("Wali", R.drawable.ic_user);
        mainMenuList.add(main);
        RecyclerView.Adapter adapter = new MainMenuAdapter(mainMenuList, this);

        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }

    private void setupDrawer(){

        // Read your drawable from somewhere
        //Drawable dr = getResources().getDrawable(R.drawable.logo);
        //Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        //Drawable resizelogo = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 70, 70, true));

        placeHolderView
                .addView(new DrawerHeader(this.getApplicationContext()))
                .addView(new DrawerMenuItem(this.getApplicationContext(), 1))
                .addView(new DrawerMenuItem(this.getApplicationContext(), 2));
        //  .addView(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.BAR_LOGOUT));

        ActionBarDrawerToggle  drawerToggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }
}
