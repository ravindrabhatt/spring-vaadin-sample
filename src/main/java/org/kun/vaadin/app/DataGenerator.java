package org.kun.vaadin.app;

import org.kun.vaadin.backend.data.Role;
import org.kun.vaadin.backend.data.entity.User;
import org.kun.vaadin.backend.repositories.UserRepository;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.UUID;

@SpringComponent
public class DataGenerator implements HasLogger {

  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;

  @Autowired
  public DataGenerator(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @PostConstruct
  public void loadData() {
    if (userRepository.count() != 0L) {
      getLogger().info("Using existing database");
      return;
    }

    getLogger().info("Generating demo data");

    getLogger().info("... generating users");
    createAdmin(userRepository, passwordEncoder);
    // A set of products without constrains that can be deleted
    createDeletableUsers(userRepository, passwordEncoder);

  }

  private User createAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    return userRepository.save(
        createUser("admin@vaadin.com", "Göran", "Rich", passwordEncoder.encode("admin"), Role.ADMIN, true));
  }

  private void createDeletableUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    userRepository.save(
        createUser("peter@vaadin.com", "Peter", "Bush", passwordEncoder.encode("peter"), Role.NORMAL_USER, false));
    userRepository
        .save(createUser("mary@vaadin.com", "Mary", "Ocon", passwordEncoder.encode("mary"), Role.NORMAL_USER, true));
  }

  private User createUser(String email, String firstName, String lastName, String passwordHash, String role,
                          boolean locked) {
    User user = new User();
    user.setId(UUID.randomUUID().toString());
    user.setEmail(email);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setPasswordHash(passwordHash);
    user.setRole(role);
    return user;
  }
}
