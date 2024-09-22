package com.sandeep.services;

import com.sandeep.entities.Contact;

import java.util.List;

public interface IContactService {
    // save contacts
    Contact save(Contact contact);

    // update contact
    Contact update(Contact contact);

    // get contacts
    List<Contact> getAll();

    // get contact by id

    Contact getById(String id);

    // delete contact

    void delete(String id);
}
