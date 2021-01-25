package wily.apps.wilyrabbit.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import wily.apps.wilyrabbit.R;
import wily.apps.wilyrabbit.WorkAdapter;
import wily.apps.wilyrabbit.database.TodoDatabase;
import wily.apps.wilyrabbit.database.WorkDatabase;
import wily.apps.wilyrabbit.entity.Work;
import wily.apps.wilyrabbit.util.WorkDialog;

public class WorkFragment extends Fragment {
    private List<Work> mArrayList;
    private WorkAdapter mAdapter;
    RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_work, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerview_main_list);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        loadRecords();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);


        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(workBtnListener);
        return view;
    }

    private void loadRecords(){
        WorkDatabase db = WorkDatabase.getAppDatabase(getContext());
        db.workDao().getAll().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    mArrayList = item;
                    mAdapter = new WorkAdapter(mArrayList);
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                });
    }

    View.OnClickListener workBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            WorkDialog dialog = new WorkDialog(getContext());
            dialog.callFunction();
        }
    };
}
