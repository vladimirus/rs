package rs.dao;

import rs.model.Link;

import java.util.Collection;

public interface SearchDao {
    Collection<Link> search(String query, Integer pageNo);
}
