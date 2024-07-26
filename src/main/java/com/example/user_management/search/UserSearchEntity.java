package com.example.user_management.search;

import com.example.user_management.entity.User;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserSearchEntity extends SearchEntity<User> {

    @Override
    protected String buildQuery(Map<String, String> filters) {
        StringBuilder queryString = new StringBuilder("SELECT * FROM tbl_user WHERE 1=1");

        filters.forEach((key, value) -> {
            if (value != null && !value.isEmpty()) {
                queryString.append(" AND LOWER(").append(key).append(") LIKE :").append(key);
            }
        });

        return queryString.toString();
    }

    @Override
    protected String buildCountQuery(Map<String, String> filters) {
        StringBuilder countQueryString = new StringBuilder("SELECT COUNT(*) FROM tbl_user WHERE 1=1");

        filters.forEach((key, value) -> {
            if (value != null && !value.isEmpty()) {
                countQueryString.append(" AND LOWER(").append(key).append(") LIKE :").append(key);
            }
        });

        return countQueryString.toString();
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
