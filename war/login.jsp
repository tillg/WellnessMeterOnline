<%@ include file="_define_vars._jsp" %>
<%@ include file="_loginnotrequired._jsp" %>
<%@ include file="_checklogin._jsp" %>

<!DOCTYPE html>
<html>

<%@ include file="_head._jsp" %>

<body>
<div id="content">

<%@ include file="_title._jsp" %>

<!--<div class="loginbutton">-->
<!--<br/>-->
<!--<a href="error.jsp">Anmelden &uuml;ber Facebook-->
<!--<br/>-->
<!--<img src="img/facebook.png" width="100"/></a>-->
<!--<br/>-->
<!--</div>-->

<br/>
<br/>

<div class="loginbutton">
<br/>
<a href="<%= user.getGoogleLoginURL(nextpage)  %>">Anmelden &uuml;ber Google
<br/>
<img src="img/google.png" width="200"/></a>
<br/>
</div>

</div>
</body>
</html>