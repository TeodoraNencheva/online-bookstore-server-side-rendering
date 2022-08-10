package bg.softuni.onlinebookstore.model.entity;

import bg.softuni.onlinebookstore.model.dto.author.AddNewAuthorDTO;

import javax.persistence.*;

@Entity
@Table(name = "authors")
public class AuthorEntity extends BaseEntity {

    private String firstName;

    @Column(nullable = false)
    private String lastName;

    //@Column(nullable = false, columnDefinition = "text")
    @Column(nullable = false, length = 1000)
    private String biography;

    @Column(nullable = false)
    private String photoUrl;

    public AuthorEntity() {
    }

    public AuthorEntity(String firstName, String lastName, String biography, String photoUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = biography;
        this.photoUrl = photoUrl;
    }

    public AuthorEntity(AddNewAuthorDTO authorDTO) {
        this.firstName = authorDTO.getFirstName();
        this.lastName = authorDTO.getLastName();
        this.biography = authorDTO.getBiography();
        this.photoUrl = authorDTO.getPhotoUrl();
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getFullName() {
        if (firstName == null) {
            return lastName;
        }

        return firstName + " " + lastName;
    }
}
