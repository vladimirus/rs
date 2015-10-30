package rs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class SearchRequest {
    @NonNull
    private String query;
    private Integer pageNo = 0;
}
