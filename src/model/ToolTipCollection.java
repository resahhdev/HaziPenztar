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
public class ToolTipCollection {
    
    HashMap<String, String> tooltips;

    public ToolTipCollection() {
        
        tooltips = new HashMap<>();
        fillToolTips();
    }
    
    private void fillToolTips() {
        
        tooltips.put( ":__SELCATEGORY__", "Kategória kiválasztása" );
        tooltips.put( ":__SELMONTH__", "Hónap kiválasztása" );
    }
    
    public String getToolTip( String AKey ) {
        return tooltips.get( AKey );
    }
}
