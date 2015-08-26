package rs.web.convert;

import org.springframework.stereotype.Component;
import rs.model.Link;
import rs.web.ui.LinkUi;

@Component
public class LinkConverter implements Converter<Link, LinkUi> {

    @Override
    public LinkUi convert(Link link) {
        return LinkUi.builder()
                .title(link.getTitle())
                .url(link.getUrl())
                .score(link.getScore())
                .created(link.getCreated())
                .topic(link.getTopic())
                .build();
    }
}
