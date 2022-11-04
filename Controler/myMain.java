package Controler;

import Dao.DaoForBorrow;
import Dao.DaoForClib;
import Dao.DaoForLostlist;
import Dao.DaoForOlib;
import Model.oBook;
import Service.UI;

import java.util.Scanner;

public class myMain {
    DaoForOlib dfo = new DaoForOlib();
    DaoForClib dfc = new DaoForClib();
    public static void main(String[] args) {
        while(true){
            UI.showcase();
            Scanner input = new Scanner(System.in);
            int flag = 0;
            System.out.println("你所要进行的操作的编号是：");
            flag = input.nextInt();

            if (flag == 1) {
//                增加图书
                DaoForClib dfc = new DaoForClib();
                DaoForOlib dfo = new DaoForOlib();
                System.out.println("请选择图书类型，计算机类请按1，非计算机类请按2：");
                int choise3=input.nextInt();
                if (choise3 == 1){
                    dfc.addBook();
                }else if (choise3 == 2){
                    dfo.addBook();
                }else{
                    System.out.println("输入错误");
                    break;
                }
            }

            else if (flag == 2) {
//                检索书籍信息
                DaoForOlib dfo2 = new DaoForOlib();
                DaoForClib dfc2 = new DaoForClib();
                System.out.println("查询所有图书请按1 ； 查询单本图书请按2 ：");
                Scanner sc = new Scanner(System.in);
                int choise4 = sc.nextInt();
                if (choise4 ==1 ){
                   dfo2.retriveAll();
                   dfc2.retriveComputerAll();
                }
                else{
                    System.out.println("请输入书籍类型的序号：1.非计算机类；2.计算机类");
                    int num = sc.nextInt();
                    if (num ==1){
                        System.out.println("请输入书籍名称");
                        String bn = sc.next();
                    dfo2.retriveSpecificBook(bn);}
                    else {
                        System.out.println("请输入书籍名称");
                        String bn = sc.next();
                        dfc2.retriveSpecificComputerBook(bn);
                    }
                }
                break;
            } else if (flag == 3) {
//                删除  丢失图书

                DaoForLostlist daoForLostlist = new DaoForLostlist();
                daoForLostlist.addLostBook();
                break;
            } else if (flag == 4) {
//                修改图书信息
                DaoForOlib daoForOlib = new DaoForOlib();
                DaoForClib daoForClib = new DaoForClib();
                Scanner sc = new Scanner(System.in);
                System.out.println("请选择书籍类型:  1. 非计算机类请按1；  2. 计算机类请按2 ");
                int cho = sc.nextInt();
                if (cho == 1){
                    System.out.println("请按下列指示进行操作");
                    daoForOlib.modifyOBook();
                }
               else{
                    System.out.println("请按下列指示进行操作");
                    daoForClib.modifyCBook();
               }
               break;
            } else if (flag == 5) {
//               borrow  借书业务
                DaoForBorrow daoForBorrow =new DaoForBorrow();
                System.out.println("请输入需要借书的书籍类别：   1.非计算机类；   2.计算机类书籍  （请直接输入序号）");
                Scanner sc = new Scanner(System.in);
                int count =sc.nextInt();
                if (count == 1){
                    DaoForOlib dfo = new DaoForOlib();
                    System.out.println("请输入非计算机类书籍的ISBN号");
                    String string = sc.next();
                    dfo.borrow1(string);
                    daoForBorrow.borrow();
                }else {
                    DaoForClib dfc = new DaoForClib();
                    System.out.println("请输入计算机类书籍的ISBN号：");
                    String string2 = sc.next();
                    dfc.borrow1(string2);
                    daoForBorrow.borrow();
                }
                break;
            }else if (flag == 6){
//                return  还书业务
                DaoForBorrow daoForBorrow =new DaoForBorrow();
                Scanner sc = new Scanner(System.in);
                System.out.println("请输入需要还书的书籍体裁：1.非计算机类  2. 计算机类 （请直接输入序号）");
                int choise = 0;
                choise = sc.nextInt();
                if (choise == 1){
                    System.out.println("请输入书籍ISBN码：");
                    String s =sc.next();
                    daoForBorrow.returnBook(s);
//                    String s2 = sc.next();
                    DaoForOlib.return2(s);
                    System.out.println("——>您已成功归还书籍<——");
                }else{
                    System.out.println("请输入书籍ISBN码：");
                    String s =sc.next();
                    daoForBorrow.returnBook(s);
//                    String s3 = sc.next();
                    DaoForClib.return2(s);
                    System.out.println("——>您已成功归还书籍<——");
                }

break;
            }else if (flag ==7){
//                展示借阅信息
                DaoForBorrow daoForBorrow = new DaoForBorrow();
                daoForBorrow.retriveBorrowList();
                break;
            }else if (flag == 8){
//                查询丢失书籍的信息
                DaoForLostlist daoForLostlist = new DaoForLostlist();
                daoForLostlist.retriveLostList();
                break;
            }else if (flag == 9) {
                System.out.println("系统已安全退出！ 再会 Bye ~");
                break;
            } else {
                System.out.println("您输入的数字有误，请重新输入");
            }
        }
    }
}

