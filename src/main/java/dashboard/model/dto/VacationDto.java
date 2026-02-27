package dashboard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data @Builder
public class VacationDto {
    Integer vcode;
    String vstart;
    String vend;
    String vreason;
    Integer scode;
}
