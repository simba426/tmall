package tmall.dao;

import tmall.bean.Product;
import tmall.bean.Property;
import tmall.bean.PropertyValue;
import tmall.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyValueDAO {
    public int getTotal (int pid) {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select count(*) from propertyvalue where pid= " + pid;
            ResultSet rs = s.executeQuery(sql);
            while (rs.next())
                total = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public void add (PropertyValue bean) {
        String sql = "insert into propertyvalue values(null, ?, ?, ?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, bean.getProduct().getId());
            ps.setInt(2, bean.getProperty().getId());
            ps.setString(3, bean.getValue());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next())
                bean.setId(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update (PropertyValue bean) {
        String sql = "update propertyvalue set pid= ?, ptid=?, value= ? where id= ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, bean.getProduct().getId());
            ps.setInt(2, bean.getProperty().getId());
            ps.setString(3, bean.getValue());
            ps.setInt(4, bean.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete (int id) {
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "delete from propertyvalue where id= " + id;
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PropertyValue get (int id) {
        PropertyValue bean = null;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select * from propertyvalue where id= " + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                bean = new PropertyValue();
                bean.setId(id);
                bean.setProduct(new ProductDAO().get(rs.getInt("pid")));
                bean.setProperty(new PropertyDAO().get(rs.getInt("ptid")));
                bean.setValue(rs.getString("value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public PropertyValue get (int pid, int ptid) {
        PropertyValue bean = null;
        String sql = "select * from propertyvalue where pid= ? and ptid= ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, pid);
            ps.setInt(2, ptid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bean = new PropertyValue();
                bean.setId(rs.getInt("id"));
                bean.setValue(rs.getString("value"));
                bean.setProperty(new PropertyDAO().get(ptid));
                bean.setProduct(new ProductDAO().get(pid));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public List<PropertyValue> list () {
        return list(0, Short.MAX_VALUE);
    }

    public List<PropertyValue> list (int start, int count) {
        List<PropertyValue> beans = new ArrayList<>();
        String sql = "select * from propertyvalue order by id desc limit ?, ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, start);
            ps.setInt(2, count);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PropertyValue bean = new PropertyValue();
                bean.setId(rs.getInt("id"));
                bean.setValue(rs.getString("value"));
                bean.setProperty(new PropertyDAO().get(rs.getInt("ptid")));
                bean.setProduct(new ProductDAO().get(rs.getInt("pid")));
                beans.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }

    public List<PropertyValue> list (int pid) {
        List<PropertyValue> beans = new ArrayList<>();
        String sql = "select * from propertyvalue where pid= ? order by ptid desc";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, pid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PropertyValue bean = new PropertyValue();
                bean.setId(rs.getInt("id"));
                bean.setValue(rs.getString("value"));
                bean.setProduct(new ProductDAO().get(pid));
                bean.setProperty(new PropertyDAO().get(rs.getInt("ptid")));
                beans.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }

    public void init (Product p) {
        List<Property> pts = new PropertyDAO().list(p.getCategory().getId());
        for (Property pt : pts) {
            PropertyValue pv = get(p.getId(), pt.getId());
            if (null == pv) {
                pv = new PropertyValue();
                pv.setProduct(p);
                pv.setProperty(pt);
                this.add(pv);
            }
        }
    }

}
