package rs.web.api;

import static java.util.stream.Collectors.toList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.model.Link;
import rs.service.SearchManager;

import java.util.Collection;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    private SearchManager searchManager;

    @RequestMapping(value = "/{query}", method = RequestMethod.GET)
    public Collection<Link> search(@PathVariable("query") String query,
                                   @RequestParam(value="pageNumber", defaultValue="0") Integer pageNumber) {
        return searchManager.search(query, pageNumber).stream()
                .collect(toList());
    }
}
