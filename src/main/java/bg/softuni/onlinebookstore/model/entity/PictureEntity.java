package bg.softuni.onlinebookstore.model.entity;

import bg.softuni.onlinebookstore.model.cloudinary.CloudinaryImage;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pictures")
public class PictureEntity extends BaseEntity {
    private String url;
    private String publicId;

    public PictureEntity() {
    }

    public PictureEntity(CloudinaryImage cloudinaryImage) {
        this.url = cloudinaryImage.getUrl();
        this.publicId = cloudinaryImage.getPublicId();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }
}
