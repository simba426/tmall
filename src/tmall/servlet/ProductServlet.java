package tmall.servlet;

import tmall.bean.Category;
import tmall.bean.Product;
import tmall.bean.PropertyValue;
import tmall.util.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ProductServlet extends BaseBackServlet {
    @Override
    public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
        int cid = Integer.parseInt(request.getParameter("cid"));
        Category c = categoryDAO.get(cid);

        String name = request.getParameter("name");
        float originalPrice = Float.parseFloat(request.getParameter("originalPrice"));
        float promotePrice = Float.parseFloat(request.getParameter("promotePrice"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        String subTitle = request.getParameter("subTitle");

        Product p = new Product();
        p.setCategory(c);
        p.setName(name);
        p.setOrignalPrice(originalPrice);
        p.setPromotePrice(promotePrice);
        p.setStock(stock);
        p.setSubTitle(subTitle);
        productDAO.add(p);
        return "@admin_product_list?cid=" + c.getId();
    }

    @Override
    public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
        int pid = Integer.parseInt(request.getParameter("id"));
        Product p = productDAO.get(pid);
        productDAO.delete(pid);
        return "@admin_product_list?cid=" + p.getCategory().getId();
    }

    @Override
    public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
        int pid = Integer.parseInt(request.getParameter("id"));
        int cid = Integer.parseInt(request.getParameter("cid"));

        Category c = categoryDAO.get(cid);
        Product p = new Product();

        int stock = Integer.parseInt(request.getParameter("stock"));
        float orignalPrice = Float.parseFloat(request.getParameter("orignalPrice"));
        float promotePrice = Float.parseFloat(request.getParameter("promotePrice"));
        String subTitle= request.getParameter("subTitle");
        String name= request.getParameter("name");

        p.setSubTitle(subTitle);
        p.setStock(stock);
        p.setPromotePrice(promotePrice);
        p.setOrignalPrice(orignalPrice);
        p.setName(name);
        p.setId(pid);
        p.setCategory(c);

        productDAO.update(p);
        return "@admin_product_list?cid=" + c.getId();
    }

    @Override
    public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
        int cid = Integer.parseInt(request.getParameter("cid"));
        Category c = categoryDAO.get(cid);
        List<Product> ps = productDAO.list(cid, page.getStart(), page.getCount());
        int total = productDAO.getTotal(cid);
        page.setTotal(total);
        page.setParam("&cid=" + c.getId());

        request.setAttribute("ps", ps);
        request.setAttribute("c", c);
        request.setAttribute("page", page);

        return "admin/listProduct.jsp";
    }

    @Override
    public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
        int pid = Integer.parseInt(request.getParameter("id"));
        Product p = productDAO.get(pid);
        request.setAttribute("p", p);
        return "admin/editProduct.jsp";
    }

    public String editPropertyValue (HttpServletRequest request, HttpServletResponse response, Page page) {
        int pid = Integer.parseInt(request.getParameter("id"));
        Product p = productDAO.get(pid);
        request.setAttribute("p", p);

        propertyValueDAO.init(p);
        List<PropertyValue> pvs = propertyValueDAO.list(p.getId());
        request.setAttribute("pvs", pvs);
        return "admin/editPropertyValue.jsp";
    }

    public String updatePropertyValue (HttpServletRequest request, HttpServletResponse response, Page page) {
        int pvid = Integer.parseInt(request.getParameter("pvid"));
        String value = request.getParameter("value");
        PropertyValue pv = propertyValueDAO.get(pvid);
        pv.setValue(value);
        propertyValueDAO.update(pv);
        return "%success";
    }


}
