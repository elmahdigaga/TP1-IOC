package gaga.elmahdi.presentation;

import gaga.elmahdi.dao.DaoImpl;
import gaga.elmahdi.metier.MetierImpl;

public class PresentationStatique {
    public static void main(String[] args) {
        DaoImpl dao = new DaoImpl();
        MetierImpl metier = new MetierImpl();
        metier.setDao(dao);

        System.out.println(metier.calcule());
    }
}
