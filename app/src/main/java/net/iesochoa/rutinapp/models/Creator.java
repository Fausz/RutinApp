package net.iesochoa.rutinapp.models;

import android.net.Uri;

import com.google.firebase.firestore.GeoPoint;

public class Creator {

    private String center;
    private String course;
    private String description;
    private String name;
    private String subname;
    private String num;
    private String address;
    private GeoPoint location;

    public Creator(){
        /**
         * CONSTRUCTOR POR DEFECTO
         */
    }

    public Creator(String center, String course, String description, String name, String subname, String num, String address, GeoPoint location) {
        /**
         * CONSTRUCTOR CON PARÁMETROS
         */
        this.center = center;
        this.course = course;
        this.description = description;
        this.name = name;
        this.subname = subname;
        this.num = num;
        this.address = address;
        this.location = location;
    }

    //GETTER Y SETTER
    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }


}
