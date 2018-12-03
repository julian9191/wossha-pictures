package com.wossha.pictures.configure;

import java.util.TimeZone;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wossha.json.events.events.pictures.RemovePictureEvent.RemovePictureEvent;
import com.wossha.json.events.events.pictures.SavePictureEvent.SavePictureEvent;
import com.wossha.pictures.commands.CommandSerializers;
import com.wossha.pictures.commands.savePicture.SavePictureCommand;
import com.wossha.pictures.commands.savePicture.SavePictureSerializer;
import com.wossha.pictures.infrastructure.jms.EventSerializers;
import com.wossha.pictures.infrastructure.jms.removePictureEvent.RemovePictureEventSerializer;
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
	
	
	//EVENTS----------------------------------------------------------------
	@Bean
	public SavePictureEvent savePictureEvent() {
		return new SavePictureEvent();
	}
	
	@Bean
	public RemovePictureEvent removePictureEvent() {
		return new RemovePictureEvent();
	}
	
	
	//EVENTS LISTENERS----------------------------------------------------------
	@Bean
	public SavePictureEventSerializer savePictureEventSerializer() {
		return new SavePictureEventSerializer();
	}
	
	@Bean
	public RemovePictureEventSerializer removePictureEventSerializer() {
		return new RemovePictureEventSerializer();
	}
	
	//-------------------------------------------------------------------------
	
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		TimeZone tz = TimeZone.getDefault();
		objectMapper.setTimeZone(tz);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return objectMapper;
	}
	
	@Bean
	public EventSerializers eventSerializers() {
		EventSerializers es = new EventSerializers();
		es.setSavePictureEventSerializer(savePictureEventSerializer());
		es.setRemovePictureEventSerializer(removePictureEventSerializer());
		es.initMapper();
		return es;
	}
	
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer init() {
	    return new Jackson2ObjectMapperBuilderCustomizer() {

	    	@Override
	        public void customize(Jackson2ObjectMapperBuilder builder) {
	            builder.timeZone(TimeZone.getDefault());
	        }
	    };
	}
	
}
