package com.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class InputAdapter implements InputProcessor {

    static int scrollSpeed = 15;

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
        int scroll = (int)(scrollSpeed*(-amountY));
        if (amountY>0 && !(Window.workspace.strings.get(0).y+Window.workspace.deltaY+scroll<Gdx.graphics.getHeight())){
            Window.workspace.deltaY += scroll;
            return false;
        }
        if (amountY<0 && !(Window.workspace.strings.get(Window.workspace.strings.size()-1).y+Window.workspace.deltaY+scroll>0)) {
            Window.workspace.deltaY += scroll;
            return false;
        }
        return false;
    }
}
