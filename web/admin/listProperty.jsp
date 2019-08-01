<%--
  Created by IntelliJ IDEA.
  User: simba
  Date: 2019-08-01
  Time: 09:09
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<script>
    $(function () {
        $("#addForm").submit(function () {
            if (checkEmpty("name", "属性名称"))
                return true;
            return false;
        });
    });
</script>

<html>
<head>
    <title>属性管理</title>
</head>
<body>
<div class="workingArea">
    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有分类</a></li>
        <li><a href="admin_property_list?cid=${c.id}">${c.name}</a> </li>
        <li class="active">属性管理</li>
    </ol>

    <div class="listDataTableDiv">
        <table
            class="table table-bordered table-striped table-hover table-condensed">
            <thead>
            <tr class="success">
                <td>ID</td>
                <td>属性名称</td>
                <td>编辑</td>
                <td>删除</td>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${ps}" var="p">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.name}</td>
                    <td><a href="admin_property_edit?id=${p.id}"><span
                            class="glyphicon glyphicon-edit"></span></a></td>
                    <td><a href="admin_property_delete?id=${p.id}"><span
                            class="glyphicon glyphicon-trash"></span></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="pageDiv">
        <%@include file="../include/admin/adminPage.jsp"%>
    </div>

    <div class="panel panel-warning addDiv">
        <div class="panel-heading">新增属性</div>
        <div class="panel-body">
            <form method="post" id="addForm" action="admin_property_add">
                <table class="addTable">
                    <tr>
                        <td>属性名称</td>
                        <td><input id="name" name="name" type="text"
                                   class="form-control"></td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <input type="hidden" name="cid" value="${c.id}">
                            <button type="submit" class="btn btn-success">提 交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>

<%@include file="../include/admin/adminFooter.jsp"%>
</body>
</html>
