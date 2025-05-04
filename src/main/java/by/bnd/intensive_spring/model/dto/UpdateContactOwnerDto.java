package by.bnd.intensive_spring.model.dto;

import by.bnd.intensive_spring.model.entity.Contact;
import by.bnd.intensive_spring.model.enums.AppRole;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UpdateContactOwnerDto {
    @NotBlank(message = "ID обязателен")
    private String id;

    @NotBlank(message = "Имя пользователя не может быть пустым")
    @Size(min = 2, max = 50, message = "Имя пользователя должно быть от 2 до 50 символов")
    private String username;

    @NotNull(message = "Значение null в description не допустимо")
    private String description;

    @NotBlank(message = "Email обязателен")
    @Email(message = "Некорректный emal")
    private String email;

//    @Size(min = 2, max = 50, message = "Полное имя должно быть от 6 до 20 символов")
//    @NotEmpty(message = "Полное имя пользователя не может быть пустым")
//    private String fullName;

}
