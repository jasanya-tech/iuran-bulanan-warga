package com.iuran_bulanan_warga.Models.Entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "houses")

public class Houses implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Size(max = 50)
  private String address;

  @ManyToOne
  @JoinColumn(name = "owner", referencedColumnName = "id")
  // Column pemilik rumah
  private Users owner;

  @ManyToOne
  @JoinColumn(name = "province")
  private Provinces province;

  @ManyToOne
  @JoinColumn(name = "city")
  private Cities city;

  @ManyToMany
  @JoinTable(name = "house_users", joinColumns = @JoinColumn(name = "house_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
  // Column penghuni rumah
  private Set<Users> occupants = new HashSet<>();

  public Houses(String address, Users owner, Provinces province, Cities city) {
    this.address = address;
    this.owner = owner;
    this.province = province;
    this.city = city;
  }
}
