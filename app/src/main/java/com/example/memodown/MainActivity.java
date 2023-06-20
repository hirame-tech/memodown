package com.example.memodown;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
//import android.view.Menu;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.titlebar);
        // set Toolbar on ActionBar
        setSupportActionBar(toolbar);
        // ツールバーに戻るボタンを設置
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(v, "ボタン１が押されました", Snackbar.LENGTH_SHORT).show();
                Toast myToast = Toast.makeText(
                        getApplicationContext(),
                        "saved",
                        Toast.LENGTH_SHORT
                );
                myToast.show();
            }
        });
    }

    //return button
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

}