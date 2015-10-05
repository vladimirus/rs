package rs.dao;

import rs.model.SearchResponse;

public interface SearchDao {
    SearchResponse search(String query, Integer pageNo);
}
