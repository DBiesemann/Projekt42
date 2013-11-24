/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt42;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author namibj
 */
public class SavePoint implements Serializable {

    private static final long serialVersionUID = 1;

    private final HashMap<String, Room> rooms;
    private final Inventar inventar;

    private SavePoint() {
        rooms = new HashMap<>();
        inventar = new Inventar();
    }

    static SavePoint getSavePoint(String name) {
        //todo: Datei lade Code einfügen
        return new SavePoint();
    }

    private class Room {

    }
}
