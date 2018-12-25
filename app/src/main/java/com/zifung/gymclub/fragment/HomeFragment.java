package com.zifung.gymclub.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zifung.gymclub.R;
import com.zifung.gymclub.list.RecyclerList;
import com.zifung.gymclub.util.MyRecyclerAdatper;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

    private List<RecyclerList> mDatas;
    private MyRecyclerAdatper recycleAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);


        mDatas = new ArrayList<>();
        RecyclerList fl;

        fl=new RecyclerList("上肢拉伸", "6分钟 · k1", "17610097128");
        mDatas.add(fl);
        fl=new RecyclerList("腹肌撕裂者初级", "9分钟 · k2", "17610097128");
        mDatas.add(fl);
        fl=new RecyclerList("小腿按摩", "15分钟 · k1", "17610097128");
        mDatas.add(fl);
        fl=new RecyclerList("躯干拉伸", "8分钟 · k1", "17610097128");
        mDatas.add(fl);
        fl=new RecyclerList("瘦腿训练", "9分钟 · k2", "17610097128");
        mDatas.add(fl);
        fl=new RecyclerList("腹肌撕裂者进阶", "11分钟 · k3", "17610097128");
        mDatas.add(fl);
        fl=new RecyclerList("马甲线养成", "12分钟 · k3", "17610097128");
        mDatas.add(fl);
        fl=new RecyclerList("一字马竖叉", "13分钟 · k1", "17610097128");
        mDatas.add(fl);


        recycleAdapter = new MyRecyclerAdatper(getContext(), mDatas, getActivity());
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        //创建并设置Adapter

        // set the header and footer
        View header = LayoutInflater.from(getContext()).inflate(R.layout.home_header,null);
        recycleAdapter.setHeaderView(header);
        View footer = LayoutInflater.from(getContext()).inflate(R.layout.home_footer,null);
        recycleAdapter.setFooterView(footer);


        recyclerView.setAdapter(recycleAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

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
}
