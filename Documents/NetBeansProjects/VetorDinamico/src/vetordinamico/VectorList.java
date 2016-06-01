package vetordinamico;

/**
 *
 * @author Mikael
 * @param <T>
 */
public class VectorList <T> {
    private T[] vetor;
    private int size = 0,
                elements = -1;
	
    /**
	 * Constructs an empty vector with an initial capacity of four.
    */
    public VectorList () {
	this.size = 4;
	this.vetor = (T[])new Object[size];
    }
	
    /**
	 * Constructs an empty vector with the specified initial capacity.
	 * @param size
     */
    public VectorList (int size) {
	this.size = size;
	this.vetor = (T[])new Object[size];
    }

    /**
	 * Returns true if the vector contains no elements.
	 * @return
     */
    public boolean isEmpty () {
	return elements == -1;
    }

    /**
	 * Returns true if the vector is full.
	 * @return
     */
    public boolean isFull () {
	return elements == size - 1;
    }

    /**
	 * Returns the size of the vector.
	 * @return
     */
    public int size () {
	return vetor.length;
    }

	/**
	 * Returns the element that is the end of the vector.
	 * @return
	 */
    public T last () {
	if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Vetor vazio!");
        }
        return vetor[elements];
    }

    /**
	 * Returns the object that is the beginning of the vector.
	 * @return
     */
	 
    public T first () {
	if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Vetor vazio!");
        }
        return vetor[0];
    }
    
    /**
	 * Returns the search the in the vector.
         * If the element does not exist null is returned.
         * @param element
	 * @return
     */
    public boolean search(T element) {
        for (int i = 0; i < elements; i++) {
            if (vetor[i].equals(element)) {
                return true;
            }
        }
        return false;
       
    }

    /**
	 * Adds an element in the vector.
	 * @param element
         * @return 
     */
    public boolean add (T element) {	
	if (isFull()) {
		System.out.println("Error: queue overflow");
                System.out.println("Dobrando o tamanho do vetor e adicionando...");
                doubleArray();
                vetor[++elements] = element;
                return true;
	} else {
		vetor[++elements] = element;
                return true;
	}
    }

	/**
	 *  Removes an element in the vector and return true.
         *  In case of error return false.
         * @param element
         * @return 
	 */
    public boolean remove (T element) {
	if (isEmpty()) {
		System.out.println("Error: Vetor Vazio");
                return false;
	} else {
            for (int i = 0; i <= elements; i++) {
                if (vetor[i].equals(element)) {
                    for (int j = i; j <= elements; j++){
                        if((j == elements)){
                            vetor[j] = null;
                            elements--;
                            return true;
                        }
                    vetor[j] = vetor[j + 1];
                    }
                    elements--;
                    return true;
                }
            }
	}
        System.out.println("Error: Elemento não está no vetor.");
        return false;
    }
    
    /**
	 * Doubles the size of the vector.
     */
    private void doubleArray () {
        size *= 2;
        T[] vetorAux = (T[])new Object[size];
        System.arraycopy(vetor, 0, vetorAux, 0, size / 2);
        vetor = vetorAux;
    }
	
    /**
	 * Prints all elements that are in the vector.
     */
    public void print () {
	if (!isEmpty()) {
		for (int i = 0; i <= elements; i++) {
			System.out.print(vetor[i] + " ");
		}
	}
    }    
}
