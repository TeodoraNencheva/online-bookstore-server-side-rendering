package bg.softuni.onlinebookstore.model.dto.author;

public class AuthorOverviewDTO {
    private Long id;
    private String fullName;

    private String photoUrl;

    public AuthorOverviewDTO() {
    }

    public AuthorOverviewDTO(Long id, String fullName, String photoUrl) {
        this.id = id;
        this.fullName = fullName;
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
