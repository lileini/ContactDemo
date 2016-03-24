package com.example.administrator.contactdemo.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

public class UiUtil {
	private static int sreenWidth = 0;
	private static int sreenHeight = 0;
	public static int getWidth(Context context) {
		if (sreenWidth<=0) {
			readSreenInfo(context);
		}
		return sreenWidth;
	}
	
	
	public static int getHeight(Context context) {
		if (sreenHeight<= 0) {
			readSreenInfo(context);
		}
		return sreenHeight;
	}


	public static void readSreenInfo(Context context) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);

		

		DisplayMetrics outMetrics = new DisplayMetrics();
		 wm.getDefaultDisplay().getMetrics(outMetrics );
		 sreenHeight =outMetrics.heightPixels;// 屏幕高度		
		sreenWidth = outMetrics.widthPixels;// 屏幕宽度
	}
	public static void showDialogAlert(Context ctx, String title, String body) {
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setTitle(title);
		builder.setMessage(body);
		Dialog dialogDetails = builder.create();
		dialogDetails.setCanceledOnTouchOutside(true);
		dialogDetails.show();
	}

	public static int getPixByDPI(Context context, float value) {
		int mPix = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, value, context.getResources()
						.getDisplayMetrics());
		return mPix;
	}
	
	public static int getFixMargin(int srceenWidth,int singleHeight){
		
		int fixMargin=0;
		
		if(srceenWidth>(singleHeight*3)&&singleHeight>0){
			
			
			fixMargin=(srceenWidth-(singleHeight*3))/4;
			
			
		}
       return fixMargin;	
	}

	/**
	 * 创建默认的图片
	 * @param context
	 * @param resId 图片资源ID
	 * @param width 图片的宽度
	 * @param height 图片的高度
	 * @return
	 */
	public static Bitmap createDefaultBitmap(Context context,int resId,int width, int height) {
		int bitmapWidth = getPixByDPI(context, (float) width);
		int bitmapHeight = getPixByDPI(context, (float) height);
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(context.getResources(), resId, opts);
		int widthPercent = opts.outWidth / bitmapWidth;
		int heightPercent = opts.outHeight / bitmapHeight;

		opts.inSampleSize = widthPercent > heightPercent ? widthPercent : heightPercent;
		opts.inJustDecodeBounds = false;
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId, opts);
		return bitmap;
	}
}
