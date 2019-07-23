package tmall.bean;

import java.util.Date;

public class Review {
    private int id;
    private Product product;
    private User user;
    private String content;
    private Date createDate;

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getContent () {
        return content;
    }

    public void setContent (String content) {
        this.content = content;
    }

    public Date getCreateDate () {
        return createDate;
    }

    public void setCreateDate (Date createDate) {
        this.createDate = createDate;
    }

    public User getUser () {
        return user;
    }

    public void setUser (User user) {
        this.user = user;
    }

    public Product getProduct () {
        return product;
    }

    public void setProduct (Product product) {
        this.product = product;
    }

}
