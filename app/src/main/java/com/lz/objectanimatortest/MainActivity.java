package com.lz.objectanimatortest;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button addImg;
    private Button removeImg;
    private LinearLayout llImgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addImg = findViewById(R.id.btn_add_image);
        removeImg = findViewById(R.id.btn_remove_image);
        llImgs = findViewById(R.id.ll_image);

        addImg.setOnClickListener(this);
        removeImg.setOnClickListener(this);


        LayoutTransition transition = new LayoutTransition();
        //设置当显示子view时，所有子view的执行动画时间间隙为30毫秒
        transition.setStagger(LayoutTransition.CHANGE_APPEARING, 0);
        //设置当显示子view时，所有子view的执行动画的周期为get到的时间
        transition.setDuration(LayoutTransition.CHANGE_APPEARING,10000);
        //设置当显示子view时，所有子view的延迟执行动画的时间为0.即不延迟执行
        transition.setStartDelay(LayoutTransition.CHANGE_APPEARING, 0);



        //创建ObjectAnimator，设置缩放和透明
        @SuppressLint("ObjectAnimatorBinding") ObjectAnimator appearingAnimator = ObjectAnimator.ofPropertyValuesHolder((Object) null,
                PropertyValuesHolder.ofFloat("scaleX", 0.0f, 1.0f),
                PropertyValuesHolder.ofFloat("alpha", 0, 1.0f));

        //设置当显示子view时，该子view的动画
        transition.setAnimator(LayoutTransition.APPEARING, appearingAnimator);
        //设置当显示子view时，该子view执行动画的周期
        transition.setDuration(LayoutTransition.APPEARING, 2000);
        //设置当显示子view时，该子view执行动画的延迟
        transition.setStartDelay(LayoutTransition.APPEARING, 2000);


        //同上
        @SuppressLint("ObjectAnimatorBinding") ObjectAnimator disappearingAnimator = ObjectAnimator.ofPropertyValuesHolder((Object) null,
                PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.0f),
                PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.0f),
                PropertyValuesHolder.ofFloat("alpha", 1.0f, 0));
        //设置当移除子view时，该子view的动画
        transition.setAnimator(LayoutTransition.DISAPPEARING, disappearingAnimator);
        //设置当移除子view时，该子view执行动画的周期
        transition.setDuration(LayoutTransition.DISAPPEARING, transition.getDuration(LayoutTransition.DISAPPEARING));
        //设置当移除子view时，该子view执行动画的延迟
        transition.setStartDelay(LayoutTransition.DISAPPEARING, 0);


        //设置当移除子view时，所有子view的执行动画时间间隙为30毫秒
        transition.setStagger(LayoutTransition.CHANGE_DISAPPEARING, 30);
        //设置当移除子view时，所有子view的执行动画的周期为get到的时间
        transition.setDuration(LayoutTransition.CHANGE_DISAPPEARING, transition.getDuration(LayoutTransition.CHANGE_DISAPPEARING));
        //设置当移除子view时，所有子view的延迟执行动画的时间为get到的时间
        transition.setStartDelay(LayoutTransition.CHANGE_DISAPPEARING, transition.getDuration(LayoutTransition.DISAPPEARING));

        //为ViewGroup设置过渡动画
        llImgs.setLayoutTransition(transition);

    }
    private int index=0;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_image: {
                TextView tv = new TextView(this);
                tv.setText("哈哈哈哈哈----"+index);
                index++;
//                ImageView imageView = new ImageView(this);
//                imageView.setImageResource(R.mipmap.ic_launcher);
//                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200, 200);
                llImgs.addView(tv, 0, layoutParams);
            }
            break;
            case R.id.btn_remove_image: {
                int count = llImgs.getChildCount();
                if (count > 0) {
                    llImgs.removeViewAt(0);
                }
            }
            break;
        }
    }
}
