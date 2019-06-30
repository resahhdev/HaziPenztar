/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;

/**
 *
 * @author resahh
 */
public class MonthCollection {
    
    HashMap<String, String> FMonth;

    public MonthCollection() {
        
        FMonth = new HashMap<>();
        fillMonth();
    }
    
    private void fillMonth() {
        
        FMonth.put( "01", "Január" );
        FMonth.put( "02","Február" );
        FMonth.put( "03", "Március" );
        FMonth.put( "04", "Április" );
        FMonth.put( "05", "Május" );
        FMonth.put( "06", "Június" );
        FMonth.put( "07", "Július" );
        FMonth.put( "08", "Augusztus" );
        FMonth.put( "09", "Szeptember" );
        FMonth.put( "10", "Október" );
        FMonth.put( "11", "November" );
        FMonth.put( "12", "December" );
    }
    
    public String getMonthNumber( String AKey ) {
        return FMonth.get( AKey );
    }
}
