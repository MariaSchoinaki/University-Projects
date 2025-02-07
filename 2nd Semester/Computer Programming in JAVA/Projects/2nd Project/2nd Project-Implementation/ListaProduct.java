import java.util.*;
class ListaProduct{
    private ArrayList <Product> products = new ArrayList<Product>();        // Array list me antikeimena product

    void addProduct(Product p){
        products.add(p);                                                    // h methodos addProduct prosthetei ena Product (p) sto array list
    }

    void printProducts(){
        int i=1;
        for(Product p : products){                                          // H methodos printProducts ektupwnei ta proionta pou briskontai sto array list products
            System.out.println(i+ ". " + p.toString());
            i++;
        }
    }
   
    int getSize(){
        return products.size();                                             // H methodos getSize epistrefei to megethos tou arraylist products
    }

    Product getProduct(int keyProduct){
        return products.get(keyProduct-1);                                  // H methodos getProduct epistrefei to antikeimeno product apo to arraylist products
    }                                                                       // analoga me thn eisodo (keyproduct) pou exei dothei

    void printKartes(){
        for (Product p : products){
            if(p.getClass()==Card.class){                                               // H methodos print Kartes ektupwnei oles tis kartes pou briskontai sto
                System.out.println(((Card) p).getKey()+". " + p.toString());            // array list products
            }
        }
    }
    int findCard(int key){
        for (Product p : products){                                             // H methodos findCard briskei mia sugkekrimenh karta ston pinaka products
            if((p.getClass()==Card.class)){
                if(((Card) p).getKey()==key){       // analoga me thn eisodo (key) pou exei dothei
                    return products.indexOf(p);
                }
            }
        }
        return -1;
    }
    double getMaxPoso(int i){
        return ((Card) products.get(i)).getMaxPoso();                           // H methodos getMaxPoso epistrefei to megisto poso kinhshs mias sugkekrimenhs kartas
    }                                                                           // analoga me thn eisodo (i) pou exei dothei
    double getYearPoso(int i){
        return ((Card) products.get(i)).getMaxYearPoso();                       // H methodos getYearposo epistrefei to megisto ethsio poso mias sugkekrimenhs kartas
    }                                                                           // analoga me thn eisodo (i) pou exei dothei
    double getTempPoso(int i){
        return ((Card) products.get(i)).getTempPoso();                     // H methodos getTempPoso epistrefei to sunoliko poso twn kinhsewn mias sugkekrimenhs kartas
    }                                                                      // analoga me thn eisodo (i) pou exei dothei
   
    int getCardCode(int i){                                                     // H methodos getMaxPoso epistrefei ton kwdiko mias sugkekrimenhs kartas
        return products.get(i).getCode();                                       // analoga me thn eisodo (i) pou exei dothei
    }

    void addTransaction(int i, Transaction t){
        ((Card) products.get(i)).addTransaction(t);                       // H methodos addTransaction prosthetei ena Transaction(t) se mia sugkekrimenh karta analoga me
    }                                                                     // thn eisodo pou dinetai(i)

    void printDaneia(){
        for (Product p : products){                                       // H methodos printDanei ektupwnei ola ta daneia pou briskontai
            if(p.getClass()==Loan.class){                                 // sto array list products
                System.out.println(p.toString());
            }
        }
    }

    ArrayList <Product> getList(){                                      // H methodos getList epistrefei th lista products
        return products;
    }

    void copyProductList(ArrayList<Product> Proionta){
        for(Product p : Proionta){
            products.add(p);
        }
    }

    void addReadTransactions(ArrayList<Transaction> Transactions){
        for(Transaction t : Transactions){
            for(Product p : products){
                if(t.getCode()==p.getCode() && p.getClass()==Card.class){
                    ((Card)p).addTransaction(t);
                }
            }
        }
    }
}