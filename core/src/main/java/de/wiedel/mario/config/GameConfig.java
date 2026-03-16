package de.wiedel.mario.config;

import com.badlogic.gdx.graphics.Color;

public class GameConfig {

    /** globale Hintergrundsfarbe */
    public final static Color CORNFLOWER_BLUE = new Color(0.388f, 0.584f, 0.933f, 0.0f);

    /** Breite des Fenster für das Spiel */
    public static final int WIDTH = 1200;
    /** Höhe des Fensters für das Spiel */
    public static final int HEIGHT = 624;

    /** virtuelle Breite des Spiles */
    public static final int V_WIDTH = 25; //400
    /** virtuelle Höhe des Spiels */
    public static final int V_HEIGHT = 13;// 208
    /** UnitScale des Spiels, Pixelgröße einer virtuellen Einheit */
    public static final float UNIT_SCALE = 16f;

    /** x-Startposition unseres Spielers */
    public static final float MARIO_START_X = 3f;
    /** y-Startposition unseres Spielers */
    public static final float MARIO_START_Y = 5f;
    /** Radius unseres Spielers */
    public static final float MARIO_RADIUS = 0.5f;
    /** Sprungstärke unseres Spielers */
    public static final float MARIO_JUMP_SPEED = 10f;
    /** maximale horizontale Geschwindigkeit unseres Spielers */
    public static final float MARIO_MAX_SPEED = 2f;
    /** horizontale Beschleunigung unseres Spielers */
    public static final float MARIO_HORIZONTAL_ACCELERATION = 0.1f;
}
