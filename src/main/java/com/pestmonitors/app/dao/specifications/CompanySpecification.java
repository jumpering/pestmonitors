package com.pestmonitors.app.dao.specifications;

import com.pestmonitors.app.dao.entities.CompanyEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CompanySpecification {

    public static Specification<CompanyEntity> hasName(String name) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("name"), name);
        });
    }

    public static Specification<CompanyEntity> containsName(String name) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("name"), "%" + name + "%");
        });
    }

    public static Specification<CompanyEntity> hasTelf(Integer telf) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("telf"), telf);
        });
    }
}
