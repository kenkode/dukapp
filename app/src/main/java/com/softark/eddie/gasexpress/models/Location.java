package com.softark.eddie.gasexpress.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Eddie on 4/30/2017.
 */

public class Location implements Parcelable {

    private String id;
    private double lng;
    private double lat;
    private String address;
    private int type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeDouble(this.lng);
        dest.writeDouble(this.lat);
        dest.writeString(this.address);
        dest.writeInt(this.type);
    }

    public Location() {
    }

    protected Location(Parcel in) {
        this.id = in.readString();
        this.lng = in.readDouble();
        this.lat = in.readDouble();
        this.address = in.readString();
        this.type = in.readInt();
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel source) {
            return new Location(source);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}