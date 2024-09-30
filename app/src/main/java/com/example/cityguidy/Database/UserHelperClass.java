package com.example.cityguidy.Database;

public class UserHelperClass {
   public String _fullName, _userName, _email, _password;

    public UserHelperClass() {
    }

    public UserHelperClass(String _fullName, String _userName, String _email, String _password) {
        this._fullName = _fullName;
        this._userName = _userName;
        this._email = _email;
        this._password = _password;
    }

    /*public String get_fullName() {
        return _fullName;
    }

    public String get_userName() {
        return _userName;
    }

    public String get_email() {
        return _email;
    }

    public String get_password() {
        return _password;
    }*/

}
