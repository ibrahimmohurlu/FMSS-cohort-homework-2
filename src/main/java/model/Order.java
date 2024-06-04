package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order extends BaseModel {
    private final LocalDate createDate;
    private List<Product> products;
    private Bill bill;

    public Order() {
        createDate = LocalDate.now();
        products = new ArrayList<>();
    }

    public Order(List<Product> products) {
        this.createDate = LocalDate.now();
        this.products = products;
        this.bill = new Bill(products);
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Bill getBill() {
        return bill;
    }

    @Override
    public String toString() {
        return "Order{" +
                "createDate=" + createDate +
                ", products=" + products +
                '}';
    }
}
