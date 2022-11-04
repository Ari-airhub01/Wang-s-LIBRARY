package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class cBook {
    private String bookNo;
    private String bookName;
    private String genre;
    private Date purchaseDay;
    private boolean islend;
    private Double value;
    private String writer;
    private String publicer;

    @Override
    public String toString() {
        return "cBook{" +
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

    public cBook() {
    }

    public cBook(String bookNo, String bookName, String genre, Date purchaseDay, boolean islend, double value, String writer, String publicer) {
        this.bookNo = bookNo;
        this.bookName = bookName;
        this.genre = genre;
        this.purchaseDay = purchaseDay;
        this.islend = islend;
        this.value = value;
        this.writer = writer;
        this.publicer = publicer;
    }

    public cBook(Scanner scanner) {
        scanner=new Scanner(System.in);
        System.out.println("请输入图书的ISBN号：");
        this.bookNo= scanner.nextLine();
        System.out.println("请输入书籍的名字：");
        this.bookName= scanner.nextLine();
        System.out.println("请输入图书购买的时间：（示例：2022-10-15）");
        String S = scanner.next();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            this.purchaseDay = sdf.parse(S);
//            Timestamp ts = new Timestamp(this.purchaseDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("请选择书籍状态（根据提示输入序号）：0.未借出；1.借出");
        int choise1;
//        while (true){
            choise1=scanner.nextInt();
            if (choise1 == 0){
                this.islend = false;
            }else if (choise1 ==1){
                this.islend = true;
            }else{
                System.out.println("输入错误！!请重新进行输入。");
            }
//        }
        System.out.println("请输入图书的价格：");
        this.value= scanner.nextDouble();
        System.out.println("请输入图书的作者：");
        this.writer= scanner.next();
        System.out.println("请输入图书的出版社：");
        this.publicer= scanner.next();
    }


    public String getBookNo() {
        return bookNo;
    }

    public void setBookNo(String bookNo) {
        this.bookNo = bookNo;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(double value) {
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

    public boolean getIslend() {
        return islend;
    }

    public void setIslend(boolean islend) {
        this.islend = islend;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Date getPurchaseDay() {
        return purchaseDay;
    }

    public void setPurchaseDay(Date purchaseDay) {
        this.purchaseDay = purchaseDay;
    }
}
