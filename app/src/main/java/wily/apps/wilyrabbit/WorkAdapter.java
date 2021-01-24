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
        protected TextView title;
        protected TextView hour;
        protected TextView minute;


        public WorkViewHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.work_title);
            this.hour = (TextView) view.findViewById(R.id.work_hour);
            this.minute = (TextView) view.findViewById(R.id.work_minute);
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

        viewholder.title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.hour.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.minute.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);

        viewholder.title.setGravity(Gravity.CENTER);
        viewholder.hour.setGravity(Gravity.CENTER);
        viewholder.minute.setGravity(Gravity.CENTER);

        viewholder.title.setText(mList.get(position).getTitle());
        viewholder.hour.setText(""+mList.get(position).getHour());
        viewholder.minute.setText(""+mList.get(position).getMinute());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}
