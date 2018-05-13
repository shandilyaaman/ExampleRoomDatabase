package demoroom.app.com.roomdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Milan on 13-May-18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    private List<User> userList;
    private Context context;

    public MyAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    public void refreshList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        User user = userList.get(position);
        holder.name.setText(user.getFirstName() + " " + user.getLastName());
        holder.age.setText(String.valueOf(user.getAge()));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView name, age;

        public MyHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_name);
            age = itemView.findViewById(R.id.text_age);
        }
    }
}
