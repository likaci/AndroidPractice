package com.xiazhiri.practice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiazhiri.practice.util.L;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            L.e(name.getShortClassName());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            L.e();
        }
    };

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.start, R.id.stop, R.id.bind, R.id.unBind})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.start:
                intent = new Intent(getActivity(), MyService.class);
                getActivity().startService(intent);
                break;
            case R.id.stop:
                intent = new Intent(getActivity(), MyService.class);
                getActivity().stopService(intent);
                break;
            case R.id.bind:
                intent = new Intent(getActivity(), MyService.class);
                getActivity().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.unBind:
                getActivity().unbindService(serviceConnection);
                break;
        }
    }
}
