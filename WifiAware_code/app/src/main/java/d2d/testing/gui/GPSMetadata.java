package d2d.testing.gui;

import android.location.Location;
import android.media.ExifInterface;

public class GPSMetadata {
    private String mLatitude;
    private String mLongitude;
    private String mLatitudeRef;
    private String mLongitudeRef;

    public GPSMetadata(){
        mLatitude = mLongitude = mLatitudeRef = mLongitudeRef = "IDK";
    }

    public GPSMetadata(Location loc){
        mLatitude = String.valueOf(loc.getLatitude());
        mLongitude = String.valueOf(loc.getLongitude());
    }

    public GPSMetadata setLatitude(String lat) {
        this.mLatitude = lat;
        return this;
    }

    public GPSMetadata setLongitude(String lon) {
        this.mLongitude = lon;
        return this;
    }
/*
    public GPSMetadata setLatitudeRef(String latRef) {
        this.mLatitudeRef = latRef;
        return this;
    }

    public GPSMetadata setLongitudeRef(String lonRef) {
        this.mLongitudeRef = lonRef;
        return this;
    }

 */

    public String toString(){
        String res = mLatitude + ", "
                + mLongitude;
        return res;
    }
}
