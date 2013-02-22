import java.util.Collection;

import flexjson.JSONSerializer;
import play.templates.JavaExtensions;


public class SerializerExtensions extends JavaExtensions {

	public static String serializeWith( Object model, String serializer ) throws IllegalArgumentException, SecurityException, IllegalAccessException, NoSuchFieldException{
		JSONSerializer jsonSerializer = ( JSONSerializer ) Serializers.class.getField(serializer).get( null );
		return jsonSerializer.serialize(model);
	}
	
	public static String serializeWith(Collection<Object> models, String serializer) throws IllegalArgumentException, SecurityException, IllegalAccessException, NoSuchFieldException {
        JSONSerializer js = (JSONSerializer) Serializers.class.getField(serializer).get(null);
        return js.serialize(models);
	}
}
