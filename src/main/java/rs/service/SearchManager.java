package rs.service;

import rs.model.SearchResponse;

public interface SearchManager {
    SearchResponse search(String query, Integer pageNo);
}
