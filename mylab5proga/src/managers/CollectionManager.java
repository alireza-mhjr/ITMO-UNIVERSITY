package managers;

import models.Product;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CollectionManager {
    private HashSet<Long> ids;
    private Hashtable<Integer, Product> collection;
    private final ZonedDateTime creationDate;
    private HashSet<String> uniquePartNumber;

    public CollectionManager() {
        creationDate = ZonedDateTime.now();
        ids = new HashSet<>();
        collection = new Hashtable<>();
        uniquePartNumber = new HashSet<>();
    }

    public HashSet<String> getUniquePartNumber() {
        return uniquePartNumber;
    }

    public void setCollection(Hashtable<Integer, Product> collection) {
        this.collection = collection;
    }

    public void setIds(HashSet<Long> ids) {
        this.ids = ids;
    }

    public HashSet<Long> getIds() {
        return ids;
    }

    public Hashtable<Integer, Product> getCollection() {
        return collection;
    }

    public boolean insert(Integer key, Product product) {
        collection.put(key, product);
        return true;
    }
    public Long generateId() {
        Long id;
        Random random = new Random();
        do {
            id = random.nextLong(1, Long.MAX_VALUE);
        } while (ids.contains(id));
        ids.add(id);
        return id;
    }


    public String collectionInfo() {
        return "Hashtable (Integer , Product) of Products       size : " + collection.size() + "       Creation Date : " + creationDate;
    }

    public boolean update(Long id, Product newProduct) {
        AtomicInteger key = new AtomicInteger();
        collection.forEach((key2, product2) -> {
            if (Objects.equals(product2.getId(), id)) {
                key.set(key2);
            }
        });
        Product oldProduct = collection.get(key.get());
        newProduct.setId(id);
        return collection.replace(key.get(), oldProduct, newProduct);
    }

    public boolean remove_key(Integer key) {
        boolean status = false;
        if (!collection.containsKey(key)) return status;
        collection.remove(key);
        return !status;
    }

    public void clear() {
        collection.clear();
        ids.clear();
    }

    public boolean replace_if_lowe(Integer key, Product product) {
        boolean status = false;
        Comparable<Product> comparable = (product2) -> Integer.compare(product2.getPrice(), product.getPrice());
        if (comparable.compareTo(collection.get(key)) > 0) {
            collection.replace(key, product);
            status = true;
        }
        return status;
    }

    public boolean remove_lower_key(Integer key) {
        return collection.keySet().removeIf(key1 -> key1 < key);
    }

    public Product max_by_unit_of_measure() {
        if (!collection.isEmpty()) {
            AtomicReference<Product> maxUnitOfMeasure = new AtomicReference<>();
            Enumeration<Integer> keys = collection.keys();
            do {
                maxUnitOfMeasure.set(collection.get(keys.nextElement()));
            } while (maxUnitOfMeasure.get().getUnitOfMeasure() == null && keys.hasMoreElements());
            collection.forEach((key, product) -> {
                if (product.getUnitOfMeasure() != null &&
                        Integer.compare(product.getUnitOfMeasure().getValue(), maxUnitOfMeasure.get().getUnitOfMeasure().getValue()) > 0)
                    maxUnitOfMeasure.set(product);
            });
            return maxUnitOfMeasure.get().getUnitOfMeasure() == null ? null : maxUnitOfMeasure.get();
        } else return null;
    }

    public ArrayList<Product> filter_contains_part_number(String partNumber) {
        ArrayList<Product> filter = new ArrayList<>();
        collection.forEach((key, product) -> {
            if (product.getPartNumber().contains(partNumber))
                filter.add(product);
        });

        return filter;
    }

    public ArrayList<Product> filter_starts_with_name(String name) {
        ArrayList<Product> filter = new ArrayList<>();
        Pattern pattern = Pattern.compile("^" + name);
        AtomicReference<Matcher> matcher = new AtomicReference<>();
        collection.forEach((key, product) -> {
            matcher.set(pattern.matcher(product.getName()));
            if (matcher.get().find())
                filter.add(product);
        });
        return filter;
    }

}








