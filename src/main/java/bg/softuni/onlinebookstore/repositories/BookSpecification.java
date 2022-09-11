package bg.softuni.onlinebookstore.repositories;

import bg.softuni.onlinebookstore.model.dto.search.SearchDTO;
import bg.softuni.onlinebookstore.model.entity.BookEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class BookSpecification implements Specification<BookEntity> {
    private final SearchDTO searchDTO;

    public BookSpecification(SearchDTO searchDTO) {
        this.searchDTO = searchDTO;
    }

    @Override
    public Predicate toPredicate(Root<BookEntity> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder cb) {
        Predicate p = cb.conjunction();

        p.getExpressions().add(cb.and(cb.like(root.get("title"),
                "%" + searchDTO.getSearchText() + "%")));

        return p;
    }
}
