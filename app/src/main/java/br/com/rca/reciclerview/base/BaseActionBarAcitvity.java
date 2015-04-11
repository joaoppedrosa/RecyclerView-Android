package br.com.rca.reciclerview.base;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

/**
 * Created by rafael on 06/04/15.
 */
public abstract class BaseActionBarAcitvity extends ActionBarActivity {

    private boolean isDestroyed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        isDestroyed = false;
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        isDestroyed = true;
    }

    @Override
    public boolean isDestroyed() {

        return isDestroyed;
    }

    protected final void showShortToast(String text) {

        if (text != null) {

            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }
    }

    protected abstract void setScreenComponents();

    protected abstract void setListeners();
}
