package com.accton.newframework.core.infrastructure.entities;

import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Setter @Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name",length = 50)
    private String userName;

    @Column(name = "display_name",length = 100)
    private String displayName;

    @Column(name = "password", length = 60)
    private String password;

    @Column(name = "ms_identity_id", length = 100)
    private String msIdentityId;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "role_id") }
    )
    @BatchSize(size = 20)
    private Set<RoleEntity> roles = new HashSet<>();

}
