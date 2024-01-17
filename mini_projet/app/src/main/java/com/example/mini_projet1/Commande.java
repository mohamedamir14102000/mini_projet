package com.example.mini_projet1;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Commande")
public class Commande extends ParseObject {

    public String getClient() {
        return getString("client");
    }

    public void setClient(String client) {
        put("client", client);
    }

    public String getProduit() {
        return getString("produit");
    }

    public void setProduit(String produit) {
        put("produit", produit);
    }

    public int getQuantite() {
        return getInt("quantite");
    }

    public void setQuantite(int quantite) {
        put("quantite", quantite);
    }
    public float getPrix() {
        return getInt("quantite");
    }

    public void setPrix(float quantite) {
        put("quantite", quantite);
    }
}
