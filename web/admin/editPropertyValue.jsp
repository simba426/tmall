<%--
  Created by IntelliJ IDEA.
  User: simba
  Date: 2019-08-03
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<html>
<head>
    <title>编辑产品属性值</title>
    <script>
        $(function () {
            $("input.pvValue").keyup(function () {
                var value = $(this).val();
                var page = "admin_product_updatePropertyValue";
                var pvid = $(this).attr("pvid");
                var parentSpan = $(this).parent("span");
                parentSpan.css("border", "1px solid yellow");
                $.post (
                    page,
                    {"value":value,"pvid":pvid},
                    function (result) {
                        if (result == "success")
                            parentSpan.css("border", "1px solid green");
                        else
                            parentSpan.css("border", "1px solid red");
                    }
                );
            });
        });
    </script>
</head>

<body>
<div class="workingArea">
    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有分类</a></li>
        <li><a href="admin_product_list?cid=${p.category.id}">${p.category.name}</a></li>
        <li class="active">${p.name}</li>
        <li class="active">编辑产品属性</li>
    </ol>

    <div class="editPVDiv">
        <c:forEach items="${pvs}" var="pv">
            <div class="eachPV">
                <span class="pvName">${pv.property.name}</span>
                <span class="pvValue"><input class="pvValue" pvid="${pv.id}"
                    type="text" value="${pv.value}"></span>
            </div>
        </c:forEach>
        <div style="clear:both"></div>
    </div>
</div>
</body>
</html>
