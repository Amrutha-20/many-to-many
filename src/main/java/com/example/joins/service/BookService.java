package com.example.joins.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.joins.model.book;
import com.example.joins.model.author;
import com.example.joins.model.genre;
import com.example.joins.repository.bookrepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class BookService {
    @Autowired
    private bookrepo brepo;
    @Autowired
    private EntityManager entityManager;

    public List<book> getBooksByAuthorAndGenre(Long authorId, Long genreId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<book> criteriaQuery = criteriaBuilder.createQuery(book.class);
        Root<book> root = criteriaQuery.from(book.class);

        // Inner join with authors
        Join<book, author> authorJoin = root.join("authors", JoinType.INNER);
        // Left outer join with genres
        Join<book, genre> genreJoin = root.join("genres", JoinType.LEFT);

        // Add conditions
        Predicate authorPredicate = criteriaBuilder.equal(authorJoin.get("id"), authorId);
        Predicate genrePredicate = criteriaBuilder.equal(genreJoin.get("id"), genreId);
        criteriaQuery.where(authorPredicate, genrePredicate);

        // Execute query
        TypedQuery<book> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List<book> createBook(List<book> bookRequests) {
        // Create a new Book entity from the request data
        List<book> createdBooks = new ArrayList<>();
        for (book bookRequest : bookRequests) {
            book createdBookk = brepo.save(bookRequest);
            createdBooks.add(createdBookk);
        }
        return createdBooks;
    }
}
