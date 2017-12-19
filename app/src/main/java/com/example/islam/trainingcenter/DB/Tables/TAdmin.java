package com.example.islam.trainingcenter.DB.Tables;

/**
 * Created by islam on 12/7/2017.
 */

public class TAdmin implements TCenter {

    private String address;
    private String name;
    private String phone;
    private String email;
    private String password;
    private String imagePath;

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getImagePath() {
        return imagePath;
    }

    private TAdmin(AdminBuilder builder) {
        this.name = builder.name;
        this.password = builder.password;
        this.phone = builder.phone;
        this.email = builder.email;
        this.address = builder.address;
        this.imagePath = builder.imagePath;
    }

    //Builder Class
    public static class AdminBuilder {
        private String address;
        private String name;
        private String phone;
        private String email;
        private String password;
        private String imagePath;

        public AdminBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public AdminBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public AdminBuilder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public AdminBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public AdminBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public AdminBuilder setImagePath(String imagePath) {
            this.imagePath = imagePath;
            return this;
        }

        public TAdmin build() {
            return new TAdmin(this);
        }

    }
}
