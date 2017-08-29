package com.bwie.workday1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;


@ContentView(R.layout.activity_main)


public class MainActivity extends AppCompatActivity {
    @ViewInject(R.id.lv_news)
    ListView lv_news;
    private List<NewsBean> list = new ArrayList<>();
    private String url = "http://v.juhe.cn/toutiao/index?key=22a108244dbb8d1f49967cd74a0c144d";
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        ImageLoader();

    }

    private void ImageLoader() {
        DisplayImageOptions opt = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .build();
        ImageLoaderConfiguration con = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(opt)
                .build();
        ImageLoader.getInstance().init(con);
    }

    private void initData() {
        RequestParams rp = new RequestParams(url);
        rp.addQueryStringParameter("username", "muzi");
        rp.addQueryStringParameter("password", "0716");
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                try {

                    JSONObject json = new JSONObject(result);
                    JSONObject object = json.getJSONObject("result");
                    JSONArray array = object.getJSONArray("data");
                    if (array != null && array.length() > 0) {
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object1 = (JSONObject) array.get(i);
                            NewsBean nb = new NewsBean();
                            nb.title = object1.optString("title");
                            nb.img = object1.optString("thumbnail_pic_s");
                            list.add(nb);
                        }
                        setDate();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void setDate() {
        adapter = new Adapter(this, list);
        lv_news.setAdapter(adapter);

    }
}
