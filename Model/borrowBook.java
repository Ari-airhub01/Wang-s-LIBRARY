
package Model;

import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.Scanner;

public class borrowBook {
    private String borrReader;
    private String bookName;
    private String bookNo;
    private double value;
    private Date borrTime;
    private String tel;

    public borrowBook() {
    }

    public borrowBook(Scanner scanner){
        scanner =new Scanner(System.in);
        System.out.println("请输入借阅者姓名：");
        this.borrReader = scanner.next();
        System.out.println("请输入图书名称：");
        this.bookName = scanner.next();
        System.out.println("请输入图书ISBN:");
        this.bookNo = scanner.next();
        System.out.println("请输入图书价格 ");
        this.value = scanner.nextDouble();
        System.out.println("请输入借阅者电话：");
        this.tel=scanner.next();
        System.out.println("请输入借阅时间（格式：2022-10-10）：");
        String tim=scanner.next();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.borrTime = sdf.parse(tim);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        borrTime= java.sql.Date.valueOf(borrTime);
    }

    public borrowBook(String borrReader, String bookName, String bookNo, double value, Date borrTime, String tel) {
        this.borrReader = borrReader;
        this.bookName = bookName;
        this.bookNo = bookNo;
        this.value = value;
        this.borrTime =  borrTime;
        this.tel = tel;
    }

    public String getBorrReader() {
        return borrReader;
    }

    public void setBorrReader(String borrReader) {
        this.borrReader = borrReader;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookNo() {
        return bookNo;
    }

    public void setBookNo(String bookNo) {
        this.bookNo = bookNo;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getBorrTime() {
        return borrTime;
    }

    public void setBorrTime(Date borrTime) {
        this.borrTime = borrTime;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "borrowBook{" +
                "borrReader='" + borrReader + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookNo='" + bookNo + '\'' +
                ", value=" + value +
                ", borrTime=" + borrTime +
                ", tel='" + tel + '\'' +
                '}';
    }
}
