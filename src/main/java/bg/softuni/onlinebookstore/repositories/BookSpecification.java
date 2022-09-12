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

        Predicate titlePredicate = cb.like(root.get("title"),
                "%" + searchDTO.getSearchText() + "%");

        Predicate summaryPredicate = cb.like(root.get("summary"),
                "%" + searchDTO.getSearchText() + "%");

        p.getExpressions().add(cb.or(titlePredicate, summaryPredicate));

        return p;
    }
}
