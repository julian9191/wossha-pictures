package com.wossha.pictures.infrastructure.jms;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wossha.json.events.events.api.EventSerializer;
import com.wossha.pictures.infrastructure.jms.removePictureEvent.RemovePictureEventSerializer;
import com.wossha.pictures.infrastructure.jms.savePictureEvent.SavePictureEventSerializer;

@Component
public class EventSerializers {

    private final HashMap<String, EventSerializer> listeners = new HashMap<>();
    
    private SavePictureEventSerializer savePictureEventSerializer;
    private RemovePictureEventSerializer removePictureEventSerializer;

    public void initMapper() {
        listeners.put("SAVE-PICTURE", savePictureEventSerializer);
        listeners.put("REMOVE-PICTURE", removePictureEventSerializer);
    }


    public EventSerializer get(String eventName) {
        return listeners.get(eventName);
    }


	public void setSavePictureEventSerializer(SavePictureEventSerializer savePictureEventSerializer) {
		this.savePictureEventSerializer = savePictureEventSerializer;
	}


	public void setRemovePictureEventSerializer(RemovePictureEventSerializer removePictureEventSerializer) {
		this.removePictureEventSerializer = removePictureEventSerializer;
	}
    
    

}