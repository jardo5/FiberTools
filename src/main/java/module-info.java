module com.fibertools.fibertools {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;

    opens com.fibertools.fibertools to javafx.fxml;
    exports com.fibertools.fibertools;
}