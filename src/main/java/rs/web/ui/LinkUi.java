package rs.web.ui;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class LinkUi {
    private String url;
    private String title;
    private String topic;
    private Long score;
    private Date created;
}
