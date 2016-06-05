package com.android.adapterpattern;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.adapterpattern.objectadapter.TurnPlateViewUtil;
import com.android.adapterpattern.objectadapter.TurnplateView;

public class MainActivity extends AppCompatActivity {
    int tag = 1;
    SoundPool soundPool = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final RelativeLayout f = new RelativeLayout(this);
        final String tag1 = "我是第";
        final String tag2 = "个item";

        final TurnplateView turnplateView = new TurnplateView(this);
        try {
            turnplateView.setInsideBG(R.mipmap.inside_bg);
            turnplateView.setOutSideBG(R.mipmap.outside_bg);
            turnplateView.setMaxSpeed(100);
            turnplateView.setAcceleratedSpeed(3);
            turnplateView.setDisFromCentre(TurnPlateViewUtil.dip2px(30.0, this));
            turnplateView.setOnItemClickListener(new TurnplateView.TurnplateClickListener() {

                @Override
                public void onClick(String tag) {
                    Toast.makeText(getBaseContext(), tag1+tag+tag2, Toast.LENGTH_SHORT).show();
                }
            });

            soundPool = new SoundPool(1, AudioManager.STREAM_NOTIFICATION, 0);
            AssetFileDescriptor afd = getAssets().openFd("notification.mp3");
            soundPool.load(afd, 1);
            AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            final float volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)/audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            turnplateView.registerSoundPlayer(new TurnplateView.PlaySound() {

                @Override
                public void onPlaySound() {
                    soundPool.play(1, volume, volume, 1, 0, 1);
                }
            });
        } catch (Exception e1) {
            Log.e("zhao", e1.getMessage());

        }

        Button btn = new Button(this);
        btn.setText("点击显示");
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, TurnPlateViewUtil.dip2px(40.0, this));
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                try {
                    turnplateView.setAdapter(new TurnPlateViewAdapter());
                } catch (TurnplateView.NumberOverFlowException e) {
                    e.printStackTrace();
                }

            }
        });
        f.addView(btn, layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT | RelativeLayout.CENTER_IN_PARENT);
//		layoutParams.rightMargin = -500;//需要同时设置，要不然控件的默认宽度会很大
//		layoutParams.leftMargin = 500;
        f.addView(turnplateView, layoutParams);

        setContentView(f);
    }

    private class TurnPlateViewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
            drawable.setBounds(0, 0,drawable.getMinimumHeight() , drawable.getMinimumHeight());
            TextView textview = new TextView(MainActivity.this);
            textview.setTextColor(getResources().getColor(android.R.color.white));
            textview.setText(R.string.text);
            textview.setCompoundDrawables(null, drawable, null, null);
            textview.setTag(tag++ +"");
            return textview;
        }
    }

    @Override
    protected void onDestroy() {
        soundPool.release();//一定要记得release，要不然下次打开就不会有声音
        super.onDestroy();
    }
}
