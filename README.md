
# Aufgabenstellung  
  
Kurvendiskussion  
  
Entwickeln Sie ein Java-Programm, das eine Kurvendiskussion entsprechend des Mathematikunterrichtes erstellt. Dabei übergibt der Benutzer eine mathematische Funktion (bspw. x^2+2x+3 oder exp(x) oder x^3+2x^2+5x+4 etc.). Das Programm erstellt daraufhin eine Wertetabelle, berechnet Schnittpunkte mit der x- und y-Achse, Extrempunkte, Wendepunkte, Asymptoten, ggf. zeichnet Funktionsgraph (falls GUI verwendet wird). Alle berechneten Werte sollen textuell ausgegeben werden. Ein Export nach PDF und Excel soll möglich sein.   
  
Hinweis (optional):   
 
Das Programm darf gerne auch mithilfe von Swing oder Java FX umgesetzt werden.   

# Maven
In diesem Projekt verwenden wir Maven als Paketmanager, dies war jedoch nicht von Anfang an so. Die ersten 3 Wochen arbeiteten wir in einem normalem Intellij Projekt, hier entstanden nun aber die ersten Fehler. Wir mussten alle externen Pakete die wir verwenden wollten händisch hinzufügen und im gleichen Zug in der .gitignore excluden hiermit stellten wir sicher das es zu keinen merge Fehlern kommt.

Wir stellten uns die Frage ob das Projekt überhaupt in dem rahmen ohne große Erklärungen von jemand anderem hätte maintained werden können. Wir kamen zu dem Entschluss nein und recherchierten daraufhin.

Maven als Abhängigkeit Management Tool war das wonach wir suchten und starteten daher mit der Migration.

Um nun Packages Hinzufügen zu können muss die Abhängigkeit nur in die Pom.xml Datei deklariert werden, Maven erledigt nun den Rest und lädt alle Abhängigkeiten automatisch herunter.

  
## Packages

##  [JavaFX 14](https://openjfx.io/index.html)
JavaFx wird verwendet für die graphische Darstellung der generierten Daten 

##  [Apache POI - the Java API for Microsoft Documents](https://poi.apache.org/index.html)
Apache POI wird verwendet für das Schreiben der Daten in ein Excel (*.xlsx) Dokument.

## [Apache PDFBox  - A Java PDF Library](https://pdfbox.apache.org/)
Apache PDFBox wird verwendet, um die Daten in einer PDF Datei zu speichern.

## [Pdfbox - layout](https://github.com/ralfstuckert/pdfbox-layout)
Durch die limitierte Bereitstellung der PDFBox API, konnten wir mit dem PDFBox Layout Package Abhilfe schaffen, um die PDF Dokumente vernünftig strukturieren zu können.

# MathPower 
### Paket für die Verarbeitung der mathematischen Funktionen

Innerhalb unseres Java-Projektes habe ich ein Package erstellt, welches die Abfrage von den benötigten Ergebnissen stark vereinfacht. Die Klasse **MathPower** ist die überliegende Klasse, welche durch das Einbringen von Abhängigkeiten der Hauptakteur innerhalb des Projektes ist. Um an die Ergebnisse zu gelangen, muss innerhalb der Konstruktoren der jeweiligen Klasse die Abhängigkeiten von **MathPower** eingebracht werden, weshalb ich mich für eine globale Auslagerung der Getter von den Ergebnissen auf die Klasse **MathPower** beschränkt habe. Somit kann das User-Interface über eine einzige Deklarierung dieser Klasse, mit der eingebrachten mathematischen Formel als Eigenschaft, alle Informationen zu den Graphen erfahren. Jegliche nicht validierbare Eingaben werden von dem „Try and Catch“ Block abgefangen und resultieren in einen Hinweis in dem User-Interface für den Nutzer. Mögliche beziehungsweise validierbare Eingaben sind folgende: 

| Multiplikatoren  | Exponenten | Vorzeichen | -------- Beispiele -------- |
| :---: | :---: | :---: | :---: |
| (1/3) <br/> (Zähler/Nenner) | 4 <br/> (ganzrationale Funktion) | + | (1/4)x^4+3x^2-10 |
| 1-99  <br/> (Ganze Zahlen) | 3 <br/> (kubische Funktion) | - | -(1/3)x^3+(1/5)x^2-(1/6)x-3 |
|  | 2 <br/> (quadratische Funktion) |  | 3x^2-(1/2)x+3 |
|  | 1 <br/> (lineare Funktion) |  | 16x-2 |

## MathPower-Eigenschaften
- [x] Schnittpunkte mit x- und y-Achse
- [x] Extrempunkte
- [x] Wendepunkte
- [x] \(Optional) Brüche 

Durch die späte Einbindung können Abweichungen vorkommen. Dies bitte über [Github Issues](https://github.com/goeckenils/curvesketching/issues/new) mitteilen.

- [ ] Asymptoten

Leider ist durch die manuelle Programmierung das Nachführen einer Validierung von gebrochenrationalen- und e-Funktionen nur schwierig möglich. Um ein möglichst gutes und richtiges Ergebnis zu erzielen, habe ich mich somit zuerst auf die ersten drei Punkte fokossiert, da zudem die mathematische Bearbeitung vollkommen von den oben genannten Funktionen abweichen würde.

### Java.utils
Innerhalb von dem Package MathPower habe ich drei Java interne Utilities genutzt:

1. java.util.ArrayList;
    - Da vor allen Dingen die mathematischen Funktionen sehr unterschiedlich in ihrer Länge ausfallen können, habe ich mich für diese Alternative entschieden, um diese dynamisch aufnehmen und verarbeiten zu können.
2. java.util.Arrays;
    - Mitunter konnten fest definierte Bereiche genutzt werden, wodurch ein dynamische Array nicht von nöten war.
3. java.util.List;
    - Bei einzelnen Informationen, welche dynamisch bearbeitet werden sollen, habe ich mich für eine Liste entschieden.
    - Entscheidener Unterschied zu dem Package .ArrayList ist, dass nur einzelne Informationen (Strings, Doubles) ohne Reihung eingefügt (außer einer weiteren Liste = List<List<Double>>) werden konnten. Die Einbindung der Daten in ein Array entfand ich somit deutlich leserlicher.
<!--stackedit_data:
eyJoaXN0b3J5IjpbLTExOTI4ODQ5ODNdfQ==
-->