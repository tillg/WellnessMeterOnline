<%@ include file="_define_vars._jsp" %>
<%@ include file="_loginrequired._jsp" %>
<%@ include file="_checklogin._jsp" %>

<%@ page import="java.util.logging.Logger" %>
<%@ page import="java.util.Iterator"%>

<%@ page import="com.tillg.util.DateHelper"%>
<%@ page import="com.tillg.wellnessmeter.Measurement"%>
<%@ page import="com.tillg.wellnessmeter.MeasurementType"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="userMeasurements"
	class="com.tillg.wellnessmeter.UserMeasurements" scope="session" />


<%
	userMeasurements.setUserId(user.getUserId());

	// Logger LOG = Logger.getLogger("mywellness.jsp");
	Object error = session.getValue("error");
	session.removeAttribute("error");
	String errorStr = "";
	if (error != null) {
		errorStr = error.toString();
	}
%>


<!DOCTYPE html>
<html>

<%@ include file="_head._jsp" %>

<body>

<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.9.custom.min.js"></script>
<script type="text/javascript" src="js/jquery.ui.datepicker-de.js"></script>
<script>
$(document).ready(function(){
	$('#date').datepicker({ dateFormat: 'dd.mm.yy' });
 });
</script>

<div id="content">

<%@ include file="_title._jsp" %>

<c:if test="${errorStr!=''}">
	<p><%=errorStr%></p>
</c:if>

<form ACTION="AddMeasurement" METHOD="post">
	<table>
	<tr>
	<td>Datum</td> 
	<td><input NAME="date" id="date" SIZE="20"  placeholder="z.B. 3.11.2011" VALUE="<%=DateHelper.ENTRY_FORMATTER_SHORT.format(new java.util.Date()) %>"></td>
	</tr>
	<% 
	Iterator<MeasurementType> it = userMeasurements.getMeasurementTypes().iterator();
	while (it.hasNext()) {
		MeasurementType t = it.next();
	%>
		<tr>
			<td><%= t.getLabel() %></td>
			<td><input NAME="<%= t.getTypeId() %>" TYPE="<%= t.getHtmlType() %>" SIZE="20" placeholder="<%= t.getSubLabel() %>" /></td> 
		</tr>
	<%
		}
	%>
</table>
<input TYPE="submit" VALUE="Hinzufügen" />
</form>
<p>
	<img src="${userMeasurements.chartUrl}" alt="Graph">
</p>

<div id="valuetable"><span>
<table>
	<tr>
		<th>Datum</th>
		<% // Write the headers
		Iterator<String> it1 = userMeasurements.getMeasurementHeadersForDisplay().iterator();
		while (it1.hasNext()) {
			String header = MeasurementType.getLabelById(it1.next());
		%>
			<th><%= header %></th>
		<%
		}
		%>
	</tr>
	<%
	Iterator<Measurement> it2 = userMeasurements.getMeasurementsForDisplay().iterator();
	while (it2.hasNext()) {
		Measurement m = it2.next();
	%>
		<tr>
			<td><%= m.getDateStr4Display() %></td>
			<%
			Iterator<String> it3 = m.getValuesStr().keySet().iterator();
			while (it3.hasNext()) {
				String type = it3.next();
			%>
				<td class="WeightValueColumn"><%= m.getValuesStr().get(type) %></td>
			<%
			} // End of it3 loop
			%>
		</tr>
	<%
	} // End of it2 loop
	%>
</table>
</span>
</div>

</div>
</body>
</html>