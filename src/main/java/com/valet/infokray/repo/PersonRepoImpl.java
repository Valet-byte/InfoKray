package com.valet.infokray.repo;

import com.valet.infokray.model.Person;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PersonRepoImpl implements PersonRepo {

    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<Person> mapper;

    @Override
    public Person findById(Long id) {
        final MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", id);
        return jdbc.queryForObject("SELECT * FROM person WHERE id = :id", source, mapper);
    }

    @Override
    public List<Person> findByName(String name) {
        final MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("name", name);
        return jdbc.query("SELECT * FROM person WHERE name = :name", source, mapper);
    }

    @Override
    public Person findByEmail(String email) {
        final MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("email", email);
        return jdbc.queryForObject("SELECT * FROM person WHERE email = :email", source, mapper);
    }

    @Override
    public Person save(Person person) {
        final MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("email", person.getEmail());
        source.addValue("password", person.getPassword());
        source.addValue("name", person.getName());

        KeyHolder holder = new GeneratedKeyHolder();

        jdbc.update(
                "INSERT INTO person (name, password, email) VALUES " + "(:name, :password, :email)",
                source,
                holder);

        person.setId((Long) Objects.requireNonNull(holder.getKeys()).get("id"));
        return person;
    }

    @Override
    public boolean existPerson(Long id) {
        try {
            final MapSqlParameterSource source = new MapSqlParameterSource();
            source.addValue("id", id);
            Integer count =
                    jdbc.queryForObject(
                            "SELECT count(*) FROM person WHERE id = :id", source, Integer.class);
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean existPerson(String email) {
        try {
            final MapSqlParameterSource source = new MapSqlParameterSource();
            source.addValue("email", email);
            Integer count =
                    jdbc.queryForObject(
                            "SELECT count(*) FROM person WHERE email = :email",
                            source,
                            Integer.class);
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteAll() {
        final MapSqlParameterSource source = new MapSqlParameterSource();
        return jdbc.update("TRUNCATE TABLE person", source) > 0;
    }
}
