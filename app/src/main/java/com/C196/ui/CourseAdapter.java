package com.C196.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.C196.R;
import com.C196.entities.Course;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    class CourseViewHolder extends RecyclerView.ViewHolder{
        private final TextView courseItemView;
        private final TextView courseEndTextView;

        private CourseViewHolder(View itemView){
            super(itemView);
            courseItemView = itemView.findViewById(R.id.courseAssessmentTitleView);
            courseEndTextView = itemView.findViewById(R.id.courseAssessmentEndView);

            itemView.setOnClickListener(view -> {
                int position=getAdapterPosition();
                final Course current = mCourses.get(position);
                Intent intent = new Intent(context, CourseDetails.class);

                intent.putExtra("id", current.getId());
                intent.putExtra("title", current.getTitle());
                intent.putExtra("start", current.getStart());
                intent.putExtra("end", current.getEnd());
                intent.putExtra("status", current.getStatus());
                intent.putExtra("instructorName", current.getInstructorName());
                intent.putExtra("instructorPhone", current.getInstructorPhone());
                intent.putExtra("instructorEmail", current.getInstructorEmail());
                intent.putExtra("termID", current.getTermID());
                intent.putExtra("note", current.getNote());
                context.startActivity(intent);
            });
        }
    }

    private List<Course> mCourses;
    private final Context context;
    private final LayoutInflater mInflater;


    public CourseAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate((R.layout.course_list_item), parent, false);

        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {

        if(mCourses != null){
            Course course = mCourses.get(position);
            holder.courseItemView.setText(course.getTitle());
            holder.courseEndTextView.setText(course.getEnd());
        }
        else{
            holder.courseItemView.setText(R.string.no_course_title);
            holder.courseEndTextView.setText(R.string.no_course_end);
        }
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setmCourses(List<Course> courses){
        mCourses = courses;
        notifyDataSetChanged();
    }
}
