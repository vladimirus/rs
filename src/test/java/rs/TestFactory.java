package rs;

import rs.model.Link;

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
}
