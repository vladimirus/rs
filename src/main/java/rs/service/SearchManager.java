package rs.service;

import rs.model.SearchResponse;
import rs.model.SuggestResponse;

public interface SearchManager {
    SearchResponse search(String query, Integer pageNo);
    SuggestResponse suggest(String query);
}
