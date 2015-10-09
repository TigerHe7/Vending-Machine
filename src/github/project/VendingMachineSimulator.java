/**
 * Regarding the authoring of classes: if there are multiple authors listed
 * in the class JavaDoc, then each method will have its respective author
 * listed therein. If there is only one author named atop each class, then
 * that author coded all of the methods within the class.
 */
package github.project;

/**
 * The vending machine simulator attempts to recreate a vending machine in
 * a virtual environment.
 *
 * @author Jeff Niu
 */
public class VendingMachineSimulator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final VendingMachine vendingMachine = new VendingMachineImpl();
        final Display display = new Display(vendingMachine);
        display.startDisplay();
    }

}
