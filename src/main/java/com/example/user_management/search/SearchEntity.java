package com.example.user_management.search;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Map;

public abstract class SearchEntity<T> {
    @PersistenceContext
    protected EntityManager entityManager;

    protected abstract String buildQuery(Map<String, String> filters);

    protected abstract String buildCountQuery(Map<String, String> filters);

    public List<T> getResult(Map<String, String> filters) {
        String queryString = buildQuery(filters);

        var query = entityManager.createNativeQuery(queryString, getEntityClass());

        filters.forEach((key, value) -> {
            query.setParameter(key, "%" + value.toLowerCase() + "%");
        });

        return query.getResultList();
    }

    public long getCount(Map<String, String> filters) {
        String countQueryString = buildCountQuery(filters);

        var countQuery = entityManager.createNativeQuery(countQueryString);

        filters.forEach((key, value) -> {
            countQuery.setParameter(key, "%" + value.toLowerCase() + "%");
        });

        return ((Number) countQuery.getSingleResult()).longValue();
    }

    protected abstract Class<T> getEntityClass();
}
