
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
<!--stackedit_data:
eyJoaXN0b3J5IjpbMjEwMTgzMTgxM119
-->