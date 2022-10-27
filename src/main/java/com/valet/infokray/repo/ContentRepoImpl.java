package com.valet.infokray.repo;

import com.valet.infokray.model.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class ContentRepoImpl implements ContentRepo {

    private final RowMapper<Content> rowMapper;
    private final NamedParameterJdbcTemplate template;

    @Override
    public Content saveContent(Content content) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("name", content.getName());
        source.addValue("creator_id", content.getCreatorId());
        source.addValue("body", content.getBody());
        source.addValue("type", content.getType().getId());
        source.addValue("images", createSqlArray(content.getImages()));

        KeyHolder holder = new GeneratedKeyHolder();

        template.update("INSERT INTO content (name, body, images, type, creator_id) VALUES " +
                "(:name, :body, :images, :type, :creator_id)", source, holder);
        content.setId((Long) Objects.requireNonNull(holder.getKeys()).get("id"));
        return content;
    }

    @Override
    public List<Content> search(String text, Pageable pageable) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("limit", pageable.getPageSize());
        source.addValue("offset", pageable.getOffset());

        return template.query("SELECT * FROM content WHERE name ILIKE '%" + text + "%' OR " +
                "body ILIKE '%" + text + "%' LIMIT :limit OFFSET :offset", source, rowMapper);
    }

    private java.sql.Array createSqlArray(List<String> list){
        java.sql.Array intArray = null;
        try {
            intArray = Objects.requireNonNull(template.getJdbcTemplate().getDataSource()).getConnection().createArrayOf("text", list.toArray());
        } catch (SQLException ignore) {
        }
        return intArray;
    }


}
