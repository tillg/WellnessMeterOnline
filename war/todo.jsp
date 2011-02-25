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
Folgende Features m�ssen bereitstehen um sinnvoll live zu gehen:
<ul>
	<li>Funktionierende und <i>eingespielte</i> GitHub-Nutzung.</li>
	<li>Kommentare: Benutzer m�ssen R�ckmeldungen geben k�nnen (zus�tzlich zu dem, was bereits funktioniert...):</li>
	<li>Numerische Werte die eingegeben werden m�ssen nach Wertebereich gepr�ft werden - und dem Benutzer m�ssen sinnvolle R�ckmeldungen bei Fehleingaben gegeben werde.</li> 
	<li>Google Analytics einbauen.</li>
	<li>FEATURE: Kommentar f�r einen Tag eingeben.</li>
	<li>Keine bekannten Bugs, angemessen viele Unit-Tests, insbesonders bei den komplexer Bereichen.</li>
	<li>Klare Information �ber Browser-Voraussetzung. Das bedeutet eine Matrix von Browsers und Betriebssystemen zu haben und systematisch Tests durchzuf�hren. Benutzer mit nicht-kompatiblen Browsern m�ssen entsprechenden Hinweis bekommen.</li>
	<li>Facebook Connect einbauen.</li>
	<li>Standard Fehlerseite bauen, inkl. dem entsprechenden logging und mechanismen die mich aktiv �ber Fehler informieren.</li>
	<li>Facebook <i>I like it!</i>-Button.</li>
	<li>Metrik <i>Bewegung</i> oder <i>Sport</i>.</li>
	<li>Code cleaning: Z.B. die ganze Altlasten namens <i>weightXY</i> l�schen.</li>
</ul> 

<h3>Bekannte Bugs</h3>
<ul>
	<li>Ich kann zu einem Datum 2 Werte eingeben. Unter anderem verschwindet dann die Grafik...</li>
</ul>

<h3>Weitere Features</h3>
<ul>
	<li>FEATURE: Ich gebe meinen Standort ein, und automatisch wird eine Dimension "Wetter" eingebettet. Diese wird automatisch bef�llt.</li>
	<li>FEATURE: Ich gebe meine Foursquare credentials und automatisch werden pro Tag meine venues eingeblendet. Das k�nnte auch das Wetter der korrekten location einflie�en lassen.</li> 
	<li>FEATURE: Englische Version. Das beinhaltet auch andere Einheiten (zB lbs f�r Gewicht).</li>
	<li><i>Eigenen</i> Login-Mechanismud f�r Leute die weder den Facebook noch den Google-Login nutzen m�chten oder k�nnen.</li>
	<li>Nutze HTML5-Features um die Seite auch offline nutzen zu k�nnen.</li>
	<li>AJAX-Features einbauen um ganze Seiten nicht immer nachladen zu m�ssen.</li>
	<li>Liste mit Daten direkt editierbar machen um auch bereits eingegebene Daten editieren zu k�nnen.</li>
	<li>CSS Design f�r iPhone und iPad.</li>
	<li>Eigene, echte iOS-App.</li>
</ul>



<h2>Historie</h2>
<ul>
	<li>25. Feb 2011: HTML5 autofocus Angeschaut und getestet: Macht keinen Sinn aus mywellness.jsp, da es nicht sinnvoll mit dem jQuery Datepicker interagiert.</li>
	
	<li>16-24. Feb 2011: Logik intern komplett umgebaut, um einfach, sicher und schnell neue Metriken einbringen zu k�nnen.
	<ul>
		<li>Vorbereitung um User-spezifische Metriken zu erlauben.</li>
		<li>Die Seite weights.jsp umbenannt bzw. umgebaut in MeineWellness.</li>
		<li>HTML5-Features nutzen, z.B. runde Ecken, Date Picker, etc.</li>
		
	</ul></li>
	<li>15.2.2011: Eigene Login-Seite auf der man sich �ber Google oder Facebook einloggen kann. Wenn man eine Adresse anspringt f�r die man eingeloggt sein mu�, so sollte man erst auf die Login-Seite weitergeleitet werden, und nach dem Login auf die Zielseite gelangen.</li>
	<li>15.2.2011: Wenn ich einen Wert eingegeben habe, sollte ich wieder auf der Seite weights.jsp landen. Zur Zeit ende ich auf AddWeight.</li>
	<li>14.2.2011: Eigene Dom�ne zB wellness-o-meter.de</li>
	<li>Feb 2011: Logo designen.</li>
	<li>9.2.2011: Men� gebaut.</li>
	<li>9.2.2011: Angefangen die Seiten in (mehrfach benutzte) Bl�cke zu strukturieren.</li>
	<li>9.2.2011: Font f�r Wellness-O-Meter-Schriftzug ausgesucht.</li> 
	<li>9.2.2011: Titelseite �berarbeitet - Inhalt und Form.</li>
	<li>8.2.2011: Neues Hintergrundbild eingebaut und Seite etwas h�bscher gemacht.</li>
	<li>7.2.2011: Die Datumseingabe wird jetzt unterst�tzt durch einen <i>date picker</i>.</li>
	<li>29.1.2011: Jetzt gibt es einen Graphen. Zur Zeit zeigt er nur den Verlauf des K�rpergewichts an, aber ein Anfang ist gemacht.</li> 
	<li>28.1.2011: Add proper log-in mechanism (based on Googles user concept).</li>
	<li>28.1.2011: Add user concept (Googles user concept).</li>
	<li>28.1.2011: Eine erste Version eines CSS-Files gebaut - jetzt sehen wir schon etwas Gr�n...</li>
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