package rs.web.ui;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LinkUi {
    private String url;
    private String title;
}
