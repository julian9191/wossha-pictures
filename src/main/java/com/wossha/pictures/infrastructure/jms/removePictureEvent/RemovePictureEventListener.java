package com.wossha.pictures.infrastructure.jms.removePictureEvent;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.wossha.json.events.events.api.Event;
import com.wossha.json.events.events.api.EventProcessor;
import com.wossha.json.events.events.pictures.RemovePictureEvent.RemovePictureEvent;
import com.wossha.json.events.exceptions.RecoverableException;
import com.wossha.pictures.dto.PictureFileDTO;
import com.wossha.pictures.infrastructure.repositories.FileRepository;

@Component
public class RemovePictureEventListener implements EventProcessor<RemovePictureEvent>  {
    private RemovePictureEvent data;

    @Autowired
    private FileRepository repo;
    
    @Override
    public String name() {
        return "AsesoriaRecibidaBPM";
    }
    @Override
    public RemovePictureEvent data() {
        return data;
    }
    @Override
    public void setData(RemovePictureEvent data) {
        this.data = data;
    }
    @Override
    public List<Event> execute() throws RecoverableException{
        List<Event> events = new ArrayList<>();
        
		repo.removeByUuid(data.getMessage().getUuidPicture());
		
        return events;
    }
}
