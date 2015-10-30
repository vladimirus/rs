package rs.dao;

import rs.model.SearchRequest;
import rs.model.SearchResponse;
import rs.model.SuggestResponse;

public interface SearchDao {
    SearchResponse search(SearchRequest request);
    SuggestResponse suggest(String query);
}
