package com.in.sakilaproject.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in.sakilaproject.entities.Category;

//import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category getCategoryByCategoryId(Integer id);
}
