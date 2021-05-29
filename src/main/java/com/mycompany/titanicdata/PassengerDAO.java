/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.titanicdata;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;


public class PassengerDAO {
    
    public List<TitanicPassengers> getPassengersFromJsonFile() throws IOException {
        List<TitanicPassengers> allPassengers = new ArrayList<TitanicPassengers> ();
        ObjectMapper objectMapper = new ObjectMapper ();
        objectMapper.configure (DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try (InputStream input = new FileInputStream ("D:\\ITI\\Java\\titanic_csv.json")) {
            //Read JSON file
            allPassengers = objectMapper.readValue (input, new TypeReference<List<TitanicPassengers>> () {
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return allPassengers;
}
    
    
    
    public void graphPassengerAges(List<TitanicPassengers> passengerList) 
    {
        List<Float> pAges= passengerList.stream().map (TitanicPassengers::getAge).limit (8).collect (Collectors.toList());
       List<String> pNames= passengerList.stream().map (TitanicPassengers::getName).limit (8).collect (Collectors.toList());
       CategoryChart chart = new CategoryChartBuilder().width (1024).height (768).title ("Age Histogram").xAxisTitle("Names").yAxisTitle("Age").build ();
       chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
       chart.getStyler().setHasAnnotations(true);
       chart.getStyler().setStacked(true);
       chart.addSeries("Passenger's Ages", pNames, pAges);
       new SwingWrapper(chart).displayChart();
}
    public void graphPassengerClass(List<TitanicPassengers> passengerList) {
        
        Map<String, Long> result =passengerList.stream().collect (Collectors.groupingBy(TitanicPassengers::getPclass, Collectors.counting() ) );
        PieChart chart = new PieChartBuilder().width (800).height (600).title (getClass().getSimpleName()).build ();
        Color[] sliceColors= new Color[]{new Color (180, 68, 50), new Color (130, 105, 120), new Color (80, 143, 160)};chart.getStyler().setSeriesColors(sliceColors);
        chart.getStyler().setSeriesColors(sliceColors);
        chart.addSeries("First Class", result.get("1"));
        chart.addSeries("Second Class", result.get("2"));
        chart.addSeries("Third Class", result.get("3"));
        new SwingWrapper(chart).displayChart();
    }
    
        public void graphPassengerSurvived(List<TitanicPassengers> passengerList) {
            
            Map<String, Long> result =passengerList.stream().collect (Collectors.groupingBy(TitanicPassengers::getSurvived, Collectors.counting() ) );
            PieChart chart = new PieChartBuilder().width (800).height (600).title (getClass().getSimpleName()).build ();
            Color[] sliceColors= new Color[]{new Color (180, 68, 50), new Color (130, 105, 120), new Color (80, 143, 160)};chart.getStyler().setSeriesColors(sliceColors);
            chart.getStyler().setSeriesColors(sliceColors);
            chart.addSeries("Survived", result.get("1"));
            chart.addSeries("Not Survived", result.get("0"));
            new SwingWrapper(chart).displayChart();
    }
         public void graphPassengerSurvivedGender(List<TitanicPassengers> passengerList) {

        Map<String, Long> result =passengerList.stream().filter(p -> p.getSurvived().equals("1")).collect (Collectors.groupingBy(TitanicPassengers::getSex, Collectors.counting() ) );
        PieChart chart = new PieChartBuilder().width (800).height (600).title (getClass().getSimpleName()).build ();
        Color[] sliceColors= new Color[]{new Color (180, 68, 50), new Color (130, 105, 120), new Color (80, 143, 160)};chart.getStyler().setSeriesColors(sliceColors);
        chart.getStyler().setSeriesColors(sliceColors);
        chart.addSeries("Male", result.get("male"));
        chart.addSeries("Female", result.get("female"));
        new SwingWrapper(chart).displayChart();
}
}