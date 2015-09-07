package rs.dao;

import org.springframework.stereotype.Repository;
import rs.model.Topic;

import java.util.Collection;

@Repository
public class TopicDao extends AbstractDao<Topic> implements ModelDao<Topic> {

    @Override
    public Collection<Topic> get(int pageNumber, int size) {
        return get(RsQuery.builder()
                .clazz(Topic.class)
                .type("topic")
                .index("rs")
                .sortDesc(true)
                .sortField("subscribers")
                .pageNumber(pageNumber)
                .size(size)
                .build());
    }
}
