module com.fibertools.fibertools {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;

    requires java.sql;
    requires MaterialFX;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires java.xml.bind;

    opens com.fibertools.models.TaceViewerModels to java.xml.bind;
    opens com.fibertools.models to javafx.base;
    opens com.fibertools.main to javafx.fxml;
    exports com.fibertools.main;
    exports com.fibertools.controllers;
    opens com.fibertools.controllers to javafx.fxml;
    exports com.fibertools.controllers.InventoryControllers;
    opens com.fibertools.controllers.InventoryControllers to javafx.fxml;
    exports com.fibertools.controllers.EmployeeControllers;
    opens com.fibertools.controllers.EmployeeControllers to javafx.fxml;
    exports com.fibertools.controllers.SpliceRecordControllers;
    opens com.fibertools.controllers.SpliceRecordControllers to javafx.fxml;
    exports com.fibertools.controllers.TraceViewerControllers;
    opens com.fibertools.controllers.TraceViewerControllers to javafx.fxml;
}