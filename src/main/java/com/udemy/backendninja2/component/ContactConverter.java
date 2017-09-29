package com.udemy.backendninja2.component;

import com.udemy.backendninja2.entity.Contact;
import com.udemy.backendninja2.model.ContactModel;
import org.springframework.stereotype.Component;

@Component("contactConverter")
public class ContactConverter {
    public Contact convertContactModelToContact(ContactModel contactModel){
        Contact contact = new Contact();

        contact.setId(contactModel.getId());
        contact.setFirstname(contactModel.getFirstname());
        contact.setLastname(contactModel.getLastname());
        contact.setCity(contactModel.getCity());
        contact.setTelephone(contactModel.getTelephone());

        return contact;
    }

    public ContactModel convertContactToContactModel(Contact contact){
        ContactModel contactModel = new ContactModel();

        contactModel.setId(contact.getId());
        contactModel.setFirstname(contact.getFirstname());
        contactModel.setLastname(contact.getLastname());
        contactModel.setCity(contact.getCity());
        contactModel.setTelephone(contact.getTelephone());

        return contactModel;
    }
}
