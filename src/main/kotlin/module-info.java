module org.example.matrixcalculatorjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens org.example.matrixcalculatorjfx to javafx.fxml;
    exports org.example.matrixcalculatorjfx;
}