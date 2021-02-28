package br.com.CarNote.activity.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import br.com.CarNote.R;
import br.com.CarNote.activity.adapter.TutorialAdapter;

public class TutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        ViewPager viewPager = findViewById(R.id.viewPagerTutorial);

        viewPager.setAdapter(new TutorialAdapter(this));
    }
}