package tmall.bean;

import tmall.dao.OrderDAO;

import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private String orderCode;
    private String address;
    private String post;
    private String receiver;
    private String mobile;
    private String userMessage;
    private Date createDate;
    private Date payDate;
    private Date deliverDate;
    private Date confirmDate;
    private User user;
    private List<OrderItem> orderItems;
    private float total;
    private int totalNumber;
    private String status;

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getAddress () {
        return address;
    }

    public void setAddress (String address) {
        this.address = address;
    }

    public String getPost () {
        return post;
    }

    public void setPost (String post) {
        this.post = post;
    }

    public String getMobile () {
        return mobile;
    }

    public void setMobile (String mobile) {
        this.mobile = mobile;
    }

    public String getUserMessage () {
        return userMessage;
    }

    public void setUserMessage (String userMessage) {
        this.userMessage = userMessage;
    }

    public Date getCreateDate () {
        return createDate;
    }

    public void setCreateDate (Date createDate) {
        this.createDate = createDate;
    }

    public Date getPayDate () {
        return payDate;
    }

    public void setPayDate (Date payDate) {
        this.payDate = payDate;
    }

    public Date getDeliveryDate () {
        return deliverDate;
    }

    public void setDeliveryDate (Date deliveryDate) {
        this.deliverDate = deliveryDate;
    }

    public Date getConfirmDate () {
        return confirmDate;
    }

    public void setConfirmDate (Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getReceiver () {
        return receiver;
    }

    public void setReceiver (String receiver) {
        this.receiver = receiver;
    }

    public String getOrderCode () {
        return orderCode;
    }

    public void setOrderCode (String orderCode) {
        this.orderCode = orderCode;
    }

    public User getUser () {
        return user;
    }

    public void setUser (User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems () {
        return orderItems;
    }

    public void setOrderItems (List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public float getTotal () {
        return total;
    }

    public void setTotal (float total) {
        this.total = total;
    }

    public String getStatus () {
        return status;
    }

    public void setStatus (String status) {
        this.status = status;
    }

    public int getTotalNumber () {
        return totalNumber;
    }

    public void setTotalNumber (int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getStatusDesc (){
        String desc ="未知";
        switch(status){
          case OrderDAO.waitPay:
              desc="待付款";
              break;
          case OrderDAO.waitDelivery:
              desc="待发货";
              break;
          case OrderDAO.waitConfirm:
              desc="待收货";
              break;
          case OrderDAO.waitReview:
              desc="等评价";
              break;
          case OrderDAO.finish:
              desc="完成";
              break;
          case OrderDAO.delete:
              desc="刪除";
              break;
          default:
              desc="未知";
        }
        return desc;
    }
}
