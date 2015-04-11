package br.com.rca.reciclerview.view;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import br.com.rca.reciclerview.R;
import br.com.rca.reciclerview.adapter.UserAdapter;
import br.com.rca.reciclerview.base.BaseActionBarAcitvity;
import br.com.rca.reciclerview.model.User;
import br.com.rca.reciclerview.model.WsCliente;


public class Main extends BaseActionBarAcitvity implements ClickListener<User> {

    private static final String TAG_CLASS = Main.class.getSimpleName();

    private WsCliente wsCliente;

    private RecyclerView rvItens;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setScreenComponents();

        wsCliente = new WsCliente();
        userAdapter = new UserAdapter();
        rvItens.setAdapter(userAdapter);

        setListeners();
    }

    @Override
    protected void onResume() {

        super.onResume();

        if (userAdapter.isEmpty()) {

            wsCliente.listUsers(findUsersListener);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void setScreenComponents() {

        rvItens = (RecyclerView) findViewById(R.id.rv_itens);
        rvItens.setLayoutManager(new LinearLayoutManager(this));
        rvItens.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void setListeners() {

        userAdapter.setClickListener(this);
    }

    @Override
    public void onItemClicked(View view, User user) {

        showShortToast(user.getNome());
    }

    private WsListener<User> findUsersListener = new WsListener<User>() {

        @Override
        public void onUpdateData(User... data) {

            userAdapter.addUsers(data);
        }

        @Override
        public void onUpdateError(Exception exception) {

            showShortToast("Mostrar o erro ao usuário");
        }
    };
}
