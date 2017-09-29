package com.udemy.backendninja2.service.impl;

import com.udemy.backendninja2.component.ContactConverter;
import com.udemy.backendninja2.entity.Contact;
import com.udemy.backendninja2.model.ContactModel;
import com.udemy.backendninja2.repository.ContactRepository;
import com.udemy.backendninja2.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService {

    @Autowired
    @Qualifier("contactRepository")
    private ContactRepository contactRepository;

    @Autowired
    @Qualifier("contactConverter")
    private ContactConverter contactConverter;


    @Override
    public ContactModel addContact(ContactModel contactModel) {
        Contact contact = contactRepository.save(contactConverter.convertContactModelToContact(contactModel));
        return contactConverter.convertContactToContactModel(contact);
    }

    @Override
    public List<ContactModel> listAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        List<ContactModel> contactModels = new ArrayList<>();

        for(Contact contact: contacts){
           contactModels.add(contactConverter.convertContactToContactModel(contact));
        }

        return contactModels;
    }

    @Override
    public Contact findContactById(int id) {
        return contactRepository.findById(id);
    }

    @Override
    public ContactModel findContactByIdModel(int id) {
        return contactConverter.convertContactToContactModel(findContactById(id));
    }

    @Override
    public void removeContact(int id) {
        Contact contact = findContactById(id);
        if (null != contact) {
            contactRepository.delete(id);
        }
    }


}
