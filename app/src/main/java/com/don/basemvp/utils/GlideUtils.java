package com.don.basemvp.utils;

import android.content.Context;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.don.basemvp.config.GlideConfig;

import org.jetbrains.annotations.NotNull;

public class GlideUtils {

    /**
     * 清理所有缓存
     * @param context
     */
    public static void clearAllCache(@NotNull Context context) {
        Glide.get(context).clearMemory();
        Glide.get(context).clearDiskCache();
    }

    /**
     * 默认居中裁剪
     *
     * @param context
     * @param url
     * @param view
     */
    public static void loadImageContext(@NotNull Context context, @NotNull String url, @NotNull ImageView view) {
        Glide.with(context).applyDefaultRequestOptions(GlideConfig.BaseGlideOptions)
                .load(url)
                .into(view);
    }

    public static void loadImage(@NotNull AppCompatActivity activity, @NotNull String url, @NotNull ImageView imageView) {
        loadImageContext(activity, url, imageView);
    }

    public static void loadImage(@NotNull Fragment fragment, @NotNull String url, @NotNull ImageView imageView) {
        loadImageContext(fragment.getContext(), url, imageView);
    }

    /**
     * 默认居中圆形裁剪
     *
     * @param context
     * @param url
     * @param view
     */
    public static void loadCircleImageContext(@NotNull Context context, @NotNull String url, @NotNull ImageView view) {
        Glide.with(context).applyDefaultRequestOptions(GlideConfig.BaseGlideOptions.circleCrop())
                .load(url)
                .into(view);
    }

    public static void loadCircleImage(@NotNull AppCompatActivity activity, @NotNull String url, @NotNull ImageView imageView) {
        loadCircleImageContext(activity, url, imageView);
    }

    public static void loadCircleImage(@NotNull Fragment fragment, @NotNull String url, @NotNull ImageView imageView) {
        loadCircleImageContext(fragment.getContext(), url, imageView);
    }

    /**
     * 默认居中圆角矩形裁剪
     *
     * @param context
     * @param url
     * @param view
     */
    public static void loadRoundedImageContext(@NotNull Context context, @NotNull String url, @NotNull ImageView view) {
        Glide.with(context).applyDefaultRequestOptions(GlideConfig.BaseGlideOptions.transform(new RoundedCorners(GlideConfig.BaseRoundedCorners)))
                .load(url)
                .into(view);
    }

    public static void loadRoundedImage(@NotNull AppCompatActivity activity, @NotNull String url, @NotNull ImageView imageView) {
        loadCircleImageContext(activity, url, imageView);
    }

    public static void loadRoundedImage(@NotNull Fragment fragment, @NotNull String url, @NotNull ImageView imageView) {
        loadCircleImageContext(fragment.getContext(), url, imageView);
    }

    /****Target 待补充****/

}
