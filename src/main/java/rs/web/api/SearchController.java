package rs.web.api;

import static java.util.Arrays.asList;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.web.ui.Link;

import java.util.Collection;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @RequestMapping(value = "/{query}", method = RequestMethod.GET)
    public Collection<Link> search(@PathVariable("query") String query) {
        return asList(
                Link.builder().url("http://google.com").title(query).build(),
                Link.builder().url("http://google.com").title("two").build(),
                Link.builder().url("http://google.com").title("three").build()
        );
    }
}
