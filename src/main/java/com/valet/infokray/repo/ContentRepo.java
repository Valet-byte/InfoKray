package com.valet.infokray.repo;

import com.valet.infokray.model.Content;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContentRepo {
    Content saveContent(Content content);
    List<Content> search(String text, Pageable pageable);
    boolean deleteAll();
}
