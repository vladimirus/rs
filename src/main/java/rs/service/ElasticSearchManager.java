package rs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.dao.SearchDao;
import rs.model.SearchResponse;

@Service
public class ElasticSearchManager implements SearchManager {
    @Autowired
    private SearchDao searchDao;

    @Override
    public SearchResponse search(String query, Integer pageNo) {
        return searchDao.search(query, 0);
    }
}
