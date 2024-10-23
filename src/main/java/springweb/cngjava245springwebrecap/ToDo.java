package springweb.cngjava245springwebrecap;

import lombok.Builder;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@With
@Builder
@Document("todo")
public record ToDo(
        @Id String id,
        String description,
        String status
) {
}
