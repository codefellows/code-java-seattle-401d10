package com.amplifyframework.datastore.generated.model;


import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the CartItem type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "CartItems")
public final class CartItem implements Model {
  public static final QueryField ID = field("CartItem", "id");
  public static final QueryField NAME = field("CartItem", "name");
  public static final QueryField PRICE = field("CartItem", "price");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="String") String price;
  public String getId() {
      return id;
  }
  
  public String getName() {
      return name;
  }
  
  public String getPrice() {
      return price;
  }
  
  private CartItem(String id, String name, String price) {
    this.id = id;
    this.name = name;
    this.price = price;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      CartItem cartItem = (CartItem) obj;
      return ObjectsCompat.equals(getId(), cartItem.getId()) &&
              ObjectsCompat.equals(getName(), cartItem.getName()) &&
              ObjectsCompat.equals(getPrice(), cartItem.getPrice());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getPrice())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("CartItem {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("price=" + String.valueOf(getPrice()))
      .append("}")
      .toString();
  }
  
  public static NameStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static CartItem justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new CartItem(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      name,
      price);
  }
  public interface NameStep {
    BuildStep name(String name);
  }
  

  public interface BuildStep {
    CartItem build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep price(String price);
  }
  

  public static class Builder implements NameStep, BuildStep {
    private String id;
    private String name;
    private String price;
    @Override
     public CartItem build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new CartItem(
          id,
          name,
          price);
    }
    
    @Override
     public BuildStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep price(String price) {
        this.price = price;
        return this;
    }
    
    /** 
     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
     * This should only be set when referring to an already existing object.
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     * @throws IllegalArgumentException Checks that ID is in the proper format
     */
    public BuildStep id(String id) throws IllegalArgumentException {
        this.id = id;
        
        try {
            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
        } catch (Exception exception) {
          throw new IllegalArgumentException("Model IDs must be unique in the format of UUID.",
                    exception);
        }
        
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String name, String price) {
      super.id(id);
      super.name(name)
        .price(price);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder price(String price) {
      return (CopyOfBuilder) super.price(price);
    }
  }
  
}
