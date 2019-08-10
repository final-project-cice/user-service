package com.trl.user.repository;

import com.trl.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

/**
 *
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * @param id
     * @param firstName
     */
    @Modifying(clearAutomatically = true)
    @Query("update UserEntity u set u.firstName=:firstName where u.id =:id")
    void updateFirstName(@Param("id") Long id, @Param("firstName") String firstName);

    /**
     * @param id
     * @param lastName
     */
    @Modifying(clearAutomatically = true)
    @Query("update UserEntity u set u.lastName=:lastName where u.id =:id")
    void updateLastName(@Param("id") Long id, @Param("lastName") String lastName);

    /**
     * @param id
     * @param email
     */
    @Modifying(clearAutomatically = true)
    @Query("update UserEntity u set u.email=:email where u.id =:id")
    void updateEmail(@Param("id") Long id, @Param("email") String email);

    /**
     * @param id
     * @param password
     */
    @Modifying(clearAutomatically = true)
    @Query("update UserEntity u set u.password=:password where u.id =:id")
    void updatePassword(@Param("id") Long id, @Param("password") String password);

    /**
     * @param id
     * @param birthday
     */
    @Modifying(clearAutomatically = true)
    @Query("update UserEntity u set u.birthday=:birthday where u.id =:id")
    void updateBirthday(@Param("id") Long id, @Param("birthday") LocalDate birthday);


    /**
     * @param firstName
     * @return
     */
    Set<UserEntity> findByFirstName(String firstName);

    /**
     * @param lastName
     * @return
     */
    Set<UserEntity> findByLastName(String lastName);

    /**
     * @param email
     * @return
     */
    Optional<UserEntity> findByEmail(String email);

    /**
     * @param firstName
     * @param lastName
     * @return
     */
    Set<UserEntity> findByFirstNameAndLastName(String firstName, String lastName);

    /**
     * @param birthDay
     * @return
     */
    Set<UserEntity> findByBirthday(LocalDate birthDay);

}
