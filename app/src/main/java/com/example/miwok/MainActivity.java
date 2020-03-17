package com.example.miwok;

import android.os.Bundle;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // RECUPERATION DE LA VIEW PAGER, CELLE QUI CONTIENT LES FRAGMENTS
        ViewPager viewPager=(ViewPager)findViewById(R.id.viewpager);

        //RECUPERATION DE LA TAB LAYOUT, CELLE QUI CONTIENT LES STRINGS A INDIQUER
        // A L'UTILISATEUR
        TabLayout table=(TabLayout)findViewById(R.id.tabs);

        // APPEL DE LA FRAGMENT ADAPTEUR POUR ETRE EN ACTION EN CREANT UN OBJET DE LA CLASSE
        FragmentAdapter adapter=new FragmentAdapter(MainActivity.this,getSupportFragmentManager());

        //ON MONTE L'ADAPTER SUR LA VIEW PAGER
        viewPager.setAdapter(adapter);


      //ON LES MET ENSEMBLE LA TAB LAYOUT ET LA VIEW PAGER
        table.setupWithViewPager(viewPager);
    }
    }
