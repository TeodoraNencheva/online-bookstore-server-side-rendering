package bg.softuni.onlinebookstore.model.entity;

import bg.softuni.onlinebookstore.model.enums.UserRoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRoleEnum name;

    public UserRoleEntity() {
    }

    public UserRoleEntity(UserRoleEnum name) {
        this.name = name;
    }

    public UserRoleEnum getName() {
        return name;
    }

    public void setName(UserRoleEnum name) {
        this.name = name;
    }
}
