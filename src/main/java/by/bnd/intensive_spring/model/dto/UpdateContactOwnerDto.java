package by.bnd.intensive_spring.model.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

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
}
