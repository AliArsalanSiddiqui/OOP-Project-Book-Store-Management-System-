import java.util.ArrayList;
import java.util.List;

class Order {
    private String customerName;
    private List<Book> books = new ArrayList<>();

    public Order(String customerName) {
        this.customerName = customerName;
    }

    public void addBookToOrder(Book book) {
        books.add(book);
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getBookIdsAsString() {
        StringBuilder ids = new StringBuilder();
        for (Book book : books) {
            if (ids.length() > 0) ids.append(",");
            ids.append(book.getId());
        }
        return ids.toString();
    }

    public double getTotalPrice() {
        return books.stream().mapToDouble(Book::getPrice).sum();
    }
}