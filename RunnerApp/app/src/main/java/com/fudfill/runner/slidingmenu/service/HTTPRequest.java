package com.fudfill.runner.slidingmenu.service;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by praveenthota on 3/13/15.
 */
public class HTTPRequest implements Parcelable {
    public final static int GET = 1;
    public final static int POST = 2;
    public final static int PUT = 3;
    private String url;
    private int method;
    private int reqType;
    private String reqData;
    private String respData;
    private int status;

    public HTTPRequest()
    {}


    public HTTPRequest(Parcel temp) {

        this.reqType = temp.readInt();
        this.status = temp.readInt();
        this.method = temp.readInt();
        this.url = temp.readString();
        this.reqData = temp.readString();
        this.respData = temp.readString();

    }

    public int parseData() {
        return 0;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public int getReqType() {
        return reqType;
    }

    public void setReqType(int reqType) {
        this.reqType = reqType;
    }

    public String getReqData() {
        return reqData;
    }

    public void setReqData(String reqData) {
        this.reqData = reqData;
    }

    public String getRespData() {
        return respData;
    }

    public void setRespData(String respData) {
        this.respData = respData;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(this.reqType);
        dest.writeInt(this.status);
        dest.writeInt(this.method);
        dest.writeString(this.url);
        dest.writeString(this.reqData);
        dest.writeString(this.respData);

    }

    public static final Parcelable.Creator<HTTPRequest> CREATOR = new Parcelable.Creator<HTTPRequest>() {

        @Override
        public HTTPRequest createFromParcel(Parcel source) {
            return new HTTPRequest(source); // RECREATE VENUE GIVEN SOURCE
        }

        @Override
        public HTTPRequest[] newArray(int size) {
            return new HTTPRequest[size]; // CREATING AN ARRAY OF VENUES
        }

    };
}
