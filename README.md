# RecyclerView-Android
####Este repositório contém a implementação passo a passo da utilização da RecyclerView em uma aplicação android.

## 01 - Gradle 
Adicionando as dependências no gradle, arquivo build.gradle

```gradle
dependencies {

    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile 'com.android.support:appcompat-v7:21.0.3'
    compile 'com.android.support:recyclerview-v7:+'
}
```

## 06 - Vamos codar 
Criando a interface para que possamos receber o retorno do clique do usuário em um item da lista, observe como foi tratado de forma genérica o retorno da interação do usuário com um item da lista.

```java
public interface ClickListener<T> {

    public void onItemClicked(View view, T data);
}
```

Implementando o listener para receber o retorno do interação do usuário com um item da lista. Apenas precisamos tipar a nossa interface para que ela possa receber o tipo de objeto correto, no caso objetos User ou qualquer outro que desejarmos.

```java
public class Main extends BaseActionBarAcitvity implements ClickListener<User> {

   ...
   
    @Override
    protected void setListeners() {

        userAdapter.setClickListener(this);
    }

    @Override
    public void onItemClicked(View view, User user) {

        showShortToast(user.getNome());
    }
    
    ...
}
```

Implementando o nosso adapter de usuários, arquivo UserAdapter.java
```java
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
```
