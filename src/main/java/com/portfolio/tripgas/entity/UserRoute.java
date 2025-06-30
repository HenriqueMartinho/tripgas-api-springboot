package com.portfolio.tripgas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_routes_tb")
public class UserRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "start_point", nullable = false)
    private String startPoint;
    @Column(name = "end_point", nullable = false)
    private String endPoint;

    @JsonIgnore
    @Column(name = "start_coord", nullable = false)
    private List<Double> startCoord;
    @JsonIgnore
    @Column(name = "end_coord", nullable = false)
    private List<Double> endCoord;

    public UserRoute(String startPoint, String endPoint, List<Double> startCoord, List<Double> endCoord) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.startCoord = startCoord;
        this.endCoord = endCoord;
    }
}
