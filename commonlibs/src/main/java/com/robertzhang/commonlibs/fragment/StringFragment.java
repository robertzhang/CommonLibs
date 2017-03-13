package com.robertzhang.commonlibs.fragment;

import android.widget.TextView;

import com.robertzhang.commonlibs.R;
import com.robertzhang.commonlibs.fragment.base.BaseFragment;

public class StringFragment extends BaseFragment {
    private String mText;
    private TextView mTvText;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_string;
    }

    @Override
    protected void setUpView() {
        mText = getArguments().getString("text");
        mTvText = $(R.id.tv_text);
    }

    @Override
    protected void setUpData() {
        if (!mText.equals(""))
            mTvText.setText(mText);
        else
            mTvText.setText("暂无信息");
    }
}
