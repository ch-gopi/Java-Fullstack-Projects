package To_Do_Management_System.Todo_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tododto {

        private Long id;

        private String title;

        private String description;
        private boolean completed;
}
