public class Calculation extends SuperDetails{
    int[][] table_min_charges = {
            {30,30,50,50,75,100,125,150,175},               //5Amps     == 0
            {50, 50, 75, 75, 100, 125, 150, 175, 200},      //15Amps    == 1
            {75,75,100,100,125,150,175,200,225},            //30Amps    == 2
            {125,125,125,125,150,200,200,250,275}           //60Amps    == 3
    };

    double [][] table_rates = {
            {0,3,6.5,8,9.5,9.5,10,11,12},   //5Amps     == 0
            {4,4,6.5,8,9.5,9.5,10,11,12},   //15Amps    == 1
            {5,5,6.5,8,9.5,9.5,10,11,12},   //30Amps    == 2
            {6,6,6.5,8,9.5,9.5,10,11,12}    //60Amps    == 3
    };

    int[] min_limit = {0,10,20,30,50,100,150,250,400};
    void calculate_Bill(){
        int amp_category = getAmps();
        int use_unit = getCurr_unit_num() - getPrev_unit_num();
        double final_amt = calculation(amp_category,use_unit);

        setFinal_bill(final_amt);
        setFinal_consume(use_unit);
    }

    double calculation(int amp_cat,int use_unit){
        int unitCategory = unit_use_category(use_unit);
        double cumulative_amount = cumulative_bill(unitCategory,amp_cat);
        int rateable_unit = use_unit - min_limit[unitCategory];

        return cumulative_amount + table_min_charges[amp_cat][unitCategory] + (rateable_unit * table_rates[amp_cat][unitCategory]);
    }

    int unit_use_category(int unit_use){
        int i = 8;
        while(true){
            if(unit_use <= min_limit[i]){
                i--;
            }
            else{
                return i;
            }
        }
    }

    double cumulative_bill(int inp,int amp){
        double sum = 0;
        for(int i = 0;i < inp;i++){
            sum = sum + (double)(min_limit[i+1] - min_limit[i]) * table_rates[amp][i];
        }
        return sum;
    }
}
