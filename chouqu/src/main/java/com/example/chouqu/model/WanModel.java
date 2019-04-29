package com.example.chouqu.model;

import com.example.chouqu.api.jiekou;
import com.example.chouqu.base.BaseModel;
import com.example.chouqu.bean.Fuli;
import com.example.chouqu.util.ResultCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
public class WanModel extends BaseModel{
    public void getData(final ResultCallBack<Fuli>callBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(jiekou.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        jiekou jiekou = retrofit.create(jiekou.class);
        Observable<Fuli> fuli = jiekou.getFuli();
        fuli.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Fuli>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Fuli fuli) {
                        if (fuli != null) {
                            callBack.onSuccess(fuli);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                            callBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
