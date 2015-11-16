package rs.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import rs.model.Facet;

import java.util.Collection;

@AllArgsConstructor
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class UiSearchResponse {
    private Long totalElements;
    private Integer currentPage;
    private Integer totalPages;
    private Integer elementsPerPage;
    private Collection<UiLink> links;
    private Collection<Facet> topics;
}
