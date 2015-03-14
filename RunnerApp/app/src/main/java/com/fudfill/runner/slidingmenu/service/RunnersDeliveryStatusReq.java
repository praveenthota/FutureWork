package com.fudfill.runner.slidingmenu.service;

import android.os.Parcel;
import android.util.Log;

/**
 * Created by praveenthota on 3/13/15.
 */
public class RunnersDeliveryStatusReq extends HTTPRequest {

    private static String TAG="RunnersDeliveryStatusReq";

    public RunnersDeliveryStatusReq(Parcel temp) {
        super(temp);
  }

    @Override
    public int parseData() {
        String jsonStr = getRespData();
        if (jsonStr != null && jsonStr.contains("success")) {

            Log.d("Fudfill", "Deleting the item records");
            return 0;
        }
        return 1;
    }
}
