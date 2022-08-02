package bg.softuni.onlinebookstore.model.dto.author;

import bg.softuni.onlinebookstore.model.dto.book.BookOverviewDTO;

import java.util.List;

public class AuthorDetailsDTO {
    private Long id;
    private String fullName;
    private String biography;

    private String photoUrl;

    public AuthorDetailsDTO() {
    }

    public AuthorDetailsDTO(Long id, String fullName, String biography, String photoUrl) {
        this.id = id;
        this.fullName = fullName;
        this.biography = biography;
        this.photoUrl = photoUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
