package models;

public class Coordinates {
    private Integer x; //Поле не может быть null
    private Integer y; //Значение поля должно быть больше -802, Поле не может быть null

    public Coordinates(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    /**
     * Coordinate display
     */
    @Override
    public String toString() {
        return "coordinates : {" +
                "\n\tx : " + x +
                "\n\ty : " + y +
                "\n}";
    }

    public boolean validate() {
        return x != 0 && y != 0 && y > -802;
    }
}
