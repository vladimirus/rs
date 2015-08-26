package rs.service;

import rs.model.Link;

import java.util.Collection;

public interface SearchManager {
    Collection<Link> search(String query);
}
