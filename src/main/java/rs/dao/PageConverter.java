package rs.dao;


import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.stereotype.Component;
import rs.model.Link;
import rs.model.SearchResponse;

@Component
public class PageConverter {

    public SearchResponse convert(FacetedPage<Link> page) {
        return SearchResponse.builder().links(page.getContent())
                .totalElements(page.getTotalElements())
                .currentPage(page.getNumber())
                .totalPages(page.getTotalPages())
                .build();
    }
}
