package rs.dao;


import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.core.facet.FacetResult;
import org.springframework.data.elasticsearch.core.facet.result.TermResult;
import org.springframework.stereotype.Component;
import rs.model.Facet;
import rs.model.Link;
import rs.model.SearchResponse;

import java.util.Collection;
import java.util.List;

@Component
public class PageConverter {

    public SearchResponse convert(FacetedPage<Link> page) {
        return SearchResponse.builder().links(page.getContent())
                .totalElements(page.getTotalElements())
                .currentPage(page.getNumber())
                .totalPages(page.getTotalPages())
                .topics(facets(page.getFacets(), "topics"))
                .build();
    }

    private Collection<Facet> facets(List<FacetResult> facets, String name) {
        if(facets == null) {
            return emptyList();
        }

        return facets.stream()
                .filter(facet -> facet.getName().equals(name))
                .flatMap(facet -> ((TermResult) facet).getTerms().stream())
                .map(term -> Facet.builder().term(term.getTerm()).count(term.getCount()).build())
                .collect(toList());
    }
}
