package rs;

import rs.model.Comment;
import rs.model.Link;
import rs.model.Topic;

import java.util.Date;

public class TestFactory {

    private TestFactory() {
        //don't instantiate
    }

    public static Link aLink(String id) {
        return Link.builder()
                .id(id)
                .title("title")
                .url("http://url")
                .commentsUrl("http://comments")
                .author("author")
                .topic("topic")
                .topicId("topicId")
                .commentCount(1l)
                .score(1l)
                .created(new Date())
                .self(false)
                .nsfw(false)
                .hidden(false)
                .build();
    }

    public static Topic aTopic(String id) {
        return Topic.builder()
                .id(id)
                .displayName(id)
                .title("title")
                .created(new Date())
                .updated(new Date())
                .nsfw(false)
                .subscribers(1L)
                .description("this is description")
                .type("public")
                .build();
    }

    public static Comment aComment(String id) {
        return Comment.builder()
                .id(id)
                .author("author")
                .parentId("parentId")
                .topic("funny")
                .topicId("funnyId")
                .linkId("linkId")
                .bodyHTML("<p>test</p>")
                .body("test")
                .created(new Date())
                .score(1L)
                .build();
    }
}
