package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer extends BaseModel {
    private String name;
    private List<Order> orders;
    private LocalDate createDate;

    public Customer(String name) {
        this.name = name;
        this.orders = new ArrayList<>();
        this.createDate = LocalDate.now();
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", orders=" + orders +
                ", createDate=" + createDate +
                '}';
    }
}
