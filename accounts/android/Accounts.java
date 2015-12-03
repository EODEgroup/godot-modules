package com.android.godot;

import android.app.Activity;
import android.accounts.Account;
import android.accounts.AccountManager;

public class Accounts extends Godot.SingletonBase {
    public Activity activity;
    public String[] getAccounts() {
        Account[] accounts = AccountManager.get(activity).getAccounts(); 

        String[] response = new String[accounts.length][2];
        for (int i = 0; i < accounts.length; ++i) {
            response[i][0] = accounts[i].name;
            response[i][1] = accounts[i].type;
        }

        return response;
    }

    static public Godot.SingletonBase initialize(Activity p_activity) {
        return new Accounts(p_activity);
    }

    //===================================== MAIN ===========================================//
    public Accounts(Activity p_activity) {
        activity = p_activity;
        registerClass("Accounts", new String[]{"getAccounts"});
    }
}
