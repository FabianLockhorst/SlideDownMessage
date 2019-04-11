package com.fabianlockhorst.slidedownmessage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fabianlockhorst.sdm.SlideDownMessage;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private SlideDownMessage slideDownMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button shortButton = findViewById(R.id.short_button);
        shortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SlideDownMessage.make(findViewById(R.id.root), "Short message", MainActivity.this, SlideDownMessage.LENGTH_SHORT).show();
            }
        });

        Button longButton = findViewById(R.id.long_button);
        longButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SlideDownMessage.make(findViewById(R.id.root), "long message", MainActivity.this, SlideDownMessage.LENGTH_LONG).show();
            }
        });

        slideDownMessage = SlideDownMessage.make(findViewById(R.id.root), "indefinite message", MainActivity.this, SlideDownMessage.LENGTH_INDEFINITELY);

        Button showButton = findViewById(R.id.show_button);
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideDownMessage.show();
            }
        });

        Button hideButton = findViewById(R.id.hide_button);
        hideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideDownMessage.hide();
            }
        });

        Button blueButton = findViewById(R.id.short_blue);
        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SlideDownMessage message = SlideDownMessage.make(findViewById(R.id.root), "i'm blue and short", MainActivity.this, SlideDownMessage.LENGTH_SHORT);
                message.getView().setBackgroundResource(R.color.colorPrimary);
                message.show();
            }
        });
    }
}
