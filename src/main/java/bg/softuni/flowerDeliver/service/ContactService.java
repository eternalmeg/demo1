package bg.softuni.flowerDeliver.service;

import bg.softuni.flowerDeliver.domain.dto.binding.ContactBindingDto;
import bg.softuni.flowerDeliver.domain.entities.ContactEntity;
import bg.softuni.flowerDeliver.repositories.ContactRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static bg.softuni.flowerDeliver.constants.Messages.DATE_TIME_NOW_PATTERN;

@Service
public class ContactService {

    private final ModelMapper modelMapper;
    private final ContactRepository contactRepository;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_NOW_PATTERN);

    public ContactService(ModelMapper modelMapper,
                          ContactRepository contactRepository) {
        this.modelMapper = modelMapper;
        this.contactRepository = contactRepository;
    }

    public void saveContactMessage(ContactBindingDto contactBinding) {

        ContactEntity contactToSave = mapToContactEntity(contactBinding);

        contactToSave.setCreatedOn(LocalDateTime.parse(dateTimeFormatter.format(LocalDateTime.now())));

        this.contactRepository.saveAndFlush(contactToSave);

    }

    public ContactEntity mapToContactEntity(ContactBindingDto contactBinding) {
        return this.modelMapper.map(contactBinding, ContactEntity.class);
    }

}
