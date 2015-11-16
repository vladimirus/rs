package rs.web.model;

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
public class UiLink {
    @NonNull
    private String url;
    @NonNull
    private String displayedUrl;
    @NonNull
    private String title;
    @NonNull
    private String description;
    @NonNull
    private String commentsUrl;

    private String imageUrl;
}
