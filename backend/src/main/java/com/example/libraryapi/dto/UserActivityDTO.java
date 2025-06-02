package com.example.libraryapi.dto;

public class UserActivityDTO {

    private String email;
    private String role;
    private Long bookCount;

    public UserActivityDTO(){

    }

    public UserActivityDTO(String email, String role, Long bookCount) {
        this.email = email;
        this.role = role;
        this.bookCount = bookCount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getBookCount() {
        return bookCount;
    }

    public void setBookCount(Long bookCount) {
        this.bookCount = bookCount;
    }

    @Override
    public String toString() {
        return "UserActivityDTO{" +
                "email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", bookCount=" + bookCount +
                '}';
    }
}


