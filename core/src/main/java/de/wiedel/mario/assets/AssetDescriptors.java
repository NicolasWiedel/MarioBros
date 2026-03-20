package de.wiedel.mario.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class AssetDescriptors {

    public static final AssetDescriptor<BitmapFont> DEFAULT_FONT =
        new AssetDescriptor<>(Assets.DEFAULT_FONT, BitmapFont.class);

    public static final AssetDescriptor<Texture> LOADING_BAR_EMPTY =
        new AssetDescriptor<>(Assets.LOADING_BAR_EMPTY, Texture.class);
    public static final AssetDescriptor<Texture> LOADING_BAR_FILL =
        new AssetDescriptor<>(Assets.LOADING_BAR_FILL, Texture.class);

    public static final AssetDescriptor<TextureAtlas> MARIO_AND_ENEMIES =
        new AssetDescriptor<>(Assets.MARIO_ENEMIES_ATLAS, TextureAtlas.class);

    public static final AssetDescriptor<TiledMap> LEVEL_1 =
        new AssetDescriptor<>(Assets.LEVEL_1, TiledMap.class);

    public static final AssetDescriptor<Music> MARIO_MUSIC =
        new AssetDescriptor<>(Assets.MARIO_MUSIC, Music.class);

    public static final AssetDescriptor<Sound> BREAK_BLOCK =
        new AssetDescriptor<>(Assets.BREAK_BLOCK, Sound.class);
    public static final AssetDescriptor<Sound> BUMP =
        new AssetDescriptor<>(Assets.BUMP, Sound.class);
    public static final AssetDescriptor<Sound> COIN =
        new AssetDescriptor<>(Assets.COIN, Sound.class);
    public static final AssetDescriptor<Sound> MARIO_DIE =
        new AssetDescriptor<>(Assets.MARIO_DIE, Sound.class);
    public static final AssetDescriptor<Sound> POWER_DOWN =
        new AssetDescriptor<>(Assets.POWER_DOWN, Sound.class);
    public static final AssetDescriptor<Sound> POWER_UP =
        new AssetDescriptor<>(Assets.POWER_UP, Sound.class);
    public static final AssetDescriptor<Sound> POWER_UP_SPAWN =
        new AssetDescriptor<>(Assets.POWER_UP_SPAWN, Sound.class);
    public static final AssetDescriptor<Sound> STOMP =
        new AssetDescriptor<>(Assets.STOMP, Sound.class);
}
