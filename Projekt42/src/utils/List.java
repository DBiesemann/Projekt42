package utils;

/**
 *
 * @author larcado , namibj
 * @param <E> Der Objekttyp, der in der Liste aufgenommen werden soll.
 */
public class List<E>{
    private ListElement<E> kenntErstes;
    
    public List(){
        kenntErstes=null;
    }
    
    public ListElement<E> getFirst(){
        return kenntErstes;
    }
    
    public void vorneAnhängen(E pE){
        if(kenntErstes==null){
            kenntErstes=new ListElement<>(pE);
        }
        else{
            ListElement<E> listElement = new ListElement<>(pE);
            listElement.kenntNachfolger=kenntErstes;
            kenntErstes=listElement;
        }
    }
    
    public void hinzufügen(E pE){
        if(kenntErstes==null){
            kenntErstes=new ListElement<>(pE);
        }
        else{
            kenntErstes.haengeAn(pE);
        }
    }
    
    public void entferneErstesElement(){
        if(kenntErstes!=null){
            kenntErstes=kenntErstes.kenntNachfolger;
        }
    }
    
    public boolean entfernen(E pE){
        if(kenntErstes!=null){
            kenntErstes = kenntErstes.entfernen(pE);
            return true;
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
    public class ListElement<T>{
        private ListElement<T> kenntNachfolger;
        private final T kenntContent;

        private ListElement(T pE) {
            kenntContent = pE;
            kenntNachfolger=null;
        }

        private void haengeAn(T pE) {
            if(kenntNachfolger==null){
                kenntNachfolger=new ListElement<>(pE);
            }else{
                kenntNachfolger.haengeAn(pE);
            }
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
