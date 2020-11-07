//testing

import java.util.*;
public class testing
{
    public static void main(String args[])
    {
        System.out.println("Hello");
        /*String str = "NEHA";
        Vector v = new Vector<>();
        v.add(str);
        System.out.println(v.get(0));
        String s= v.get(0).toString();
        System.out.println(s);
        */
        String plain_password = "12345";
        String pw_hash = BCrypt.hashpw(plain_password, BCrypt.gensalt());
        System.out.println(pw_hash);
        String candidate_password = "12345";
        String stored_hash = pw_hash;
        if (BCrypt.checkpw(candidate_password, stored_hash))
        {
            System.out.println(true);
        }
        candidate_password = "002348n";
        if (!BCrypt.checkpw(candidate_password, stored_hash))
        {
            System.out.println(false);
        }


    }
}