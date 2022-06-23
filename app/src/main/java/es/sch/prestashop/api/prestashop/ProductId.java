package es.sch.prestashop.api.prestashop;


import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "product")
public class ProductId {

   @Attribute(name = "id")
   private int id;
   @Attribute(name = "href")
   private String href;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getHref() {
      return href;
   }

   public void setHref(String href) {
      this.href = href;
   }

   @Override
   public String toString() {
      return "Product{" +
              "id=" + id +
              ", href='" + href + '\'' +
              '}';
   }
}
