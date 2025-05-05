package by.bnd.intensive_spring.services;

import by.bnd.intensive_spring.dao.ContactRepository;
import by.bnd.intensive_spring.exception.customException.EntityNotFoundException;
import by.bnd.intensive_spring.exception.customException.ValidationException;
import by.bnd.intensive_spring.model.dto.CreateContactDto;
import by.bnd.intensive_spring.model.dto.UpdateContactDto;
import by.bnd.intensive_spring.model.entity.Contact;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository, ModelMapper modelMapper) {
        this.contactRepository = contactRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getContactById(int id) {
        return contactRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Контакт не найден"));
    }

    @Override
    public Contact createContact(CreateContactDto createContactDto) {
        contactRepository.findByEmail(createContactDto.getEmail())
                .ifPresent(contact -> {
                    throw new ValidationException("Контакт с таким email уже существует");
                });
        Contact contact = modelMapper.map(createContactDto, Contact.class);
        return contactRepository.save(contact);
    }

    @Override
    public Contact updateContact(UpdateContactDto updateContactDto) {

        Contact existingContact = contactRepository.findById(updateContactDto.getId())
                .orElseThrow(() -> new ValidationException("Контакт с таким ID не найден"));
        contactRepository.findByEmail(updateContactDto.getEmail()).ifPresent(
                contact -> {
                    if (contact.getId() != updateContactDto.getId()) {
                        throw new ValidationException("Контакт с таким email уже существует");
                    }
                    ;
                }
        );
        contactRepository.findByTelephone(updateContactDto.getTelephone()).ifPresent(
                contact -> {
                    if (contact.getId() != updateContactDto.getId()) {
                        throw new ValidationException("Контакт с таким телефоном уже существует");
                    }
                    ;
                }
        );
        Contact updatedContact = modelMapper.map(updateContactDto, Contact.class);
        return contactRepository.save(updatedContact);
    }

    @Override
    public boolean deleteContact(int id) {
        if (contactRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Контакта не существует для удаления");
        }
        return contactRepository.deleteById(id);
    }
}
