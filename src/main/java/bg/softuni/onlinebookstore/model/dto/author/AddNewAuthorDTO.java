package bg.softuni.onlinebookstore.model.dto.author;

import bg.softuni.onlinebookstore.model.entity.AuthorEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AddNewAuthorDTO {
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 30)
    private String lastName;

    @NotEmpty
    private String biography;

    private MultipartFile picture;

    public AddNewAuthorDTO() {
    }

    public AddNewAuthorDTO(String firstName, String lastName, String biography, MultipartFile picture) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = biography;
        this.picture = picture;
    }

    public AddNewAuthorDTO(AuthorEntity author) {
        this.firstName = author.getFirstName();
        this.lastName = author.getLastName();
        this.biography = author.getBiography();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }
}
