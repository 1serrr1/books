package org.example.books;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Books {
    private final DatabaseConnection databaseConnection;

    public Books() {
        this.databaseConnection = new DatabaseConnection();

}
    public void addBook(Book book) {
        String sql = "INSERT INTO books(title, author, year) VALUES(?, ?, ?)";
        try (PreparedStatement pstmt = databaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setInt(3, book.getYear());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Statement stmt = databaseConnection.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                books.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getInt("year")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public void updateBook(Book book) {
        String sql = "UPDATE books SET title = ?, author = ?, year = ? WHERE id = ?";
        try (PreparedStatement pstmt = databaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setInt(3, book.getYear());
            pstmt.setInt(4, book.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (PreparedStatement pstmt = databaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

