package tmall.dao;

import tmall.bean.User;
import tmall.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public void add (User bean) {
        String sql = "insert into user values(null, ?, ?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, bean.getName());
            ps.setString(2, bean.getPassword());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                bean.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update (User bean) {
        String sql = "update user set password= ? where id= ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, bean.getPassword());
            ps.setInt(2, bean.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete (int id) {
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "delete from user where id= " + id;
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User get (int id) {
        User bean = null;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select * from user where id= " + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                bean = new User();
                bean.setId(id);
                bean.setName(rs.getString("name"));
                bean.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public int getTotal () {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select count(*) from user";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next())
                total = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public List<User> list () {
        return list(0, Short.MAX_VALUE);
    }

    public List<User> list (int start, int count) {
        List<User> beans = new ArrayList<>();
        String sql = "select * from user order by id desc limit ?,?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User bean = new User();
                bean.setId(rs.getInt(1));
                bean.setName(rs.getString("name"));
                bean.setPassword(rs.getString("password"));
                beans.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }

    public User get (String name) {
        User bean = null;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select * from user where name= " + name;
            ResultSet rs =s.executeQuery(sql);
            if (rs.next()) {
                bean = new User();
                bean.setId(rs.getInt("id"));
                bean.setName(name);
                bean.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public User get (String name, String password) {
        User bean = null;
        String sql = "select * from user where name= ? and password= ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bean = new User();
                bean.setId(rs.getInt("id"));
                bean.setName(name);
                bean.setPassword(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public boolean isExist (String name) {
        User user = get(name);
        return user != null;
    }

}
