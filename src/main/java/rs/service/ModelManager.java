package rs.service;

import java.util.Collection;

public interface ModelManager<T> {
    Collection<T> get(Integer pageNumber, Integer size);
}
