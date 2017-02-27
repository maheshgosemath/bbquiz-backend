<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <!-- <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" /> -->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title><tiles:insertAttribute name="title" ignore="true" /> </title>
    <tiles:insertAttribute name="commonscript" />
</head>
<body>
<div>
    <tiles:insertAttribute name="header" ignore="true"/>
</div>
<div>
    <tiles:insertAttribute name="body" />
</div>
<div>
    <tiles:insertAttribute name="footer" ignore="true"/>
</div>
</body>
</html>