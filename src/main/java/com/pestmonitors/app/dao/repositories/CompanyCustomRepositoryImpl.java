package com.pestmonitors.app.dao.repositories;

import com.pestmonitors.app.dao.entities.CompanyEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class CompanyCustomRepositoryImpl implements CompanyCustomRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<CompanyEntity> findByNameAndTelf(String name, Integer telf) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(CompanyEntity.class);
        Root<CompanyEntity> root = criteriaQuery.from(CompanyEntity.class);

        Predicate namePredicate = criteriaBuilder.equal(root.get("name"),name);
        Predicate telfPredicate = criteriaBuilder.equal(root.get("telf"),telf);

        criteriaQuery.where(namePredicate,telfPredicate);

        TypedQuery<CompanyEntity> query = this.entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
