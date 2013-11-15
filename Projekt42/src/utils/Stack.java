package utils;

/**
 *
 * @author larcado , namibj
 */
public class Stack<E> {

    private StackElement<E> kenntErstes;

    public Stack() {
        kenntErstes = null;
    }

    public StackElement<E> getFirst() {
        return kenntErstes;
    }

    public void hinzufügen(E pE) {
        kenntErstes = new StackElement<>(pE, kenntErstes);
    }

    public E aufnehnem() {
        E toReturn = kenntErstes.getContent();
        kenntErstes = kenntErstes.getNachfolger();
        return toReturn;
    }

    public boolean alleEntfernen() {
        if (kenntErstes != null) {
            kenntErstes = null;
            return true;
        }
        return false;
    }

    //------------------------------------------------------
    //-------------ListElement------------------------------
    //------------------------------------------------------
    public class StackElement<T> {

        private StackElement<T> kenntNachfolger;
        private T kenntContent;

        private StackElement(T pE, StackElement<T> nachfolger) {
            kenntContent = pE;
            kenntNachfolger = nachfolger;
        }

        StackElement<T> getNachfolger() {
            return kenntNachfolger;
        }

        public T getContent() {
            return kenntContent;
        }
    }
    //-------------------------------------------------------
}
