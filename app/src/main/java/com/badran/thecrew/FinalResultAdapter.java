package com.badran.thecrew;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.badran.thecrew.databinding.FinalResultItemBinding;

import java.util.ArrayList;


public class FinalResultAdapter extends RecyclerView.Adapter<FinalResultAdapter.FinalResultHolder> {

    private final LayoutInflater mLayoutInflater;
    ArrayList<TheCrew> strings;


    public FinalResultAdapter(LayoutInflater layoutInflater, ArrayList<TheCrew> string) {
        this.strings = string;
        this.mLayoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public FinalResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FinalResultItemBinding finalResultItemBinding = FinalResultItemBinding.inflate(mLayoutInflater);
        return new FinalResultHolder(finalResultItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FinalResultHolder holder, int position) {
        holder.binding.otherName.setText(strings.get(position).name + ":");
        holder.binding.otherOrder.setText(strings.get(position).otherText + ".");
        holder.binding.getRoot().setOnLongClickListener(view -> {
            showConfirmationDialog(holder.getAdapterPosition());
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    public class FinalResultHolder extends RecyclerView.ViewHolder {

        public FinalResultItemBinding binding;

        public FinalResultHolder(@NonNull FinalResultItemBinding b) {
            super(b.getRoot());
            binding = b;
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
                        strings.remove(position);
                        notifyItemRemoved(position);
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
