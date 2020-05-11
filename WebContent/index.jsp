<%@page import="de.devloop.rrd4j.Const.TimeRange"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Graph List</title>
	<script src="js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript">
		function refreshGraph() {
			var uri = $("#graphs option:selected").val() + "?image";
			uri += "&range=" + $("#range").val();
			
			var widthScale = ($(document).width() / 16) - 10;
			
			uri += "&scale=" + widthScale; 
			
			$("#graph").attr("src", uri);
		}
	
		$(document).ready(function() {
			$("#graphs").on("change", function() {
				refreshGraph();
			});
			$("#range").on("change", function() {
				refreshGraph();
			});
			$("#refresh").on("click", function() {
				refreshGraph();
			});
		});
	</script> 
</head>
<body>
	<select id="graphs" size="1">
		<option value="weather">Weather</option>
		<option value="gas">Gas</option>
		<option value="heating">Heating</option>
	</select>
	<select id="range" size="1">
	<%
		for (TimeRange tr: TimeRange.values()) {
	%>
	<option value="<%= tr.toString() %>"><%= tr.toString() %></option>
	<%
		}
	%>
	</select>
	<button id="refresh">Refresh</button><br/>
	
	<img id="graph"/>
</body>
</html>