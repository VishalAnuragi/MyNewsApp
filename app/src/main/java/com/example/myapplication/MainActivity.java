package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.parameter.Articles;
import com.example.myapplication.parameter.Headlines;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter adapter;
    final String API_KEY = "e82c2c2a1b58416cb98c21b33af90cb8";
    Button button;
    List<Articles> articles = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        recyclerView = (RecyclerView) findViewById( R.id.recyclerView );
        button = findViewById( R.id.refreshButton );
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        final String country = getCountry();


        retrieveJson( country, API_KEY );
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieveJson( country, API_KEY );
            }
        } );
    }

    public void retrieveJson(String country, String apiKey) {
        Call<Headlines> call = Client.getInstance().getApi().getHeadlines( country, apiKey );
        call.enqueue( new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if (response.isSuccessful() && response.body().getArticles() != null) {
                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new Adapter( MainActivity.this, articles );
                    recyclerView.setAdapter( adapter );
                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {

                Toast.makeText( MainActivity.this, "There is An Error", Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    public String getCountry() {
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }
}