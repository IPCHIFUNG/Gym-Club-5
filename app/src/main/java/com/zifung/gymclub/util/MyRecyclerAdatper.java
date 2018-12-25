package com.zifung.gymclub.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zifung.gymclub.CourseActivity;
import com.zifung.gymclub.list.RecyclerList;
import com.zifung.gymclub.R;
import com.zifung.gymclub.model.MainActivity;

import java.util.List;

public class MyRecyclerAdatper extends RecyclerView.Adapter<MyRecyclerAdatper.MyViewHolder> {
    private List<RecyclerList> mDatas;
    private Context mContext;
    private LayoutInflater inflater;
    //HeaderView, FooterView
    private View mHeaderView;
    private View mFooterView;

    private Activity activity;

    public static final int TYPE_HEADER = 0;  //说明是带有Header的
    public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
    public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的


    public MyRecyclerAdatper(Context context, List<RecyclerList> datas, Activity activity){
        this.mContext=context;
        this.mDatas=datas;
        this.activity = activity;
        inflater=LayoutInflater.from(mContext);
    }


    //返回View中Item的个数，这个时候，总的个数应该是ListView中Item的个数加上HeaderView和FooterView
    @Override
    public int getItemCount() {
        if(mHeaderView == null && mFooterView == null){
            return mDatas.size();
        }else if(mHeaderView == null && mFooterView != null){
            return mDatas.size() + 1;
        }else if (mHeaderView != null && mFooterView == null){
            return mDatas.size() + 1;
        }else {
            return mDatas.size() + 2;
        }
    }

    //创建View，如果是HeaderView或者是FooterView，直接在Holder中返回
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(mHeaderView != null && viewType == TYPE_HEADER) {
            return new MyViewHolder(mHeaderView);
        }
        if(mFooterView != null && viewType == TYPE_FOOTER){
            return new MyViewHolder(mFooterView);
        }
        View view = inflater.inflate(R.layout.sport_list_view,parent, false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }


    //绑定View，这里是根据返回的这个position的类型，从而进行绑定的，   HeaderView和FooterView, 就不同绑定了

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        if(getItemViewType(position) == TYPE_NORMAL){
            if(holder instanceof MyViewHolder) {
                //这里加载数据的时候要注意，是从position-1开始，因为position==0已经被header占用了
                holder.course_name.setText(mDatas.get(position-1).getName());
                holder.course_name.getPaint().setFakeBoldText(true);
                holder.course_time.setText(mDatas.get(position-1).getTime());
                holder.root_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(activity, CourseActivity.class);
                        intent.putExtra("tel_number", mDatas.get(position-1).getTelNumber());
                        activity.startActivity(intent);
                    }
                });
                return;
            }
            return;
        }else if(getItemViewType(position) == TYPE_HEADER){
            return;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView course_name;
        TextView course_time;
        View root_layout;
        View v;
        public MyViewHolder(View view) {
            super(view);
            course_name = (TextView) view.findViewById(R.id.tv_course_name);
            course_time = (TextView)view.findViewById(R.id.tv_course_time);
            root_layout = view.findViewById(R.id.list_root_item);
            v = view;
        }
    }

    //HeaderView和FooterView的get和set函数
    public View getHeaderView() {
        return mHeaderView;
    }
    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }
    public View getFooterView() {
        return mFooterView;
    }
    public void setFooterView(View footerView) {
        mFooterView = footerView;
        notifyItemInserted(getItemCount()-1);
    }

    /** 重写这个方法，很重要，是加入Header和Footer的关键，我们通过判断item的类型，从而绑定不同的view    * */
    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null && mFooterView == null){
            return TYPE_NORMAL;
        }
        if (position == 0){
            //第一个item应该加载Header
            return TYPE_HEADER;
        }
        if (position == getItemCount()-1){
            //最后一个,应该加载Footer
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }
}
