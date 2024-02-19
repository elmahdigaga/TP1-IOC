**Département Mathématiques et Informatique**


# Architecture distribuée J2EE & Middlewares

Filière: **« Génie du Logiciel et des Systèmes Informatiques Distribués »** - **GLSID-2**

Activité Pratique N°1: **Inversion de contrôle et Injection des dépendances**

Année Universitaire: **2023-2024**

# GAGA EL MAHDI


# <span style="text-decoration:underline;">Partie 1:</span>



1. Interface IDao

```
package dao;

public interface IDao {
   double getData();
}
```



2. Implémentation de IDao

```
package dao;

public class DaoImpl implements IDao{
   @Override
   public double getData() {
       double temp = Math.random() * 40;
       return temp;
   }
}
```



3. Interface IMetier

```
package metier;

public interface IMetier {
   double calcule();
}
```



4. Implémentation de IMetier avec Couplage Faible

```
package metier;

import dao.IDao;

public class MetierImpl implements IMetier {
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
```



5. Injection des dépendances
5.1. Instanciation statique

```
package presentation;

import dao.DaoImpl;
import metier.MetierImpl;

public class Presentation {
   public static void main(String[] args) {
       DaoImpl dao = new DaoImpl();
       MetierImpl metier = new MetierImpl();
       metier.setDao(dao);


       System.out.println(metier.calcule());
   }
}
```


5.2. Instanciation dynamique

```
package presentation;

import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Presentation2 {
   public static void main(String[] args) throws Exception {
       Scanner scanner = new Scanner(new File("src/config.txt"));

       String daoClassName = scanner.nextLine();
       Class cDao = Class.forName(daoClassName);
       IDao dao = (IDao) cDao.newInstance();

       String metierClassName = scanner.nextLine();
       Class cMetier = Class.forName(metierClassName);
       IMetier metier = (IMetier) cMetier.newInstance();

       Method method = cMetier.getMethod("setDao", IDao.class);
       method.invoke(metier, dao);

       System.out.println(metier.calcule());
   }
}
```


5.3. Instanciation avec le Framework Spring (Version XML)

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

   <bean id="dao" class="gaga.elmahdi.dao.DaoImpl"></bean>
   <bean id="metier" class="gaga.elmahdi.metier.MetierImpl">
       <property name="dao" ref="dao"></property>
   </bean>
</beans>

package gaga.elmahdi.presentation;

import gaga.elmahdi.metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PresentationSpring {
   public static void main(String[] args) {
       ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
       IMetier metier = (IMetier) context.getBean("metier");

       System.out.println(metier.calcule());
   }
}
```


5.4. Instanciation avec le Framework Spring (Version annotations)

```
package gaga.elmahdi.presentation;

import gaga.elmahdi.metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PresentationSpringAnnotations {
   public static void main(String[] args) {
       ApplicationContext context = new AnnotationConfigApplicationContext("dao", "metier");
       IMetier metier = context.getBean(IMetier.class);

       System.out.println(metier.calcule());
   }
}
```
