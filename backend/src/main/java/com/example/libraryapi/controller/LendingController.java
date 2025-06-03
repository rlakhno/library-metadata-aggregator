package com.example.libraryapi.controller;

import com.example.libraryapi.model.Lending;
import com.example.libraryapi.service.LendingService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lendings")
public class LendingController {

    @Autowired
    private LendingService lendingService;

    @Operation(summary = "Create a new lending record")
    @PostMapping
    public ResponseEntity<Lending> saveLending(@RequestBody Lending lending) {
        Lending saved = lendingService.saveLending(lending);
        return ResponseEntity.ok(saved);
    }

    @Operation(summary = "Get all the lending records")
    @GetMapping
    public ResponseEntity<List<Lending>> getAllLendings() {
        return ResponseEntity.ok(lendingService.getAllLendings());
    }

    @Operation(summary = "Get lending records by user email")
    @GetMapping("/user-email")
    public ResponseEntity<List<Lending>> getLendingsByUserEmail(@RequestParam String email) {
        return ResponseEntity.ok(lendingService.getLendingsByUserEmail(email));
    }
}
