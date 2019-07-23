package tmall.bean;

public class Property {
    private int id;
    private String name;
    private Category category;

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

    public void setCategory (Category category) {
        this.category = category;
    }

    public Category getCategory () {
        return category;
    }

}
