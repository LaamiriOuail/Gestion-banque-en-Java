package com.mycompany.firstproject;
import java.sql.SQLOutput;
import java.util.Scanner;
import static java.lang.Math.pow;
public class CompteBancaire{
    private String proprietaire=" ";
    private int numero=0;
    private double solde;
    static int nbreCompte=0;
    public CompteBancaire(){
        numero=++nbreCompte;
        solde=0.0;
        //Saisir nom
        Scanner sc=new Scanner(System.in);
        System.out.print("Entrer votre nom : ");
        proprietaire=sc.next();
    }
    public CompteBancaire(String proprietaire){
        this.proprietaire=proprietaire;
        numero=++nbreCompte;
        solde=0.0;
    }
    public String toString(){
        String desc="nom : "+proprietaire;
        desc+="\nnumero : "+numero;
        desc+="\nSolde : "+solde;
        return desc;
    }
    public void verser(double somme){
        solde+=somme;
    }
    public void verser(){
        Scanner input=new Scanner(System.in);
        System.out.print("Entrer le $ verser :");
        double somme=input.nextDouble();
        solde+=somme;
    }
    public void retirer(double somme){
        if(somme>solde){
            System.out.println("Solde insuffisant !!");
        }else {
            solde -= somme;
            System.out.println("L'operation est terminer");
        }
    }
    public void retire(){
        Scanner input=new Scanner(System.in);
        System.out.print("Entrer le $ retirer :");
        double somme=input.nextDouble();
        retirer(somme);
    }
    public char menuCompte(){
        System.out.println("i -Les nformation personnel");
        System.out.println("r -Retirer");
        System.out.println("v -Verser");
        System.out.println("q - Quitter");
        Scanner input=new Scanner(System.in);
        System.out.print("Entrer votre choix : ");
        char choix=input.nextLine().charAt(0);
        return choix;
    }
    public String getProprietaire(){
        return this.proprietaire;
    }
    public int getNumero(){
        return this.numero;
    }
    public double getSolde(){
        return solde;
    }
    public void setSolde(double solde){
        this.solde=solde;
    }
    public void pressButton(){
        Scanner input=new Scanner(System.in);
        System.out.print("Entrer sur clavier pour retour au menu precedent ou q pour quitter le programme : ");
        char choix=input.next().charAt(0);
    }
    public void operationCompte(){
        char choix;
        Scanner input=new Scanner(System.in);
        do{
            choix= this.menuCompte();
            switch(choix){
                case 'i':
                    System.out.println(this);
                    pressButton();
                    break;
                case 'r':
                    this.retire();
                    pressButton();
                    break;
                case 'v':
                    this.verser();
                    pressButton();
                    break;
                case 'q':
                    System.out.println("BONNE CHANCE");
                    break;
                case '*':
                    break;
                default:
                    System.out.println("Choix n'existe pas !");
            }
        }while(choix!='q');
    }
}