package ru.personacode;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        FullStackDeveloper fullStackDeveloper = new FullStackDeveloper();
        fullStackDeveloper.developBack();
        fullStackDeveloper.developGUI();
        fullStackDeveloper.drinkCoffee();

        Developer developer = new Frontender() {
            @Override
            public void developGUI() {
                System.out.println("Frontender working...");
            }

            @Override
            public void develop() {

            }

            @Override
            public void drinkCoffee() {

            }
        };

        if (developer instanceof Frontender) {
            ((Frontender)developer).developGUI();
        }
    }
}
