package bg.softuni.onlinebookstore.model.entity;

import bg.softuni.onlinebookstore.model.dto.book.AddNewBookDTO;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "books")
public class BookEntity extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @ManyToOne(optional = false)
    private AuthorEntity author;

    @ManyToOne
    private GenreEntity genre;

    @Column(nullable = false)
    private String yearOfPublication;

    //@Column(nullable = false, columnDefinition = "text")
    @Column(nullable = false, length = 65535)
    private String summary;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private BigDecimal price;

    public BookEntity() {
    }

    public BookEntity(String title, AuthorEntity author, GenreEntity genre, String yearOfPublication, String summary, String imageUrl, BigDecimal price) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.yearOfPublication = yearOfPublication;
        this.summary = summary;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public BookEntity(AddNewBookDTO bookDTO, AuthorEntity author, GenreEntity genre) {
        this.title = bookDTO.getTitle();
        this.author = author;
        this.genre = genre;
        this.yearOfPublication = bookDTO.getYearOfPublication();
        this.summary = bookDTO.getSummary();
        this.imageUrl = bookDTO.getImageUrl();
        this.price = bookDTO.getPrice();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    public GenreEntity getGenre() {
        return genre;
    }

    public void setGenre(GenreEntity genre) {
        this.genre = genre;
    }

    public String getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(String yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
