package com.promobileapp.chiasenhac.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Banner {
    public String album;
    public String url;
    public String thumbNail;
    public String single;
    public String quality;

    public Banner(String album, String url, String thumb, String single, String quality) {
        this.album = album;
        this.url = url;
        this.thumbNail = thumb;
        this.single = single;
        this.quality = quality;
    }
}
