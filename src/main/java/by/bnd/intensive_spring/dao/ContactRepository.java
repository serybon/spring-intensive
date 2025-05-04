package by.bnd.intensive_spring.dao;

import by.bnd.intensive_spring.model.entity.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactRepository {

    List<Contact> findAll();

    Optional<Contact> findById(Long id);

    Contact save(Contact contact);

    boolean deleteById(Long id);

    Optional<Contact> findByEmail(String email);

    Optional<Contact> findByTelephone(String telephone);


}
