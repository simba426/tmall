package tmall.dao;

import tmall.bean.Category;
import tmall.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    public void add (Category bean) {
        String sql = "insert into category values(null, ?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, bean.getName());
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

    public void update (Category bean) {
        String sql = "update category set name= ? where id= ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, bean.getName());
            ps.setString(2, String.valueOf(bean.getId()));

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete (int id) {
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "delete from category where id= " + id;
            s.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Category get (int id) {
        Category bean = null;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select * from category where id= " + id;
            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                bean = new Category();
                bean.setId(id);
                bean.setName(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bean;
    }

    public List<Category> list () {
        return list(0, Short.MAX_VALUE);
    }

    public List<Category> list(int start, int count) {
        List<Category> beans = new ArrayList<Category>();
        String sql = "select * from category order by id desc limit ?,?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category bean = new Category();
                bean.setId(rs.getInt(1));
                bean.setName(rs.getString(2));
                beans.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }

    public int getTotal () {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select count(*) from category";
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
}
