package br.com.rca.reciclerview.view;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by rafael on 06/04/15.
 */
public interface WsListener<T> {

    void onUpdateData(final T... data);

    void onUpdateError(final Exception exception);

}
