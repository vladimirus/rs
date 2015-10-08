package rs.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.model.Link;
import rs.service.SearchManager;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/suggest")
public class SuggestController {
    @Autowired
    private SearchManager searchManager;

    @RequestMapping(value = "/{query}", method = RequestMethod.GET)
    public Collection<String> suggest(@PathVariable("query") String query) {

        return searchManager.search(query, 0).getLinks().stream()
                .map(Link::getTitle)
                .collect(Collectors.toList());
    }
}
