package com.example.mark1;

public class User
{
    String name;
    String email;
    String aptCode;
    String status;
    String phoneNo;

    public User()
    {

    }

    public User(String name, String phoneNo, String email, String status, String aptCode)
    {
        this.name = name;
        this.phoneNo = phoneNo;
        this.email = email;
        this.aptCode = aptCode;
        this.status = status;
    }

    public String getName()
    {
        return name;
    }

    public String getPhoneNo()
    {
        return phoneNo;
    }

    public String getEmail()
    {
        return email;
    }

    public String getStatus()
    {
        return status;
    }

    public String getAptCode()
    {
        return aptCode;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setAptCode(String aptCode)
    {
        this.aptCode = aptCode;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
