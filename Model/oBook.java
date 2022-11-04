package Model;

import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class oBook {
    private String bookNo;
    private String bookName;
    private String genre;
    private Date purchaseDay;
    private boolean islend;
    private Double value;
    private String writer;
    private String publicer;

    public oBook() {
    }

    public oBook(String bookNo, String bookName, String genre, Date purchaseDay, boolean islend, double value, String writer, String publicer) {
        this.bookNo = bookNo;
        this.bookName = bookName;
        this.genre = genre;
        this.purchaseDay = (java.sql.Date) purchaseDay;
        this.islend = islend;
        this.value = value;
        this.writer = writer;
        this.publicer = publicer;
    }


    public oBook(Scanner scanner) {
        scanner=new Scanner(System.in);
        System.out.println("请输入图书的ISBN号：");
        this.bookNo= scanner.nextLine();
        System.out.println("请输入图书名字");
        this.bookName= scanner.nextLine();
//        System.out.println("请输入书籍名称：");
//        this.bookName = scanner.nextLine();
//        System.out.println("请选择书籍的体裁（请根据提示输入序号：计算机类请输入0；非计算机类请输入1；）：");
//        int choise = 3;
//        while (true){
//            choise=scanner.nextInt();
//            if (choise == 0){
//                this.genre = "计算机类";
//            }else if (choise ==1){
//                this.genre = "非计算机类";
//            }else{
//                System.out.println("输入错误！!请重新进行输入。");
//            }
//        }
//        System.out.println("请输入图书的价格：");
//        this.value= scanner.nextDouble();
//        System.out.println("请输入图书的作者：");
//        this.writer= scanner.next();
//        System.out.println("请输入图书的出版社：");
//        this.publicer= scanner.nextLine();
        System.out.println("请输入图书购买的时间：（示例：2022-10-15）");
        String string00= scanner.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        java.sql.Date date =  sdf.parse(string00);
//        Timestamp ts = new Timestamp(date.getTime());
        try {
            this.purchaseDay = sdf.parse(string00);
//            date = (java.sql.Date) new SimpleDateFormat("yyyy-mm-dd").parse(string00);
//            Date purchaseDate = sdf.parse(string00);
//            Timestamp ts = new Timestamp((java.sql.Date)purchaseDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("请输入图书的价格：");
        this.value= scanner.nextDouble();
        System.out.println("请输入图书的作者：");
        this.writer= scanner.next();
        System.out.println("请输入图书的出版社：");
        this.publicer= scanner.next();
        System.out.println("请选择书籍状态（根据提示输入序号）：0.未借出；1.借出（请输入序号）");
        int choise1 ;
//        while (true){
            choise1=scanner.nextInt();
            if (choise1 == 0){
                this.islend = false;
            }else if (choise1 ==1){
                this.islend = true;
            }else{
                System.out.println("输入错误！!请重新进行输入。");
            }

    }



    public String getBookNo() {
        return bookNo;
    }

    public void setBookNo(String bookNo) {
        this.bookNo = bookNo;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getPurchaseDay() {
        return purchaseDay;
    }

    public void setPurchaseDay(Date purchaseDay) {
        this.purchaseDay = purchaseDay;
    }

    public boolean getIslend() {
        return islend;
    }

    public void setIslend(boolean islend) {
        this.islend = islend;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPublicer() {
        return publicer;
    }

    public void setPublicer(String publicer) {
        this.publicer = publicer;
    }

    @Override
    public String toString() {
        return "oBook{" +
                "bookNo='" + bookNo + '\'' +
                ", bookName='" + bookName + '\'' +
                ", genre='" + genre + '\'' +
                ", purchaseDay=" + purchaseDay +
                ", islend=" + islend +
                ", value=" + value +
                ", writer='" + writer + '\'' +
                ", publicer='" + publicer + '\'' +
                '}';
    }
}