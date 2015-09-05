package rs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.dao.ModelDao;
import rs.model.Link;

import java.util.Collection;

@Service
public class LinkManager implements ModelManager<Link> {
    @Autowired
    private ModelDao<Link> modelDao;

    @Override
    public Collection<Link> get(Integer pageNumber, Integer size) {
        return modelDao.get(pageNumber, size);
    }
}
