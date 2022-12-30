package com.lab.application.models.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Data
@Table(name = "categories")
@Entity
//@ToString(of = {"user_id", "description", "manipulation_id"})
@NoArgsConstructor
@EqualsAndHashCode()
@AllArgsConstructor
public class Category {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<MoneyManipulation> manipulationList;

   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
