package com.badran.thecrew;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.badran.thecrew.databinding.ActivityMainBinding;
import com.badran.thecrew.databinding.ItmeBinding;

import java.util.ArrayList;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

public class TheCrewAdapter extends RecyclerView.Adapter<TheCrewAdapter.TheCrewHolder> {

    public interface AdapterCallback {
        void onItemUpdated();
    }

    private final ArrayList<TheCrew> mNames;
    private final LayoutInflater mLayoutInflater;
    private AdapterCallback callback;


    public TheCrewAdapter (ArrayList<TheCrew> names, LayoutInflater layoutInflater, AdapterCallback callback) {
        this.mNames = names;
        this.mLayoutInflater = layoutInflater;
        this.callback = callback;
    }

    @NonNull
    @Override
    public TheCrewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItmeBinding itemBinding = ItmeBinding.inflate(mLayoutInflater, parent, false);
        return new TheCrewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TheCrewHolder holder, int position) {
        holder.binding.name.setText(mNames.get(position).name);
        holder.binding.Nescafe.setChecked(mNames.get(position).nescafe);
        holder.binding.Coffee.setChecked(mNames.get(position).coffee);
        holder.binding.Canderel.setChecked(mNames.get(position).canderel);
        holder.binding.Sugar.setChecked(mNames.get(position).sugar);
        holder.binding.Nestle.setChecked(mNames.get(position).nestle);
        holder.binding.black.setChecked(mNames.get(position).black);
        holder.binding.coffeeMate.setChecked(mNames.get(position).coffeeMate);
        holder.binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showConfirmationDialog(holder.getAdapterPosition());
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    public class TheCrewHolder extends RecyclerView.ViewHolder {
        public ItmeBinding binding;

        public TheCrewHolder(@NonNull ItmeBinding i) {
            super(i.getRoot());
            binding = i;
        }
    }

    public void showConfirmationDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mLayoutInflater.getContext());
        builder.setTitle("Confirmation")
                .setMessage("Are you sure you want to remove the order?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Perform action when "Yes" button is clicked
                        mNames.remove(position);
                        notifyItemRemoved(position);
                        callback.onItemUpdated();
                        Toast.makeText(mLayoutInflater.getContext(), "Item removed " + position, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Perform action when "No" button is clicked
                        Toast.makeText(mLayoutInflater.getContext(), "Removing canceled", Toast.LENGTH_SHORT).show();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

}
