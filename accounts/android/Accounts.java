package com.android.godot;

import android.app.Activity;
import android.accounts.Account;
import android.accounts.AccountManager;

public class Accounts extends Godot.SingletonBase {
    public Activity activity;
    private Account[] accounts;

    public int getAccountCount() {
        return accounts.length;
    }

    public String getAccount(int id) {
        if (accounts.length > id) {
            return "{\"name\":\""+accounts[id].name+"\", \"type\" : \"" + accounts[id].type + "\"}";
        }

        return null;
    }

    static public Godot.SingletonBase initialize(Activity p_activity) {
        return new Accounts(p_activity);
    }

    //===================================== MAIN ===========================================//
    public Accounts(Activity p_activity) {
        activity = p_activity;
        registerClass("Accounts", new String[]{"getAccountCount", "getAccount"});

        accounts = AccountManager.get(activity).getAccounts(); 
    }
}
