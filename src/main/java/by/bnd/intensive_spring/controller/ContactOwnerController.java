package by.bnd.intensive_spring.controller;


import by.bnd.intensive_spring.common.util.ServerResponseHelper;
import by.bnd.intensive_spring.model.ServerResponse;
import by.bnd.intensive_spring.model.dto.CreateContactOwnerDto;
import by.bnd.intensive_spring.model.dto.UpdateContactOwnerDto;
import by.bnd.intensive_spring.model.entity.ContactOwner;
import by.bnd.intensive_spring.services.ContactOwnerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/owner")
public class ContactOwnerController {
    private final ContactOwnerService contactOwnerService;

    @Autowired
    public ContactOwnerController(ContactOwnerService contactOwnerService) {
        this.contactOwnerService = contactOwnerService;
    }

    @GetMapping("/get")
    public ResponseEntity<ServerResponse<List<ContactOwner>>> getAllContactOwner() {
//        return ServerResponseHelper.ok(contactOwners);
        return ServerResponseHelper.ok(contactOwnerService.getAllContactOwners());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ServerResponse<ContactOwner>> getContactOwnerById(
            @PathVariable String id) {
//        return contactOwners.stream().filter(ow -> ow.getId().equals(id))
//                .findFirst()
//                .map(ow -> ServerResponseHelper.ok(ow))
//                .orElseGet(() -> ServerResponseHelper.notFound(null));
        return ServerResponseHelper.ok(contactOwnerService.getContactOwnerById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ServerResponse<ContactOwner>> createContactOwner(
            @Valid @RequestBody CreateContactOwnerDto createContactOwnerDto) {
//        if (contactOwners.stream()
//                .anyMatch(contactOwner -> contactOwner.getEmail()
//                        .equalsIgnoreCase(createContactOwnerDto.getEmail()))) {
//            return ServerResponseHelper.conflict(null, Collections.singletonList("Email уже существует"));
//        }
//
//        ContactOwner newOwner = modelMapper.map(createContactOwnerDto, ContactOwner.class);
//        contactOwners.add(newOwner);
//
//        return ServerResponseHelper.created(newOwner);
        ContactOwner newContactOwner = contactOwnerService.createContactOwner(createContactOwnerDto);
        return ServerResponseHelper.created(newContactOwner);
    }

    @PutMapping("/update")
    public ResponseEntity<ServerResponse<ContactOwner>> updateContactOwner(
            @Valid @RequestBody UpdateContactOwnerDto updateContactOwnerDto) {

        ContactOwner updateContactOwner = contactOwnerService.updateContactOwner(updateContactOwnerDto);
        return ServerResponseHelper.ok(updateContactOwner);
//        // Ищем в хранилище owner с таким же id
//        ContactOwner existingContactOwner = contactOwners.stream()
//                .filter(owner -> owner.getId()
//                        .equalsIgnoreCase(updateContactOwnerDto.getId()))
//                .findFirst()
//                .orElse(null);
//        if (existingContactOwner == null) {
//            return ServerResponseHelper.notFound(null,
//                    Collections.singletonList("Update: Владелец с указанным ID не найден"));
//        }
//        //Проверяем наличие другого владельца с тем же email. Если такой есть — возвращает ответ о конфликте
//        boolean emailExists = contactOwners.stream()
//                .filter(co -> !co.getId().equalsIgnoreCase(updateContactOwnerDto.getId()))
//                        .anyMatch(co -> co.getEmail().equalsIgnoreCase(updateContactOwnerDto.getEmail()));
//        if (emailExists) {
//            ServerResponseHelper.conflict(null, Collections.singletonList("Владелец с указанным email уже существует"));
//        }
//
//        ContactOwner updatedContactOwner = modelMapper.map(updateContactOwnerDto, ContactOwner.class);
//        int index = contactOwners.indexOf(existingContactOwner);
//        contactOwners.set(index, updatedContactOwner);
//
//        return ServerResponseHelper.ok(updatedContactOwner);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ServerResponse<Void>> deleteContactOwner(
            @PathVariable String id) {
//        boolean isRemoved = contactOwners.removeIf(ow -> ow.getId().equals(id));
//        return isRemoved ? ServerResponseHelper.ok(null)
//                : ServerResponseHelper.notFound(null,
//                Collections.singletonList("Delete: Владелец с указанным ID не найден"));
        contactOwnerService.deleteContactOwner(id);
        return ServerResponseHelper.ok(null);
    }

    @GetMapping("/search/name/{name}")
    public ResponseEntity<ServerResponse<List<ContactOwner>>> searchByName(@PathVariable String name) {
//        List<ContactOwner> contactOwnerList = contactOwners.stream()
//                .filter(co -> co.getUsername().equalsIgnoreCase(name))
//                .collect(Collectors.toList());
//        return ServerResponseHelper.ok(contactOwnerList);
        List<ContactOwner> found = contactOwnerService.searchContactOwnersByUsername(name);
        return ServerResponseHelper.ok(found);
    }

    @GetMapping("/search/keyword/{keyword}")
    public ResponseEntity<ServerResponse<List<ContactOwner>>> searchByKeyWord(@PathVariable String keyword) {
//        List<ContactOwner> contactOwnerList = contactOwners.stream()
//                .filter(co -> co.getUsername().contains(keyword)
//                || co.getEmail().contains(keyword)
//                || co.getDescription().contains(keyword))
//                .collect(Collectors.toList());
//        return ServerResponseHelper.ok(contactOwnerList);
        List<ContactOwner> found = contactOwnerService.searchContactOwnersByKeyword(keyword);
        return ServerResponseHelper.ok(found);
    }

}
