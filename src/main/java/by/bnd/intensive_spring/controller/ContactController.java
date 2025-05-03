package by.bnd.intensive_spring.controller;

import by.bnd.intensive_spring.common.util.ServerResponseHelper;
import by.bnd.intensive_spring.model.ServerResponse;
import by.bnd.intensive_spring.model.entity.Contact;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;

@RestController
@RequestMapping("api/contact")
public class ContactController {

    private ArrayList<Contact> contacts = new ArrayList<>();

    public ContactController() {
        for (int i = 1; i < 10; i++) {
            contacts.add(new Contact(
                    i,
                    "Имя" + i,
                    "Фамилия" + i,
                    "37529000000" + i,
                    "user" + i + "@gmail.com"));
        }

    }


    //http://localhost:8080/api/contact/get
    @GetMapping("/get")
    public ResponseEntity<ServerResponse<ArrayList<Contact>>> getContacts() {
        return ServerResponseHelper.ok(this.contacts);
    }

    //http://localhost:8080/api/contact/getById?id=2
    @GetMapping("/get/{id}")
    public ResponseEntity<ServerResponse<Contact>> getContactById(@PathVariable int id) {
        Contact contact = contacts.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
        if (contact == null) {
            return ServerResponseHelper.notFound(null);
        }
        return ServerResponseHelper.ok(contact);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ServerResponse<Void>> deleteContact(@PathVariable int id) {
        boolean canRemove = contacts.removeIf(contact -> contact.getId() == id);
        if (canRemove) {
            return ServerResponseHelper.ok(null);
        } else return ServerResponseHelper.notFound(null,
                Collections.singletonList("Контакт с указанным id не найден"));
    }

    @PostMapping("/create")
    public ResponseEntity<ServerResponse<Contact>> addContact(
            @RequestBody Contact contact) {
        int newId = contacts.stream()
                            .mapToInt(Contact::getId)
                            .max().orElse(0) + 1;
        boolean emailExists = contacts.stream()
                .anyMatch(c -> c.getEmail().equalsIgnoreCase(contact.getEmail()));
        if (emailExists) {
            return ServerResponseHelper.conflict(null,
                    Collections.singletonList("Контакт с таким email уже существует"));
        }
        contact.setId(newId);
        contacts.add(contact);
        return ServerResponseHelper.created(contact);
    }

    @PutMapping("/update")
    public ResponseEntity<ServerResponse<Contact>> updateContact(
            @RequestBody Contact contact) {

        return contacts.stream()
                .filter(c -> c.getId() == contact.getId())
                .findFirst()
                .map(existingContact -> {
                    int indexOfExistingContact = contacts.indexOf(existingContact);
                    contacts.set(indexOfExistingContact, contact);
                    return ServerResponseHelper.ok(contact);
                }).orElse(ServerResponseHelper.notFound(null,
                        Collections.singletonList("Контакт для обновления с указанным id не найден")));
    }
}
