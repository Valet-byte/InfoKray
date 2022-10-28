package com.valet.infokray.repo.mapper;

import com.valet.infokray.model.Content;
import com.valet.infokray.model.ContentType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ContentMapper implements RowMapper<Content> {
    @Override
    public Content mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            return Content.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .body(rs.getString("body"))
                    .creatorId(rs.getLong("creator_id"))
                    .images(List.of((String[]) (rs.getArray("images").getArray())))
                    .type(ContentType.getContentTypeById(rs.getLong("type")))
                    .build();
        } catch (ClassCastException ignore) {
            return Content.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .body(rs.getString("body"))
                    .creatorId(rs.getLong("creator_id"))
                    .type(ContentType.getContentTypeById(rs.getLong("type")))
                    .build();
        }
    }
}
