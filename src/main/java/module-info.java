module com.fibertools.fibertools {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires java.sql;
    requires MaterialFX;

    opens com.fibertools.models to javafx.base;
    opens com.fibertools.main to javafx.fxml;
    exports com.fibertools.main;
    exports com.fibertools.controllers;
    opens com.fibertools.controllers to javafx.fxml;
    exports com.fibertools.controllers.InventoryControllers;
    opens com.fibertools.controllers.InventoryControllers to javafx.fxml;
}