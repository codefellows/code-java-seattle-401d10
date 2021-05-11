package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.BelongsTo;

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

/** This is an auto generated class representing the SuckedUpThing type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "SuckedUpThings")
@Index(name = "byThing", fields = {"tornadoId"})
public final class SuckedUpThing implements Model {
  public static final QueryField ID = field("SuckedUpThing", "id");
  public static final QueryField NAME = field("SuckedUpThing", "name");
  public static final QueryField LONGITUDE = field("SuckedUpThing", "longitude");
  public static final QueryField LATITUDE = field("SuckedUpThing", "latitude");
  public static final QueryField S3_IMAGE_KEY = field("SuckedUpThing", "s3ImageKey");
  public static final QueryField TORNADO = field("SuckedUpThing", "tornadoId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String") String name;
  private final @ModelField(targetType="Float") Double longitude;
  private final @ModelField(targetType="Float") Double latitude;
  private final @ModelField(targetType="String") String s3ImageKey;
  private final @ModelField(targetType="Tornado", isRequired = true) @BelongsTo(targetName = "tornadoId", type = Tornado.class) Tornado tornado;
  public String getId() {
      return id;
  }
  
  public String getName() {
      return name;
  }
  
  public Double getLongitude() {
      return longitude;
  }
  
  public Double getLatitude() {
      return latitude;
  }
  
  public String getS3ImageKey() {
      return s3ImageKey;
  }
  
  public Tornado getTornado() {
      return tornado;
  }
  
  private SuckedUpThing(String id, String name, Double longitude, Double latitude, String s3ImageKey, Tornado tornado) {
    this.id = id;
    this.name = name;
    this.longitude = longitude;
    this.latitude = latitude;
    this.s3ImageKey = s3ImageKey;
    this.tornado = tornado;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      SuckedUpThing suckedUpThing = (SuckedUpThing) obj;
      return ObjectsCompat.equals(getId(), suckedUpThing.getId()) &&
              ObjectsCompat.equals(getName(), suckedUpThing.getName()) &&
              ObjectsCompat.equals(getLongitude(), suckedUpThing.getLongitude()) &&
              ObjectsCompat.equals(getLatitude(), suckedUpThing.getLatitude()) &&
              ObjectsCompat.equals(getS3ImageKey(), suckedUpThing.getS3ImageKey()) &&
              ObjectsCompat.equals(getTornado(), suckedUpThing.getTornado());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getLongitude())
      .append(getLatitude())
      .append(getS3ImageKey())
      .append(getTornado())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("SuckedUpThing {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("longitude=" + String.valueOf(getLongitude()) + ", ")
      .append("latitude=" + String.valueOf(getLatitude()) + ", ")
      .append("s3ImageKey=" + String.valueOf(getS3ImageKey()) + ", ")
      .append("tornado=" + String.valueOf(getTornado()))
      .append("}")
      .toString();
  }
  
  public static TornadoStep builder() {
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
  public static SuckedUpThing justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new SuckedUpThing(
      id,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      name,
      longitude,
      latitude,
      s3ImageKey,
      tornado);
  }
  public interface TornadoStep {
    BuildStep tornado(Tornado tornado);
  }
  

  public interface BuildStep {
    SuckedUpThing build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep name(String name);
    BuildStep longitude(Double longitude);
    BuildStep latitude(Double latitude);
    BuildStep s3ImageKey(String s3ImageKey);
  }
  

  public static class Builder implements TornadoStep, BuildStep {
    private String id;
    private Tornado tornado;
    private String name;
    private Double longitude;
    private Double latitude;
    private String s3ImageKey;
    @Override
     public SuckedUpThing build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new SuckedUpThing(
          id,
          name,
          longitude,
          latitude,
          s3ImageKey,
          tornado);
    }
    
    @Override
     public BuildStep tornado(Tornado tornado) {
        Objects.requireNonNull(tornado);
        this.tornado = tornado;
        return this;
    }
    
    @Override
     public BuildStep name(String name) {
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep longitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }
    
    @Override
     public BuildStep latitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }
    
    @Override
     public BuildStep s3ImageKey(String s3ImageKey) {
        this.s3ImageKey = s3ImageKey;
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
    private CopyOfBuilder(String id, String name, Double longitude, Double latitude, String s3ImageKey, Tornado tornado) {
      super.id(id);
      super.tornado(tornado)
        .name(name)
        .longitude(longitude)
        .latitude(latitude)
        .s3ImageKey(s3ImageKey);
    }
    
    @Override
     public CopyOfBuilder tornado(Tornado tornado) {
      return (CopyOfBuilder) super.tornado(tornado);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder longitude(Double longitude) {
      return (CopyOfBuilder) super.longitude(longitude);
    }
    
    @Override
     public CopyOfBuilder latitude(Double latitude) {
      return (CopyOfBuilder) super.latitude(latitude);
    }
    
    @Override
     public CopyOfBuilder s3ImageKey(String s3ImageKey) {
      return (CopyOfBuilder) super.s3ImageKey(s3ImageKey);
    }
  }
  
}
