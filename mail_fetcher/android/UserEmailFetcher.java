package com.android.godot;

import android.app.Activity;
import android.accounts.Account;
import android.accounts.AccountManager;

public class UserEmailFetcher extends Godot.SingletonBase {
    public Activity activity;
    public String getEmail() {
        Account[] accounts = AccountManager.get(activity).getAccountsByType("com.google"); 
        if (accounts.length > 0) { 
            return accounts[0].name;
        } else {
            return null;
        }
    }

    static public Godot.SingletonBase initialize(Activity p_activity) {
        return new UserEmailFetcher(p_activity);
    }

    //===================================== MAIN ===========================================//
    public UserEmailFetcher(Activity p_activity) {
        activity = p_activity;
        registerClass("UserEmailFetcher", new String[]{"getEmail"});
    }
}
