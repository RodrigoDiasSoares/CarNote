package br.com.CarNote.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

import br.com.CarNote.R;
import br.com.CarNote.activity.model.MyModel;

public class PageAdapter extends FragmentStatePagerAdapter{
    private Context context;
    private ArrayList<MyModel> modelArrayList;



    public PageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public PageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }


    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item, container, false);
        ImageView bannerIv = view.findViewById(R.id.bannerIv);




        MyModel model = modelArrayList.get(position);



        int image = model.getImage();

        bannerIv.setImageResource(image);




        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Tutorial", Toast.LENGTH_SHORT).show();
            }
        });

        container.addView(view,position);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
