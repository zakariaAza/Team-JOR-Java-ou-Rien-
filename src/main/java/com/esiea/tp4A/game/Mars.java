package com.esiea.tp4A.game;

import com.esiea.tp4A.domain.PlanetMap;
import com.esiea.tp4A.domain.Position;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Mars implements PlanetMap, Serializable {

    private final int size;
    private final int limit_pos;
    private final int limit_neg;
    private Set<Obstacle> obstacles = new HashSet<>();

    public Mars() {
        this.size = new RandomGame().getRandomMapSize();
        this.limit_pos = this.size/2;
        this.limit_neg = 0 - this.limit_pos + 1;
        this.obstacles = new RandomGame().generateObstaclesPosition(this.size, this.limit_neg, this.limit_pos);
    }

    public Mars(int size, Set<Obstacle> obstacles) {
        this.size = size;
        this.limit_pos = this.size/2;
        this.limit_neg = 0 - this.limit_pos + 1;
        this.obstacles = obstacles;
    }


    @JsonProperty("obstacles")
    @Override
    public Set<Obstacle> obstaclePositions() {
        return this.obstacles;
    }

    public void removeObstacle(Obstacle obstacle){
        this.obstacles.remove(obstacle);
    }

    public int getSize() {
        return size;
    }

    @JsonIgnore
    public int getLimit_pos() {
        return limit_pos;
    }
    @JsonIgnore
    public int getLimit_neg() {
        return limit_neg;
    }

    public boolean isObstacle(Position temp_position) {
        for(Obstacle point : this.obstacles){
            if(point.comparePosition(temp_position)) return true;
        }
        return false;
    }
}
