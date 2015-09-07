package rs.dao;

import org.springframework.stereotype.Repository;
import rs.model.Topic;

@Repository
public class TopicDao extends AbstractDao<Topic> implements ModelDao<Topic> {
    @Override
    public Class<Topic> modelClass() {
        return Topic.class;
    }
}
