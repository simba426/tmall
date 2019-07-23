package tmall.bean;

import java.util.List;

public class Category {
    private int id;
    private String name;
    List<Product> products;
    List<List<Product>> productsByRow;

    public void setId (int id) {
        this.id = id;
    }

    public int getId () {
        return id;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getName () {
        return name;
    }

    public void setProducts (List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts () {
        return products;
    }

    public void setProductsByRow (List<List<Product>> productsByRow) {
        this.productsByRow = productsByRow;
    }

    public List<List<Product>> getProductsByRow () {
        return productsByRow;
    }

    @Override
    public String toString() {
        return "Category:[name=" + name + "]";
    }
}

