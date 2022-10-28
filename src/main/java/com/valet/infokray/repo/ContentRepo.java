package com.valet.infokray.repo;

import com.valet.infokray.model.Content;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ContentRepo {
    Content saveContent(Content content);

    List<Content> search(String text, Pageable pageable);

    boolean deleteAll();
}
