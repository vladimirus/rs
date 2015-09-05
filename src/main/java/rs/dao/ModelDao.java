package rs.dao;


import java.util.Collection;

public interface ModelDao<T> {
    Collection<T> get(int pageNumber, int size);
}
