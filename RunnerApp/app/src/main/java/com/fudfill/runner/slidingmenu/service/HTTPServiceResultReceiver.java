package com.fudfill.runner.slidingmenu.service;

/**
 * Created by praveenthota on 3/14/15.
 */
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class HTTPServiceResultReceiver extends ResultReceiver {
    private Receiver mReceiver;

    public HTTPServiceResultReceiver(Handler handler) {
        super(handler);
    }

    public void setReceiver(Receiver receiver) {
        mReceiver = receiver;
    }

    public interface Receiver {
        public void onReceiveResult(int resultCode, Bundle resultData);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (mReceiver != null) {
            mReceiver.onReceiveResult(resultCode, resultData);
        }
    }
}
