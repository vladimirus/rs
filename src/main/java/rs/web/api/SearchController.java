package rs.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.service.SearchManager;
import rs.web.converter.SearchConverter;
import rs.web.model.UiSearchResponse;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    private SearchManager searchManager;
    @Autowired
    private SearchConverter searchConverter;

    @RequestMapping(value = "/{query}", method = RequestMethod.GET)
    public UiSearchResponse search(@PathVariable("query") String query,
                                 @RequestParam(value="p", defaultValue="1") Integer pageNumber,
                                 @RequestParam(value="t", defaultValue="web") String type
    ) {
        return searchConverter.convert(searchManager.search(query, pageNumber - 1, type.toLowerCase()));
    }
}
