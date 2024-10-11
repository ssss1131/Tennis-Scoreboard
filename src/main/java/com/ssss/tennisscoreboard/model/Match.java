package com.ssss.tennisscoreboard.model;


import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "matches",
        indexes = {
                @Index(name = "idx_player1", columnList = "player1"),
                @Index(name = "idx_player2", columnList = "player2")
        })
public class Match implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player1", nullable = false)
    @NonNull
    private Player player1;

    @ManyToOne
    @JoinColumn(name = "player2", nullable = false)
    @NonNull
    private Player player2;

    @ManyToOne
    @JoinColumn(name = "winner", nullable = false)
    private Player winner;

    @Builder
    public Match(Player player1, Player player2, Player winner) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }
}
