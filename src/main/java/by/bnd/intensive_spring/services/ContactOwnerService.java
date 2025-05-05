package by.bnd.intensive_spring.services;

import by.bnd.intensive_spring.model.dto.CreateContactOwnerDto;
import by.bnd.intensive_spring.model.dto.UpdateContactOwnerDto;
import by.bnd.intensive_spring.model.entity.ContactOwner;

import java.util.List;

public interface ContactOwnerService {
    List<ContactOwner> getAllContactOwners();

    ContactOwner getContactOwnerById(String id);

    ContactOwner createContactOwner(CreateContactOwnerDto createContactOwnerDto);

    ContactOwner updateContactOwner(UpdateContactOwnerDto updateContactOwnerDto);

    boolean deleteContactOwner(String id);

    List<ContactOwner> searchContactOwnersByUsername(String username);

    List<ContactOwner> searchContactOwnersByKeyword(String keyword);
}

