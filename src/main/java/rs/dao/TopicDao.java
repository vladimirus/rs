package rs.dao;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import rs.model.Topic;

import java.util.Collection;

@Repository
public class TopicDao extends AbstractDao<Topic> implements ModelDao<Topic> {
    @Value("${rs.index.name:rs}")
    String indexName;

    @Override
    public Collection<Topic> get(int pageNumber, int size) {
        return get(RsQuery.builder()
                .queryBuilder(matchAllQuery())
                .clazz(Topic.class)
                .type("topic")
                .index(indexName)
                .sortDesc(true)
                .sortField("subscribers")
                .pageNumber(pageNumber)
                .size(size)
                .build());
    }
}
