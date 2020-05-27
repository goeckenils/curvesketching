
# Aufgabenstellung  
  
Kurvendiskussion  
  
Entwickeln Sie ein Java-Programm, das eine Kurvendiskussion entsprechend des Mathematikunterrichtes erstellt. Dabei übergibt der Benutzer eine mathematische Funktion (bspw. x^2+2x+3 oder exp(x) oder x^3+2x^2+5x+4 etc.). Das Programm erstellt daraufhin eine Wertetabelle, berechnet Schnittpunkte mit der x- und y-Achse, Extrempunkte, Wendepunkte, Asymptoten, ggf. zeichnet Funktionsgraph (falls GUI verwendet wird). Alle berechneten Werte sollen textuell ausgegeben werden. Ein Export nach PDF und Excel soll möglich sein.   
  
Hinweis (optional):   
 
Das Programm darf gerne auch mithilfe von Swing oder Java FX umgesetzt werden.   
  
# Dokumentation

Eine Auflistung aller der im Projekt verwendeten Technologien

##  [JavaFX 14](https://openjfx.io/index.html)

JavaFx wird verwendet für die Graphische Darstellung der Generierten Daten 


##  [Apache POI - the Java API for Microsoft Documents](https://poi.apache.org/index.html)

Apache POI wird verwendet für das Schreiben der Daten in ein Excel (xlsx) Dokument.

## [Apache PDFBox  - A Java PDF Library](https://pdfbox.apache.org/)

Apache PDFBox wird verwendet um die Daten in einer PDF Datei zu speichern.

## [Pdfbox - layout](https://github.com/ralfstuckert/pdfbox-layout)
Als wir versuchten die normale PDFBox API anzusprechen viel auf das Formatierung nur limitiert zu verfügung stand. Abhilfe dabei schaffte PDFBox Layout die es uns ermöglichte das PDF Dokument vernünftig zu Strukturieren.

# Backend

Innerhalb unseres Java-Projektes habe ich ein Package erstellt, welches die Abfrage von den benötigten Ergebnissen stark vereinfacht. Die Klasse "MathPower" ist überliegende Klasse, welche durch das Einbringen von Abhängigkeiten der Hauptakteur innerhalb des Backends ist. Um an die Ergebnisse zu gelangen, muss innerhalb der Konstruktoren der jeweiligen Klasse die Abhängigkeiten von „MathPower“ eingebracht werden, weshalb ich mich für eine globale Auslagerung der Getter von den Ergebnissen auf die Klasse „MathPower“ beschränkt habe. Somit kann das Frontend über eine einzige Deklarierung dieser Klasse, mit der eingebrachten mathematischen Formel als Eigenschaft, alle Informationen zu den Graphen erfahren. Jegliche nicht validierbare Eingaben werden von dem „Try and Catch“ Block abgefangen und resultieren in einen Hinweis im Frontend für den Nutzer. Mögliche beziehungsweise validierbare Eingaben sind folgende: 

| Multiplikatoren  | Exponenten | Vorzeichen | Beispiele |
| :------------- | :------------- | :-------------: | :-------------: |
| (1/3) => (Zähler/Nenner) | 4 (ganzrationale Funktion) | + | (1/4)x^4+3x^2-10 `siehe Backend-Probleme`|
| 1-99  => (Ganze Zahlen) | 3 (kubische Funktion) | - | x^3+2x^2+5x+4 |
|  | 2 (quadratische Funktion) |  | x^4+x-3 |
|  | 1 (lineare Funktion) |  |  |

## Backend-Probleme
- [x] Schnittpunkte mit x- und y-Achse
- [x] Extrempunkte
- [x] Wendepunkte
- [ ] Asymptoten

Leider ist durch die manuelle Programmierung das Nachführen einer Validierung von gebrochenrationalen- und e-Funktionen nur schwierig möglich. Um ein möglichst gutes und richtiges Ergebnis zu erzielen, habe ich mich somit zuerst auf die ersten drei Punkte fokossiert, da zudem die mathematische Bearbeitung vollkommen von den oben genannten Funktionen abweicht.

- [x] Brüche

Die Ergebnisse von den Funktionen, welche Brüche enthalten, weichen mitunter von den korrekten Kalkulationen von [GeoGebra](https://www.geogebra.org/graphing) ab. Innerhalb der Methoden wird nicht mit den Brüchen gerechnet, sondern mit Ergebnissen dieser als Dezimalzahlen in Doubles, weshalb dieser Fehler auftreten kann.
