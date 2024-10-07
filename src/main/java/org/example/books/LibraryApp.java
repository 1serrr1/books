package org.example.books;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class LibraryApp extends Application {

    private final Books bookDAO = new Books();
    private final ObservableList<Book> booksObservableList = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Библиотека");

        ListView<Book> bookListView = new ListView<>(booksObservableList);
        loadBooks();

        TextField titleField = new TextField();
        titleField.setPromptText("Название");

        TextField authorField = new TextField();
        authorField.setPromptText("Автор");

        TextField yearField = new TextField();
        yearField.setPromptText("Год");

        Button addButton = new Button("Добавить книгу");
        addButton.setOnAction(e -> {
            String title = titleField.getText();
            String author = authorField.getText();
            int year = Integer.parseInt(yearField.getText());
            bookDAO.addBook(new Book(0, title, author, year));
            loadBooks();
        });

        Button updateButton = new Button("Обновить книгу");
        updateButton.setOnAction(e -> {
            Book selectedBook = bookListView.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                selectedBook.setTitle(titleField.getText());
                selectedBook.setAuthor(authorField.getText());
                selectedBook.setYear(Integer.parseInt(yearField.getText()));
                bookDAO.updateBook(selectedBook);
                loadBooks();
            }
        });

        Button deleteButton = new Button("Удалить книгу");
        deleteButton.setOnAction(e -> {
            Book selectedBook = bookListView.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                bookDAO.deleteBook(selectedBook.getId());
                loadBooks();
            }
        });

        VBox vbox = new VBox(10, bookListView, titleField, authorField, yearField, addButton, updateButton, deleteButton);
        vbox.setPadding(new Insets(10));

        Scene scene = new Scene(vbox, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadBooks() {
        List<Book> books = bookDAO.getAllBooks();
        booksObservableList.setAll(books);
    }
}
