package gaga.elmahdi.metier;

import gaga.elmahdi.dao.IDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("metier")
public class MetierImpl implements IMetier {
    @Autowired
    private IDao dao;

    @Override
    public double calcule() {
        double temp = dao.getData();
        double result = (temp * 9 / 5) + 32;
        return result;
    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
