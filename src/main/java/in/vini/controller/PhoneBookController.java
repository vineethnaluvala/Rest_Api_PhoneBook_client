package in.vini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.vini.binding.ContactDetails;
import in.vini.service.PhoneBookServiceImpl;

@Controller
public class PhoneBookController {

	@Autowired
	private PhoneBookServiceImpl service;

	@GetMapping("/")
	public String loadForm(Model model) {
		model.addAttribute("contact", new ContactDetails());
		return "index";
	}

	@PostMapping("/save")
	public String saveContact(@ModelAttribute("contact") ContactDetails details, Model model) {

		ContactDetails saveContact = service.saveContact(details);

		model.addAttribute("msg", "contact saved successfully");

		return "index";
	}

	@GetMapping("/contacts")
	public String viewContacts(Model model) {

		List<ContactDetails> contacts = service.getContacts();

		model.addAttribute("contacts", contacts);

		return "contacts";
	}

	@PostMapping("/edit")
	public String editContacts(@RequestParam("id") Integer id, Model model,ContactDetails updateContact) {

		ContactDetails editContact = service.editContact(id, updateContact);

		model.addAttribute("contacts", editContact);

		return "contacts";
	}

}
