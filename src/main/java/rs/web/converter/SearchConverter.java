package rs.web.converter;

import static java.util.Collections.emptyList;
import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static org.elasticsearch.common.collect.Iterables.getFirst;
import static org.elasticsearch.common.collect.Iterables.getLast;

import org.elasticsearch.common.base.Splitter;
import org.springframework.stereotype.Component;
import rs.model.Comment;
import rs.model.Link;
import rs.model.SearchResponse;
import rs.web.model.UiLink;
import rs.web.model.UiSearchResponse;

import java.util.Collection;

@Component
public class SearchConverter {
    public UiSearchResponse convert(SearchResponse searchResponse) {
        return UiSearchResponse.builder()
                .links(convert(searchResponse.getLinks()))
                .currentPage(searchResponse.getCurrentPage())
                .topics(searchResponse.getTopics())
                .totalElements(searchResponse.getTotalElements())
                .totalPages(searchResponse.getTotalPages())
                .elementsPerPage(searchResponse.getElementsPerPage())
                .build();
    }

    public Collection<UiLink> convert(Collection<Link> links) {
        return links.stream()
                .map(this::convert)
                .collect(toList());
    }

    public UiLink convert(Link link) {
        return UiLink.builder()
                .title(link.getTitle())
                .url(link.getUrl())
                .displayedUrl(displayedUrl(link.getUrl()))
                .description(description(ofNullable(link.getComments()).orElse(emptyList())))
                .commentsUrl(link.getCommentsUrl())
                .imageUrl(imageUrl(link.getUrl()))
                .build();
    }

    public String imageUrl(String url) {
        return ofNullable(url)
                .filter(u -> u.contains("imgur.com"))
                .map(u -> getLast(Splitter.on("/").split(u)))
                .map(u -> getFirst(Splitter.on(".").split(u), ""))
                .map(u -> "http://i.imgur.com/" + u + "m.jpg")
                .orElse(null);
    }

    private String displayedUrl(String url) {
        return of(url)
                .filter(u -> u.length() > 33)
                .map(u -> u.substring(0, 30) + "...")
                .orElse(url);
    }

    private String description(Collection<Comment> comments) {
        return comments.stream()
                .limit(1)
                .map(Comment::getBody)
                .reduce((a, b) -> a + " " + b)
                .orElse("comments");
    }
}
