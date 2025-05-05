package by.bnd.intensive_spring.services;

import by.bnd.intensive_spring.dao.ContactOwnerRepository;
import by.bnd.intensive_spring.exception.customException.EntityNotFoundException;
import by.bnd.intensive_spring.exception.customException.ValidationException;
import by.bnd.intensive_spring.model.dto.CreateContactOwnerDto;
import by.bnd.intensive_spring.model.dto.UpdateContactOwnerDto;
import by.bnd.intensive_spring.model.entity.ContactOwner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactOwnerServiceImpl implements ContactOwnerService {
    private final ContactOwnerRepository contactOwnerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ContactOwnerServiceImpl(ContactOwnerRepository contactOwnerRepository, ModelMapper modelMapper) {
        this.contactOwnerRepository = contactOwnerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ContactOwner> getAllContactOwners() {
        return contactOwnerRepository.findAll();
    }

    @Override
    public ContactOwner getContactOwnerById(String id) {
        return contactOwnerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Владелец не найден"));
    }

    @Override
    public ContactOwner createContactOwner(CreateContactOwnerDto createContactOwnerDto) {
        String normalizedEmail = createContactOwnerDto
                .getEmail()
                .trim()
                .toLowerCase();
        createContactOwnerDto.setEmail(normalizedEmail);

        boolean emailExists = contactOwnerRepository.findAll().stream()
                .anyMatch(co -> co.getEmail().equals(normalizedEmail));
        if (emailExists) {
            throw new ValidationException("Владелец с таким email уже существвует");
        }
        ContactOwner newContactOwner = modelMapper.map(createContactOwnerDto, ContactOwner.class);
        return contactOwnerRepository.save(newContactOwner);
    }

    @Override
    public ContactOwner updateContactOwner(UpdateContactOwnerDto updateContactOwnerDto) {
        String normalizedEmail = updateContactOwnerDto
                .getEmail()
                .trim()
                .toLowerCase();

        ContactOwner existingContactOwner = contactOwnerRepository
                .findById(updateContactOwnerDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Владелец с указанным ID не найден"));

        //Проверяем есть ли такой email у другого владельца, не того которого мы хотим обновить
        contactOwnerRepository.findAll().stream()
                .filter(co -> co.getEmail().equalsIgnoreCase(normalizedEmail)
                              && !co.getId().equals(updateContactOwnerDto.getId()))
                .findFirst()
                .ifPresent(co -> {
                    throw new ValidationException("Владелец с указанным email существует");
                });

        ContactOwner updatedContactOwner = modelMapper.map(updateContactOwnerDto, ContactOwner.class);
        updatedContactOwner.setRole(existingContactOwner.getRole());
        return contactOwnerRepository.save(updatedContactOwner);
    }

    @Override
    public boolean deleteContactOwner(String id) {
        if (contactOwnerRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Владелец с указанным ID не найден");
        }
        return contactOwnerRepository.deleteById(id);
    }

    @Override
    public List<ContactOwner> searchContactOwnersByUsername(String username) {
        return contactOwnerRepository.findByUsername(username);
    }

    @Override
    public List<ContactOwner> searchContactOwnersByKeyword(String keyword) {
        return contactOwnerRepository.searchByKeyword(keyword);
    }
}
