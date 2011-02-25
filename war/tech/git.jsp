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
	<li><tt>git status</tt> to see what files I changed.
	<li><tt>git diff [file]</tt> to see exactly what I modified.
	<li><tt>git commit -a -m [message]</tt> to commit (into my local repo).
	<li><tt>git push</tt> to push the changes to GitHub. 
</ul>

<h2>Branching</h2>

For more details see <a href="http://www.eecs.harvard.edu/~cduan/technical/git/git-2.shtml">here</a>.

<h2>Merging</h2>
For more details see <a href="http://www.eecs.harvard.edu/~cduan/technical/git/git-3.shtml">here</a>.
    
<h2>Initial steps</h2>
<b>Global setup</b>
<ul>
	<li>Download and install Git
	<li><tt>git config --global user.name "Till Gartner"</tt>
	<li><tt>git config --global user.email till.gartner@gmail.com</tt>
</ul>    

<b>Next steps</b>
<ul>
	<li><tt>mkdir WellnessMeterOnline</tt>
	<li><tt>cd WellnessMeterOnline</tt>
	<li><tt>git init</tt>
	<li><tt>touch README</tt>
	<li><tt>git add README</tt>
	<li><tt>git commit -m 'first commit'</tt>
	<li><tt>git remote add origin <i>git@github.com:tillg/WellnessMeterOnline.git</i></tt>
	<li><tt>git push -u origin master</tt>
</ul>

<b>Existing Git Repo?</b>
<ul>
	<li><tt>cd existing_git_repo</tt>
	<li><tt>git remote add origin <i>git@github.com:tillg/WellnessMeterOnline.git</i></tt>
	<li><tt>git push -u origin master</tt>
</ul>
      

</div>
</body>
</html>