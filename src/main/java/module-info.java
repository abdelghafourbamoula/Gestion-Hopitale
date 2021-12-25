module hopitale {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens hopitale to javafx.fxml;
    exports hopitale;
}
