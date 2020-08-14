package com.example.mj_talk.ui.Login;

import android.accounts.Account;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mj_talk.R;

import java.util.HashMap;
import java.util.Map;

public class AccountData {
    public String name;
    public String phonenum;
    public String num;
    public String major;
    public String account_id;
    public String account_password;
    public String job;

    public AccountData(String name, String phonenum, String num, String major, String id, String password, String job) {
        setName(name);
        setPhonenum(phonenum);
        setNum(num);
        setMajor(major);
        setAccount_id(id);
        setAccount_password(password);
        setJob(job);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }


    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getAccount_password() {
        return account_password;
    }

    public void setAccount_password(String account_password) {
        this.account_password = account_password;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("phonenum", phonenum);
        result.put("num", num);
        result.put("major", major);
        return result;
    }

}
