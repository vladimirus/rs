package rs.web.api;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.model.SearchRequest;
import rs.service.QueryParser;
import rs.web.model.UiQuery;

@RestController
@RequestMapping("/api/query")
public class QueryController {
    @Autowired
    private QueryParser queryParser;

    @RequestMapping(value = "/{query}", method = GET)
    public UiQuery parse(@PathVariable("query") String query) {
        SearchRequest request = queryParser.parse(query).build();

        StringBuilder builder = new StringBuilder();
        builder.append(request.getQuery());

        if (request.getTopics() != null && !request.getTopics().isEmpty()) {
            builder.append(" subreddit: ")
                    .append(request.getTopics().stream()
                            .reduce((a, b) -> a + ", " + b).orElse(""));
        }

        return UiQuery.builder().query(builder.toString()).build();
    }
}
