package by.bnd.intensive_spring.controller;

import by.bnd.intensive_spring.common.util.ServerResponseHelper;
import by.bnd.intensive_spring.model.ServerResponse;
import by.bnd.intensive_spring.model.dto.CreateContactDto;
import by.bnd.intensive_spring.model.dto.UpdateContactDto;
import by.bnd.intensive_spring.model.entity.Contact;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

@RestController
@RequestMapping("api/contact")
public class ContactController {

    //http://localhost:8080/api/contact/get
    @GetMapping("/get")
    public ResponseEntity<ServerResponse<ArrayList<Contact>>> getContacts() {
        return null;
        //return ServerResponseHelper.ok(this.contacts);
    }

    //http://localhost:8080/api/contact/getById?id=2
    @GetMapping("/get/{id}")
    public ResponseEntity<ServerResponse<Contact>> getContactById(@PathVariable int id) {
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ServerResponse<Void>> deleteContact(@PathVariable int id) {

        return null;
//        if (canRemove) {
//            return ServerResponseHelper.ok(null);
//        } else return ServerResponseHelper.notFound(null,
//                Collections.singletonList("Контакт с указанным id не найден"));
    }

    @PostMapping("/create")
    public ResponseEntity<ServerResponse<Contact>> addContact(
            @Valid @RequestBody CreateContactDto createContactDto) {
        return null;

//        boolean emailExists = contacts.stream()
//                .anyMatch(c -> c.getEmail().equalsIgnoreCase(createContactDto.getEmail()));
//        if (emailExists) {
//            return ServerResponseHelper.conflict(null,
//                    Collections.singletonList("Контакт с таким email уже существует"));
//        }
//        Contact contact = modelMapper.map(createContactDto, Contact.class);
//        contact.setId(newId);
//
//        contacts.add(contact);
//
//        return ServerResponseHelper.created(contact);
    }

    @PutMapping("/update")
    public ResponseEntity<ServerResponse<Contact>> updateContact(
            @Valid @RequestBody UpdateContactDto updateContactDto) {

//        // Ищем контакт для обновления по ID
//        Contact existingContact = contacts.stream()
//                .filter(c -> c.getId() == updateContactDto.getId())
//                .findFirst()
//                .orElse(null);
//
//        // Если контакт с таким ID не нашли, выдаем response notFound и ошибку
//        if (existingContact == null) {
//            return ServerResponseHelper.notFound(null,
//                    Collections.singletonList("Update: Контакт с указанным ID не найден"));
//        }
//        // Проверяем, есть ли контакт с таким email уже существует, выдаем response conflict и ошибку
//        boolean emailExist = contacts.stream()
//                .filter(c -> c.getId() != updateContactDto.getId())
//                .anyMatch(c -> c.getEmail().equalsIgnoreCase(updateContactDto.getEmail()));
//        if (emailExist) {
//            return  ServerResponseHelper.conflict(null,
//                    Collections.singletonList("Update: Контакт с указанным email уже существует"));
//        }
//        // Проверяем, есть ли контакт с таким телефоном уже существует, выдаем response conflict и ошибку
//        boolean phoneExist = contacts.stream()
//                .filter(c -> c.getId() != updateContactDto.getId())
//                .anyMatch(c -> c.getTelephone().equals(updateContactDto.getTelephone()));
//        if (phoneExist) {
//            return  ServerResponseHelper.conflict(null,
//                    Collections.singletonList("Update: Контакт с указанным телефоном уже существует"));
//        }
//
//        // Маппим updateContactDto в Contact с помощью библиотеки modelMapper
//        Contact updatedContact = modelMapper.map(updateContactDto, Contact.class);
//
//        // Запоминаем индекс обновляемого контакта
//        int index = contacts.indexOf(existingContact);
//
//        // Обновляем контакт новым контактом updateContact
//        contacts.set(index, updatedContact);
//
//        return ServerResponseHelper.ok(updatedContact);
        return null;
    }
}
