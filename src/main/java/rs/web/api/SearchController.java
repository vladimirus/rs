package rs.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.model.SearchResponse;
import rs.service.SearchManager;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    private SearchManager searchManager;

    @RequestMapping(value = "/{query}", method = RequestMethod.GET)
    public SearchResponse search(@PathVariable("query") String query,
                                   @RequestParam(value="p", defaultValue="0") Integer pageNumber) {
        return searchManager.search(query, pageNumber);
    }
}
