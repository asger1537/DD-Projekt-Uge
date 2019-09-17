package dungeonGamePackage;

import java.awt.Color;

import processing.core.PVector;

abstract class MovingUnit {
    PVector position;//center of unit
    PVector targetPosition;//the position the unit is moving towards
    float ms;//movement speed
    float radius;
    Color color;
    boolean dead;
    int hp;//hit points

}