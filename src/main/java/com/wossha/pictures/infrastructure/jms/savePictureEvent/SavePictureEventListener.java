package com.wossha.pictures.infrastructure.jms.savePictureEvent;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.wossha.json.events.events.api.Event;
import com.wossha.json.events.events.api.EventProcessor;
import com.wossha.json.events.events.pictures.SavePictureEvent.SavePictureEvent;
import com.wossha.json.events.exceptions.RecoverableException;
import com.wossha.pictures.dto.PictureFileDTO;
import com.wossha.pictures.infrastructure.repositories.FileRepository;

@Component
public class SavePictureEventListener implements EventProcessor<SavePictureEvent>  {
    private SavePictureEvent data;

    @Autowired
    private FileRepository repo;
    
    @Override
    public String name() {
        return "AsesoriaRecibidaBPM";
    }
    @Override
    public SavePictureEvent data() {
        return data;
    }
    @Override
    public void setData(SavePictureEvent data) {
        this.data = data;
    }
    @Override
    public List<Event> execute() throws RecoverableException{
        List<Event> events = new ArrayList<>();

		PictureFileDTO dto = new PictureFileDTO();
    	dto.setUuid(data.getMessage().getUuidPicture());
    	dto.setUsername(data.getUsername());
    	dto.setName(data.getMessage().getName());
    	dto.setFileType(data.getMessage().getFileType());
    	dto.setType(data.getMessage().getType());
    	dto.setFileSize(data.getMessage().getFileSize());
    	
    	String[] parts = data.getMessage().getValue().split(",");
    	String base64 = parts[parts.length-1];
    	
    	byte[] fileByteArray = Base64.getDecoder().decode(base64.getBytes());

		dto.setValue(fileByteArray);
		repo.add(dto);
		
		if(data.getMessage().getUuidPictureToRemove()!=null) {
			repo.removeByUuid(data.getMessage().getUuidPictureToRemove());
		}

        return events;
    }
}
