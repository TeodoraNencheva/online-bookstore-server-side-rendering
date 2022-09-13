package bg.softuni.onlinebookstore.repositories;

import bg.softuni.onlinebookstore.model.dto.search.SearchDTO;
import bg.softuni.onlinebookstore.model.entity.AuthorEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AuthorSpecification implements Specification<AuthorEntity> {
    private final SearchDTO searchDTO;

    public AuthorSpecification(SearchDTO searchDTO) {
        this.searchDTO = searchDTO;
    }

    @Override
    public Predicate toPredicate(Root<AuthorEntity> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder cb) {
        Predicate p = cb.conjunction();

        Predicate firstNamePredicate = cb.like(root.get("firstName"),
                "%" + searchDTO.getSearchText() + "%");

        Predicate lastNamePredicate = cb.like(root.get("lastName"),
                "%" + searchDTO.getSearchText() + "%");

        Predicate biographyPredicate = cb.like(root.get("biography"),
                "%" + searchDTO.getSearchText() + "%");

        p.getExpressions().add(cb.or(firstNamePredicate, lastNamePredicate, biographyPredicate));

        return p;
    }
}
