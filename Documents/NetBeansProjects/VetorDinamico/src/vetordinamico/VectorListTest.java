package vetordinamico;

/**
 *
 * @author Mikael
 */
public class VectorListTest {

    public static void main(String[] args) {
        VectorList<Integer> vetor = new VectorList<>();
        
        System.out.println("Está vazio? " +vetor.isEmpty());
        
        for (int i = 0; i < 4; i++)
		vetor.add((i*2+1));
        
        System.out.println("Print vector: ");
	vetor.print();
	System.out.println("");
        System.out.println("Está cheio? " +vetor.isFull());
        System.out.println("Size: " +vetor.size());
	System.out.println("Último elemento: " +vetor.last());
        System.out.println("Primeiro elemento: " +vetor.first());
        System.out.println("O elemento " + 5 + " está no vetor? " +vetor.search(5));
        System.out.println("O elemento " + 100 + " está no vetor? " +vetor.search(100));
        vetor.remove(5);
	vetor.add(100);
        vetor.add(150);
        System.out.println("Print vector: ");
	vetor.print();
        System.out.println("");
        System.out.println("Está cheio? " +vetor.isFull());
        System.out.println("Size: " +vetor.size());
    }    
}
