package com.example.Redm.classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class Dates {

    @Getter
    @Setter
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date startDate;

    @Getter
    @Setter
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date endDate;

    public String StartDateToString() {
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return outputDateFormat.format(this.startDate);
    }
    public String EndDateToString() {
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return outputDateFormat.format(this.endDate);
    }
    public int getMonth() {
        Date date = this.startDate;
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getMonthValue();
    }
    public int getYear() {
        Date date = this.startDate;
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getYear();
    }
}
