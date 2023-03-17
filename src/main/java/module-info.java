module com.cleanbast.cleanbast {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.sun.jna.platform;
    requires com.sun.jna;
    requires java.desktop;


    opens com.cleanbast to javafx.fxml;
    exports com.cleanbast;
}