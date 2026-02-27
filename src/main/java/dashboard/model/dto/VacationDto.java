package dashboard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data @Builder
public class VacationDto {
   private Integer vcode;
   private String vstart;
   private String vend;
   private String vreason;
   private Integer scode;
}
