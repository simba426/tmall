package tmall.bean;

public class OrderItem {
    private int id;
    private int number;
    private Product product;
    private User user;
    private Order order;

    public int getNumber () {
        return number;
    }

    public void setNumber (int number) {
        this.number = number;
    }

    public Product getProduct () {
        return product;
    }

    public void setProduct (Product product) {
        this.product = product;
    }

    public Order getOrder () {
        return order;
    }

    public void setOrder (Order order) {
        this.order = order;
    }

    public User getUser () {
        return user;
    }

    public void setUser (User user) {
        this.user = user;
    }

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

}
