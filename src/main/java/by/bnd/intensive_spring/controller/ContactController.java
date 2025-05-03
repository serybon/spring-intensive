package by.bnd.intensive_spring.controller;

import by.bnd.intensive_spring.common.util.ServerResponseHelper;
import by.bnd.intensive_spring.model.ServerResponse;
import by.bnd.intensive_spring.model.entity.Contact;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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

}
