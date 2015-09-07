package rs.dao;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RsQuery {
    private int pageNumber;
    private int size;
    private Class clazz;
    private String type;
}
