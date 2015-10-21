package rs.dao;

import static java.util.Optional.empty;
import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import rs.model.Comment;

import java.util.Collection;
import java.util.Optional;

@Repository
public class CommentDao extends AbstractDao<Comment> implements ModelDao<Comment> {
    @Value("${rs.index.name:rs}")
    String indexName;

    @Override
    public Collection<Comment> get(int pageNumber, int size) {
        return get(RsQuery.builder()
                .queryBuilder(matchAllQuery())
                .clazz(Comment.class)
                .type("comment")
                .index(indexName)
                .sortDesc(true)
                .sortField("score")
                .pageNumber(pageNumber)
                .size(size)
                .build());
    }

    public Optional<Comment> getTopComment(String linkId) {
        Optional<Comment> result = empty();

        Collection<Comment> comments = get(RsQuery.builder()
                .queryBuilder(matchQuery("linkId", linkId))
                .clazz(Comment.class)
                .type("comment")
                .index("rs")
                .sortDesc(true)
                .sortField("score")
                .pageNumber(0)
                .size(1)
                .build());

        if (comments != null && !comments.isEmpty()) {
            result = Optional.ofNullable(comments.iterator().next());
        }

        return result;
    }

}
