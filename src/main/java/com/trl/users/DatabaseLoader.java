package com.trl.users;

import com.trl.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    @Autowired
    public DatabaseLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

//        this.userRepository.save(
//                new UserEntity()
//                        .setFirstName("First Name User_4")
//                        .setLastName("Last Name User_4")
//                        .setEmail("user_4@email.com")
//                        .setPassword("strong_password")
//                        .setBankData(new HashSet<>(asList(
//                                new BankDataEntity()
//                                        .setBankAccountNumber("1111111111111111111")
//                                        .setDateOfExpiry(new GregorianCalendar(1970, Calendar.JANUARY, 1).getTime())
//                                        .setCvi(111),
//                                new BankDataEntity()
//                                        .setBankAccountNumber("2222222222222222222")
//                                        .setDateOfExpiry(new GregorianCalendar(1970, Calendar.JANUARY, 1).getTime())
//                                        .setCvi(222))))
//                        .setAddress(new HashSet<>(asList(
//                                new AddressEntity()
//                                        .setCountry("Spain")
//                                        .setCity("Madrid")
//                                        .setStreet("Calle Madrid")
//                                        .setHouseNumber("1A")
//                                        .setPostCode(111111),
//                                new AddressEntity()
//                                        .setCountry("Spain")
//                                        .setCity("Barcelona")
//                                        .setStreet("Calle Barcelona")
//                                        .setHouseNumber("2A")
//                                        .setPostCode(222222))))
//                        .setBirthday(new GregorianCalendar(1970, Calendar.JANUARY, 1).getTime()));
//
    }

}
