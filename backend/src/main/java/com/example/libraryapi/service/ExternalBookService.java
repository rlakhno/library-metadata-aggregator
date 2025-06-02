package com.example.libraryapi.service;

import com.example.libraryapi.model.Book;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ExternalBookService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Book fetchFromGoogleBooks(String isbn) {
        String url = UriComponentsBuilder.fromHttpUrl("https://www.googleapis.com/books/v1/volumes")
                .queryParam("q", "isbn:" + isbn)
                .toUriString();

        try {
            String response = restTemplate.getForObject(url, String.class);
            JsonNode root = objectMapper.readTree(response);
            JsonNode items = root.path("items");

            if (!items.isArray() || items.isEmpty()) {
                throw new RuntimeException("No book found for ISBN: " + isbn);
            }

            JsonNode volumeInfo = items.get(0).path("volumeInfo");
            String title = volumeInfo.path("title").asText("N/A");

            // Join authors into a comma-separated string
            StringBuilder authorBuilder = new StringBuilder();
            JsonNode authorsNode = volumeInfo.path("authors");
            if (authorsNode.isArray()) {
                for (int i = 0; i < authorsNode.size(); i++) {
                    authorBuilder.append(authorsNode.get(i).asText());
                    if (i < authorsNode.size() - 1) authorBuilder.append(", ");
                }
            }

            String description = volumeInfo.path("description").asText("No description");
            String publishedDate = volumeInfo.path("publishedDate").asText("");
            int publishedYear = 0;
            if (publishedDate.length() >= 4) {
                try {
                    publishedYear = Integer.parseInt(publishedDate.substring(0, 4));
                } catch (Exception ignored) {}
            }

            return Book.builder()
                    .title(title)
                    .author(authorBuilder.toString())
                    .description(description)
                    .publishedYear(publishedYear)
                    .isbn(isbn)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch book from Google Books API: " + e.getMessage(), e);
        }
    }
}
