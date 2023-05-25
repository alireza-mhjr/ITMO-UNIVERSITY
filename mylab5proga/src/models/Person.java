package models;

import java.time.ZonedDateTime;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.ZonedDateTime birthday; //Поле может быть null
    private long height; //Значение поля должно быть больше 0
    private Color hairColor; //Поле может быть null
    private Location location; //Поле не может быть null

    public Person(String name, ZonedDateTime birthday, long height, Color hairColor, Location location) {

        this.name = name;
        this.birthday = birthday;
        this.height = height;
        this.hairColor = hairColor;
        this.location = location;
    }

    public String getName() {return name;}

    public ZonedDateTime getBirthday() {return birthday;}

    public long getHeight() {return height;}

    public Color getHairColor() {return hairColor;}

    public Location getLocation() {return location;}

    public boolean validate(){
        return (name != null && !name.equals("")) &&
                (birthday==null || (birthday!= null && !birthday.equals(""))) &&
                (height > 0) &&
                (location!=null );
    }

    @Override
    public String toString() {
        return "{" +
                "\n\tname : '" + name + '\'' +
                "\n\tbirthday : " + birthday +
                "\n\theight : " + height +
                "\n\thairColor : " + hairColor +
                "\n\tlocation : " + location +
                "\n}";
    }
}
