package rs.dao;

import org.springframework.stereotype.Repository;
import rs.model.Link;

@Repository
public class LinkDao extends AbstractDao<Link> implements ModelDao<Link> {
    @Override
    public Class<Link> modelClass() {
        return Link.class;
    }
}
