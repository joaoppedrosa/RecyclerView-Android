package br.com.rca.reciclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.rca.reciclerview.R;
import br.com.rca.reciclerview.model.User;
import br.com.rca.reciclerview.view.ClickListener;

/**
 * Created by rafael on 06/04/15.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> userList;
    private ClickListener<User> clickListener;

    public UserAdapter() {

        userList = new ArrayList<User>(0);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_user_list,
                viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        User user = userList.get(position);

        viewHolder.tvNome.setText(user.getNome());
        viewHolder.tvEmail.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {

        return userList.size();
    }

    public boolean isEmpty() {

        return userList == null || userList.isEmpty();
    }

    public void addUsers(User... users) {

        if (users == null || users.length <= 0) {

            return;
        }

        userList.addAll(new ArrayList<User>(Arrays.asList(users)));
        notifyDataSetChanged();
    }

    public List<User> getUserList() {

        return userList;
    }

    public User getItem(int position) {

        if (userList == null || userList.isEmpty()) {

            return null;
        }

        if(position >= userList.size()) {

            return userList.get(userList.size() -1);
        }

        if(position < 0) {

            return userList.get(0);
        }

        return userList.get(position);
    }

    public void setClickListener(ClickListener<User> clickListener) {

        this.clickListener = clickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvNome;
        TextView tvEmail;

        public ViewHolder(View view) {

            super(view);
            view.setOnClickListener(this);

            tvNome = (TextView) view.findViewById(R.id.tv_name);
            tvEmail = (TextView) view.findViewById(R.id.tv_email);
        }

        @Override
        public void onClick(View v) {

            if(clickListener != null) {

                clickListener.onItemClicked(v, getItem(getPosition()));
            }
        }
    }
}
