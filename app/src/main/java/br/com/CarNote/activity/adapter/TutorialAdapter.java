package br.com.CarNote.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import br.com.CarNote.activity.model.ModelObject;


public class TutorialAdapter extends PagerAdapter {
    private Context mContext;


    public TutorialAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ModelObject modelObject = ModelObject.values()[position];
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        ViewGroup viewGroup = (ViewGroup) layoutInflater.inflate(modelObject.getmLayoutResId(),
                container, false);
        container.addView(viewGroup);
        return viewGroup;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return ModelObject.values().length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    public CharSequence getPageTitle(int position){
        ModelObject customModel = ModelObject.values()[position];
        return mContext.getString(customModel.getmTitleResId());
    }

}
