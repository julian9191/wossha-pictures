package com.wossha.pictures.infrastructure.jms;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wossha.json.events.events.api.EventSerializer;
import com.wossha.pictures.infrastructure.jms.savePictureEvent.SavePictureEventSerializer;

@Component
public class EventSerializers {

    private final HashMap<String, EventSerializer> listeners = new HashMap<>();
    
    @Autowired
    private SavePictureEventSerializer savePictureEventSerializer;

    public void initMapper() {
        listeners.put("SAVE-PICTURE", savePictureEventSerializer);
    }


    public EventSerializer get(String eventName) {
        return listeners.get(eventName);
    }


	public void setSavePictureEventSerializer(SavePictureEventSerializer savePictureEventSerializer) {
		this.savePictureEventSerializer = savePictureEventSerializer;
	}
    
    

}