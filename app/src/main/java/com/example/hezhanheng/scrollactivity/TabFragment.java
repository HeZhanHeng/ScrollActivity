package com.example.hezhanheng.scrollactivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by HeZhanHeng on 2018/10/18.
 */
public class TabFragment extends Fragment
{
    public static final String TITLE = "title";
    private String mTitle = "Defaut Value";
    private ListView mListView;
    // private TextView mTextView;
    private List<String> mDatas = new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mTitle = getArguments().getString(TITLE);
            Log.i("标题===",mTitle);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        mListView = (ListView) view
                .findViewById(R.id.id_stickynavlayout_innerscrollview);
        if (mTitle.equals("消息")){
            for (int i = 0; i < 50; i++)
            {
                mDatas.add("消息的第"+ i+"条数据");
            }
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getActivity(),"您点击了"+position,Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getActivity(),MessageActivity.class);
                    startActivity(intent);
                }
            });
        }
        if (mTitle.equals("通讯录")){
            for (int i = 0; i < 50; i++)
            {
                mDatas.add("通讯录的第"+ i+"条数据");
            }
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getActivity(),"您点击了通讯录"+position,Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getActivity(),MailListActivity.class);
                    startActivity(intent);
                }
            });
        }
        if (mTitle.equals("发现")){
            for (int i = 0; i < 50; i++)
            {
                mDatas.add("发现的第"+ i+"条数据");
            }
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getActivity(),"您点击了发现"+position,Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getActivity(),FindActivity.class);
                    startActivity(intent);
                }
            });
        }
        if (mTitle.equals("个人中心")){
            for (int i = 0; i < 50; i++)
            {
                mDatas.add("个人中心的第"+ i+"条数据");
            }
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getActivity(),"您点击了个人中心"+position,Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getActivity(),PersonCenterActivity.class);
                    startActivity(intent);


                }
            });
        }
        mListView.setAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.item, R.id.id_info, mDatas)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                return super.getView(position, convertView, parent);
            }
        });
        return view;
    }
    public static TabFragment newInstance(String title)
    {
        TabFragment tabFragment = new TabFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }
}
