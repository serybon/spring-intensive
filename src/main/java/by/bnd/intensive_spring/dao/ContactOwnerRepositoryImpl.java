package by.bnd.intensive_spring.dao;

import by.bnd.intensive_spring.model.entity.ContactOwner;
import by.bnd.intensive_spring.model.enums.AppRole;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class ContactOwnerRepositoryImpl implements ContactOwnerRepository {
    private final List<ContactOwner> contactOwners = new ArrayList<>();
    private final Faker faker = new Faker(Locale.ENGLISH);

    public ContactOwnerRepositoryImpl() {
        init();
    }

    private void init() {
        IntStream.range(0, 5).forEach(i -> {
            ContactOwner owner = new ContactOwner();
            owner.setId(UUID.randomUUID().toString());
            owner.setRole(AppRole.USER);
            owner.setEmail(faker.internet().emailAddress());
            owner.setPassword(faker.internet().password(8, 20, true,
                    false, true));
            owner.setDescription(faker.lorem().sentence());
            owner.setUsername(faker.name().username());
            contactOwners.add(owner);
        });
    }

    @Override
    public List<ContactOwner> findAll() {
        return new ArrayList<>(contactOwners);
    }

    @Override
    public Optional<ContactOwner> findById(String id) {
        return contactOwners.stream().filter(o -> o.getId().equalsIgnoreCase(id)).findFirst();
    }

    @Override
    public Optional<ContactOwner> findByEmail(String email) {
        return contactOwners.stream().filter(o -> o.getEmail().equalsIgnoreCase(email)).findFirst();
    }

    @Override
    public ContactOwner save(ContactOwner contactOwner) {
        if (contactOwner.getId() == null) {
            contactOwner.setId(UUID.randomUUID().toString());
            contactOwner.setRole(AppRole.USER);
            contactOwners.add(contactOwner);
        } else {
            int index = contactOwners.indexOf(contactOwners.stream()
                    .filter(co -> co.getId().equalsIgnoreCase(contactOwner.getId()))
                    .findFirst().orElse(null));
            if (index != -1) {
                contactOwners.set(index, contactOwner);
            }
        }
        return contactOwner;
    }

    @Override
    public boolean deleteById(String id) {
        return contactOwners.removeIf(o -> o.getId().equalsIgnoreCase(id));
    }

    @Override
    public List<ContactOwner> findByUsername(String username) {
        return contactOwners.stream()
                .filter(o -> o.getUsername().equalsIgnoreCase(username))
                .toList();
    }

    @Override
    public List<ContactOwner> searchByKeyword(String keyword) {
        return contactOwners.stream()
                .filter(o -> o.getUsername().contains(keyword)
                             || o.getEmail().contains(keyword)
                             || o.getDescription().contains(keyword))
                .toList();
    }
}
