package com.zifung.gymclub.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zifung.gymclub.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DiscoveryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DiscoveryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiscoveryFragment extends Fragment implements ViewPager.OnPageChangeListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ViewPager banner;
    private int[] imageResIds;
    private ArrayList<ImageView> imageViewList;
    private boolean isBannerRunning = false;

    private ListView list;
    private ArrayList<Map<String, Object>> list_item;
    private MyBaseAdapter adapter;

    private LinkedList<Map<String, Object>> waitingQueue;

    public DiscoveryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiscoveryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiscoveryFragment newInstance(String param1, String param2) {
        DiscoveryFragment fragment = new DiscoveryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discovery, container, false);
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.discovery_list_header, null);
        banner = headerView.findViewById(R.id.vp_banner);

        imageResIds = new int[]{R.drawable.banner_1, R.drawable.banner_2, R.drawable.banner_3, R.drawable.banner_4};
        imageViewList = new ArrayList<>();

        ImageView imageView;
        int img_num = imageResIds.length;
        for (int i = 0; i < img_num; i++) {
            imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(imageResIds[i]);
            imageViewList.add(imageView);
        }

        list = view.findViewById(R.id.lv_discovery);
        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                switch (scrollState){
                    case SCROLL_STATE_IDLE:
                        adapter.setScrollState(false);
                        while (!waitingQueue.isEmpty()) {
                            Map<String, Object> temp = waitingQueue.pop();
                            ImageView iv = (ImageView) temp.get("obj");
                            iv.setBackgroundResource((Integer) temp.get("img"));
                        }
                        break;
                    case SCROLL_STATE_FLING:
                        adapter.setScrollState(true);
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL:
                        adapter.setScrollState(true);
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
            }
        });

        list_item = new ArrayList<>();
        waitingQueue = new LinkedList<>();

        for (int i = 0; i < 4; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("img", imageResIds[i]);
            map.put("text", "item" + i);
            list_item.add(map);
        }

        adapter = new MyBaseAdapter(getContext(), list_item);

        list.addHeaderView(headerView);
        list.setAdapter(adapter);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    class MyBaseAdapter extends BaseAdapter {

        private Context mContext;
        private List<Map<String, Object>> mMapList;
        private List<View> vViewList;

        private  boolean scrollState = false;

        public MyBaseAdapter(Context context, List<Map<String, Object>> mapList) {
            mContext = context;
            mMapList = mapList;
            vViewList = new ArrayList<>();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            for (int i = 0; i < mapList.size(); i++) {
                View view = inflater.inflate(R.layout.discovery_list_item, null);
                ViewHolder viewHolder = new ViewHolder();

                viewHolder.img = view.findViewById(R.id.iv_dli);
                viewHolder.text = view.findViewById(R.id.tv_dli);
                view.setTag(viewHolder);
                vViewList.add(view);
            }
        }

        public void setScrollState(boolean scrollState) {
            this.scrollState = scrollState;
        }

        @Override
        public int getCount() {
            return mMapList.size();
        }

        @Override
        public Map<String, Object> getItem(int i) {
            Map<String, Object> item = null;
            if (null != mMapList)
                item = mMapList.get(i);
            return item;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder = (ViewHolder) vViewList.get(i).getTag();

            Map<String, Object> item = getItem(i);
            if (null != item || viewHolder.img.getBackground() == null) {
                if (!scrollState) {
                    viewHolder.img.setBackgroundResource((int) item.get("img"));
                } else {
                    Map<String, Object> temp = new HashMap<>();
                    temp.put("obj", viewHolder.img);
                    temp.put("img", (int) item.get("img"));
                    waitingQueue.push(temp);
                }
                viewHolder.text.setText((String) item.get("text"));
            }
            return vViewList.get(i);
        }

        private class ViewHolder {
            ImageView img;
            TextView text;
        }
    }
}
