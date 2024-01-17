package com.example.mini_projet1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CommandeAdapter extends ArrayAdapter<Commande> {


    public CommandeAdapter(Context context, List<Commande> commandes) {
        super(context, 0, commandes);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Commande commande = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_commande_adapter, parent, false);
        }

        TextView clientText = convertView.findViewById(R.id.textViewClient);
        clientText.setText("Client: " + commande.getClient());

        TextView produit= convertView.findViewById(R.id.textViewProduit);
        produit.setText("Produit: " + commande.getProduit());

        TextView quantite= convertView.findViewById(R.id.textViewQuantite);
        quantite.setText("Quantité: " + String.valueOf(commande.getQuantite()));

        TextView prix = convertView.findViewById(R.id.textViewPrix);
        prix.setText("Quantité: " + String.valueOf(commande.getQuantite()));



        return convertView;
    }
}
