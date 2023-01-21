package com.iuran_bulanan_warga.Models.Entities;

import com.iuran_bulanan_warga.Helpers.Entities.ERole;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles", uniqueConstraints = {
        @UniqueConstraint(columnNames = "roleName")
})
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "roleName")
    private ERole roleName;

    public Role(ERole roleName) {
        this.roleName = roleName;
    }
}
