package com.example.listycitylab3;

import static com.example.listycitylab3.R.*;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EditCityFragment extends DialogFragment {

    public interface EditCityDialogListener {
        void updateCity(int position, City updatedCity);
    }

    private EditCityDialogListener listener;
    private City cityToEdit;
    private int position;

    public EditCityFragment(City city, int position) {
        this.cityToEdit = city;
        this.position = position;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view =
                getLayoutInflater().inflate(R.layout.fragment_add_city, null);

        EditText cityName = view.findViewById(id.edit_text_city_text);
        EditText provinceName = view.findViewById(id.edit_text_province_text);

        cityName.setText(cityToEdit.getName());
        provinceName.setText(cityToEdit.getProvince());

        builder.setView(view)
                .setTitle("Edit City")
                .setPositiveButton("Save", (dialog, which) -> {
                    City updated = new City(
                            cityName.getText().toString(),
                            provinceName.getText().toString()
                    );
                    listener.updateCity(position, updated);
                })
                .setNegativeButton("Cancel", null);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (EditCityDialogListener) context;
    }
}

