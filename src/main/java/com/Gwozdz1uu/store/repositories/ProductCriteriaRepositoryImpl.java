package com.Gwozdz1uu.store.repositories;

import com.Gwozdz1uu.store.entities.Category;
import com.Gwozdz1uu.store.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class ProductCriteriaRepositoryImpl implements ProductCriteriaRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Product> findProductsByCriteria(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = builder.createQuery(Product.class);
        Root<Product> product = cq.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();
        if(name !=null){
            predicates.add(builder.like(product.get("name"), "%"+name+"%"));
        }
        if(minPrice !=null){
            predicates.add(builder.greaterThanOrEqualTo(product.get("price"), minPrice));
        }
        if(maxPrice !=null){
            predicates.add(builder.lessThanOrEqualTo(product.get("price"), maxPrice));
        }

        cq.select(product).where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<Product> findProductsByCategory(Category category) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = builder.createQuery(Product.class);
        Root<Product> product = cq.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();
        if(category != null){
            predicates.add(builder.equal(product.get("category"), category));
        }
        cq.select(product).where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(cq).getResultList();

    }
}
