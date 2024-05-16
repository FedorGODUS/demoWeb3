package com.example.demo.model;

import com.example.demo.model.enums.Rarity;
import com.example.demo.model.enums.Status;
import com.example.demo.model.enums.Type;
import com.example.demo.model.enums.UniqEquipment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Equipment {
    @Id
    @GeneratedValue
    private Long id;
    private UniqEquipment uniqEquipment;
    //@ManyToOne(targetEntity = Account.class)
    private String accountId;
    private Status status;
    private Date lastCollect;
}
