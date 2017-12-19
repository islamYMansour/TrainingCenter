package com.example.islam.trainingcenter.DB.Tables;

/**
 * Created by islam on 12/7/2017.
 */

public class TAds implements TCenter {


    private String title;
    private String details;
    private byte[] imagePath;
    private int forignKeyID;

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }


    public byte[] getImagePath() {
        return imagePath;
    }

    public int getForignKeyID() {
        return forignKeyID;
    }
    private TAds(AdsBuilder builder) {
        this.title = builder.title;
        this.details = builder.details;
        this.imagePath = builder.imagePath;
        this.forignKeyID=builder.forignKeyID;
    }

    //Builder Class
    public static class AdsBuilder {
        private String details;
        private String title;
        private byte[] imagePath;
        private int forignKeyID;

        public AdsBuilder setDetails(String details) {
            this.details = details;
            return this;
        }

        public AdsBuilder setTitle(String title) {
            this.title = title;
            return this;
        }


        public AdsBuilder setForignKey(int forignKeyID) {
            this.forignKeyID = forignKeyID;
            return this;
        }

        public AdsBuilder setImagePath(byte[] imagePath) {
            this.imagePath = imagePath;
            return this;
        }

        public TAds build() {
            return new TAds(this);
        }

    }
}
