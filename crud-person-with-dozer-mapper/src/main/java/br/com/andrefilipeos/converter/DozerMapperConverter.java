package br.com.andrefilipeos.converter;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerMapperConverter {
	
	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	//Return a simple object converted 
	public static <O, D> D parseObject(O origin, Class<D> destination) {
		return mapper.map(origin, destination);
	}
	
	//Return a simple object converted 
	public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
		List<D> destinationObjects = new ArrayList<D>();
		
		for (Object o : origin) {
			destinationObjects.add(mapper.map(o, destination));
		}
		
		return destinationObjects;
	}

}
