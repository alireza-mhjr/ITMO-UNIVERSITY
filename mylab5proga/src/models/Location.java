package models;

public class Location {
    private long x;
    private double y;
    private String name; //Поле может быть null

    public Location(long x,double y,String name){
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public long getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{" +
                "\n\t\tx : " + x +
                "\n\t\ty : " + y +
                "\n\t\tname : '" + name + '\'' +
                "\n\t}";
    }
}
