package com.fudfill.runner.slidingmenu.service;

import android.os.Parcel;
import android.util.Log;

import com.fudfill.runner.slidingmenu.adapter.CustomerOrderDetails;
import com.fudfill.runner.slidingmenu.adapter.CustomerWaypointDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by praveenthota on 3/13/15.
 */
public class RunnerTaskReq extends HTTPRequest {

    // JSON Node names
    private static final String TAG_ORDER = "order";
    private static final String TAG_ORDER_ID = "order_id";
    private static final String TAG_NAME = "name";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_ADDRESS = "address";
    private static final String TAG_LOCATION = "location";
    private static final String TAG_LATITUDE = "latitude";
    private static final String TAG_LONGITUDE = "longitude";
    private static final String TAG_PHONE = "phone";
    private static final String TAG_PHONE_MOBILE = "mobile";
    private static final String TAG_PHONE_HOME = "home";
    private static final String TAG_PHONE_OFFICE = "office";
    private static final String TAG_ORDER_STATUS = "order_status";
    private static final String TAG_ITEMS = "items";
    private static final String TAG_ITEMS_ID = "item_id";
    private static final String TAG_ITEM_NAME = "item_name";
    private static final String TAG_ITEM_COST = "item_cost";
    private static final String TAG_ITEM_COUNT = "item_count";
    private String TAG = "RunnerTaskReq";
    private List<CustomerWaypointDetails> wayPoints;

    public RunnerTaskReq()
    {

    }

    public RunnerTaskReq(Parcel temp) {
        super(temp);
    }

    @Override
    public int parseData() {

        String jsonStr = getRespData();

        if(jsonStr!=null)
        {
            if(null == parseJsonString(jsonStr))
            {
                return 1;
            }
        }

        return 0;
    }

    public String parseJsonString(String jsonStr) {
        String parsedStr = null;
        try {
            JSONArray order;

            JSONArray items;
            JSONObject c = new JSONObject(jsonStr);

            int totalOrderCost = 0;

            String order_id = c.getString(TAG_ORDER_ID);
            String name = c.getString(TAG_NAME);
            String email = c.getString(TAG_EMAIL);
            String address = c.getString(TAG_ADDRESS);
            String order_status = c.getString(TAG_ORDER_STATUS);

            // Location node is JSON Object
            JSONObject location = c.getJSONObject(TAG_LOCATION);
            String latitude = location.getString(TAG_LATITUDE);
            String longitude = location.getString(TAG_LONGITUDE);
            // Phone node is JSON Object
            JSONObject phone = c.getJSONObject(TAG_PHONE);
            String mobile = phone.getString(TAG_PHONE_MOBILE);

            // tmp hashmap for single contact
            HashMap<String, String> OrderMap = new HashMap<String, String>();

            // adding each child node to HashMap key => value
            OrderMap.put(TAG_ORDER_ID, order_id);
            OrderMap.put(TAG_NAME, name);
            OrderMap.put(TAG_EMAIL, email);
            OrderMap.put(TAG_PHONE_MOBILE, mobile);


            // Getting JSON Array node
            items = c.getJSONArray(TAG_ITEMS);


            List<CustomerOrderDetails> tCustomerOrderList = new ArrayList<CustomerOrderDetails>();

            for (int j = 0; j < items.length(); j++) {
                JSONObject item = items.getJSONObject(j);

                String item_id = item.getString(TAG_ITEMS_ID);
                String item_name = item.getString(TAG_ITEM_NAME);
                String item_cost = item.getString(TAG_ITEM_COST);
                String item_count = item.getString(TAG_ITEM_COUNT);
                totalOrderCost += Integer.parseInt(item_cost) * Integer.parseInt(item_count);
                CustomerOrderDetails tOrderItem = new CustomerOrderDetails(item_name,
                        Integer.parseInt(item_count), Integer.parseInt(item_cost));
                tCustomerOrderList.add(tOrderItem);

            }
            CustomerWaypointDetails tCustomerOrder = new CustomerWaypointDetails(order_id, name,
                    "" + totalOrderCost, address, mobile);
            // Set the customer list
            tCustomerOrder.setItemList(tCustomerOrderList);


            wayPoints.add(tCustomerOrder);

            Log.d("Fudfill", " String: " + c.toString());
            parsedStr = c.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parsedStr;
    }

}
