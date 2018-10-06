package com.wossha.pictures.commands.savePicture;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wossha.msbase.commands.ICommand;
import com.wossha.msbase.commands.ICommandSerializer;
import com.wossha.pictures.commands.savePicture.model.SavePicture;

@Component
public class SavePictureSerializer implements ICommandSerializer {
	
	private ObjectMapper m = new ObjectMapper();
	
	@Autowired
	private SavePictureCommand command;
	
	@Override
	public ICommand<SavePicture> deserialize(String json) throws IOException {
		SavePicture dto = m.readValue(json, SavePicture.class);
        command.setData(dto);
        return command;
	}

}
