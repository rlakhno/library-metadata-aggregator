package com.example.libraryapi.repository;

import com.example.libraryapi.model.Lending;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LendingRepository extends JpaRepository<Lending, Long> {

    // For overdue
    List<Lending> findByReturnDateIsNull();

    // For support filtering by user email
    List<Lending> findByUserEmail(String email);

}
