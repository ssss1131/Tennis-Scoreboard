package com.ssss.tennisscoreboard.entity;



import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "name")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Players")
public class Player implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NaturalId
    @NonNull
    private String name;

    @Builder
    public Player(String name){
        this.name = name;
    }


}
