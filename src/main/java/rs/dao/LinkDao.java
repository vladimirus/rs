package rs.dao;

import org.springframework.stereotype.Repository;
import rs.model.Link;

import java.util.Collection;

@Repository
public class LinkDao extends AbstractDao<Link> implements ModelDao<Link> {
    @Override
    public Collection<Link> get(int pageNumber, int size) {
        return get(RsQuery.builder()
                .clazz(Link.class)
                .type("link")
                .index("rs")
                .sortDesc(true)
                .sortField("score")
                .pageNumber(pageNumber)
                .size(size)
                .build());
    }
}
