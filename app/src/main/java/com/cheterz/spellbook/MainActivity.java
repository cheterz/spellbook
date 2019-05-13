package com.cheterz.spellbook;

import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spell_card_detail);
        TextView descr = findViewById(R.id.tv_text_of_description_detail);
        descr.setMovementMethod(new ScrollingMovementMethod());
        Log.d("test", "start activity");
    }
}
