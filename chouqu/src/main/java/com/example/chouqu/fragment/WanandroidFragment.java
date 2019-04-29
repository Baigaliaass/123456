package com.example.chouqu.fragment;



import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.chouqu.R;
import com.example.chouqu.adapter.ReAdapter;
import com.example.chouqu.base.BaseFragment;
import com.example.chouqu.bean.Fuli;
import com.example.chouqu.presenter.WanP;
import com.example.chouqu.view.WanV;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class WanandroidFragment extends BaseFragment<WanV, WanP> implements WanV {
    @BindView(R.id.rec)
    RecyclerView mRec;
    private ArrayList<Fuli.ResultsBean> mList;
    private ReAdapter mReAdapter;

    @Override
    protected WanP initPresenter() {
        return new WanP();
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wanandroid;
    }

    @Override
    protected void initView() {
        mList = new ArrayList<>();
        mReAdapter = new ReAdapter(mList);
        mRec.setAdapter(mReAdapter);
        mRec.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    @Override
    public void setData(Fuli fuli) {
        List<Fuli.ResultsBean> results = fuli.getResults();
        mReAdapter.shua(results);
    }


}
