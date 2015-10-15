package rs.dao;

import rs.model.SearchResponse;
import rs.model.SuggestResponse;

public interface SearchDao {
    SearchResponse search(String query, Integer pageNo);
    SuggestResponse suggest(String query);
}
