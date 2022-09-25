package com.bridgelabz.bookstorebookservice.repository;

import com.bridgelabz.bookstorebookservice.model.BookServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookServiceRepository extends JpaRepository<BookServiceModel, Long>{

}
