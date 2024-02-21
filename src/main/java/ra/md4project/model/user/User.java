package ra.md4project.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(length = 100 , unique = true)
    private String username;
    @Column(length = 100)
    private String password;
    @Column(length = 100)
    private String confirmPassword;
    @Column(length = 100 , unique = true)
    private String email;
    @Column(length = 100 , unique = true)
    private String phoneNumber;
    @Column(length = 100)
    private String address;
    @Column(columnDefinition = "bit")
    Boolean statusAccount;
    private String avatarUrl;
    @ManyToOne
    @JoinColumn(name = "roleName")
    private Role role;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartId")
    private Cart cart;
}
