package rs.web.api;

import static java.util.stream.Collectors.toList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.model.Link;
import rs.service.SearchManager;
import rs.web.convert.Converter;
import rs.web.ui.LinkUi;

import java.util.Collection;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    private SearchManager searchManager;
    @Autowired
    private Converter<Link, LinkUi> linkConverter;

    @RequestMapping(value = "/{query}", method = RequestMethod.GET)
    public Collection<LinkUi> search(@PathVariable("query") String query) {
        return searchManager.search(query).stream()
                .map(linkConverter::convert)
                .collect(toList());
    }
}
