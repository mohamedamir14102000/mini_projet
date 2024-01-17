package com.example.mini_projet1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewCommands;
    private CommandeAdapter commandeAdapter;
    private EditText editTextProduit;
    private EditText editTextQuantite;
    private Button buttonCommander;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation des vues
        listViewCommands = findViewById(R.id.listViewCommands);
        editTextProduit = findViewById(R.id.editTextProduit);
        editTextQuantite = findViewById(R.id.editTextQuantite);
        buttonCommander = findViewById(R.id.buttonCommander);

        // Initialisation de l'adaptateur
        List<Commande> commandesList = new ArrayList<>();
        commandeAdapter = new CommandeAdapter(this, commandesList);
        listViewCommands.setAdapter(commandeAdapter);

        // Récupération des commandes depuis Back4App
        fetchCommandsFromBack4App();

        // Gestion du clic sur le bouton "Commander"
        buttonCommander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commanderProduit();
            }
        });
    }

    // Méthode pour récupérer les commandes depuis Back4App
    private void fetchCommandsFromBack4App() {
        ParseQuery<Commande> query = ParseQuery.getQuery(Commande.class);

        query.findInBackground(new FindCallback<Commande>() {
            @Override
            public void done(List<Commande> commandes, ParseException e) {
                if (e == null) {
                    // Mise à jour de l'adaptateur avec les nouvelles données
                    commandeAdapter.clear();
                    commandeAdapter.addAll(commandes);
                    commandeAdapter.notifyDataSetChanged();
                } else {
                    // Gestion des erreurs
                    e.printStackTrace();
                }
            }
        });
    }

    // Méthode pour commander un produit
    private void commanderProduit() {
        String produit = editTextProduit.getText().toString();
        String quantiteStr = editTextQuantite.getText().toString();

        if (!produit.isEmpty() && !quantiteStr.isEmpty()) {
            int quantite = Integer.parseInt(quantiteStr);

            // Créer une nouvelle instance de la classe Commande
            Commande nouvelleCommande = new Commande();
            nouvelleCommande.setProduit(produit);
            nouvelleCommande.setQuantite(quantite);

            // Enregistrer la nouvelle commande sur Back4App
            nouvelleCommande.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        // Rafraîchir la liste des commandes
                        fetchCommandsFromBack4App();
                        // Effacer les champs de saisie après la commande
                        editTextProduit.setText("");
                        editTextQuantite.setText("");
                    } else {
                        // Gestion des erreurs lors de l'enregistrement
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Erreur lors de la commande", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
