package rs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.completion.Completion;

@NoArgsConstructor //this needed for ES
@AllArgsConstructor
@Builder
@Getter
@ToString
@EqualsAndHashCode
@Document(indexName = "rs", type = "suggestion")
public class CompletionEntity {
    private String id;
    private String name;
    private Completion suggest;
}
