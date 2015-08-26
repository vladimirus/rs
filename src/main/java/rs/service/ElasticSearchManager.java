package rs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.dao.SearchDao;
import rs.model.Link;

import java.util.Collection;

@Service
public class ElasticSearchManager implements SearchManager {
    @Autowired
    private SearchDao searchDao;

    @Override
    public Collection<Link> search(String query) {
        return searchDao.search(query);
    }
}
