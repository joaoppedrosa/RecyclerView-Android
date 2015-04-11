package br.com.rca.reciclerview.view;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by rafael on 06/04/15.
 */
public class WsUiHandler<T> {

    private Handler handler;
    private WsListener<T> wsListener;

    public WsUiHandler(WsListener<T> wsListener) {

        this.wsListener = wsListener;
        this.handler = new Handler(Looper.getMainLooper());
    }

    public void onUpdateData(final T... data) {

        if (wsListener != null) {

            handler.post(new Runnable() {
                @Override
                public void run() {

                    wsListener.onUpdateData(data);
                }
            });
        }
    }

    public void onUpdateError(final Exception exception) {

        if (wsListener != null) {

            handler.post(new Runnable() {
                @Override
                public void run() {

                    wsListener.onUpdateError(exception);
                }
            });
        }
    }
}
