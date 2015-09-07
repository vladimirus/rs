package rs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.dao.ModelDao;
import rs.model.Topic;

import java.util.Collection;

@Service
public class TopicManager implements ModelManager<Topic> {
    @Autowired
    private ModelDao<Topic> modelDao;

    @Override
    public Collection<Topic> get(Integer pageNumber, Integer size) {
        return modelDao.get(pageNumber, size);
    }
}
