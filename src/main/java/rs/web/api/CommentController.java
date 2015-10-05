package rs.web.api;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.model.Comment;
import rs.service.CommentManager;

import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentManager commentManager;

    @RequestMapping(method = GET)
    public Collection<Comment> list(@RequestParam(value="pageNumber", defaultValue="0") Integer pageNumber,
                                 @RequestParam(value="size", defaultValue="50") Integer size) {
        return commentManager.get(pageNumber, size);
    }

    @RequestMapping(value = "/linkid/{linkid}", method = GET)
    public Comment getTopByLinkId(@PathVariable("linkid") String linkId) {
        return commentManager.getTopComment(linkId).orElse(defaultComment());
    }

    private Comment defaultComment() {
        return Comment.builder()
                .id("")
                .author("")
                .parentId("")
                .topic("")
                .topicId("")
                .linkId("")
                .bodyHTML("comments")
                .body("comments")
                .created(new Date())
                .score(1L)
                .build();
    }
}
