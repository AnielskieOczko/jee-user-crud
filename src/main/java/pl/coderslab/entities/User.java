package pl.coderslab.entities;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
    private int id;
    private String userName;
    private String email;
    private String password;

}
