package models;


import java.io.Serializable;
import java.time.ZonedDateTime;

public class Product implements  Serializable {
    private Long id;  //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate ; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int price; //Значение поля должно быть больше 0
    private String partNumber; //Строка не может быть пустой, Значение этого поля должно быть уникальным, Поле может быть null
    private float manufactureCost;
    private UnitOfMeasure unitOfMeasure; //Поле может быть null
    private Person owner; //Поле может быть null



    public Product( String name, Coordinates coordinates
                   ,int price, String partNumber, float manufactureCost, UnitOfMeasure unitOfMeasure, Person owner) {

        this.name = name;
        this.coordinates = coordinates;
        this.price = price;
        this.partNumber = partNumber;
        this.manufactureCost = manufactureCost;
        this.unitOfMeasure = unitOfMeasure;
        this.owner = owner;
        creationDate = ZonedDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public int getPrice() {
        return price;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public float getManufactureCost() {
        return manufactureCost;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public Person getOwner() {
        return owner;
    }

    public boolean validate() {
        return  (id > 0) &&
                (name != null && !name.equals("")) &&
                (coordinates != null && coordinates.validate()) &&
                (creationDate!=null) &&
                (price > 0) &&
                (partNumber==null || (partNumber!=null && !partNumber.equals(""))) &&
                (owner==null || (owner!=null && owner.validate()));
    }

    @Override
    public String toString() {
        var output = "id: " + id + "\n" +
                "name: " + name + "\n" +
                coordinates + "\n" +
                "creation date: " + creationDate + "\n" +
                "price: " + price + "\n" +
                "part Number: " + partNumber + "\n" +
                "manufacture cost: " + manufactureCost+ "\n"+
                "unit of measure: " + unitOfMeasure+ "\n"+
                "owner: " + owner;
        return output;
    }
}
