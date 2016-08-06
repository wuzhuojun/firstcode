// (c)2016 Flipboard Inc, All Rights Reserved.

package com.example.fcode16.fragment;

import android.app.AlertDialog;
import android.app.Fragment;

import butterknife.OnClick;
import rx.Subscription;

public abstract class BaseFragment extends Fragment {
    protected Subscription subscription;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unsubscribe();
    }

    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
