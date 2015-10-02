package rs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.dao.CommentDao;
import rs.model.Comment;

import java.util.Collection;
import java.util.Optional;

@Service
public class CommentManager implements ModelManager<Comment> {
    @Autowired
    private CommentDao commentDao;

    @Override
    public Collection<Comment> get(Integer pageNumber, Integer size) {
        return commentDao.get(pageNumber, size);
    }

    public Optional<Comment> getTopComment(String linkId) {
        return commentDao.getTopComment(linkId);
    }
}
