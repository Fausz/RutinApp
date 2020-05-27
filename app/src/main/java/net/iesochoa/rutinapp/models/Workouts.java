package net.iesochoa.rutinapp.models;

public class Workouts {

    //VARIABLES DE WORKOUTS
    private String name;
    private String group;
    private String img;
    private String description;



    public Workouts(){
        /**
         * MÉTIDO VACÍO PARA FIREBASE
         */
    }

    public Workouts(String name, String group, String description, String img){
        /**
         * CONSTRUCTOR DE WORKOUTS
         */
        this.name=name;
        this.group=group;
        this.description=description;
        this.img=img;
    }

    public Workouts(String name, String group, String description){
        this.name=name;
        this.group=group;
        this.description=description;
    }
    //GETTERS Y SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() { return group; }

    public void setGroup(String group) { this.group = group; }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
