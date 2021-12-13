package com.example.nativex;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.jobrunr.JobRunrException;
import org.jobrunr.jobs.lambdas.JobRequestHandler;
import org.jobrunr.storage.StorageProvider;
import org.reflections.Reflections;
import org.springframework.aot.context.bootstrap.generator.infrastructure.nativex.BeanFactoryNativeConfigurationProcessor;
import org.springframework.aot.context.bootstrap.generator.infrastructure.nativex.NativeConfigurationRegistry;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.nativex.hint.TypeAccess;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
	* @author Josh Long
	*/
@Log4j2
public class JobrunrBeanFactoryNativeConfigurationProcessor implements BeanFactoryNativeConfigurationProcessor {

	@SneakyThrows
	@Override
	public void process(ConfigurableListableBeanFactory beanFactory, NativeConfigurationRegistry registry) {

		var jobRequestHandlers = beanFactory.getBeanNamesForType(JobRequestHandler.class);
		for (var beanName : jobRequestHandlers) {
			var bd = beanFactory.getBeanDefinition(beanName);
			var clazz = bd.getBeanClassName();

			var clazzObject = Class.forName(clazz);
			registry.reflection().forType(clazzObject).withAccess(TypeAccess.values()).build();
			var runMethod = Stream.of(clazzObject.getMethods()).filter(m -> m.getName().equals("run"))
				.collect(Collectors.toList()).get(0);
			var types = runMethod.getParameterTypes()[0];
			registry.reflection().forType(types).withAccess(TypeAccess.values()).build();
		}

		var reflections = new Reflections(JobRunrException.class.getPackage().getName()) ;
		var subTypesOf = reflections.getSubTypesOf(StorageProvider.class);
		for (var sp : subTypesOf) {
			log.info ("registering " + StorageProvider.class.getName() + " of type " + sp.getName());
			registry.reflection().forType( sp).withAccess(TypeAccess.values()).build();
		}
	}

}
