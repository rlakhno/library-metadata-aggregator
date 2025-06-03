package com.example.libraryapi.repository;

import com.example.libraryapi.model.Lending;

import java.util.List;

public interface LendingRepository {

    // For overdue
    List<Lending> findByReturnDateIsNull();
}
