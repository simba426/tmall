package tmall.bean;

public class User {
    private int id;
    private String name;
    private String password;

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

    public void setPassword (String password) {
        this.password = password;
    }

    public String getPassword () {
        return password;
    }

    public String getAnonymousName () {
        if (null == name)
            return null;

        if (name.length() <= 1)
            return "*";

        if (name.length() == 2)
            return name.substring(0, 1) + "*";

        char[] cs = name.toCharArray();
        for (int i = 1; i < name.length() - 1; i++)
            cs[i] = '*';
        return new String(cs);

    }
}
