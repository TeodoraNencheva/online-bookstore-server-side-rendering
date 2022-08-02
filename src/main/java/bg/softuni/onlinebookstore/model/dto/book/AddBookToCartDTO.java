package bg.softuni.onlinebookstore.model.dto.book;

public class AddBookToCartDTO {
    private Long bookId;
    private int quantity;

    public AddBookToCartDTO() {
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
