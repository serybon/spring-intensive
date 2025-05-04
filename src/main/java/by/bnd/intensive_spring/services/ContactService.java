package by.bnd.intensive_spring.services;

import by.bnd.intensive_spring.model.dto.CreateContactDto;
import by.bnd.intensive_spring.model.dto.UpdateContactDto;
import by.bnd.intensive_spring.model.entity.Contact;

import java.util.List;

public interface ContactService {

    List<Contact> getAllContacts();

    Contact getContactById(Long id);

    Contact createContact(CreateContactDto createContactDto);

    Contact updateContact(Long id, UpdateContactDto updateContactDto);

    void deleteContact(Long id);

}
