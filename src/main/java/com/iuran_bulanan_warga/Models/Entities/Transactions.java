package com.iuran_bulanan_warga.Models.Entities;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "transactions")

public class Transactions implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "houseId", referencedColumnName = "id")
  private Houses houseId;

  @ManyToOne
  @JoinColumn(name = "userId", referencedColumnName = "id")
  private Users userId;

  private Integer totalCost;

  @Temporal(TemporalType.DATE)
  private Date date;
}
