package rs.dao;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import rs.model.Link;

import java.util.Collection;

@Repository
public class LinkDao extends AbstractDao<Link> implements ModelDao<Link> {
    @Value("${rs.index.name:rs}")
    String indexName;

    @Override
    public Collection<Link> get(int pageNumber, int size) {
        return get(RsQuery.builder()
                .queryBuilder(matchAllQuery())
                .clazz(Link.class)
                .type("link")
                .index(indexName)
                .sortDesc(true)
                .sortField("score")
                .pageNumber(pageNumber)
                .size(size)
                .build());
    }
}
