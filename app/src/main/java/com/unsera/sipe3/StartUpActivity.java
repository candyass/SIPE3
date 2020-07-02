package com.unsera.sipe3;


import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;
import com.heinrichreimersoftware.materialintro.slide.Slide;
import com.unsera.sipe3.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class StartUpActivity extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SimpleSlide.Builder builder1 =  new SimpleSlide.Builder()
                .title(R.string.title_slide_1)
                .description(R.string.description_slide_1)
                .image(R.drawable.gambar_20)
                .background(R.color.colorPrimaryDark)
                .backgroundDark(R.color.colorPrimary)
                .scrollable(true);
        SimpleSlide.Builder builder2 =  new SimpleSlide.Builder()
                .title(R.string.title_slide_2)
                .description(R.string.description_slide_2)
                .image(R.drawable.gambar_10)
                .background(R.color.colorPrimaryDark)
                .backgroundDark(R.color.colorPrimary)
                .scrollable(false)
                .buttonCtaLabel(R.string.title_mulai)
                .buttonCtaClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        Intent main = LoginActivity.newIntent(getBaseContext());
                        startActivity(main);
                        finish();
                    }
                });
        List<SimpleSlide> listSlide = new ArrayList<>();
        listSlide.add(builder1.build());
        listSlide.add(builder2.build());
        addSlides(listSlide);
        setButtonBackVisible(false);
        setButtonNextVisible(false);
    }
}
