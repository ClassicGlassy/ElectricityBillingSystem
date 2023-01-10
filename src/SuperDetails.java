public class SuperDetails {
    static String name;
    static int meter,amps;
    static int prev_unit_num, curr_unit_num;
    static int year,month,date;
    static int final_consume;
    static double final_bill;

    //Setter functions
    void setName(String inp){name = inp;}
    void setMeter(int inp){meter = inp;}
    void setAmps(int inp){amps = inp;}
    void setPrev_unit_num(int inp){prev_unit_num = inp;}
    void setCurr_unit_num(int inp){curr_unit_num = inp;}
    void setYear(int inp){year = inp;}
    void setMonth(int inp){month = inp;}
    void setDate(int inp){date = inp;}
    void setFinal_bill(double inp){final_bill = inp;}
    void setFinal_consume(int inp){final_consume = inp;}

    //Getter functions
    String getName(){return name;}
    int getMeter(){return meter;}
    int getPrev_unit_num(){return prev_unit_num;}
    int getCurr_unit_num(){return curr_unit_num;}
    int getYear(){return year;}
    int getMonth(){return month;}
    int getAmps(){return  amps;}
    double getFinal_bill(){return final_bill;}
    int getFinal_consume(){return final_consume;}


}
