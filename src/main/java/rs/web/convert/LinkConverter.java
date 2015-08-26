package rs.web.convert;

import org.springframework.stereotype.Component;
import rs.model.Link;
import rs.web.ui.LinkUi;

@Component
public class LinkConverter implements Converter<Link, LinkUi> {

    @Override
    public LinkUi convert(Link link) {
        return null;
    }
}
