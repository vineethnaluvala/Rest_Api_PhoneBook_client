package in.vini.service;

import java.util.List;

import in.vini.binding.ContactDetails;

public interface PhoneBookService {

	public ContactDetails saveContact(ContactDetails details);
	public List<ContactDetails> getContacts();
	public ContactDetails editContact(Integer id,ContactDetails updateContact);
	public ContactDetails deleteContact(Integer id);
}
