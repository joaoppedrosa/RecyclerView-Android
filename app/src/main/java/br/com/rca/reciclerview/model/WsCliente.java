package br.com.rca.reciclerview.model;

import br.com.rca.reciclerview.view.WsListener;
import br.com.rca.reciclerview.view.WsUiHandler;

/**
 * Created by rafael on 06/04/15.
 */
public class WsCliente {

    public void listUsers(final WsListener<User> wsListener){

        final WsUiHandler<User> uiHandler = new WsUiHandler<>(wsListener);

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    int total = 100;
                    User[] users = new User[total];

                    for(int x = 0; x < total; x++) {

                        users[x] = new User("User " + x, "user_" + x + "@gmail.com");
                    }

                    uiHandler.onUpdateData(users);

                } catch (Exception exception) {

                    uiHandler.onUpdateError(exception);
                }

            }

        }).start();
    }
}
