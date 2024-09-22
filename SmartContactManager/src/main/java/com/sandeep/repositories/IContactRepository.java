package com.sandeep.repositories;

import com.sandeep.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContactRepository extends JpaRepository<Contact,String> {
}
