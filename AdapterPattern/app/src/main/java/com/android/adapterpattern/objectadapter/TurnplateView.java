package com.android.adapterpattern.objectadapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 转盘viewgroup,将会已转盘的形式排列所有子view,view之间间距一样，所以不要添加过多的view
 * 支持长按删除
 * 支持复位播放音效
 * @author zhao_zepeng@hotmail.com
 *
 */
public class TurnplateView extends ViewGroup{

	private final static int MAX_NUM = 6;

	private Paint paint = null;//画笔
	private int width = 0;
	private float disFromCentre = 20;
	private Bitmap insideBG = null;
	private Bitmap outsideBG = null;
	private RectF rectFBG = new RectF();
	private ArrayList<FloatWithFlag> location = new ArrayList<FloatWithFlag>();
	private float maxSpeed = 150;//转盘最大速度
	private float acceleratedSpeed = 3;//逆向加速度
	private float lastX = 0;
	private float lastY = 0;
	private MyRunnable runnable = new MyRunnable(false);
	private ArrayList<Float> speedChangeRate = new ArrayList<Float>();
	private PlaySound sound;
	private TurnplateClickListener listener = null;
//	private GestureDetector gd = new GestureDetector(new MyGestureDetectorListener());
	private View viewIsBeingLongClick;
//	private PopupMenu menu;
	private PopupWindow window;
	private VelocityTracker tracker = null;
	private Boolean isMove = false;
	private ViewConfiguration viewConfiguration= null;
	private BaseAdapter adapter;
	private OnDeleteItemCallback deleteCallback;

	public TurnplateView(Context context) {
		this(context, null);
	}
	
	public TurnplateView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		setWillNotDraw(false);
		viewConfiguration = ViewConfiguration.get(getContext());
//		initPopUpMenu();
	}

	/**
	 * 循环算出每个子view的布局位置
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
//		Log.e("zhao", "onlayout");
		if(getChildCount() > 0){
			for(int i = 0; i < getChildCount(); i++){
//				Log.e("zhao", location[i]+"location");
				int width = getChildAt(i).getMeasuredWidth();
				int height = getChildAt(i).getMeasuredHeight();
				int f_width = getMeasuredWidth();//width == height
				int child_l = (int) ((f_width - width)/2 + (f_width - width)/2 * Math.sin(location.get(i).getNumber()/180 * Math.PI)) 
						                                 - (int)(Math.sin(location.get(i).getNumber()/180 * Math.PI) * disFromCentre);
				int child_t = (int) ((f_width - height)/2 - ((f_width - height)/2 * Math.cos(location.get(i).getNumber()/180 * Math.PI)))
														 + (int)(Math.cos(location.get(i).getNumber()/180 * Math.PI) * disFromCentre);
				getChildAt(i).layout(child_l, child_t, child_l+width, child_t+height) ;
			}
		}
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//		Log.e("zhao", "onmeasure");
		int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeigth = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(measureWidth, measureWidth); //按照宽度，布局成正方形
        for(int i= 0;i<getChildCount();i++){
            View v = getChildAt(i);
            int widthSpec = 0;
            int heightSpec = 0;
            LayoutParams params = v.getLayoutParams();
            if(params.width > 0){
                widthSpec = MeasureSpec.makeMeasureSpec(params.width, MeasureSpec.EXACTLY);//已明确确定空间的长度和宽度
            }else if (params.width == -1) {
                widthSpec = MeasureSpec.makeMeasureSpec(measureWidth, MeasureSpec.EXACTLY);//matchparent
            } else if (params.width == -2) {
                widthSpec = MeasureSpec.makeMeasureSpec(measureWidth/6, MeasureSpec.AT_MOST);//wrapcontent,size参数的意义即为最大的大小，比他小就有多大显示多大，比他大就减小为size的大小
            }
             
            if(params.height > 0){
                heightSpec = MeasureSpec.makeMeasureSpec(params.height, MeasureSpec.EXACTLY);//已明确确定空间的长度和宽度 
            }else if (params.height == -1) {
                heightSpec = MeasureSpec.makeMeasureSpec(measureHeigth, MeasureSpec.EXACTLY);
            } else if (params.height == -2) {
                heightSpec = MeasureSpec.makeMeasureSpec(measureHeigth/6, MeasureSpec.AT_MOST);
            }
            v.measure(widthSpec, heightSpec);
        }
	}
	
	/**
	 * ondraw函数里面不用调用子view的draw方法
	 */
	@Override
	protected void onDraw(Canvas canvas) {
//		Log.e("zhao", "ondraw" + getChildCount());
		if(outsideBG != null)
			canvas.drawBitmap(outsideBG, null, getRectF(rectFBG), paint);
		if(insideBG != null)
			canvas.drawBitmap(insideBG, (width-insideBG.getWidth())/2, (width-insideBG.getHeight())/2, paint);
		dispatchDraw(canvas);
//		for(int i = 0;i < getChildCount() ;i ++){
//			getChildAt(i).draw(canvas);
//		}
		super.onDraw(canvas);
	}
	
	/**
	 * 设置适配器
	 * @param adapter
	 */
	public void setAdapter(BaseAdapter adapter) throws NumberOverFlowException {
		this.adapter = adapter;
		if (adapter.getCount() > MAX_NUM) {
			throw new NumberOverFlowException(adapter.getCount());
		}
		adapter.registerDataSetObserver(new DataSetObserver() {
			@Override
			public void onChanged() {
				super.onChanged();
				onDataSetChanged();
			}

			@Override
			public void onInvalidated() {
				super.onInvalidated();
				onDataSetChanged();
			}
		});
		initChild();
	}

	/**
	 * 数据源发生变更，需要重新绘制布局
	 */
	private void onDataSetChanged(){
		initChild();
	}

	public void setDeleteCallback(OnDeleteItemCallback callback) {
		this.deleteCallback = callback;
	}

	private void initChild() {
		removeAllViews();
		location.clear();

		for (int i=0; i < adapter.getCount(); i++) {
			//每次添加子view的时候都要重新计算location数组
			location.add(new FloatWithFlag());
			TurnPlateViewUtil.getLocationByNum(location);
			View view = adapter.getView(i, null, this);
			view.setTag(i);
			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					listener.onClick((String)arg0.getTag());
				}
			});
			view.setOnLongClickListener(new OnLongClickListener() {

				@Override
				public boolean onLongClick(View arg0) {
					initPopUpWindow();
					window.showAsDropDown(arg0);
					viewIsBeingLongClick = arg0;
					return false;
				}
			});
			addView(view);
		}
	}

	/**
	 * 子view个数最好不要超过6个,每次添加后，重布重画,支持设置最大数量
	 */
//	public void addChild(final View view) throws NumberOverFlowException{
//		if(childNum < adapter.getCount()){
//			//每次添加子view的时候都要重新计算location数组
//			location.add(new FloatWithFlag());
//			TurnPlateViewUtil.getLocationByNum(location);
//			view.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View arg0) {
//					listener.onClick((String)arg0.getTag());
//				}
//			});
//			view.setOnLongClickListener(new OnLongClickListener() {
//
//				@Override
//				public boolean onLongClick(View arg0) {
//					initPopUpWindow();
//					window.showAsDropDown(arg0);
//					viewIsBeingLongClick = arg0;
//					return false;
//				}
//			});
//			addView(view);
//			childNum++;
//		}else{
//			throw new NumberOverFlowException(adapter.getCount());
//		}
//	}
	
	/**
	 * 删除子view
	 * @param view
	 */
//	public void removeChild(final View view){
//		try{
//			this.removeView(view);
//			location.remove(0);
//			childNum--;
//			TurnPlateViewUtil.getLocationByNum(location);
//			requestLayout();
//		}catch(Exception e){
//
//		}
//	}
	
	/**
	 * 设置内部图片
	 * @param resId
	 */
	public void setInsideBG(int resId){
		insideBG = BitmapFactory.decodeResource(getResources(), resId);
	}
	
	/**
	 * 设置外部图片
	 * @param resId
	 */
	public void setOutSideBG(int resId){
		outsideBG = BitmapFactory.decodeResource(getResources(), resId);
	}
	
	private RectF getRectF(RectF rectf){
		width = getWidth();
		rectf.left = 0;
		rectf.top = 0;
		rectf.bottom = width;
		rectf.right = width;
		return rectf;
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		Log.e("zhao", "dispatch");
		return super.dispatchTouchEvent(ev);
	}
	
	/**
	 * 应不应该截取此touchevent，需要查看子控件是否能够处理此次事件
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if(!isMove){
			switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				lastX = ev.getRawX();
				lastY = ev.getRawY();
				this.removeCallbacks(runnable);
				speedChangeRate.clear();
				break;
			case MotionEvent.ACTION_MOVE:
				//只有当手指移动的距离大于最小距离时，才显示为移动,并且以后的move事件一直被ontouch处理
				if(Math.abs(lastY - ev.getRawY()) > viewConfiguration.getScaledTouchSlop()){
					isMove = true;
					onTouchEvent(ev);
				}
				break;
	
			default:
				break;
			}
		}
		return isMove;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.e("zhao", "ontouch");
		int area = checkArea(event.getRawX(), event.getRawY());
		getVelocityTracker();
		tracker.addMovement(event);
		switch (event.getAction()) {
		case MotionEvent.ACTION_MOVE:
			float moveY = event.getRawY() - lastY;
			float moveX = event.getRawX() - lastX;
			switch (area) {
			case 1:
				turnClockwise(moveX);
				break;
			case 2:
				turnClockwise(moveY);
				break;
			case 3:
				turnClockwise(-moveX);
				break;
			case 4:
				turnClockwise(-moveY);
				break;
			}
			lastX = event.getRawX();
			lastY = event.getRawY();
			break;
		case MotionEvent.ACTION_OUTSIDE:
		case MotionEvent.ACTION_UP:
			isMove = false;
			turnByInertia(area);
			break;
		default:
			break;
		}
		//将所有手势同时交给gd,用于捕捉用户的长按和短按
//		gd.onTouchEvent(event);
		return true;
	}

	/**
	 * 带标志的float类型，用于声音的播放
	 * @author dev
	 *
	 */
	public static class FloatWithFlag{
		private boolean flag;
		private float number;
		
		public FloatWithFlag() {
			flag = true;
			number = 0;
		}
		
		public boolean isFlag() {
			return flag;
		}
		public void setFlag(boolean flag) {
			this.flag = flag;
		}
		public float getNumber() {
			return number;
		}
		public void setNumber(float number) {
			this.number = number;
		}
		@Override
		public String toString() {
			return "flag" + flag + "number" + number;
		}
	}
	
	/**
	 * 根据位移转动
	 * @param move
	 */
	private void turnClockwise(float move){
		float angle = move / 5;
		for(int i=0 ; i<location.size() ; i++){
			location.get(i).setNumber((location.get(i).getNumber() + angle)%360);
			if(location.get(i).getNumber() >= -10 && location.get(i).getNumber() <= 10 ){//需要一个范围
				if(location.get(i).isFlag()){
					sound.onPlaySound();
					location.get(i).setFlag(false); //用于表示该图标已经播放完声音
				}
			}
			else{
				location.get(i).setFlag(true);
			}
		}
		requestLayout();//重新计算每个子view的位置
//		invalidate();
	}

	private class MyRunnable implements Runnable{
		
		boolean isAnti;
		public MyRunnable(boolean isAnti) {
			this.isAnti = isAnti;
		}
		
		public void setAnti(boolean isAnti){
			this.isAnti = isAnti;
		}
		
		@Override
		public void run() {
			try{
			if(isAnti)
				turnClockwise(-speedChangeRate.get(0));
			else
				turnClockwise(speedChangeRate.get(0));
			speedChangeRate.remove(0);
			}catch(Exception e){
				Log.e("zhao", e.getMessage());
			}
		}
		
	}
	
	/**
	 * 获取单例VelocityTracker
	 */
	private void getVelocityTracker(){
		if(tracker == null){
			tracker = VelocityTracker.obtain();
		}
	}
	
	/**
	 * 转盘停止转动，则会以一定的逆向加速度进行减速,形成一个转盘的效果
	 */
	private void turnByInertia(int area){
		getVelocityTracker(); 
		tracker.computeCurrentVelocity(10, maxSpeed);//pixel per 10 millisecond
		float velocity = 0;
		float time = 0;
		switch (area) {
		case 1:
			velocity = tracker.getXVelocity();
			if(velocity > 0)
				runnable.setAnti(false);
			else
				runnable.setAnti(true);
			break;
		case 2:
			velocity = tracker.getYVelocity();
			if(velocity > 0)
				runnable.setAnti(false);
			else
				runnable.setAnti(true);
			break;
		case 3:
			velocity = tracker.getXVelocity();
			if(velocity < 0)
				runnable.setAnti(false);
			else
				runnable.setAnti(true);
			break;
		case 4:
			velocity = tracker.getYVelocity();
			if(velocity < 0)
				runnable.setAnti(false);
			else
				runnable.setAnti(true);
			break;
		default:
			break;
		}
		time = velocity = Math.abs(velocity);
		while(velocity > acceleratedSpeed){
			speedChangeRate.add(Float.valueOf(velocity));
			this.postDelayed(runnable, (long) ((long)(time - velocity)*30.0/acceleratedSpeed));
			velocity = velocity - acceleratedSpeed;
		}
		tracker.clear();
		tracker.recycle();
		tracker = null;
	}
	
	/**
	 * 以一个“x”型的形状将一个圆等分成４份(参考数学系的象限)，每个区域所支持的手势不一样
	 * @param x
	 * @param y
	 * @return
	 */
	private int checkArea(float x, float y){
		int locationInWindow[] = new int[2];
		getLocationInWindow(locationInWindow);
		int parentX = locationInWindow[0] + getWidth()/2;
		int parentY = locationInWindow[1] + getHeight()/2;
		double distance = Math.sqrt((parentX - x)*(parentX - x) + (parentY - y)*(parentY - y));//两个点之间的距离
		double disY = Math.abs(y - parentY);
		double radian = Math.asin(disY/distance);
		double angle = (radian / Math.PI) * 180;
		if(y <= parentY && x <= parentX){// 1,4象限
			if(angle < 45)
				return 4;
			else
				return 1;
		}else if(y <= parentY && x >= parentX){ //1,2象限
			if(angle <=90 && angle >=45)
				return 1;
			else
				return 2;	
		}else if(y >= parentY && x >= parentX){//2,3象限
			if(angle <= 45)
				return 2;
			else
				return 3;
		}else{// 3,4象限
			if(angle < 45)
				return 4;
			else
				return 3;
		}
	}
	
	public class NumberOverFlowException extends Exception{
		private static final long serialVersionUID = 5374224301616912862L;
		private int num = 0;
		public NumberOverFlowException(int num) {
			this.num = num;
		}
		
		@Override
		public String getMessage(){
			return "number over flow " + num;
		}
	}
	
	/**
	 * 设置手指离开屏幕之后，转盘滚动的最大速度
	 * @param maxSpeed
	 */
	public void setMaxSpeed(float maxSpeed){
		this.maxSpeed = maxSpeed;
	}
	
	/**
	 * 设置逆向加速度
	 * @param acceleratedSpeed
	 */
	public void setAcceleratedSpeed(int acceleratedSpeed){
		this.acceleratedSpeed = acceleratedSpeed;
	}
	
	/**
	 * 设置每个元素距离圆心的距离
	 * @param disFromCentre
	 */
	public void setDisFromCentre(int disFromCentre) throws Exception{
		if(disFromCentre > outsideBG.getWidth()/2){
			throw new Exception("width too big!!");
		}
		this.disFromCentre = (float)disFromCentre;
	}
	
	/**
	 * 声音播放接口,模拟转盘的转动声音
	 * @author dev
	 *
	 */
	public interface PlaySound{
		public void onPlaySound();
	}
	
	/**
	 * 注册声音播放器
	 * @param sound
	 */
	public void registerSoundPlayer(PlaySound sound){
		this.sound = sound;
	}
	
	/**
	 * 元素的点击事件,每个view使用之前，使用settag为其设置标示
	 * @author dev
	 *
	 */
	public interface TurnplateClickListener{
		public void onClick(String tag);
	}
	
	/**
	 * 设置点击事件
	 */
	public void setOnItemClickListener(final TurnplateClickListener listener){
		this.listener = listener;
	}
	
//	/**
//	 * 用于处理复杂的手势
//	 * @author dev
//	 *
//	 */
//	private class MyGestureDetectorListener extends SimpleOnGestureListener{
//
//		/**
//		 * down事件
//		 */
//		@Override
//		public boolean onDown(MotionEvent arg0) {
//			return false;
//		}
//
//		/**
//		 * 滑动手势事件；Touch了滑动一点距离后，在ACTION_UP时才会触发       
//			参数：e1 第1个ACTION_DOWN MotionEvent 并且只有一个；
//			e2 最后一个ACTION_MOVE MotionEvent ；velocityX X轴上的移动速度，像素/秒 ；
//			velocityY Y轴上的移动速度，像素/秒.触发条件：X轴的坐标位移大于FLING_MIN_DISTANCE，
//			且移动速度大于FLING_MIN_VELOCITY个像素/秒
//		 */
//		@Override
//		public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
//				float arg3) {
//			return false;
//		}
//
//		/**
//		 * 长按事件；Touch了不移动一直Touch down时触发 
//		 */
//		@Override
//		public void onLongPress(MotionEvent arg0) {
//			viewIsBeingLongClick = getChildAt(checkClickChild(arg0.getX(), arg0.getY()));
////			initPopUpMenu(); ---1
////			showContextMenuForChild(viewIsBeingLongClick); ---2
////			menu.show();
//			initPopUpWindow();
//			
//			window.showAsDropDown(viewIsBeingLongClick);
//		}
//
//		/**
//		 * 拖动事件。无论是用手拖动view，或者是以抛的动作滚动，都会多次触发,这个方法在ACTION_MOVE动作发生时就会触发
//        抛：手指触动屏幕后，稍微滑动后立即松开
//		onDown-----》onScroll----》onScroll----》onScroll----》………----->onFling 
//		拖动
//		onDown------》onScroll----》onScroll------》onFiling
//		 */
//		@Override
//		public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
//				float arg3) {
//			return false;
//		}
//
//		/**
//		 * down事件发生而move或则up还没发生前触发该   
//			事件；Touch了还没有滑动时触发（与onDown，onLongPress）比较onDown只要Touch down一定立刻触发。
//			而Touchdown后过一会没有滑动先触发onShowPress再是onLongPress。所以Touchdown后一直不滑动
//			按照onDown->onShowPress->onLongPress这个顺序触发。
//		 */
//		@Override
//		public void onShowPress(MotionEvent arg0) {
//			
//		}
//
//		/**
//		 * 一次点击up事件；在touch down后又没有滑动
//			（onScroll），又没有长按（onLongPress），然后Touchup时触发。
//			 点击一下非常快的（不滑动）Touchup：
//			onDown->onSingleTapUp->onSingleTapConfirmed 
//			          点击一下稍微慢点的（不滑动）Touchup：
//			onDown->onShowPress->onSingleTapUp->onSingleTapConfirmed
//		 */
//		@Override
//		public boolean onSingleTapUp(MotionEvent arg0) {
////			if(listener != null){
////				int i = checkClickChild(arg0.getX(), arg0.getY());
////				if( i != -1)
////					listener.onClick((String) getChildAt(i).getTag());
////			}
//			return false;
//		}
//	}
	
	/**
	 * 确定点击的是哪个子view
	 * @param x
	 * @param y
	 * @return
	 */
	private int checkClickChild(float x, float y){
		for(int i=0; i < getChildCount(); i++){
				if(getChildAt(i).getLeft() <= x && getChildAt(i).getRight() >= x
						&& getChildAt(i).getTop() <= y && getChildAt(i).getBottom() >= y)
					return i;
		}
		return -1;
	}
	
	//3--- 弹出窗口样式 popupwindow
	/**
	 * 初始化弹出式窗口
	 */
	private void initPopUpWindow(){
		SpannableStringBuilder builder = new SpannableStringBuilder("删除 | 取消");
		builder.setSpan(new ClickableSpan() {
			
			@Override
			public void updateDrawState(TextPaint ds) {
				ds.setColor(getResources().getColor(android.R.color.white));
				ds.setUnderlineText(false);
			}
			
			@Override
			public void onClick(View widget) {
				if(viewIsBeingLongClick != null) {
					deleteCallback.onDeleteCallback((Integer) viewIsBeingLongClick.getTag());
				}
				window.dismiss();
			}
		}, 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		builder.setSpan(new ClickableSpan() {
			
			@Override
			public void updateDrawState(TextPaint ds) {
				ds.setColor(getResources().getColor(android.R.color.white));
				ds.setUnderlineText(false);
			}
			
			@Override
			public void onClick(View widget) {
				window.dismiss();
			}
		}, 5, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//		builder.setSpan(new BackgroundColorSpan(android.R.color.white), 0, builder.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
		TextView text = new TextView(getContext());
		text.setHighlightColor(Color.GREEN);//设置点击效果的高亮色为绿色
		text.setText(builder);
		text.setMovementMethod(LinkMovementMethod.getInstance());//加上此句用来相应点击
		text.setTextSize(25);
		text.setTextColor(getResources().getColor(android.R.color.white));
		text.setBackgroundColor(getResources().getColor(android.R.color.black));
		window = new PopupWindow(text, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		window.setBackgroundDrawable(new BitmapDrawable());//一定要设置backgrounddrawable，要不然点击外部不会消失
		window.setOutsideTouchable(true);
		window.setFocusable(true);
	}
	
	//1.弹出式菜单样式
//	/**
//	 * 初始化弹出式菜单
//	 */
//	private void initPopUpMenu(){
//		menu = new PopupMenu(context, viewIsBeingLongClick);
//		menu.getMenu().add(0, 1, 0, "删除");
//		menu.getMenu().add(0, 2, 0, "取消");
//		for(int i=0;i < menu.getMenu().size() ;i++)
//			menu.getMenu().getItem(i).setOnMenuItemClickListener(clickListener);
//	}
	
	//2.contextmenu样式,最好不要在一个单独的view里面使用一个contextmenu，需要和activity相关联,这样的编程习惯不好
	
//	/**
//	 * 创建上下文菜单
//	 */
//	@Override
//	protected void onCreateContextMenu(ContextMenu menu) {
//		if(viewIsBeingLongClick != null){
//			menu.setHeaderTitle("你确定要删除"+viewIsBeingLongClick.getTag());
//			menu.add(0, 1, 0, "确定");
//			menu.add(0, 2, 0, "取消");
//		}
//		for(int i = 0;i < menu.size(); i++)
//			menu.getItem(i).setOnMenuItemClickListener(clickListener);
//	}
	
//	OnMenuItemClickListener clickListener = new OnMenuItemClickListener() {
//		
//		@Override
//		public boolean onMenuItemClick(MenuItem item) {
//			switch (item.getItemId()) {
//			case 1:
//				removeChild(viewIsBeingLongClick);
//				break;
//			case 2:
//				break;
//			default:
//				break;
//			}
//			return true;
//		}
//	};

	public interface OnDeleteItemCallback {
		void onDeleteCallback(int position);
	}
	
}
