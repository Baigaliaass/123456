package com.example.chouqu.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import com.example.chouqu.R;
import com.example.chouqu.base.BaseFragment;
import com.example.chouqu.presenter.XiangP;
import com.example.chouqu.view.XiangV;
import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class XiangjiFragment extends BaseFragment<XiangV, XiangP> implements XiangV {

    private Uri imageUri;
    @BindView(R.id.btn_xingji)
    Button mBXingji;
    private PopupWindow mPopupWindow;

    @Override
    protected XiangP initPresenter() {
        return new XiangP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_xiangji;
    }

    @Override
    protected void initView() {
        super.initView();
        mBXingji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showpopupWindow(v);
            }
        });
    }
    @SuppressLint("InlinedApi")
    private void showpopupWindow(View v) {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.popwindow_layout, null);

        mPopupWindow = new PopupWindow(view, FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, true);
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        mPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.popupwindow_background));
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setAnimationStyle(R.style.MyPopupWindow_anim_style);
        mPopupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
        Button ji = view.findViewById(R.id.ji);
        Button ce = view.findViewById(R.id.ce);
        ji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xiangji();
                mPopupWindow.dismiss();
            }
        });
        ce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xiangce();
                mPopupWindow.dismiss();
            }
        });
        backgroundAlpha(0.5f);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });

    }

    private void xiangce() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);

    }

    private void xiangji() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 启动intent开始拍照
        startActivityForResult(intent, 2);

    }

    // 设置屏幕透明度
    public void backgroundAlpha(float bgAlpha) {
//        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        lp.alpha = bgAlpha; // 0.0~1.0
//        getWindow().setAttributes(lp);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 2:
                Intent intent = new Intent("com.android.camera.action.CROP");
                // 设置数据为文件uri，类型为图片格式
                intent.setDataAndType(imageUri, "image/*");
                if (data != null) {
                    // 根据返回的data，获取Bitmap对象
                    Bitmap bitmap = data.getExtras().getParcelable("data");

                }
                break;
        }
    }
}
