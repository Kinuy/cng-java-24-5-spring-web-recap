package springweb.cngjava245springwebrecap;

import lombok.Builder;
import lombok.With;
import org.springframework.data.annotation.Id;

@With
@Builder
public record ToDoDto(
        @Id String id,
        String description,
        String status
) {
}
