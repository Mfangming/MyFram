package com.fangming.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.fangming.adapter.NewsAdapter;
import com.fangming.entity.NewsEntity;
import com.fangming.entity.Respones;
import com.fangming.jsoup.JsoupUtil;
import com.fangming.myapp.MyApp;
import com.fangming.news.R;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends BaseFragment {
    private final static String TAG = "NewsFragment";
    private ListView mlistview;
    private NewsAdapter mAdapter;
    private List<NewsEntity> newsEntityList;
    private Button btn_update;
    private Activity _activity;
    MyApp myApp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myApp = MyApp.getInstance();
    }

    @Override
    public void onAttach(Activity activity) {
        _activity=activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_news, null);
        initView(view);
        return view;
    }

    private void initView(View v) {
        mlistview = (ListView) v.findViewById(R.id.mlistview);
        newsEntityList = new ArrayList<NewsEntity>();
        mAdapter = new NewsAdapter(_activity, newsEntityList,myApp.imageManager);
        mlistview.setAdapter(mAdapter);
        btn_update = (Button) v.findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myApp.jsoupUtil.getNewsList("http://sina.cn/", new JsoupUtil.ResponseListener() {
                    @Override
                    public void onResponse(String respons) {
                        List<NewsEntity> mlist=new ArrayList<NewsEntity>();
                        Respones<NewsEntity[]> respon= myApp.gsonHttpComExchange.formGson(respons,NewsEntity[].class);
                        if(respon.getStatus()){
                            if(respon.getData()!=null){
                                for(int i = 0; i < respon.getData().length; i++){
                                    mlist.add(respon.getData()[i]);
                                }
                                newsEntityList.addAll(mlist);
                            }
                        }else{
                            Toast.makeText(_activity,respon.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onVisibleLoad() {

    }

    @Override
    protected void invisibleLoad() {

    }
}
