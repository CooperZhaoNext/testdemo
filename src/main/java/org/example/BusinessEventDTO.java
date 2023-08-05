package org.example;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessEventDTO {

    private String payload;

    private String type;

    private Instant completedAt;

    private Boolean isError;

    private String correlationId;

    private Boolean isCommand;

    private String publishedBy;

    private String entityId;

    private String source;

}
