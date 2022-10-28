package com.valet.infokray.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Content {
    private Long id;
    private String name;
    private String body;
    private List<String> images;
    private ContentType type;
    private Long creatorId;
}
