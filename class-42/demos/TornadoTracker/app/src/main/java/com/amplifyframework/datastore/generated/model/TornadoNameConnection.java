package com.amplifyframework.datastore.generated.model;


import androidx.core.util.ObjectsCompat;

import java.util.Objects;
import java.util.List;

/** This is an auto generated class representing the TornadoNameConnection type in your schema. */
public final class TornadoNameConnection {
  private final List<Tornado> items;
  private final String nextToken;
  public List<Tornado> getItems() {
      return items;
  }
  
  public String getNextToken() {
      return nextToken;
  }
  
  private TornadoNameConnection(List<Tornado> items, String nextToken) {
    this.items = items;
    this.nextToken = nextToken;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      TornadoNameConnection tornadoNameConnection = (TornadoNameConnection) obj;
      return ObjectsCompat.equals(getItems(), tornadoNameConnection.getItems()) &&
              ObjectsCompat.equals(getNextToken(), tornadoNameConnection.getNextToken());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getItems())
      .append(getNextToken())
      .toString()
      .hashCode();
  }
  
  public static BuildStep builder() {
      return new Builder();
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(items,
      nextToken);
  }
  public interface BuildStep {
    TornadoNameConnection build();
    BuildStep items(List<Tornado> items);
    BuildStep nextToken(String nextToken);
  }
  

  public static class Builder implements BuildStep {
    private List<Tornado> items;
    private String nextToken;
    @Override
     public TornadoNameConnection build() {
        
        return new TornadoNameConnection(
          items,
          nextToken);
    }
    
    @Override
     public BuildStep items(List<Tornado> items) {
        this.items = items;
        return this;
    }
    
    @Override
     public BuildStep nextToken(String nextToken) {
        this.nextToken = nextToken;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(List<Tornado> items, String nextToken) {
      super.items(items)
        .nextToken(nextToken);
    }
    
    @Override
     public CopyOfBuilder items(List<Tornado> items) {
      return (CopyOfBuilder) super.items(items);
    }
    
    @Override
     public CopyOfBuilder nextToken(String nextToken) {
      return (CopyOfBuilder) super.nextToken(nextToken);
    }
  }
  
}
