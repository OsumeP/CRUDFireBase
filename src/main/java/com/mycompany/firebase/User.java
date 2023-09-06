
package com.mycompany.firebase;

/**
 *
 * @author David Patarroyo
 */
public class User {
    public String name;
    public String birthDay;
    public String nickname;
    public User(String birthDay, String nickname){
        this.birthDay = birthDay;
        this.nickname = nickname;
    }
    public User(String name, String birthDay, String nickname){
        this.name = name;
        this.birthDay = birthDay;
        this.nickname = nickname;
    }
}
