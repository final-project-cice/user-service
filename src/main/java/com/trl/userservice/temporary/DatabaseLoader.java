package com.trl.userservice.temporary;

import com.trl.userservice.repository.AddressRepository;
import com.trl.userservice.repository.BankDataRepository;
import com.trl.userservice.repository.UserRepository;
import com.trl.userservice.repository.entity.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Profile("dev")
@Component
public class DatabaseLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BankDataRepository bankDataRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public DatabaseLoader(UserRepository userRepository, BankDataRepository bankDataRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.bankDataRepository = bankDataRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("Tsyupryk");
        userEntity.setLastName("Roman");
        userEntity.setUserName("TRL");
        userEntity.setEmail("tsyupryk.roman@gmail.com");
        userEntity.setPassword("strong password");
        userEntity.setBirthday(LocalDate.of(1988, 6, 26));
        this.userRepository.save(userEntity);
    }
}