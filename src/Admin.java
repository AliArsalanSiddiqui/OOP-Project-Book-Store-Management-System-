import java.sql.SQLException;

class Admin {
    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void addBook(DatabaseManager dbManager, String title, String author, double price) {
        try {
            dbManager.addBook(title, author, price);
            System.out.println("Book added successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to add book: " + e.getMessage());
        }
    }

    public void updateBook(DatabaseManager dbManager, int id, String title, String author, double price) {
        try {
            dbManager.updateBook(id, title, author, price);
            System.out.println("Book updated successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to update book: " + e.getMessage());
        }
    }

    public void deleteBook(DatabaseManager dbManager, int id) {
        try {
            dbManager.deleteBook(id);
            System.out.println("Book deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to delete book: " + e.getMessage());
        }
    }
}