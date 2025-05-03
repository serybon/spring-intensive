package by.bnd.intensive_spring.model.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    private int id;
    private String firstName;
    private String lastName;
    private String telephone;
    private String email;
}
