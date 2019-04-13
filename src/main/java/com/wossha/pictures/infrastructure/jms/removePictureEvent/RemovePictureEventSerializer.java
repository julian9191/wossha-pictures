package com.wossha.pictures.infrastructure.jms.removePictureEvent;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wossha.json.events.events.api.EventProcessor;
import com.wossha.json.events.events.api.EventSerializer;
import com.wossha.json.events.events.pictures.RemovePictureEvent.RemovePictureEvent;

@Component
public class RemovePictureEventSerializer implements EventSerializer {
    private final ObjectMapper m = new ObjectMapper();
    
    @Autowired
	private RemovePictureEventListener eventListener;

    @SuppressWarnings("rawtypes")
	@Override
    public EventProcessor deserialize(String json) throws IOException {
    	RemovePictureEvent event = m.readValue(json, RemovePictureEvent.class);
    	eventListener.setData(event);
        return eventListener;
    }
	
}
