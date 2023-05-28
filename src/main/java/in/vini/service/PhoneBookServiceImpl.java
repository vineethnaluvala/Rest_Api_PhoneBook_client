package in.vini.service;

import java.awt.PageAttributes.MediaType;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import in.vini.binding.ContactDetails;

@Service
public class PhoneBookServiceImpl implements PhoneBookService {

	private static final String ADD_CONTACT_URL = "http://localhost:8088/add";
	private static final String GET_CONTACT_URL = "http://localhost:8088/view";
	private static final String EDIT_CONTACT_URL = "http://localhost:8088/edit/{id}/";
	private static final String DELETE_CONTACT_URL = "http://localhost:8088/delete/{id}/";

	@Override
	public ContactDetails saveContact(ContactDetails details) {
		ContactDetails cDetails = new ContactDetails();
		BeanUtils.copyProperties(details, cDetails);

		WebClient webClient = WebClient.create();

		ContactDetails block = webClient.post()
				.uri(ADD_CONTACT_URL)
				.header("Accept", "application/json")
				.bodyValue(cDetails)
				.retrieve()
				.bodyToMono(ContactDetails.class)
				.block();

		return block;
	}

	public List<ContactDetails> getContacts() {

		WebClient webClient = WebClient.create();

		List<ContactDetails> contactList = webClient.get()
				.uri(GET_CONTACT_URL)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<ContactDetails>>() {})
				.block();
		

		return contactList;
	}
	
	public ContactDetails editContact(Integer id,ContactDetails updateContact) {
		
		WebClient webClient = WebClient.create();

		ContactDetails editContact = webClient.get()
				.uri(EDIT_CONTACT_URL,id)
				.header("Accept", "application/json")
				.retrieve()
				.bodyToMono(ContactDetails.class)
				.block();
		
		
		return editContact;
		
	}
	public ContactDetails deleteContact(Integer id) {
		return null;
		
	}
	
}





















