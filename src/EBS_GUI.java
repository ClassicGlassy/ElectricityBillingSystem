import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import  java.lang.Math;


public class EBS_GUI implements ActionListener {

    //Elements Used
    JFrame frame = new JFrame("Electricity Billing System");     //Creating Frame Element;
    JPanel UE_panel = new JPanel(),About_panel = new JPanel(), Display_panel = new JPanel();
    JLabel user_name_label,meter_num_label,prev_unit_label,current_unit_label,amps_label;
    JLabel display_user,display_meter,display_date,display_unit;
    JTextField user_name_text,meter_num_text,prev_unit_text,current_unit_text;
    JComboBox year_select,month_select,date_select,amps_select;
    JButton submit_btn,clear_btn;
    static JMenuBar mb;
    static JMenu x;
    static JMenuItem m1,m2;

    void calculator_GUI(){
        setMenuBar();

        UE_panel.setLayout(null);

        UE_panel.setVisible(true);
        About_panel.setVisible(false);
        Display_panel.setVisible(false);

        //UserName Label and TextField
        user_name_label = new JLabel("Enter your Name:");
        user_name_label.setBounds(20,10,150,20);
        user_name_text = new JTextField();
        user_name_text.setBounds(180,10,150,20);
        UE_panel.add(user_name_label);
        UE_panel.add(user_name_text);

        //Meter Number
        meter_num_label = new JLabel("Enter your Meter Number:");
        meter_num_label.setBounds(20,30,150,20);
        meter_num_text = new JTextField();
        meter_num_text.setBounds(180,30,150,20);
        UE_panel.add(meter_num_label);
        UE_panel.add(meter_num_text);

        //Unit Number
        prev_unit_label = new JLabel("Previous Unit number:");
        prev_unit_label.setBounds(20,50,150,20);
        prev_unit_text = new JTextField();
        prev_unit_text.setBounds(180,50,150,20);

        current_unit_label = new JLabel("Current Unit number:");
        current_unit_label.setBounds(20,70,150,20);
        current_unit_text = new JTextField();
        current_unit_text.setBounds(180,70,150,20);

        UE_panel.add(prev_unit_label);
        UE_panel.add(prev_unit_text);

        UE_panel.add(current_unit_label);
        UE_panel.add(current_unit_text);

        //Select Amps
        amps_label = new JLabel("Select your AMPS:");
        amps_label.setBounds(20,90,150,20);
        String[] amps_option = {"5","15","30","60"};
        amps_select = new JComboBox(amps_option);
        amps_select.setBounds(180,90,150,20);

        UE_panel.add(amps_label);
        UE_panel.add(amps_select);

        //Select Date
        JLabel month = new JLabel("Select your Date");
        month.setBounds(80,120,150,20);
        UE_panel.add(month);

        //Date Selector
        String[] year;
        year = returnArray(2078,2085);
        year_select = new JComboBox(year);
        year_select.setBounds(20,150,100,20);
        UE_panel.add(year_select);

        String[] month_names = {"Baishakh","Jestha","Ashar","Shrawan","Bhadra","Ashwin","Kartik","Mangsir","Poush","Magh","Falgun","Chaitra"};
        month_select = new JComboBox(month_names);
        month_select.setBounds(130,150,100,20);
        UE_panel.add(month_select);

        String[] date;
        date = returnArray(1,31);
        date_select = new JComboBox(date);
        date_select.setBounds(240,150,100,20);
        UE_panel.add(date_select);


        //Buttons
        submit_btn = new JButton("Submit");
        submit_btn.setBounds(20,220,100,50);
        submit_btn.addActionListener(this::actionPerformed);
        UE_panel.add(submit_btn);

        clear_btn = new JButton("Clear");
        clear_btn.setBounds(140,220,100,50);
        clear_btn.addActionListener(this::actionPerformed);
        UE_panel.add(clear_btn);

        frame.add(UE_panel);
        setFrameProp(); //Set Frame's Properties
    }

    void about_GUI(){
        setMenuBar();

        UE_panel.setVisible(false);
        About_panel.setVisible(true);
        Display_panel.setVisible(false);

        About_panel.setLayout(null);

        JLabel dev_title = new JLabel("Developer");
        dev_title.setBounds(100,10,400,20);
        JLabel dev_description1 = new JLabel("This App is develop by Saurab Ratna Bajracharya");
        dev_description1.setBounds(10,70,400,20);
        JLabel dev_description2 = new JLabel("BCA 3rd Semester, Mega National College");
        dev_description2.setBounds(10,90,400,20);

        JLabel dev_description3 = new JLabel("Electricity Billing System let's you calculate your electricity bill for the month.");
        dev_description3.setBounds(10,120,400,20);

        About_panel.add(dev_title);
        About_panel.add(dev_description1);
        About_panel.add(dev_description2);
        About_panel.add(dev_description3);

        frame.add(About_panel);
        setFrameProp();
    }


    void displayingGUI(){
        setMenuBar();

        UE_panel.setVisible(false);
        About_panel.setVisible(false);
        Display_panel.setVisible(true);

        Display_panel.setLayout(null);

        //Display Function
        Display obj = new Display();

        //Elements
        display_user = new JLabel("Dear user: "+ obj.name);
        display_user.setBounds(20,20,400,20);
        Display_panel.add(display_user);
        display_meter = new JLabel("Meter number: "+ obj.meter_number);
        display_meter.setBounds(20,40,400,20);
        Display_panel.add(display_meter);

        display_date = new JLabel("Your bill of "+obj.year +"/" + obj.month + " is Rs "+obj.bill_amt);
        display_date.setBounds(20,60,400,20);
        Display_panel.add(display_date);

        display_unit = new JLabel("Unit used:" +obj.consumption + " " + "Current reading: "+obj.meter_reading);
        display_unit.setBounds(20,120,400,30);
        Display_panel.add(display_unit);

        frame.add(Display_panel);

        setFrameProp();
    }

    void calculate_func(){
        Calculation calc = new Calculation();
        calc.calculate_Bill();
        displayingGUI();
    }

    void clear(){
        user_name_text.setText("");
        meter_num_text.setText("");
        prev_unit_text.setText("");
        current_unit_text.setText("");
        year_select.setSelectedIndex(0);
        month_select.setSelectedIndex(0);
        date_select.setSelectedIndex(0);

    }

    void submit(){
        try{
            String name = user_name_text.getText();
            int meter = extractInt(meter_num_text);
            int previous_int = extractInt(prev_unit_text);
            int current_int = extractInt(current_unit_text);
            int amp = amps_select.getSelectedIndex();
            int year = Integer.parseInt((String)year_select.getItemAt(year_select.getSelectedIndex()));
            int month = month_select.getSelectedIndex() +1;
            int date = date_select.getSelectedIndex() +1;
            UserInputs obj = new UserInputs(name,meter,amp,previous_int,current_int,year,month,date);
            calculate_func();

        }
        catch (Exception e){
            System.out.println(e);
        }


    }
    String[] returnArray(int start,int end){
        int size = start - end;
        size = Math.abs(size) + 1;

        String[] returnValues = new String[size];
        int i=0;

        //Ascending
        if(start<=end){
            while(start<=end)
            {
                returnValues[i] = Integer.toString(start);
                start++;
                i++;
            }
        }
        //Descending
        else {
            while(end<=start)
            {
                returnValues[i] = Integer.toString(start);
                start--;
                i++;
            }
        }

        return returnValues;

    }

    void setMenuBar(){
        //JMenuBar
        mb = new JMenuBar();
        x = new JMenu("File");
        m1 = new JMenuItem("Calculator");
        m2 = new JMenuItem("About");
        m1.addActionListener(this::actionPerformed);
        m2.addActionListener(this::actionPerformed);
        x.add(m1);
        x.add(m2);
        mb.add(x);
    }
    void setFrameProp() {
        //Final Frame Properties
        frame.setJMenuBar(mb);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    int extractInt(JTextField s) {
        int value;
        value = Integer.parseInt(s.getText());
        return value;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() ==submit_btn){
            try{
                submit();
                clear();
            }
            catch (Exception ep){
                System.out.println(ep);
            }
        }
        else if(e.getSource() == clear_btn){
            clear();
        }
        else if(e.getSource() == m1){
            clear();
            calculator_GUI();
        }
        else if(e.getSource() == m2){
            clear();
            about_GUI();
        }
    }
}
