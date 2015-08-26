package rs.web.ui;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Link {
    private String url;
    private String title;
    private String description;
}
