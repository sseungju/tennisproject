package JavaTenisProject;


public class GameElement extends GameMethod {
      
   private int set;
   private int selectSet;
   
   public GameElement() {
      
   }
      
   public GameElement(int set, int selectSet) {
      super();
      this.set = set;
      this.selectSet = selectSet;
   }

   public int getset() {
      return set;
   }

   public void setset(int set) {
      this.set = set;
   }

   public int getSelectSet() {
      return selectSet;
   }

   public void setSelectSet(int selectSet) {
      this.selectSet = selectSet;
   }

   @Override
   public String toString() {
      return "GameElement [set=" + set + ", selectSet=" + selectSet + "]";
   }
      
   
}