package com.lab.application.models.entities;

import com.lab.application.models.enums.MoneyManipulationColorEnum;
import com.lab.application.models.enums.MoneyManipulationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Table(name = "money_manipulations")
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class MoneyManipulation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "money_amount")
    private BigDecimal moneyAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private MoneyManipulationTypeEnum type;

    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    private MoneyManipulationColorEnum color;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
