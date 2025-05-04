package by.bnd.intensive_spring.model.dto;

import by.bnd.intensive_spring.model.entity.Contact;
import by.bnd.intensive_spring.model.enums.AppRole;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateContactOwnerDto {

    @NotEmpty(message = "Имя пользователя не может быть пустым")
    @Size(min = 6, max = 20, message = "Имя пользователя должно быть от 6 до 20 символов")
    private String username;

    @NotNull(message = "Значение null в description не допустимо")
    private String description;

    @Email(message = "Некорректный email")
    @NotEmpty(message = "Email не может быть пустым")
    private String email;

//    @Size(min = 2, max = 50, message = "Полное имя должно быть от 6 до 20 символов")
//    @NotEmpty(message = "Полное имя пользователя не может быть пустым")
//    private String fullName;

    @NotBlank(message = "Поле password не может быть пустым")
    // ^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)[a-zA-Z0-9]{8,}$
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[a-zA-Z0-9]{8,}$",
            message = "Пароль должен содержать минимум 8 символов, включая заглавные, строчные буквы, и цифпы")
    private String password;
}
