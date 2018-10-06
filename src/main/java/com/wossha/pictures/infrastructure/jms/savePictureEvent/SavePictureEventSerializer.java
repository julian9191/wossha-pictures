package com.wossha.pictures.infrastructure.jms.savePictureEvent;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wossha.json.events.events.api.EventProcessor;
import com.wossha.json.events.events.api.EventSerializer;
import com.wossha.json.events.events.pictures.SavePictureEvent.SavePictureEvent;

@Component
public class SavePictureEventSerializer implements EventSerializer {
    private final ObjectMapper m = new ObjectMapper();
    
    @Autowired
	private SavePictureEventListener eventListener;

    @SuppressWarnings("rawtypes")
	@Override
    public EventProcessor deserialize(String json) throws IOException {
    	SavePictureEvent event = m.readValue(json, SavePictureEvent.class);
    	eventListener.setData(event);
        return eventListener;
    }
	
}
