package com.udacity.firebase.shoppinglistplusplus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.firebase.client.ServerValue;

import java.util.HashMap;

/**
 * Created by dhava on 6/14/2016.
 */
public class ShoppingList {
    String listName;
    String owner;
    private HashMap<String, Object> dateCreated;
    private HashMap<String, Object> dateLastChanged;

    public ShoppingList() {
    }

    public ShoppingList(String listName, String owner, HashMap<String, Object> dateCreated) {
        this.listName = listName;
        this.owner = owner;
        this.dateCreated = dateCreated;

        HashMap<String, Object> dateLastChangedObj = new HashMap<String, Object>();
        dateLastChangedObj.put("date", ServerValue.TIMESTAMP);
        this.dateLastChanged = dateLastChangedObj;

    }

    public String getListName() {
        return listName;
    }

    public String getOwner() {
        return owner;
    }

    public HashMap<String, Object> getDateLastChanged() {
        return dateLastChanged;
    }

    public HashMap<String, Object> getDateCreated() {
        //If there is a dateCreated object already, then return that
        if (dateCreated != null) {
            return dateCreated;
        }
        //Otherwise make a new object set to ServerValue.TIMESTAMP
        HashMap<String, Object> dateCreatedObj = new HashMap<String, Object>();
        dateCreatedObj.put("date", ServerValue.TIMESTAMP);
        return dateCreatedObj;


    }

    // Use the method described in http://stackoverflow.com/questions/25500138/android-chat-crashes-on-datasnapshot-getvalue-for-timestamp/25512747#25512747
// to get the long values from the date object.
    @JsonIgnore
    public long getDateLastChangedLong() {

        return (long)dateLastChanged.get("date");
    }

    @JsonIgnore
    public long getDateCreatedLong() {
        return (long)dateCreated.get("date");
    }



}
