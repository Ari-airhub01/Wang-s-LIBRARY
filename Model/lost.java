package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class lost {
    private String bookNo;
    private String bookName;
    private String genre;
    private Date Time;
    private boolean islend;
    private Double value;
    private String writer;
    private String publicer;

    public lost() {
    }

    public lost(String bookNo, String bookName, String genre, Date time, boolean islend, Double value, String writer, String publicer) {
        this.bookNo = bookNo;
        this.bookName = bookName;
        this.genre = genre;
        this.Time = time;
        this.islend = islend;
        this.value = value;
        this.writer = writer;
        this.publicer = publicer;
    }


    public lost(Scanner scanner){
        scanner =new Scanner(System.in);
        System.out.println("请输入图书ISBN:");
        this.bookNo = scanner.nextLine();
        System.out.println("请输入图书名称：");
        this.bookName = scanner.nextLine();
        System.out.println("请输入图书体裁：");
        this.genre= scanner.nextLine();
        System.out.println("请输入图书价格 ");
        this.value = scanner.nextDouble();
        System.out.println("请输入作者名称：");
        this.writer=scanner.nextLine();
        System.out.println("请输入出版社名称：");
        this.publicer=scanner.nextLine();
        System.out.println("请输入图书购买的时间：（示例：2022-10-15）");
        String string01= scanner.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.Time = sdf.parse(string01);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        Time= java.sql.Date.valueOf(Time);
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

    public Date getTime() {
        return Time;
    }

    public void setTime(Date time) {
        Time = time;
    }

    public boolean isIslend() {
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
        return "lost{" +
                "书籍ISBN='" + bookNo + '\'' +
                ", 图书名='" + bookName + '\'' +
                ", 类别='" + genre + '\'' +
                ", 丢失时间=" + Time +
                ", 是否外借=" + islend +
                ", 图书价值=" + value +
                ", 作者='" + writer + '\'' +
                ", 出版社='" + publicer + '\'' +
                '}';
    }
}
