module com.cleanbast.cleanbast {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.cleanbast to javafx.fxml;
    exports com.cleanbast;
}