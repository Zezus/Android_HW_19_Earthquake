
package com.example.HW_19_Earthquake;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EarthquakesBase implements Parcelable {

    public final static Parcelable.Creator<EarthquakesBase> CREATOR = new Creator<EarthquakesBase>() {


        @SuppressWarnings({
                "unchecked"
        })
        public EarthquakesBase createFromParcel(Parcel in) {
            return new EarthquakesBase(in);
        }

        public EarthquakesBase[] newArray(int size) {
            return (new EarthquakesBase[size]);
        }

    };
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;
    @SerializedName("features")
    @Expose
    private List<Feature> features = null;
    @SerializedName("bbox")
    @Expose
    private List<Double> bbox = null;

    protected EarthquakesBase(Parcel in) {
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.metadata = ((Metadata) in.readValue((Metadata.class.getClassLoader())));
        in.readList(this.features, (com.example.HW_19_Earthquake.Feature.class.getClassLoader()));
        in.readList(this.bbox, (java.lang.Double.class.getClassLoader()));
    }

    public EarthquakesBase() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public List<Double> getBbox() {
        return bbox;
    }

    public void setBbox(List<Double> bbox) {
        this.bbox = bbox;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(type);
        dest.writeValue(metadata);
        dest.writeList(features);
        dest.writeList(bbox);
    }

    public int describeContents() {
        return 0;
    }

}
