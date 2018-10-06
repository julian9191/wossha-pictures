package com.wossha.pictures.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wossha.json.events.events.pictures.SavePictureEvent.SavePictureEvent;
import com.wossha.pictures.commands.CommandSerializers;
import com.wossha.pictures.commands.savePicture.SavePictureCommand;
import com.wossha.pictures.commands.savePicture.SavePictureSerializer;
import com.wossha.pictures.infrastructure.jms.EventSerializers;
import com.wossha.pictures.infrastructure.jms.savePictureEvent.SavePictureEventSerializer;
import com.wossha.pictures.infrastructure.repositories.FileRepository;

@Configuration
public class BeansConfig {
	
	@Bean
	public FileRepository fileRpository() {
			return new FileRepository();
	}


	@Bean
	public SavePictureCommand savePictureCommand() {
		return new SavePictureCommand();
	}
	
	@Bean
	public SavePictureSerializer savePictureSerializer() {
		return new SavePictureSerializer();
	}
	
	@Bean
	public CommandSerializers commandSerializers() {
		CommandSerializers cs = new CommandSerializers();
		cs.setModifyUserSerializer(savePictureSerializer());
		cs.initMapper();
		return cs;
	}
	
	
	@Bean
	public SavePictureEvent savePictureEvent() {
		return new SavePictureEvent();
	}
	
	@Bean
	public SavePictureEventSerializer savePictureEventSerializer() {
		return new SavePictureEventSerializer();
	}
	
	@Bean
	public EventSerializers eventSerializers() {
		EventSerializers es = new EventSerializers();
		es.setSavePictureEventSerializer(savePictureEventSerializer());
		es.initMapper();
		return es;
	}
	
}
