package com.ashish.workindia.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Items implements Parcelable {
    private String name;

    private String price;

    private String extra;

    protected Items(Parcel in) {
        name = in.readString();
        price = in.readString();
        extra = in.readString();
    }

    public static final Creator<Items> CREATOR = new Creator<Items>() {
        @Override
        public Items createFromParcel(Parcel in) {
            return new Items(in);
        }

        @Override
        public Items[] newArray(int size) {
            return new Items[size];
        }
    };

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setPrice(String price){
        this.price = price;
    }
    public String getPrice(){
        return this.price;
    }
    public void setExtra(String extra){
        this.extra = extra;
    }
    public String getExtra(){
        return this.extra;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(extra);
    }
}