package bg.softuni.onlinebookstore.model.dto.author;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AddNewAuthorDTO {
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 30)
    private String lastName;

    @NotEmpty
    private String biography;

    @NotEmpty
    private String photoUrl;

    public AddNewAuthorDTO() {
    }

    public AddNewAuthorDTO(String firstName, String lastName, String biography, String photoUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = biography;
        this.photoUrl = photoUrl;
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
}
