<%@ include file="_define_vars._jsp" %>
<%@ include file="_loginrequired._jsp" %>
<%@ include file="_checklogin._jsp" %>

<%@ page import="java.util.logging.Logger" %>
<%@ page import="com.tillg.util.DateHelper"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="userWeights"
	class="com.tillg.wellnessmeter.UserWeights" scope="session" />


<%
	userWeights.setUserId(user.getUserId());

	Logger LOG = Logger.getLogger("weights.jsp");
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

<form ACTION="AddWeight" METHOD="post">
	Datum 
	<input NAME="date" id="date" SIZE="20"  placeholder="z.B. 3.11.2011" VALUE="<%=DateHelper.ENTRY_FORMATTER_SHORT.format(new java.util.Date()) %>"/>
	Gewicht in kg 
	<input NAME="weightvalue" TYPE="text" SIZE="10" /> 
	<input TYPE="submit" VALUE="Hinzufügen" />
</form>
<p>
	<img src="${userWeights.chartURL}" alt="Graph">
</p>

<div id="valuetable"><span>
<table>
	<tr>
		<th>Datum</th>
		<th>Gewicht in kg</th>
	</tr>
	<c:forEach items="${userWeights.weightsForDisplay}" var="m">
		<tr>
			<td>${m.dateStr4Display}</td>
			<td class="WeightValueColumn">${m.weightValue}</td>
		</tr>
	</c:forEach>
</table>
</span>
</div>

</div>
</body>
</html>