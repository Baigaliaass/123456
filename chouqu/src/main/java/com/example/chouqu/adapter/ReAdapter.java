package com.example.chouqu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chouqu.R;
import com.example.chouqu.bean.Fuli;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2019/3/27 8:55
 * //                            _ooOoo_
 * //                           o8888888o
 * //                           88" . "88
 * //                           (| -_- |)
 * //                           O\  =  /O
 * //                        ____/`---'\____
 * //                      .'  \\|     |//  `.
 * //                     /  \\|||  :  |||//  \
 * //                     /  _||||| -:- |||||-  \
 * //                     |   | \\\  -  /// |   |
 * //                    | \_|  ''\---/''  |   |
 * //                    \  .-\__  `-`  ___/-. /
 * //                  ___`. .'  /--.--\  `. . __
 * //                ."" '<  `.___\_<|>_/___.'  >'"".
 * //              | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * //               \  \ `-.   \_ __\ /__ _/   .-` /  /
 * //          ======`-.____`-.___\_____/___.-`____.-'======
 * //                             `=---='
 * //         ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 * //                    佛祖保佑        永无BUG
 * //            佛曰:
 * //                  写字楼里写字间，写字间里程序员；
 * //                  程序人员写程序，又拿程序换酒钱。
 * //                  酒醒只在网上坐，酒醉还来网下眠；
 * //                  酒醉酒醒日复日，网上网下年复年。
 * //                  但愿老死电脑间，不愿鞠躬老板前；
 * //                  奔驰宝马贵者趣，公交自行程序员。
 * //                  别人笑我忒疯癫，我笑自己命太贱；
 * //                  不见满街漂亮妹，哪个归得程序员？
 * //                                        --白嘎力
 */
public class ReAdapter extends RecyclerView.Adapter<ReAdapter.ViewHodler>{
    private ArrayList<Fuli.ResultsBean> mList;
    private Context mContext;

    public ReAdapter(ArrayList<Fuli.ResultsBean> list) {

        mList = list;
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item1, null);
        return new ViewHodler(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler holder, int position) {
        holder.mName.setText(mList.get(position).getType());
        Glide.with(mContext).load(mList.get(position).getUrl()).into(holder.mImg);
    }
    public void shua(List<Fuli.ResultsBean> list){
        if (mList!=null){
            mList.addAll(list);

        }
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder {
        private final ImageView mImg;
        private final TextView mName;
        public ViewHodler(View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.img);
            mName = itemView.findViewById(R.id.name);
        }
    }
}
