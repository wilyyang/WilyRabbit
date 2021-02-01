package wily.apps.wilyrabbit;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import wily.apps.wilyrabbit.entity.Work;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.WorkViewHolder> {
    private List<Work> mList;

    public class WorkViewHolder extends RecyclerView.ViewHolder {
        protected TextView id;
        protected TextView type;
        protected TextView title;
        protected TextView active;
        protected TextView goal;
        protected TextView init;
        protected TextView per;

        public WorkViewHolder(View view) {
            super(view);
            this.id = (TextView) view.findViewById(R.id.work_id);
            this.type = (TextView) view.findViewById(R.id.work_type);
            this.title = (TextView) view.findViewById(R.id.work_title);
            this.active = (TextView) view.findViewById(R.id.work_active);
            this.goal = (TextView) view.findViewById(R.id.work_goal);
            this.init = (TextView) view.findViewById(R.id.work_init);
            this.per = (TextView) view.findViewById(R.id.work_per);
        }
    }

    public WorkAdapter(List<Work> list) {
        this.mList = list;
    }

    @Override
    public WorkViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_work, viewGroup, false);

        WorkViewHolder viewHolder = new WorkViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WorkViewHolder viewholder, int position) {
        viewholder.id.setGravity(Gravity.CENTER);
        viewholder.type.setGravity(Gravity.CENTER);
        viewholder.title.setGravity(Gravity.CENTER);
        viewholder.active.setGravity(Gravity.CENTER);
        viewholder.goal.setGravity(Gravity.CENTER);
        viewholder.init.setGravity(Gravity.CENTER);
        viewholder.per.setGravity(Gravity.CENTER);

        viewholder.id.setText(""+mList.get(position).getId());
        viewholder.type.setText(""+mList.get(position).getType());
        viewholder.title.setText(""+mList.get(position).getTitle());
        viewholder.active.setText(""+mList.get(position).isActive());
        viewholder.goal.setText(""+mList.get(position).getGoalCost());
        viewholder.init.setText(""+mList.get(position).getInitCost());
        viewholder.per.setText(""+mList.get(position).getPerCost());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}
