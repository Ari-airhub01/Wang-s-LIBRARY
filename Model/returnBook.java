package Model;

import java.util.Date;
import java.util.Scanner;

public class returnBook {
    private String borrReader;
    private String bookName;
    private String bookNo;
    private double value;
    private Date borrTime;
    private String tel;

    public returnBook(){}

    public returnBook(String borrReader, String bookName, String bookNo, double value, Date borrTime, String tel) {
        this.borrReader = borrReader;
        this.bookName = bookName;
        this.bookNo = bookNo;
        this.value = value;
        this.borrTime = borrTime;
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
}
