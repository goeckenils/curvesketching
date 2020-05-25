module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires java.datatransfer;
    requires java.desktop;
    requires poi;
    requires org.apache.pdfbox;
    requires pdfbox2.layout;
    requires poi.ooxml;

    opens org.example to javafx.fxml;
    exports org.example;
}