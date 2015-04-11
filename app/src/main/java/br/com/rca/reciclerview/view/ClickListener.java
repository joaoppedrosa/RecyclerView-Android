package br.com.rca.reciclerview.view;

import android.view.View;

/**
 * Created by rafael on 11/04/15.
 */
public interface ClickListener<T> {

    public void onItemClicked(View view, T data);
}
