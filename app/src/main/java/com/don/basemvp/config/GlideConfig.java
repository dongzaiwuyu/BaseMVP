package com.don.basemvp.config;

import com.bumptech.glide.request.RequestOptions;
import com.don.basemvp.R;

/***
 *@author Don
 *@date on 2020/4/26 10:56
 *@describe Glide 相关配置
 */
public class GlideConfig {

    /**
     * 默认圆角角度
     */
    public static final int BaseRoundedCorners = 6;

    /**
     * 默认加载器
     */
    public static RequestOptions BaseGlideOptions = new RequestOptions()
            .placeholder(R.mipmap.ic_launcher)
            .centerCrop()
            .error(R.mipmap.ic_launcher);

}
