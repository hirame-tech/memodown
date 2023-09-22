package com.example.memodown;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout cardLinear = (LinearLayout)this.findViewById(R.id.cardLinear);
        cardLinear.removeAllViews();


        for(int i = 0; i< 5; i++) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.content_card, null);
            CardView cardView = (CardView) linearLayout.findViewById(R.id.card_view);
            TextView textBox = (TextView) linearLayout.findViewById(R.id.info_text);
            textBox.setText("CardView" + i);
            cardView.setTag(i);
            cardView.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, String.valueOf(v.getTag()) + "番目のCardViewがクリックされました", Toast.LENGTH_SHORT).show();
                }
            });
            cardLinear.addView(linearLayout,i);
        }
    }
}