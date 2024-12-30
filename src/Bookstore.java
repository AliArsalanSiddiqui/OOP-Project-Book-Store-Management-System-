import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Main Bookstore Class
public class Bookstore {
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        Admin admin = new Admin("admin", "admin123");
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("\n--- Bookstore Menu ---");
                System.out.println("1. Register Customer");
                System.out.println("2. Admin - Add Book");
                System.out.println("3. Admin - Update Book");
                System.out.println("4. Admin - Delete Book");
                System.out.println("5. Admin - Show Customers");
                System.out.println("6. Customer - View Books");
                System.out.println("7. Customer - Place Order");
                System.out.println("8. Customer - Leave Review");
                System.out.println("9. View Reviews for a Book");
                System.out.println("10. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                try {
                    switch (choice) {
                        case 1: // Register Customer
                            System.out.println("Enter Customer Name:");
                            String customerName = scanner.nextLine();
                            dbManager.registerCustomer(customerName);
                            break;

                        case 2: // Add Book
                            System.out.println("Enter Book Title:");
                            String title = scanner.nextLine();
                            System.out.println("Enter Book Author:");
                            String author = scanner.nextLine();
                            System.out.println("Enter Book Price:");
                            double price = scanner.nextDouble();
                            scanner.nextLine();
                            admin.addBook(dbManager, title, author, price);
                            break;

                        case 3: // Update Book
                            System.out.println("Enter Book ID to update:");
                            int updateBookId = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Enter New Title:");
                            String newTitle = scanner.nextLine();
                            System.out.println("Enter New Author:");
                            String newAuthor = scanner.nextLine();
                            System.out.println("Enter New Price:");
                            double newPrice = scanner.nextDouble();
                            scanner.nextLine();
                            admin.updateBook(dbManager, updateBookId, newTitle, newAuthor, newPrice);
                            break;

                        case 4: // Delete Book
                            System.out.println("Enter Book ID to delete:");
                            int deleteBookId = scanner.nextInt();
                            scanner.nextLine();
                            admin.deleteBook(dbManager, deleteBookId);
                            break;
                        case 5: // Show Customers
                            System.out.println("\n--- Registered Customers ---");
                            List<Customer> allCustomers = dbManager.getAllCustomers();
                            for (Customer cust : allCustomers) {
                                System.out.println(cust);
                            }
                            break;
                        case 6: // View Books
                            System.out.println("\n--- Available Books ---");
                            List<Book> books = dbManager.getAllBooks();
                            for (Book book : books) {
                                System.out.println(book);
                            }
                            break;

                        case 7: // Place Order

                            System.out.println("\n--- Available Customers ---");
                            List<Customer> customers = dbManager.getAllCustomers();
                            if (customers.isEmpty()) {
                                System.out.println("No customers available. Please register first.");
                                break;
                            }
                            for (Customer cust : customers) {
                                System.out.println(cust);
                            }
                            System.out.println("Enter Customer ID:");
                            int customerId = scanner.nextInt();
                            scanner.nextLine();
                            Customer customer = dbManager.getCustomerById(customerId);
                            if (customer == null) {
                                System.out.println("Customer not found. Please register first.");
                                break;
                            }
                            System.out.println("\n--- Available Books ---");
                            List<Book> availableBooks = dbManager.getAllBooks();
                            if (availableBooks.isEmpty()) {
                                System.out.println("No books available to order.");
                                break;
                            }
                            for (Book book : availableBooks) {
                                System.out.println(book);
                            }
                            System.out.println("Enter the Book IDs to order (comma-separated):");
                            String[] bookIds = scanner.nextLine().split(",");
                            Order order = new Order(customer.getName());
                            for (String bookId : bookIds) {
                                Book book = dbManager.getBookById(Integer.parseInt(bookId.trim()));
                                if (book != null) {
                                    order.addBookToOrder(book);
                                } else {
                                    System.out.println("Book with ID " + bookId.trim() + " not found.");
                                }
                            }
                            System.out.println("Confirm Order? (yes/no):");
                            String confirmOrder = scanner.nextLine();
                            if (confirmOrder.equalsIgnoreCase("yes")) {
                                dbManager.placeOrder(order);
                                System.out.println("Order placed successfully! Total price: $" + order.getTotalPrice());
                            } else {
                                System.out.println("Order canceled.");
                            }
                            break;

                        case 8: // Leave Review
                            System.out.println("Enter Book ID to review:");
                            int reviewBookId = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Enter Your Name:");
                            String reviewerName = scanner.nextLine();
                            System.out.println("Enter Your Rating (1-5):");
                            int rating = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Enter Your Comment:");
                            String comment = scanner.nextLine();
                            Review review = new Review(reviewerName, reviewBookId, comment, rating);
                            try {
                                dbManager.addReview(review);
                                System.out.println("Thank you for your review! It has been successfully added.");
                            } catch (SQLException e) {
                                System.out.println("Failed to add review: " + e.getMessage());
                            }
                            break;

                        case 9: // View Reviews for a Book
                            System.out.println("\n--- Available Books ---");
                            List<Book> allBooks = dbManager.getAllBooks(); // Fetch all books
                            if (allBooks.isEmpty()) {
                                System.out.println("No books available.");
                                break;
                            }
                            for (Book book : allBooks) {
                                System.out.println(book); // Show books with IDs
                            }
                            System.out.println("Enter Book ID:");
                            int bookId = scanner.nextInt();
                            scanner.nextLine();
                            List<Review> bookReviews = dbManager.getReviewsForBook(bookId);
                            if (bookReviews.isEmpty()) {
                                System.out.println("No reviews found for this book.");
                            } else {
                                System.out.println("\n--- Reviews ---");
                                for (Review rev : bookReviews) {
                                    System.out.println(rev);
                                }
                            }
                            break;

                        case 10: // Exit
                            System.out.println("Exiting the bookstore system. Goodbye!");
                            scanner.close();
                            dbManager.closeConnection();
                            return;

                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } catch (Exception e) {
                    System.out.println("An error occurred: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Wrong Input Entered!");
        }
    }
}








