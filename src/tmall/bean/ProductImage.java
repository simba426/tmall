package tmall.bean;

public class ProductImage {
    private int id;
    private String type;
    Product product;

    public void setId (int id) {
        this.id = id;
    }

    public int getId () {
        return id;
    }

    public void setType (String type) {
        this.type = type;
    }

    public String getType () {
        return type;
    }

    public void setProduct (Product product) {
        this.product = product;
    }

    public Product getProduct () {
        return product;
    }

}
