package by.bnd.intensive_spring.model.entity;

import lombok.*;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return id == contact.id
               && Objects.equals(firstName, contact.firstName)
               && Objects.equals(lastName, contact.lastName)
               && Objects.equals(telephone, contact.telephone)
               && Objects.equals(email, contact.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, telephone, email);
    }
}
