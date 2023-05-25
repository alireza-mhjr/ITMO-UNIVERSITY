package managers;


import exceptions.InvalidDataException;
import models.*;

import java.time.*;
import java.util.ArrayList;
import java.util.Scanner;

public class InputDataManager {
    private final Scanner input;
    private final CollectionManager collectionManager;

    public InputDataManager(CollectionManager collectionManager) {
        input = new Scanner(System.in);
        this.collectionManager = collectionManager;
    }

    public String readNameOfProduct() {
        String name;
        for (; ; ) {
            System.out.println("Enter name of the product (should not be null) : ");
            name = input.nextLine();
            if (!name.equals("")) break;
            else System.err.println("Name can't be empty");
        }
        return name;
    }

    public Integer readXCoordinate() {
        String x1;
        int x;
        for (; ; ) {
            System.out.println("Enter coordinate x (Should not be null ): ");
            x1 = input.nextLine();
            if (!x1.equals("")) {
                try {
                    x = Integer.parseInt(x1);
                    break;
                } catch (NumberFormatException exception) {
                    System.err.println("should be a number !!!");
                }
            } else System.err.println("can't be empty !!!");
        }
        return x;
    }

    public Integer readYCoordinate() {
        String y1;
        int y;
        for (; ; ) {
            System.out.println("Enter coordinate y (Must be greater than -802 and can't be null ): ");
            y1 = input.nextLine();
            if (!y1.equals("")) {
                try {
                    y = Integer.parseInt(y1);
                    if (-802 > y) {
                        System.err.println("should be greater than -802");
                        continue;
                    }
                    break;
                } catch (NumberFormatException exception) {
                    System.err.println("should be a number !!!");
                }
            } else System.err.println("can't be empty !!!");
        }
        return y;
    }


    public int readPrice() {
        String number;
        int price = 0;
        for (; ; ) {
            System.out.println("Enter price of the product in DOLLARS $ (should be greater than 0) : ");
            number = input.nextLine();
            try {
                price = Integer.parseInt(number);
                if (price <= 0) {
                    System.err.println("should be greater than 0 !!!");
                } else break;
            } catch (NumberFormatException exception) {
                System.err.println("should be a number !!!");
            }
        }
        return price;
    }

    public String readPartNumber() {
        String partNumber;
        for (; ; ) {
            System.out.println("Enter a String as partNumber of the product: ");
            partNumber = input.nextLine();
            if (!partNumber.equals("")) {
                if (collectionManager.getUniquePartNumber().contains(partNumber)) {
                    System.err.println("This part number already exists");
                } else break;
            } else System.err.println("can't be empty !!!");
        }
        return partNumber;
    }

    public float readManufactureCost() {
        String number;
        float cost = 0.0f;
        for (; ; ) {
            System.out.println("Enter a Float as a manufactureCost in DOLLARS $ : ");
            number = input.nextLine();
            try {
                cost = Float.parseFloat(number);
                return cost;
            } catch (NumberFormatException exception) {
                System.err.println("should be a number !!!");
            }
        }
    }

    public UnitOfMeasure readUnitOfMeasure() {
        UnitOfMeasure unitOfMeasure = null;
        String unit;
        for (; ; ) {
            try {
                System.out.println("Enter unit of Measure (METERS, CENTIMETERS, SQUARE_METERS, LITERS, GRAMS) : ");
                unit = input.nextLine();
                if (unit.equals("")) return null;
                if (!unit.equalsIgnoreCase("METERS") && !unit.equalsIgnoreCase("CENTIMETERS") && !unit.equalsIgnoreCase("SQUARE_METERS") && !unit.equalsIgnoreCase("LITERS") && !unit.equalsIgnoreCase("GRAMS"))
                    throw new IllegalArgumentException("Invalid Input, try again !!!");
                else {
                    unitOfMeasure = UnitOfMeasure.valueOf(unit.toUpperCase());
                    return unitOfMeasure;
                }
            } catch (IllegalArgumentException exception) {
                System.err.println(exception.getMessage());
            }
        }

    }

    public String readOwnerName() {
        String name;
        for (; ; ) {
            System.out.println("Enter name of owner (should not be null) : ");
            name = input.nextLine();
            if (!name.equals("")) break;
            else System.err.println("Name can't be empty");
        }
        return name;
    }

    public ZonedDateTime readPersonBirthday() {
        for (; ; ) {
            System.out.println("Enter owner's birthday in format YYYY-MM-DD (can be empty) : ");
            String birthday = input.nextLine();
            if (birthday.equals("")) return null;
            else {
                String[] spilit = birthday.split("-");
                try {
                    int year = Integer.parseInt(spilit[0]);
                    int month = Integer.parseInt(spilit[1]);
                    int day = Integer.parseInt(spilit[2]);
                    LocalDate localDate = LocalDate.of(year, month, day);
                    LocalTime localTime = LocalTime.now();
                    ZoneId zoneId = ZoneId.systemDefault();
                    return ZonedDateTime.of(LocalDateTime.of(localDate, localTime), zoneId);
                } catch (NumberFormatException | IndexOutOfBoundsException | DateTimeException exception) {
                    System.err.println("Invalid BirthDay!!!");
                }

            }
        }
    }

    public long readPersonHeight() {
        String number;
        long height;
        for (; ; ) {
            System.out.println("Enter owner's height into cm: ");
            number = input.nextLine();
            if (number.equals("")) {
                System.err.println("can't be empty");
                continue;
            }
            try {
                height = Long.parseLong(number);
                if (height <= 0) System.err.println("should be greater than 0 ");
                else return height;
            } catch (NumberFormatException exception) {
                System.err.println("should be number ");
            }
        }
    }

    public Color readPersonHairColor() {
        String hairColor;
        for (; ; ) {
            System.out.println("Enter owner's hair Color (GREEN, RED, BLACK, ORANGE, BROWN) : ");
            hairColor = input.nextLine();
            if (hairColor.equals("")) return null;
            else {
                try {
                    if (!hairColor.equalsIgnoreCase("green") && !hairColor.equalsIgnoreCase("red") && !hairColor.equalsIgnoreCase("black") && !hairColor.equalsIgnoreCase("orange") && !hairColor.equalsIgnoreCase("brown")) {
                        System.err.println("Invalid hair color !!!");
                    } else {
                        return Color.valueOf(hairColor.toUpperCase());
                    }
                } catch (IllegalArgumentException exception) {
                    System.err.println("Invalid hair color !!!");
                }
            }
        }
    }

    public long readLocationX() {
        for (; ; ) {
            System.out.println("Enter owner's location x : ");
            String number = input.nextLine();
            if (number.equals("")) {
                System.err.println("can't be empty");
                continue;
            }
            try {
                return Long.parseLong(number);
            } catch (NumberFormatException exception) {
                System.err.println("Invalid location x !!!");
            }
        }
    }

    public double readLocationY() {
        for (; ; ) {
            System.out.println("Enter owner's location y : ");
            String number = input.nextLine();
            if (number.equals("")) {
                System.err.println("can't be empty");
                continue;
            }
            try {
                return Double.parseDouble(number);
            } catch (NumberFormatException exception) {
                System.err.println("Invalid location y !!!");
            }
        }
    }

    public String readLocationName() {
        System.out.println("Enter owner's location name (can be empty): ");
        String locationName = input.nextLine();
        if (locationName.equals("")) return null;
        else {
            return locationName;
        }
    }

    public Coordinates readCoordinates() {
        return new Coordinates(readXCoordinate(), readYCoordinate());
    }

    public Location readPersonLocation() {
        return new Location(readLocationX(), readLocationY(), readLocationName());
    }

    public Person readPerson() {
        for (; ; ) {
            System.out.println("Do you want add an owner? (\"yes\" or \"no\")");
            String answer = input.nextLine();
            if (answer.equals("yes"))
                return new Person(readOwnerName(), readPersonBirthday(), readPersonHeight(), readPersonHairColor(), readPersonLocation());
            else if (answer.equals("no")) return null;
            else System.err.println("answer is not valid, try again !!!\n");
        }
    }

    public Product readUserProduct() {
        return new Product(readNameOfProduct(), readCoordinates(), readPrice(), readPartNumber(), readManufactureCost(), readUnitOfMeasure(), readPerson());
    }

    public Product getNonUserProduct(ArrayList<String> lines) {
        if (lines.size() < 6)
            throw new InvalidDataException("There's NOT ENOUGH DATA in the file to create new object of product !!!");
        if(lines.size() <8){
            lines.add("");
            lines.add("");
        }
        class GetProduct {
            static int index ;
            Product product = null;
            private GetProduct() {
                index = 0;
                try {
                    System.out.print("name of product > ");
                    String nameOfProduct = lines.get(index);
                    System.out.println(nameOfProduct);

                    Coordinates coordinates = getCoordinates(lines.get(++index),lines.get(++index));

                    System.out.print("price of product > ");
                    int price = Integer.parseInt(lines.get(++index));
                    System.out.println(price);

                    System.out.print("part number of product > ");
                    String partNumber = lines.get(++index);
                    System.out.println(partNumber);

                    System.out.print("manufacture cost of product > ");
                    float manufactureCost = Float.parseFloat(lines.get(++index));
                    System.out.println(manufactureCost);

                    System.out.print("unit of measure of product > ");
                    UnitOfMeasure unitOfMeasure = getUnitOfMeasure(lines.get(++index));
                    System.out.println(unitOfMeasure==null? "null":unitOfMeasure);

                    Person owner = getOwner(lines);
                    product = new Product(nameOfProduct, coordinates, price, partNumber, manufactureCost, unitOfMeasure, owner);
                    product.setId(collectionManager.generateId());
                    System.out.println("Id of PRODUCT : "+product.getId());
                    setIndex(index);
                }catch (InvalidDataException | ArrayIndexOutOfBoundsException | IllegalArgumentException | DateTimeException exception){
                    throw new InvalidDataException();
                }
            }

            private Coordinates getCoordinates(String xcoor,String ycoor) {
                try {
                    System.out.print("coordinate \"x\" of product > ");
                    int x = Integer.parseInt(xcoor);
                    System.out.println(x);

                    System.out.print("coordinate \"y\" of product > ");
                    int y = Integer.parseInt(ycoor);
                    System.out.println(y);

                    return new Coordinates(x, y);
                } catch (NumberFormatException exception) {
                    throw new InvalidDataException();
                }
            }

            private UnitOfMeasure getUnitOfMeasure(String line) {
                return !line.equals("") ? UnitOfMeasure.valueOf(line.toUpperCase()) : null;
            }

            private Person getOwner(ArrayList<String> lines) {
                Person owner;
                if (!lines.get(++index).equals("")) {

                    System.out.print("name of owner > ");
                    String nameOfOwner = lines.get(index);
                    System.out.println(nameOfOwner);

                    System.out.print("birthday of owner > ");
                    ZonedDateTime birthday = getBirthday(lines.get(++index));
                    System.out.println(birthday==null ? "null":birthday);

                    System.out.print("height of owner > ");
                    long heightOfOwner = Long.parseLong(lines.get(++index));
                    System.out.println(heightOfOwner);

                    System.out.print("hair color of owner > ");
                    Color hairColor = getHairColor(lines.get(++index));
                    System.out.println(hairColor==null? "null" : hairColor);

                    Location locationOfOwner = getLocationOfOwner(new String[]{lines.get(++index), lines.get(++index), lines.get(++index)});

                    owner = new Person(nameOfOwner, birthday, heightOfOwner, hairColor, locationOfOwner);
                } else {
                    owner = null;
                    System.out.println("owner : null");
                }
                return owner;
            }
            private Location getLocationOfOwner(String[] lines){
                System.out.print("\"x\" location of owner > ");
                long x = Long.parseLong(lines[0]);
                System.out.println(x);

                System.out.print("\"y\" location of owner > ");
                double y = Double.parseDouble(lines[1]);
                System.out.println(y);

                System.out.print("name location of owner > ");
                String nameOfLocation = lines[2];
                System.out.println(nameOfLocation);

                return new Location(x,y,nameOfLocation);
            }

            private ZonedDateTime getBirthday(String line) {
                if(line.equals("")) return null;
                String[] spilit = line.split("-");
                int year = Integer.parseInt(spilit[0]);
                int month = Integer.parseInt(spilit[1]);
                int day = Integer.parseInt(spilit[2]);
                LocalDate localDate = LocalDate.of(year, month, day);
                LocalTime localTime = LocalTime.now();
                ZoneId zoneId = ZoneId.systemDefault();
                return ZonedDateTime.of(LocalDateTime.of(localDate, localTime), zoneId);
            }

            private Color getHairColor(String line) {
                return line.equals("") ? null : Color.valueOf(lines.get(++index).toUpperCase());
            }
        }

        return new GetProduct().product;
    }
    private int index;

    public void setIndex(int index) {
        this.index = index;
    }
    public int getIndex() {
        int i = index;
        setIndex(0);
        return i;
    }
}
