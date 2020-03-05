package gpibph.daoImpl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class JsonDateSerializer extends JsonSerializer<Date>{
	
	// format tanggal pada Json
		private static final SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
		
		public void serialize(Date date, org.codehaus.jackson.JsonGenerator gen, SerializerProvider provider)
				throws IOException, JsonProcessingException {
			// TODO Auto-generated method stub

			String formattedDate = sdf.format(date);

			gen.writeString(formattedDate);
		}

}
