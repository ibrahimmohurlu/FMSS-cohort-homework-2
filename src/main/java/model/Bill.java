package model;

import java.util.List;

public class Bill extends BaseModel {
    private final Double total;

    public Bill(List<Product> productList) {
        this.total = productList.stream().mapToDouble(Product::getPrice).sum();
    }

    public Double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "total=" + total +
                '}';
    }
}
