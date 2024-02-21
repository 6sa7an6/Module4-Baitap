package ra.md4project.repository.AccountRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.md4project.model.user.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT U FROM User U where U.username = :findName")
    Optional<User> findUserByName(@Param("findName") String findName);

    @Query("SELECT U FROM User U where U.phoneNumber = :phoneNumber AND (:id IS NULL OR U.userId = :id)")
    Optional<User> existsByPhoneNumberAndUserIdIsNot(String phoneNumber, Integer id);
    @Query("SELECT U FROM User U where U.username = :username AND (:id IS NULL OR U.userId = :id)")
    Optional<User> existsByUsernameAndUserIdIsNot(String username, Integer id);
    @Query("SELECT U FROM User U where U.email = :email AND (:id IS NULL OR U.userId = :id)")
    Optional<User> existsByEmailAndUserIdIsNot(String email, Integer id);
}
