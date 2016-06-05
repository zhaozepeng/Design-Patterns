package com.android.adapterpattern.objectadapter;


import android.content.Context;

import java.util.ArrayList;

public class TurnPlateViewUtil {
	
	/**
	 * 算出每个view的角度
	 * @param location
	 */
	public static void getLocationByNum(ArrayList<TurnplateView.FloatWithFlag> location){
		int perAngle = 360 / location.size();
		for(int i=0; i < location.size(); i++){
			location.get(i).setNumber(perAngle * i);
			location.get(i).setFlag(false);
		}
	}
	
	public static int dip2px(Double dp, Context context){
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dp * scale + 0.5f);
	}
}
