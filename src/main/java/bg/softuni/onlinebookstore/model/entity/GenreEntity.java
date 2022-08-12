package bg.softuni.onlinebookstore.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "genres")
public class GenreEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;

    public GenreEntity() {
    }

    public GenreEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
