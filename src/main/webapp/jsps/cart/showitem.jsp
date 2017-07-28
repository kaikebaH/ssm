<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>showitem.jsp</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css"
	href="<c:url value='/jsps/css/cart/showitem.css'/>">

<style type="text/css">
#addr {
	width: 500px;
	height: 32px;
	border: 1px solid #7f9db9;
	padding-left: 10px;
	line-height: 32px;
}
</style>
</head>

<body>
	<form id="form1" action="<c:url value='/orderAction/saveOrder.do'/>" method="post">
		<input type="hidden" name="cartItemIds" value="${cartItemIds }" />
		<table width="95%" align="center" cellpadding="0" cellspacing="0">
			<tr bgcolor="#efeae5">
				<td width="400px" colspan="5"><span style="font-weight: 900;">生成订单</span></td>
			</tr>
			<tr align="center">
				<td width="10%">&nbsp;</td>
				<td width="50%">图书名称</td>
				<td>单价</td>
				<td>数量</td>
				<td>小计</td>
			</tr>


			<c:forEach items="${clist }" var="c">
				<tr align="center">
					<td align="right"><a class="linkImage"
						href="<c:url value='/bookAction/getBookByBid.do?bid=${c.book.bid}'/>"><img
							border="0" width="54" align="top"
							src="<c:url value='/${c.book.image_b }'/>" /></a></td>
					<td align="left"><a
						href="<c:url value='/bookAction/getBookByBid?bid=${c.book.bid}'/>"><span>${c.book.bname }</span></a>
					</td>
					<td>&yen;${c.book.currPrice }</td>
					<td>${c.quantity }</td>
					<td><span class="price_n">&yen;<span class="subtotal">${c.subtotal }</span></span>
					</td>
				</tr>
			</c:forEach>


			<tr>
				<td colspan="6" align="right"><span>总计：</span><span
					class="price_t">&yen;<span id="total"></span></span></td>
			</tr>
			<tr>
				<td colspan="5" bgcolor="#efeae5"><span
					style="font-weight: 900">收货地址</span></td>
			</tr>
			<tr>
				<td colspan="6">
                    <input id="addr" type="text" name="address" value="杭州市 江干区 火车东站 顾家大厦5楼 xxx" /></td>
			</tr>
			<tr>
				<td style="border-top-width: 4px;" colspan="5" align="right"><a
					id="linkSubmit" href="javascript:$('#form1').submit();">提交订单</a></td>
			</tr>
		</table>
	</form>
	<script src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script src="<c:url value='/js/round.js'/>"></script>
	<script type="text/javascript">
		//计算合计
		$(function() {
			var total = 0;
			$(".subtotal").each(function() {
				total += Number($(this).text());
			});
			$("#total").text(round(total, 2));
		});
	</script>
</body>
</html>
