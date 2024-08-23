module come.github.luxinenglish.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;

    opens come.github.luxinenglish.demo to javafx.fxml;
    exports come.github.luxinenglish.demo;
}