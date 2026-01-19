package org.example.passwordmanager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList ;
public class Safe_items {
    private final ObservableList<Items>items =FXCollections.observableArrayList();


    public ObservableList<Items> getItems(){
        return items;
    }
  public void add(String website, String username,String password){
        items.add(new Items(website,username,password));
  }
  public void remove(Items item){
        items.remove(item);
  }

}


