package com.example.finalloanproject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Loantable {
    @Id
    @Column(name = "clientno", nullable = false)
    private Long clientno;
    private String clientname;
    private double loanamount;
    private int years;
    private String loantype;

}
