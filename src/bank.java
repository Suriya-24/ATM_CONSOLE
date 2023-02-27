import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;

public class bank
{
    static HashMap<Integer,Integer> map = new HashMap<>();
    public static int find(ArrayList<String>arr,String str)
    {
        int i=0;
        for(String s:arr)
        {
            String[] st=s.split(" ");
            if(st[0].equals(str))return i;
            i++;
        }
        return -1;
    }
    public static int denom(HashMap<Integer,Integer> map,int target)
    {
        for(int i:map.keySet())
        {
            if(i<=target)
            {
                int j=0;
                while(map.get(i)!=0 && i<=target)
                {
                    target-=i;
                    map.put(i,map.get(i)-1);
                    j++;
                    if(target<=0)break;
                }
            }
        }
        return target;
    }
    public static int total(HashMap<Integer,Integer>map)
    {
        int total=0;
        for(int i:map.keySet())
        {
            total+=i*map.get(i);
        }
        return total;
    }
    public static void main(String[] args)
    {
        map.put(2000,10);
        map.put(500,10);
        map.put(100,10);
        System.out.println("Welcome");
        Scanner in=new Scanner(System.in);
        ArrayList<String>user=new ArrayList<>();
        HashMap<String,String>user_map=new HashMap<>();
        ArrayList<String>admin=new ArrayList<>();
        HashMap<String,String>admin_map=new HashMap<>();
        while(true)
        {
            System.out.println("1.ADD USER");
            System.out.println("2.ADMIN");
            System.out.println("3.USER");
            System.out.println("4.BREAK");
            String s=in.next();
            if(s.equals("4"))
            {
                System.out.println("THANK YOU \nSEE YOU AGAIN");
                break;
            }
            if(s.equals("1"))
            {
                System.out.println("USER_NAME USER_TYPE USER_PASS");
                String s2="";
                s2=in.next();
                s2+=" ";
                s2+=in.next();
                s2+=" ";
                s2+=in.next();
                String[] user_create=s2.split(" ");
                if(user_create[1].equals("ADMIN"))
                {
                    String temp=user_create[0]+" "+user_create[2];
                    admin.add(temp);
                    admin_map.put(user_create[0],user_create[2]);
                }
                else
                {
                    String temp=user_create[0]+" "+user_create[2]+" "+"1000";
                    if(user_create[1].equals("USER"))
                    {
                        user.add(temp);
                        user_map.put(user_create[0],user_create[2]);
                    }
                    else
                    {
                        System.out.println("Incorrect Statement");
                    }
                }
            }
            if(s.equals("2"))
            {
                System.out.println("Name"+" "+"Password");
                String temp=in.next();
                temp+=" ";
                temp+=in.next();
                String[] arr=temp.split(" ");
                if(admin_map.containsKey(arr[0]))
                {
                    if(!arr[1].equals(admin_map.get(arr[0])))
                    {
                        System.out.println("Incorrect password");
                    }
                    else
                    {
                        System.out.println("SUCESSFULLY LOGGED IN");
                        System.out.println("1.LOAD AMOUNT");
                        System.out.println("2.ATM BALANCE");
                        System.out.println("3.LOGOUT");
                        String adm="";
                        adm=in.next();
                        while(!adm.equals("3")) {
                            if (adm.equals("1")) {
                                int amt_added=total(map);
                                System.out.println("Amount");
                                String temp2 = in.next();
                                System.out.println("Enter no of 2000:");
                                String two=in.next();
                                map.put(2000,map.get(2000)+Integer.parseInt(two));
                                System.out.println("Enter no of 500:");
                                String five=in.next();
                                map.put(100,map.get(100)+Integer.parseInt(five));
                                System.out.println("Enter no of 100:");
                                String hun=in.next();
                                map.put(500,map.get(500)+Integer.parseInt(hun));
                                int amt_temp=total(map);
                                System.out.println("AMOUNT ADDED:"+(amt_temp-amt_added));
                                System.out.println("1.LOAD AMOUNT");
                                System.out.println("2.ATM BALANCE");
                                System.out.println("3.LOGOUT");
                                adm = in.next();
                            }
                            else {
                                if (adm.equals("2")) {
                                    System.out.println(total(map));
                                    System.out.println("Number of 2000:"+map.get(2000));
                                    System.out.println("Number of 500:"+map.get(500));
                                    System.out.println("Number of 100:"+map.get(100));
                                    System.out.println("1.LOAD AMOUNT");
                                    System.out.println("2.ATM BALANCE");
                                    System.out.println("3.LOGOUT");
                                    adm = in.next();
                                }
                            }
                        }
                    }
                }
            }
            if(s.equals("3"))
            {
                ArrayList<String>q=new ArrayList<>();
                System.out.println("Name Pin");
                String temp=in.next();
                temp+=" ";
                temp+=in.next();
                String[] temp2=temp.split(" ");
                if(user_map.containsKey(temp2[0]))
                {
                    if(user_map.get(temp2[0]).equals(temp2[1]))
                    {
                        System.out.println("1.Balance check");
                        System.out.println("2.Withdrawl");
                        System.out.println("3.Deposit");
                        System.out.println("4.PIN change");
                        System.out.println("5.TRANSFER");
                        System.out.println("6.MINI STATEMENT");
                        System.out.println("7.LOGOUT");
                        String cmd=in.next();
                        while(!cmd.equals("7"))
                        {
                            if(q.size()==6)q.remove(0);
                            if (cmd.equals("1"))
                            {
                                int ind = find(user, temp2[0]);
                                String[] arr = user.get(ind).split(" ");
                                System.out.println("USER NAME:"+" "+arr[0]);
                                System.out.println("ACCOUNT BALANCE:"+" "+arr[arr.length - 1]);
                                q.add("Balance check:"+arr[2]);
                            }
                            else
                            {
                                if (cmd.equals("2"))
                                {
                                    System.out.println("Enter amount:");
                                    String amt = in.next();
                                    int amt2 = Integer.parseInt(amt);
                                    int poss=denom(map,amt2);
                                    if(poss!=0)
                                    {
                                        System.out.println("Insufficient Balance in Bank");
                                    }
                                    else {
                                        int ind = find(user, temp2[0]);
                                        String[] user_with = user.get(ind).split(" ");
                                        int balance = Integer.parseInt(user_with[2]);
                                        int fin = balance - amt2;
                                        if (amt2 > balance)
                                        {
                                            System.out.println("Insufficient balance in your account");
                                        } else
                                        {
                                            for(int i:map.keySet())
                                            {
                                                if(i<=amt2)
                                                {
                                                    int j=0;
                                                    while(map.get(i)!=0 && i<=amt2)
                                                    {
                                                        amt2-=i;
                                                        map.put(i,map.get(i)-1);
                                                        j++;
                                                        if(amt2<=0)break;
                                                    }
                                                }
                                            }
                                            String val = user_with[0] + " " + user_with[1] + " " + String.valueOf(fin);
                                            user.set(ind, val);
                                            System.out.println("SUCESSFULLY FETCHED AMOUNT");
                                            q.add("WITHDRAWL:" + amt);
                                        }
                                    }
                                }
                                else
                                {
                                    if (cmd.equals("3"))
                                    {
                                        int  amt_entered=total(map);
                                        System.out.println("ENTER THE VALUE");
                                        String amt_val=in.next();
                                        System.out.println("Enter no of 2000:");
                                        String amt = in.next();
                                        map.put(2000,map.get(2000)+Integer.parseInt(amt));
                                        System.out.println("Enter no of 500:");
                                        amt=in.next();
                                        map.put(500,map.get(500)+Integer.parseInt(amt));
                                        System.out.println("Enter the no of 100:");
                                        amt=in.next();
                                        map.put(100,map.get(100)+Integer.parseInt(amt));
                                        int amt2 = Integer.parseInt(amt_val);
                                        int ind = find(user, temp2[0]);
                                        String[] user_with = user.get(ind).split(" ");
                                        int balance = Integer.parseInt(user_with[2]);
                                        String val = user_with[0] + " " + user_with[1] +" " +String.valueOf(amt2 + balance);
                                        user.set(ind, val);
                                        int amt_temp=total(map);
                                        System.out.println("AMOUNT ADDED SUCESSFULLY"+" "+(amt_temp-amt_entered));
                                        q.add("DEPOSIT:"+amt_val);
                                    }
                                    else
                                    {
                                        if(cmd.equals("4"))
                                        {
                                            int ind=find(user,temp2[0]);
                                            System.out.println("ENTER PREVIOUS PIN:");
                                            String tem=in.next();
                                            if(user_map.get(temp2[0]).equals(tem))
                                            {
                                                System.out.println("ENTER NEW PIN:");
                                                String new_pin=in.next();
                                                user_map.put(temp2[0],new_pin);
                                                String[] user_with = user.get(ind).split(" ");
                                                String val=user_with[0]+" "+new_pin+" "+user_with[2];
                                                user.set(ind,val);
                                                System.out.println("PIN CHANGED SUCESSFULLY");
                                                q.add("Pin changed");
                                            }
                                            else
                                            {
                                                System.out.println("Incorrect pin");
                                            }
                                        }
                                        else
                                        {
                                            if(cmd.equals("5"))
                                            {
                                                System.out.println("TRANSFER ACCOUNT NAME:");
                                                String transfer=in.next();
                                                System.out.println("ENTER AMOUNT:");
                                                String amount=in.next();
                                                int sender_ind=find(user,temp2[0]);
                                                int receiver_ind=find(user,transfer);
                                                if(receiver_ind==-1)
                                                {
                                                    System.out.println("RECEIVER NOT FOUND");
                                                }
                                                else
                                                {
                                                    String sender = user.get(sender_ind);
                                                    String receiver = user.get(receiver_ind);
                                                    String[] sender_arr=sender.split(" ");
                                                    String[] receiver_arr=receiver.split(" ");
                                                    int transfer_amt=Integer.parseInt(amount);
                                                    if(Integer.parseInt(sender_arr[2])<transfer_amt)
                                                    {
                                                        System.out.println("Insufficient Balance");
                                                    }
                                                    else
                                                    {
                                                        int change1=Integer.parseInt(sender_arr[2])-transfer_amt;
                                                        sender_arr[2]=String.valueOf(change1);
                                                        int change2=Integer.parseInt(receiver_arr[2]+transfer_amt);
                                                        receiver_arr[2]=String.valueOf(change2);
                                                        String modify1=sender_arr[0]+" "+sender_arr[1]+" "+sender_arr[2];
                                                        String modify2=receiver_arr[0]+" "+receiver_arr[1]+" "+receiver_arr[2];
                                                        user.set(sender_ind,modify1);
                                                        user.set(receiver_ind,modify2);
                                                        q.add("TRANSFER "+amount+" FROM "+temp2[0]+" TO "+transfer);
                                                        System.out.println("Sucessfully Transferred");
                                                    }
                                                }
                                            }
                                            else
                                            {
                                                if(cmd.equals("6"))
                                                {
                                                    for(String st:q)
                                                    {
                                                        System.out.println(st);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            System.out.println("1.Balance check");
                            System.out.println("2.Withdrawl");
                            System.out.println("3.Deposit");
                            System.out.println("4.PIN change");
                            System.out.println("5.TRANSFER");
                            System.out.println("6.MINI STATEMENT");
                            System.out.println("7.LOGOUT");
                            cmd=in.next();
                        }
                    }
                    else
                    {
                        System.out.println("Incorrect pin");
                    }
                }
                else
                {
                    System.out.println("User not found");
                }
            }
        }
    }
}
