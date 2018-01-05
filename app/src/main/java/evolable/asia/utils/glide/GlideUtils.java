package evolable.asia.utils.glide;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

import evolable.asia.R;

/*
 * ******************************************************************************
 *  Copyright Ⓒ 2018. All rights reserved
 *  Author HoanDC. Create on 1/4/2018.
 * ******************************************************************************
 */
public class GlideUtils {

    public static void loadUrl(String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        Glide.with(imageView.getContext())
                .load(url)
                .thumbnail(0.5f)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_img_default)
                        .error(R.drawable.ic_img_default)
                        .priority(Priority.HIGH)
                        .dontAnimate()
                        .dontTransform())
                .into(imageView);
    }

    public static void loadResource(int resource, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(resource)
                .into(imageView);
    }

    public static void loadFile(File file, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(file)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_img_default)
                        .error(R.drawable.ic_img_default)
                        .priority(Priority.HIGH)
                        .dontAnimate()
                        .dontTransform())
                .into(imageView);
    }

}
