/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt42;

import projekt42.places.Raum_2;

/**
 *
 * @author namibj
 */
public enum GegenstandsKombination {

    Tuch_Knochen(false, Gegenstand.TUCH, Gegenstand.KNOCHEN, () -> {
        System.out.println("Knochen - Tuch Event");
        Gegenstand.KNOCHEN.enableToTakeAway();
            }),
    Knochen_Tür(false, Gegenstand.KNOCHEN, Gegenstand.TÜR, () -> {
        System.out.println("Knochen - Tür Event");
        Projekt42.loader.setPlace(new Raum_2());
            });
    Gegenstand source, target;
    final boolean TargetIsInventory;
    Runnable Action;

    /**
     *
     * @param TargetIsInventory Ob diese Kombination gilt, wenn man den
     * @param source Der Gegenstand, der bewegt wird.
     * @param target Der Gegenstand, auf den der andere draufbewegt wird.
     */
    GegenstandsKombination(boolean TargetIsInventory, Gegenstand source, Gegenstand target, Runnable Action) {
        this.TargetIsInventory = TargetIsInventory;
        this.source = source;
        this.target = target;
        this.Action = Action;
    }

    public void doAction() {
        Action.run();
    }
    
    public Gegenstand getSource(){
        return source;
    }
    
    public Gegenstand getTarget(){
        return target;
    }
}
