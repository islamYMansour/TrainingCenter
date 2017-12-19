package com.example.islam.trainingcenter.DB.Tables;

import java.io.Serializable;

/**
 * Created by islam on 12/7/2017.
 */

public class TCourse  implements TCenter,Serializable{


    private int pk_id;
    private String forirgn_key;
    private String name;
    private String date;
    private String place;
    private String hoursCount;
    private String details;
    private String courseTrainer;
    private String imagePath;
    private String price;

    public int getPk_id() {
        return pk_id;
    }

    public String getForirgn_key() {
        return forirgn_key;
    }

    public String getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }

    public String getHoursCount() {
        return hoursCount;
    }

    public String getDetails() {
        return details;
    }

    public String getCourseTrainer() {
        return courseTrainer;
    }

    public String getImagePath() {
        return imagePath;
    }


    public TCourse(CourseBuilder courseBuilder) {
        this.pk_id = courseBuilder.pk_id;
        this.forirgn_key = courseBuilder.forirgn_key;
        this.name = courseBuilder.name;
        this.date = courseBuilder.date;
        this.place = courseBuilder.place;
        this.hoursCount = courseBuilder.hoursCount;
        this.details = courseBuilder.details;
        this.courseTrainer = courseBuilder.courseTrainer;
        this.imagePath = courseBuilder.imagePath;
        this.price = courseBuilder.price;
    }

    public static class CourseBuilder {
        private int pk_id;
        private String forirgn_key;
        private String name;
        private String date;
        private String place;
        private String hoursCount;
        private String details;
        private String courseTrainer;
        private String imagePath;
        private String price;

        public CourseBuilder setPkID(int pkID) {
            this.pk_id = pkID;
            return this;
        }

        public CourseBuilder setForignKey(String forign) {
            this.forirgn_key = forign;
            return this;
        }

        public CourseBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public CourseBuilder setDate(String date) {
            this.date = date;
            return this;

        }

        public CourseBuilder setPlace(String place) {
            this.place = place;
            return this;

        }

        public CourseBuilder setPrice(String price) {
            this.price = price;
            return this;

        }

        public CourseBuilder setHoursCount(String hoursCount) {
            this.hoursCount = hoursCount;
            return this;

        }

        public CourseBuilder setDetails(String details) {
            this.details = details;
            return this;

        }

        public CourseBuilder setCourseTrainer(String courseTrainer) {
            this.courseTrainer = courseTrainer;
            return this;

        }

        public CourseBuilder setImagePath(String imagePath) {
            this.imagePath = imagePath;
            return this;

        }

        public TCourse build() {
            return new TCourse(this);
        }

    }

}
