package bg.softuni.onlinebookstore.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class ReviewEntity extends BaseEntity {

    @ManyToOne(optional = false)
    private BookEntity book;

    @ManyToOne(optional = false)
    private UserEntity user;

    private double rating;

    @Column(nullable = false, columnDefinition = "text")
    private String comment;

    public ReviewEntity() {
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
