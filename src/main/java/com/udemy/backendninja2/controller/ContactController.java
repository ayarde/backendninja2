package com.udemy.backendninja2.controller;

import com.udemy.backendninja2.constant.ViewConstant;
import com.udemy.backendninja2.entity.Contact;
import com.udemy.backendninja2.model.ContactModel;
import com.udemy.backendninja2.service.ContactService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    private static final Log LOGGER = LogFactory.getLog(ContactController.class);

    @Autowired
    @Qualifier("contactServiceImpl")
    private ContactService contactService;

    @GetMapping("/cancel")
    private String cancel(){
       return "redirect:/contacts/showcontacts";
    }

    @GetMapping("/contactform")
    private String redirectContactForm(@RequestParam(name="id", required = false) int id, Model model){

        ContactModel contactModel = new ContactModel();

        if(id != 0) {
            contactModel = contactService.findContactByIdModel(id);
        }

        model.addAttribute("contactModel", contactModel);
        return ViewConstant.CONTACT_FORM;
    }

    @PostMapping("/addcontact")
    public String addContact(@ModelAttribute(name="contactModel") ContactModel contactModel, Model model){
        LOGGER.info("METHOD: addContact() -- Params: " + contactModel.toString() );

        if(null != contactService.addContact(contactModel)){
            model.addAttribute("result", 1);
        } else {
            model.addAttribute("result", 0);
        }

        return "redirect:/contacts/showcontacts";
    }

    @GetMapping("/showcontacts")
    public ModelAndView showContacts(){
        ModelAndView modelAndView = new ModelAndView(ViewConstant.CONTACTS);
        modelAndView.addObject("contacts", contactService.listAllContacts());
        return modelAndView;
    }

    @GetMapping("/removecontact")
    public ModelAndView removeContact(@RequestParam(name="id", required = true) int id){
       contactService.removeContact(id);
        return showContacts();
    }
}
