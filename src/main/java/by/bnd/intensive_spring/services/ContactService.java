package by.bnd.intensive_spring.services;

import by.bnd.intensive_spring.model.dto.CreateContactDto;
import by.bnd.intensive_spring.model.dto.UpdateContactDto;
import by.bnd.intensive_spring.model.entity.Contact;

import java.util.List;

public interface ContactService {

    List<Contact> getAllContacts();

    Contact getContactById(int id);

    Contact createContact(CreateContactDto createContactDto);

    Contact updateContact(UpdateContactDto updateContactDto);

    boolean deleteContact(int id);

}
