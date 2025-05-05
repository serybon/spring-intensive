package by.bnd.intensive_spring.dao;

import by.bnd.intensive_spring.model.entity.ContactOwner;

import java.util.List;
import java.util.Optional;

public interface ContactOwnerRepository {

    List<ContactOwner> findAll();

    Optional<ContactOwner> findById(String id);

    Optional<ContactOwner> findByEmail(String email);

    ContactOwner save(ContactOwner contactOwner);

    boolean deleteById(String id);

    List<ContactOwner> findByUsername(String username);

    List<ContactOwner> searchByKeyword(String keyword);
}
