package com.example.archek.fitshedule;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.archek.fitshedule.network.ObjectResponse;

import java.util.ArrayList;
import java.util.List;

public class FitAdapter extends RecyclerView.Adapter<FitAdapter.ViewHolder> {

    private List<ObjectResponse> shedules = new ArrayList<>();//main list for data to adapter
    String weekday;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate( R.layout.item, parent, false);
        return new ViewHolder( itemView );
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {//bind and load all views to holder
        ObjectResponse objectResponse = shedules.get(position);

        dayWeekChange(objectResponse.getWeekDay());
        holder.tvNameTrening.setText(objectResponse.getName());
        holder.tvDescription.setText(objectResponse.getDescription());
        holder.tvWeekDay.setText(weekday);
        holder.tvStart.setText(objectResponse.getStartTime());
        holder.tvEnd.setText(" - " + objectResponse.getEndTime());
        holder.tvPlace.setText(objectResponse.getPlace());
        holder.tvCoachName.setText(objectResponse.getTeacher());
    }

    @Override
    public int getItemCount() {
        return shedules.size();
    } //count all items

    public void replaceAll(List<ObjectResponse> reviewsToReplace) {//load all item of data in main list
        shedules.clear();
        shedules.addAll(reviewsToReplace);
        notifyDataSetChanged();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNameTrening; //instal all views into the holder
        private TextView tvDescription;
        private TextView tvWeekDay;
        private TextView tvStart;
        private TextView tvEnd;
        private TextView tvPlace;
        private TextView tvCoachName;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNameTrening = itemView.findViewById(R.id.tvNameTrening);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvWeekDay = itemView.findViewById(R.id.tvWeekday);
            tvStart = itemView.findViewById( R.id.tvStartClass );
            tvEnd = itemView.findViewById( R.id.tvEndClass );
            tvPlace = itemView.findViewById( R.id.tvPlace );
            tvCoachName = itemView.findViewById( R.id.tvCoach );
        }
    }
    public void dayWeekChange(int week){
        switch (week) {
            case 1:
                weekday = "Понедельник";
                break;
            case 2:
                weekday = "Вторник";
                break;
            case 3:
                weekday = "Среда";
                break;
            case 4:
                weekday = "Четверг";
                break;
            case 5:
                weekday = "Пятница";
                break;
            case 6:
                weekday = "Суббота";
                break;
            default:
                weekday = "Воскресенье";
                break;
        }
    }
}
