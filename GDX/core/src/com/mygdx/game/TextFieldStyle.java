package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextFieldStyle {
    public NinePatch background, cursor;
    public BitmapFont font;
    public Color fontColor;

    public TextureRegion selection;

    public BitmapFont messageFont;

    public Color messageFontColor;
}