package rs.dao;

import org.springframework.stereotype.Repository;
import rs.model.Comment;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Repository
public class CommentDao extends AbstractDao<Comment> implements ModelDao<Comment> {
    @Override
    public Collection<Comment> get(int pageNumber, int size) {
        return get(RsQuery.builder()
                .clazz(Comment.class)
                .type("comment")
                .index("rs")
                .sortDesc(true)
                .sortField("score")
                .pageNumber(pageNumber)
                .size(size)
                .build());
    }

    public Optional<Comment> getTopComment(String linkId) {
        return Optional.of(Comment.builder()
                .id("id")
                .author("author")
                .parentId("parentId")
                .topic("funny")
                .topicId("funnyId")
                .linkId("linkId")
                .bodyHTML("<p>this is comment body for linkid</p>")
                .body("comments")
                .created(new Date())
                .score(1L)
                .build());
    }
}
