package com.sandeep.serviceImpl;

import com.sandeep.entities.Contact;
import com.sandeep.entities.UserEntity;
import com.sandeep.repositories.IContactRepository;
import com.sandeep.services.IContactService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements IContactService {

    @Autowired
    private final IContactRepository contactRepository;



    @Override
    public Contact save(Contact contact) {
        String contactId = UUID.randomUUID().toString();
        contact.setId(contactId);
        return contactRepository.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        return null;
    }

    @Override
    public List<Contact> getAll() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getById(String id) {
        return  contactRepository.findByUser(UserEntity user) ;
    }

    @Override
    public void delete(String id) {

    }
}
