/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.titanicdata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import joinery.DataFrame;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.columns.Column;


/**
 *
 * @author lenovo
 */
public class MainClass {

    
    public static void main(String[] args) throws IOException {
        
        PassengerDAO passenger = new  PassengerDAO();
        List<TitanicPassengers> allPassengers = new ArrayList<TitanicPassengers> ();     
        allPassengers=passenger.getPassengersFromJsonFile();
       passenger.graphPassengerAges(allPassengers);
        passenger.graphPassengerClass(allPassengers);
        passenger.graphPassengerSurvived(allPassengers);
        passenger.graphPassengerSurvivedGender(allPassengers);

//        for ( TitanicPassengers p : allPassengers)
//        {
//            System.out.println(p.getName());
//        }
//        
       
        
        
        
        
        
        
        
        
 









    }
    
}
