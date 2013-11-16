package utils;

/**
 *
 * @author larcado , namibj
 */
public class SortedList<E extends Comparable<E>>{
    private ListElement<E> kenntErstes;
    
    public SortedList(){
        kenntErstes=null;
    }
    
    public ListElement<E> getFirst(){
        return kenntErstes;
    }
    
    public void hinzufügen(E pE){
        if(kenntErstes==null){
            kenntErstes=new ListElement<>(pE);
        }
        else{
            kenntErstes = kenntErstes.haengeAn(pE);
        }
    }
    
    public boolean entfernen(E pE){
        if(kenntErstes!=null){
            kenntErstes = kenntErstes.entfernen(pE);
        }
        return false;
    }
    
    public boolean alleEntfernen(){
        if(kenntErstes!=null){
            kenntErstes = null;
            return true;
        }
        return false;
    }
    
    //------------------------------------------------------
    //-------------ListElement------------------------------
    //------------------------------------------------------
    public class ListElement<T extends Comparable<T>>{
        private ListElement<T> kenntNachfolger;
        private T kenntContent;

        private ListElement(T pE) {
            kenntContent = pE;
            kenntNachfolger=null;
        }

        private ListElement<T> haengeAn(T pE) {
            if(pE.compareTo(kenntContent)>0){
                if(kenntNachfolger != null){
                    kenntNachfolger = kenntNachfolger.haengeAn(pE);
                }else{
                    kenntNachfolger = new ListElement<>(pE);
                }
            }else{
                ListElement<T> pElement = new ListElement<>(pE);
                pElement.kenntNachfolger = this;
                return pElement;
            }
            return this;
        }
        
        public ListElement<T> getNachfolger(){
            return kenntNachfolger;
        }
        
        public T getContent(){
            return kenntContent;
        }
        
        public ListElement<T> entfernen(T pE){
            if(getContent().equals(pE)){
                return kenntNachfolger;
            }else{
                kenntNachfolger = kenntNachfolger.entfernen(pE);
                return this;
            }
        }
    }
    //-------------------------------------------------------
}
