<%@ include file="/_define_vars._jsp" %>
<%@ include file="/_loginnotrequired._jsp" %>
<%@ include file="/_checklogin._jsp" %>

<!DOCTYPE html>
<html>

<%@ include file="/_head._jsp" %>

<body>
<div id="content">

<%@ include file="/_title._jsp" %>

<h1>Tills cheat sheet for Git</h1>

<h2>Standard workflow</h2>
<ul>
	<li>Do some programming.
	<li>git status to see what files I changed.
	<li>git diff [file] to see exactly what I modified.
	<li>git commit -a -m [message] to commit. 
</ul>
    
<h2>Initial steps</h2>
<b>Global setup</b>
<ul>
	<li>Download and install Git
	<li>git config --global user.name "Till Gartner"
	<li>git config --global user.email till.gartner@gmail.com
</ul>    

<b>Next steps</b>
<ul>
	<li>mkdir WellnessMeterOnline
	<li>cd WellnessMeterOnline
	<li>git init
	<li>touch README
	<li>git add README
	<li>git commit -m 'first commit'
	<li>git remote add origin <i>git@github.com:tillg/WellnessMeterOnline.git</i>
	<li>git push -u origin master
</ul>

<b>Existing Git Repo?</b>
<ul>
	<li>cd existing_git_repo
	<li>git remote add origin <i>git@github.com:tillg/WellnessMeterOnline.git</i>
	<li>git push -u origin master
</ul>
      

</div>
</body>
</html>