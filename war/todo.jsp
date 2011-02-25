<%@ include file="_define_vars._jsp" %>
<%@ include file="_loginnotrequired._jsp" %>
<%@ include file="_checklogin._jsp" %>

<!DOCTYPE html>
<html>

<%@ include file="_head._jsp" %>

<body>
<div id="content">

<%@ include file="_title._jsp" %>


<h2>To do</h2>

<h3>Version 1.0 - Go live</h3>
Folgende Features müssen bereitstehen um sinnvoll live zu gehen:
<ul>
	<li>Funktionierende und <i>eingespielte</i> GitHub-Nutzung.</li>
	<li>Kommentare: Benutzer müssen Rückmeldungen geben können (zusätzlich zu dem, was bereits funktioniert...):</li>
	<li>Numerische Werte die eingegeben werden müssen nach Wertebereich geprüft werden - und dem Benutzer müssen sinnvolle Rückmeldungen bei Fehleingaben gegeben werde.</li> 
	<li>Google Analytics einbauen.</li>
	<li>FEATURE: Kommentar für einen Tag eingeben.</li>
	<li>Keine bekannten Bugs, angemessen viele Unit-Tests, insbesonders bei den komplexer Bereichen.</li>
	<li>Klare Information über Browser-Voraussetzung. Das bedeutet eine Matrix von Browsers und Betriebssystemen zu haben und systematisch Tests durchzuführen. Benutzer mit nicht-kompatiblen Browsern müssen entsprechenden Hinweis bekommen.</li>
	<li>Facebook Connect einbauen.</li>
	<li>Standard Fehlerseite bauen, inkl. dem entsprechenden logging und mechanismen die mich aktiv über Fehler informieren.</li>
	<li>Facebook <i>I like it!</i>-Button.</li>
	<li>Metrik <i>Bewegung</i> oder <i>Sport</i>.</li>
	<li>Code cleaning: Z.B. die ganze Altlasten namens <i>weightXY</i> löschen.</li>
</ul> 

<h3>Bekannte Bugs</h3>
<ul>
	<li>Ich kann zu einem Datum 2 Werte eingeben. Unter anderem verschwindet dann die Grafik...</li>
</ul>

<h3>Weitere Features</h3>
<ul>
	<li>FEATURE: Ich gebe meinen Standort ein, und automatisch wird eine Dimension "Wetter" eingebettet. Diese wird automatisch befüllt.</li>
	<li>FEATURE: Ich gebe meine Foursquare credentials und automatisch werden pro Tag meine venues eingeblendet. Das könnte auch das Wetter der korrekten location einfließen lassen.</li> 
	<li>FEATURE: Englische Version. Das beinhaltet auch andere Einheiten (zB lbs für Gewicht).</li>
	<li><i>Eigenen</i> Login-Mechanismud für Leute die weder den Facebook noch den Google-Login nutzen möchten oder können.</li>
	<li>Nutze HTML5-Features um die Seite auch offline nutzen zu können.</li>
	<li>AJAX-Features einbauen um ganze Seiten nicht immer nachladen zu müssen.</li>
	<li>Liste mit Daten direkt editierbar machen um auch bereits eingegebene Daten editieren zu können.</li>
	<li>CSS Design für iPhone und iPad.</li>
	<li>Eigene, echte iOS-App.</li>
</ul>



<h2>Historie</h2>
<ul>
	<li>25. Feb 2011: HTML5 autofocus Angeschaut und getestet: Macht keinen Sinn aus mywellness.jsp, da es nicht sinnvoll mit dem jQuery Datepicker interagiert.</li>
	
	<li>16-24. Feb 2011: Logik intern komplett umgebaut, um einfach, sicher und schnell neue Metriken einbringen zu können.
	<ul>
		<li>Vorbereitung um User-spezifische Metriken zu erlauben.</li>
		<li>Die Seite weights.jsp umbenannt bzw. umgebaut in MeineWellness.</li>
		<li>HTML5-Features nutzen, z.B. runde Ecken, Date Picker, etc.</li>
		
	</ul></li>
	<li>15.2.2011: Eigene Login-Seite auf der man sich über Google oder Facebook einloggen kann. Wenn man eine Adresse anspringt für die man eingeloggt sein muß, so sollte man erst auf die Login-Seite weitergeleitet werden, und nach dem Login auf die Zielseite gelangen.</li>
	<li>15.2.2011: Wenn ich einen Wert eingegeben habe, sollte ich wieder auf der Seite weights.jsp landen. Zur Zeit ende ich auf AddWeight.</li>
	<li>14.2.2011: Eigene Domäne zB wellness-o-meter.de</li>
	<li>Feb 2011: Logo designen.</li>
	<li>9.2.2011: Menü gebaut.</li>
	<li>9.2.2011: Angefangen die Seiten in (mehrfach benutzte) Blöcke zu strukturieren.</li>
	<li>9.2.2011: Font für Wellness-O-Meter-Schriftzug ausgesucht.</li> 
	<li>9.2.2011: Titelseite überarbeitet - Inhalt und Form.</li>
	<li>8.2.2011: Neues Hintergrundbild eingebaut und Seite etwas hŸbscher gemacht.</li>
	<li>7.2.2011: Die Datumseingabe wird jetzt unterstŸtzt durch einen <i>date picker</i>.</li>
	<li>29.1.2011: Jetzt gibt es einen Graphen. Zur Zeit zeigt er nur den Verlauf des Kšrpergewichts an, aber ein Anfang ist gemacht.</li> 
	<li>28.1.2011: Add proper log-in mechanism (based on Googles user concept).</li>
	<li>28.1.2011: Add user concept (Googles user concept).</li>
	<li>28.1.2011: Eine erste Version eines CSS-Files gebaut - jetzt sehen wir schon etwas GrŸn...</li>
	<li>26.1.2011: Moved to Google appengine.</li>
	<li>19.1.2011: Set up versioning tool (GitHub).</li>
	<li>18.1.2011: Rename to WellnessMeter.</li>
	<li>16.1.2011: Read weights from file.</li>
	<li>14.1.2011: Save weights to file.</li>
	<li>14.1.2011: Display only n entries in list.</li>
	<li>14.1.2011: Sort weight entries by date.</li>
	<li>12.1.2011: basic stuff works (w/o saving data).</li>
</ul>

</div>
</body>
</html>