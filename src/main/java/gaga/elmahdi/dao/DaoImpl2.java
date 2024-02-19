package gaga.elmahdi.dao;

import org.springframework.stereotype.Component;

@Component("dao")
public class DaoImpl2 implements IDao{
    @Override
    public double getData() {
        System.out.println("Version 2:");
        double temp = Math.random() * 40;
        return temp;
    }
}
