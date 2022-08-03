package com.springboot.blogimetro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.blogimetro.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{
List<Article> findBySlug(String slug);

@Query(value = "select * from articles where slug =:slug", nativeQuery = true)
Article findOneBySlug(String slug);

@Query(value = "select * from articles AS a JOIN categories AS c ON a.category_id = c.id where c.slug =:slug", nativeQuery = true)
List<Article> findAllByCategorySlug(String slug);

}
