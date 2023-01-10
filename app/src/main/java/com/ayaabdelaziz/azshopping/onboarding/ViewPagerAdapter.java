package com.ayaabdelaziz.azshopping.onboarding;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.ayaabdelaziz.azshopping.R;

public class ViewPagerAdapter extends PagerAdapter {

    Context context;
    int images[] = {

            R.drawable.a,
            R.drawable.bjpg,
            R.drawable.cjpg,

    };

    int headings[] = {

            R.string.on_boarding1_txt1,
            R.string.on_boarding2_txt1,
            R.string.on_boarding3_txt1,
    };

    int description[] = {

            R.string.on_boarding_txt2,
            R.string.on_boarding_txt2,
            R.string.on_boarding_txt2,
    };

    public ViewPagerAdapter(Context context) {

        this.context = context;

    }

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout, container, false);

        ImageView slidetitleimage = (ImageView) view.findViewById(R.id.image_slider);
        TextView slideHeading = (TextView) view.findViewById(R.id.head);
        TextView slideDesciption = (TextView) view.findViewById(R.id.desc);

        slidetitleimage.setImageResource(images[position]);
        slideHeading.setText(headings[position]);
        slideDesciption.setText(description[position]);

        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((LinearLayout) object);

    }
}

