package com.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class InputAdapter implements InputProcessor {

    Workspace workspace;
    static int scrollSpeed = 20;

    public InputAdapter (Workspace workspace) {
        this.workspace = workspace;
    }

    public boolean keyDown (int keycode) {
        return false;
    }

    public boolean keyUp (int keycode) {
        return false;
    }

    public boolean keyTyped (char character) {
        return false;
    }

    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public boolean touchDragged (int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved (int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled (float amountX, float amountY) {
        int scroll = (int)(-amountY)*scrollSpeed;
        if ((amountY > 0) && ((this.workspace.strings.get(0).y + this.workspace.deltaY + scroll) >= Gdx.graphics.getHeight())) {
            this.workspace.deltaY += scroll;
        }
        else if (amountY < 0) {
            GlyphLayout layout = new GlyphLayout();
            SimpleText string = this.workspace.strings.get(this.workspace.strings.size()-1);
            layout.setText(string.font.font, string.text);
            if ((string.y + this.workspace.deltaY - layout.height) <= 0){
                this.workspace.deltaY += scroll;
            }
        }
        return false;
    }
}
