package rs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Collection;

@AllArgsConstructor
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class SuggestResponse {
    private Collection<String> suggestions;
}
