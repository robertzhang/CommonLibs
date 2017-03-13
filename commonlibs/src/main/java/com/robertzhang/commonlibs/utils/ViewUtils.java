package com.robertzhang.commonlibs.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.text.Html;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.robertzhang.commonlibs.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ViewUtils {

    private static Map<String, BaseFragment> fragmentList = new HashMap<>();

    /**
     * 根据Class创建Fragment
     *
     * @param clazz the Fragment of create
     * @return
     */
    public static BaseFragment createFragment(Class<?> clazz, boolean isObtain) {
        BaseFragment resultFragment = null;
        String className = clazz.getName();
        if (fragmentList.containsKey(className)) {
            resultFragment = fragmentList.get(className);
        } else {
            try {
                try {
                    resultFragment = (BaseFragment) Class.forName(className).newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (isObtain)
                fragmentList.put(className, resultFragment);
        }

        return resultFragment;
    }

    public static BaseFragment createFragment(Class<?> clazz) {
        return createFragment(clazz, true);
    }

    public static List<BaseFragment> getFragments() {
        Iterator<BaseFragment> ita = fragmentList.values().iterator();
        List<BaseFragment> list = new ArrayList<>();
        while (ita.hasNext()) {
            list.add(ita.next());
        }
        return list;
    }


    /**
     * 获取屏幕的宽度
     *
     * @param context context
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    /**
     * 获取屏幕的高度
     *
     * @param context context
     * @return
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    //转换dp为px
    public static int dp2px(Context context, int dip) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));
    }

    //转换px为dp
    public static int px2dp(Context context, int px) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f * (px >= 0 ? 1 : -1));
    }

    //转换sp为px
    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    //转换px为sp
    public static int px2sp(Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int getThemeColorPrimary(Context ctx) {
        TypedValue typedValue = new TypedValue();
        ctx.getTheme().resolveAttribute(android.R.attr.theme, typedValue, true);
        int[] attribute = new int[]{android.R.attr.colorPrimary};
        TypedArray array = ctx.obtainStyledAttributes(typedValue.resourceId, attribute);
        int color = array.getColor(0, -1);
        array.recycle();
        return color;
    }

    public static int getThemeColorPrimaryDark(Context ctx) {
        TypedValue typedValue = new TypedValue();
        ctx.getTheme().resolveAttribute(android.R.attr.theme, typedValue, true);
        int[] attribute = new int[]{android.R.attr.colorPrimaryDark};
        TypedArray array = ctx.obtainStyledAttributes(typedValue.resourceId, attribute);
        int color = array.getColor(0, -1);
        array.recycle();
        return color;
    }

    public static int getThemeColorAccent(Context ctx) {
        TypedValue typedValue = new TypedValue();
        ctx.getTheme().resolveAttribute(android.R.attr.theme, typedValue, true);
        int[] attribute = new int[]{android.R.attr.colorAccent};
        TypedArray array = ctx.obtainStyledAttributes(typedValue.resourceId, attribute);
        int color = array.getColor(0, -1);
        array.recycle();
        return color;
    }

    /**
     * is landscape
     *
     * @param context
     * @return
     */
    public static boolean isLandscape(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    /**
     * is portrait
     *
     * @param context
     * @return
     */
    public static boolean isPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    public static void showTextNormal(TextView tv, String text){
        if((null==tv)||(null==text)){
            return;
        }

        tv.setText(text);
    }

    /**
     * @param tv
     * @param baseText
     * @param highlightText
     * if the string of highlightText is a subset of the string of baseText,highlight the string of highlightText.
     */
    public static void showTextHighlight(TextView tv,String baseText,String highlightText){
        if((null==tv)||(null==baseText)||(null==highlightText)){
            return;
        }

        int index=baseText.indexOf(highlightText);
        if(index<0){
            tv.setText(baseText);
            return;
        }

        int len=highlightText.length();
        /**
         *  "<u><font color=#FF8C00 >"+str+"</font></u>"; 	//with underline
         *  "<font color=#FF8C00 >"+str+"</font>";			//without underline
         *
         *  <color name="dark_orange">#FF8C00</color>
         */
        Spanned spanned= Html.fromHtml(baseText.substring(0, index) + "<font color=#FF8C00 >"
                + baseText.substring(index, index + len) + "</font>"
                + baseText.substring(index + len, baseText.length()));

        tv.setText(spanned);
    }

    public static int getViewVisibility(View view) {
        if (null == view) {
            return View.GONE;
        }

        return view.getVisibility();
    }

    public static void showView(View view) {
        if (null == view) {
            return;
        }

        if (View.VISIBLE != view.getVisibility()) {
            view.setVisibility(View.VISIBLE);
        }
    }

    public static void invisibleView(View view) {
        if (null == view) {
            return;
        }
        if (View.INVISIBLE != view.getVisibility()) {
            view.setVisibility(View.INVISIBLE);
        }

        return;
    }

    public static void hideView(View view) {
        if (null == view) {
            return;
        }
        if (View.GONE != view.getVisibility()) {
            view.setVisibility(View.GONE);
        }

        return;
    }
}
