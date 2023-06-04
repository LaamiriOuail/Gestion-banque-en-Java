package com.mycompany.firstproject;
import java.util.Objects;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Banque {
    private String nameBanque = " ";
    private double banqueMoney = 0.0;
    private ArrayList<CompteBancaire> client=new ArrayList<CompteBancaire>();
    public Banque(String nameBanque, double banqueMoney) {
        this.banqueMoney = banqueMoney;
        client.add(new CompteBancaire("System"));
        this.banqueMoney = banqueMoney;
        client.get(0).verser(this.banqueMoney);
    }

    public Banque() {
        Scanner input = new Scanner(System.in);
        System.out.print("Entrer le nom de cette bank : ");
        nameBanque = input.next();
        client.add(new CompteBancaire("System"));
        System.out.print("Entrer la quantite d'argente dans la banque : ");
        banqueMoney = input.nextDouble();
        client.get(0).verser(this.banqueMoney);
    }

    public void creeCompte() {
        Scanner input = new Scanner(System.in);
        System.out.print("Entrer votre nom : ");
        String proprietaire = input.next();
        client.add(new CompteBancaire(proprietaire));
    }

    public int rechercheCompteParNom(String proprietaire) {
        int n = -1;
        for (CompteBancaire cmpte : client) {
            if (Objects.equals(cmpte.getProprietaire(), proprietaire)) {
                n = client.indexOf(cmpte);
                break;
            }
        }
        return n;
    }

    public int rechercheCompteParNUmero(int numero) {
        int n = -1;
        for (CompteBancaire cmpte :client) {
            if (cmpte.getNumero() == numero) {
                n = client.indexOf(cmpte);
                break;
            }
        }
        return n;
    }

    public void virement() {
        Scanner input = new Scanner(System.in);
        int j = -1;
        System.out.print("Entrer le nom de La personne qui envoyer l'argent : ");
        String nomE = input.next();
        j = rechercheCompteParNom(nomE);
        if (j != -1) {
            System.out.print("Virement par nom ou numero : ");
            String typeOfVirement = input.next();
            String nomR = "";
            int numero = 0;
            int i = -1;
            if (Objects.equals(typeOfVirement, "nom")) {
                System.out.print("Entrer le nom de La personne qui reçoit l'argent : ");
                nomR = input.next();
                i = rechercheCompteParNom(nomR);
            } else if (Objects.equals(typeOfVirement, "numero")) {
                System.out.print("Entrer le numero de la personne a envoyé l'argent : ");
                numero = input.nextInt();
                i = rechercheCompteParNUmero(numero);
            }
            if (i != -1) {
                System.out.print("Entrer l'argent a envoyer : ");
                double arg = input.nextDouble();
                client.get(j).retirer(arg);
                client.get(i).verser(arg);
            } else {
                System.out.println("Aucun compte avec cette nom saisir !!!");
            }
        } else {
            System.out.println("Aucun compte avec le nom : " + nomE + " !!!");
        }
    }

    public void listeCompte() {
        if (client.size() >= 1) {
            for (CompteBancaire cmpte : client) {
                System.out.println(cmpte.toString());
                System.out.println("------------------");
            }
        } else {
            System.out.println("Aucun compte pour le moment .");
        }
    }

    public String toString() {
        return ("Nom de la banque : " + this.nameBanque + "\n");
    }

    public void publicMenuoperationBancaire() {
        System.out.println("------------------Liste des choix : ------------------");
        System.out.println("1-Cree un Compte ");
        System.out.println("2-Liste des Compte ");
        System.out.println("3-Recherche par nom de client");
        System.out.println("4-Recherche par numero de client");
        System.out.println("5-Virement");
        System.out.println("6-Opearations client");
        System.out.println("7-Exit");
    }

    public void pressBoutton() {
        Scanner input = new Scanner(System.in);
        System.out.print("Saisir * pour continue ... ");
        char choix = input.next().charAt(0);
    }

    public void operationBancaire() {
        char choix = ' ';
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("\n\n");
            publicMenuoperationBancaire();
            System.out.println("-----------------------------------------------");
            System.out.print("Entrer votre choix : ");
            choix = input.next().charAt(0);
            System.out.println("-----------------------------------------------");
            switch (choix) {
                case '1' -> {
                    creeCompte();
                    pressBoutton();
                }
                case '2' -> {
                    listeCompte();
                    pressBoutton();
                }
                case '3' -> {
                    System.out.print("Entrer le nom : ");
                    String nom = input.next();
                    if (rechercheCompteParNom(nom) != -1) {
                        System.out.println(client.get(rechercheCompteParNom(nom)).toString());
                    } else {
                        System.out.println("Aucun compte avec le nom : " + nom);
                    }
                    pressBoutton();
                }
                case '4' -> {
                    System.out.print("Entrer le numero : ");
                    int numero = input.nextInt();
                    if (rechercheCompteParNUmero(numero) != -1) {
                        System.out.println(client.get(rechercheCompteParNUmero(numero)).toString());
                    } else {
                        System.out.println("Aucun compte avec le numero : " + numero);
                    }
                    pressBoutton();
                }
                case '5' -> {
                    virement();
                    pressBoutton();
                }
                case '6' -> {
                    System.out.print("Entrer le nom : ");
                    String nom = input.next();
                    if (rechercheCompteParNom(nom) != -1) {
                        double argClientAvantOperation = client.get(rechercheCompteParNom(nom)).getSolde();
                        client.get(rechercheCompteParNom(nom)).operationCompte();
                        if (client.get(rechercheCompteParNom(nom)).getSolde() - argClientAvantOperation < 0) {
                            banqueMoney -= Math.abs(client.get(rechercheCompteParNom(nom)).getSolde() - argClientAvantOperation);
                            client.get(0).setSolde(banqueMoney);
                        } else {
                            banqueMoney += Math.abs(client.get(rechercheCompteParNom(nom)).getSolde() - argClientAvantOperation);
                            client.get(0).setSolde(banqueMoney);
                        }
                    } else {
                        System.out.println("Aucun compte avec le nom : " + nom);
                    }
                    pressBoutton();
                }
                case '7' -> System.out.println("Merci d'utiliser MyBanque Application");
                default -> System.out.println("Choix n'existe pas !!!");
            }
        } while (choix != '7');
    }

    public static void main(String[] args) {
        Banque bank = new Banque();
        bank.operationBancaire();
    }
}