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
    public static final int V_WIDTH = 400; //400
    /** virtuelle Höhe des Spiels */
    public static final int V_HEIGHT = 204;// 208
    /** UnitScale des Spiels, Pixelgröße einer virtuellen Einheit */
    public static final float PPM = 100f;

    public static final float TIME_STEP = 1 / 60f;

    /** x-Startposition unseres Spielers */
    public static final float MARIO_START_X = 32f;
    /** y-Startposition unseres Spielers */
    public static final float MARIO_START_Y = 100f;
    /** Radius unseres Spielers */
    public static final float MARIO_RADIUS = 6f;
    /** Sprungstärke unseres Spielers */
    public static final float MARIO_JUMP_SPEED = 4f;
    /** maximale horizontale Geschwindigkeit unseres Spielers */
    public static final float MARIO_MAX_SPEED = 2f;
    /** horizontale Beschleunigung unseres Spielers */
    public static final float MARIO_HORIZONTAL_ACCELERATION = 0.1f;

    /** Kategorie - Bits für Fixtures */
    public static final short GROUND_BIT = 1;
    public static final short MARIO_BIT = 2;
    public static final short BRICK_BIT = 4;
    public static final short COIN_BIT = 8;
    public static final short DESTROYED_BIT = 16;
    public static final short OBJECT_BIT = 32;
    public static final short ENEMY_BIT = 64;
    public static final short ENEMY_HEAD_BIT = 128;
}
