package pe.com.pragma.r2dbc.entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {

    @Id
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String dni;
    private String phone;
    private Long roleId;
    private String baseSalary;

}