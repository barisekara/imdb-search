package com.example.tazemeta.izlemelik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Random;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView filmAra,favs,unlike,random,list;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.imdb);
        filmAra =findViewById(R.id.filmAra);
        favs =findViewById(R.id.favoriler);
        unlike =findViewById(R.id.begenmediklerim);
        random =findViewById(R.id.rastgele);
        list =findViewById(R.id.liste);
        filmAra.setOnClickListener(this);
        favs.setOnClickListener(this);
        unlike.setOnClickListener(this);
        random.setOnClickListener(this);
        list.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.filmAra : i = new Intent(this,com.example.tazemeta.izlemelik.filmAraActivity.class); startActivity(i);break;
            case R.id.favoriler : i = new Intent(this,com.example.tazemeta.izlemelik.favoriler.class);startActivity(i);break;
            case R.id.begenmediklerim : i = new Intent(this,com.example.tazemeta.izlemelik.begenmediklerim.class);startActivity(i);break;
            case R.id.rastgele :
                int max=9999999;
                int min = 1000000;
                Random random = new Random();
                int number =random.nextInt(max-min)+min;
                String id= "tt"+ String.valueOf(number);
                i= new Intent(getApplicationContext(),filmActivity.class);
                i.putExtra("id",id);
                startActivity(i);
                break;
            case R.id.liste : i = new Intent(this,com.example.tazemeta.izlemelik.liste.class);startActivity(i);break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        switch(id){
            case R.id.out:
                mAuth.signOut();
                Toast.makeText(this, "Çıkış yapıldı.", Toast.LENGTH_SHORT).show();
                Intent i;
                i= new Intent(getApplicationContext(),loginActivity.class);
                startActivity(i);
                finish();

        }
        return true;
    }
}
