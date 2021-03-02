package br.com.CarNote.activity.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.CarNote.R;
import br.com.CarNote.activity.adapter.TutorialAdapter;
import br.com.CarNote.activity.model.ModelObject;

public class TutorialActivity extends AppCompatActivity {
    private LinearLayout dots;
    private Button proximo;
    private Button pular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        dots = findViewById(R.id.dots);
        ViewPager viewPager = findViewById(R.id.viewPagerTutorial);
        proximo = findViewById(R.id.proximo);
        pular = findViewById(R.id.pular);
        addDots(ModelObject.values().length,0);

        viewPager.setAdapter(new TutorialAdapter(this));

        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                page.setTranslationX(page.getWidth() * -position);

                if(position <= -1.0F || position >= 1.0F) {
                    page.setAlpha(0.0F);
                } else if( position == 0.0F ) {
                    page.setAlpha(1.0F);
                } else {

                    page.setAlpha(1.0F - Math.abs(position));
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addDots(ModelObject.values().length, position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager.getCurrentItem() == ModelObject.values().length - 1){
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
            }
        });

        pular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void addDots(int size, int position){
        dots.removeAllViews();
        for(int i = 0; i< size; i++){
            TextView textView = new TextView(getApplicationContext());
            textView.setText(R.string.dotted);
            textView.setTextSize(35f);
            if(position == i){
                textView.setTextColor(getResources().getColor(R.color.dark_gray));
            }else{
                textView.setTextColor(getResources().getColor(R.color.white));
            }

            dots.addView(textView);
        }

    }
}