module org.example.books {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.books to javafx.fxml;
    exports org.example.books;
}