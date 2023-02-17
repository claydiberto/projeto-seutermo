package com.devca.seutermo.entities;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;

import com.devca.seutermo.enums.RoleName;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_role")
public class Role implements GrantedAuthority {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName roleName;

    @Override
    public String getAuthority() {
        return this.roleName.toString();
    }
    
}
