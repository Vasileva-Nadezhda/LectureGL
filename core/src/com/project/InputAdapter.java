package com.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class InputAdapter implements InputProcessor {

    Workspace workspace;
    static int scrollSpeed = 30;

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
        int scroll = (int)amountY * scrollSpeed;
        if (amountY < 0) {
            if (!this.workspace.strings.isEmpty()) {
                if (!this.workspace.pictures.isEmpty()) {
                    Picture pic = this.workspace.pictures.get(0);
                    SimpleText string = this.workspace.strings.get(0);
                    if (pic.maxY >= string.y) {
                        if (pic.maxY + this.workspace.deltaY >= Gdx.graphics.getHeight() - 15) {
                            this.workspace.deltaY += scroll;
                            this.workspace.scroll();
                        }
                    }
                    else {
                        if ((this.workspace.strings.get(0).y + this.workspace.deltaY + scroll) >= Gdx.graphics.getHeight() - 15) {
                            this.workspace.deltaY += scroll;
                            this.workspace.scroll();
                        }
                    }
                }
                else {
                    if ((this.workspace.strings.get(0).y + this.workspace.deltaY + scroll) >= Gdx.graphics.getHeight() - 15) {
                        this.workspace.deltaY += scroll;
                        this.workspace.scroll();
                    }
                }
            }
            else {
                if (!this.workspace.pictures.isEmpty()) {
                    Picture pic = this.workspace.pictures.get(0);
                    if (pic.maxY + this.workspace.deltaY >= Gdx.graphics.getHeight() - 15) {
                        this.workspace.deltaY += scroll;
                        this.workspace.scroll();
                    }
                }
            }
        }
        else if (amountY > 0) {
            Picture pic = null;
            if (this.workspace.pictures != null && !this.workspace.pictures.isEmpty()) {
                pic = this.workspace.pictures.get(this.workspace.pictures.size() - 1);
            }
            if (!this.workspace.strings.isEmpty()) {
                SimpleText string = this.workspace.strings.get(this.workspace.strings.size() - 1);
                if (pic != null && (pic.maxY - pic.height) < string.y) {
                    if (pic.maxY - pic.height + this.workspace.deltaY <= 0) {
                        this.workspace.deltaY += scroll;
                        this.workspace.scroll();
                    }
                } else if ((string.y + this.workspace.deltaY - string.height) <= 0) {
                    this.workspace.deltaY += scroll;
                    this.workspace.scroll();
                }
            }
            else {
                if (pic.maxY - pic.height + this.workspace.deltaY <= 0) {
                    this.workspace.deltaY += scroll;
                    this.workspace.scroll();
                }
            }
        }
        return false;
    }

}
