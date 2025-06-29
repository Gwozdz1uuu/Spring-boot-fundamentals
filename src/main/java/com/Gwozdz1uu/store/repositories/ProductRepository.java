package com.Gwozdz1uu.store.repositories;

import com.Gwozdz1uu.store.dtos.ProductSummary;
import com.Gwozdz1uu.store.dtos.ProductSummaryDTO;
import com.Gwozdz1uu.store.entities.Category;
import com.Gwozdz1uu.store.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> , ProductCriteriaRepository , JpaSpecificationExecutor<Product> {
    List<Product> findByName(String name);
    List<Product> findByNameLike(String name);
    List<Product> findByNameNotLike(String name);
    List<Product> findByNameContaining(String name);
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByNameStartingWith(String name);
    List<Product> findByNameStartingWithIgnoreCase(String name);

    List<Product> findByPrice(BigDecimal price);
    List<Product> findByPriceGreaterThan(BigDecimal price);
    List<Product> findByPriceGreaterThanEqual(BigDecimal price);
    List<Product> findByPriceLessThanEqual(BigDecimal price);
    List<Product> findByPriceBetween(BigDecimal minPrice,BigDecimal maxPrice);

    List<Product> findByDescriptionNullAndNameNull();

    List<Product> findByNameOrderByPriceAsc(String name);

    List<Product> findTop5ByNameLikeOrderByPrice(String name);
    List<Product> findFirst5ByNameLikeOrderByPrice(String name);

    //Find products whose prices are in given range and sort by name

    @Procedure("findProductByPrice")
    List<Product> findProducts(BigDecimal minPrice, BigDecimal maxPrice);

    @Query("select count(*) from Product p where p.price between :min and :max ")
    Long countProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    @Modifying
    @Query("update Product p set p.price = :newPrice where p.category.id = :categoryId")
    void updatePriceByCategory(BigDecimal newPrice, Byte categoryId);

    @Query("select new com.Gwozdz1uu.store.dtos.ProductSummaryDTO(p.id, p.name) from Product p where p.category = :category")
    List<ProductSummaryDTO> findByCategory(@Param("category") Category category);
}