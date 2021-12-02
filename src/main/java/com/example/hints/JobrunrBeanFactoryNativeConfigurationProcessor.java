package com.example.hints;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.jobrunr.jobs.lambdas.JobRequestHandler;
import org.jobrunr.storage.StorageProvider;
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
		var jobRequestHandlers = beanFactory.getBeansOfType(JobRequestHandler.class);
		for (var e : jobRequestHandlers.entrySet()) {
			var value = e.getValue();
			var valueClass = value.getClass();
			registry.reflection().forType(valueClass).withAccess(TypeAccess.values()).build();
			log.info("registering reflective access for " + valueClass);
			var runMethod = Stream.of(valueClass.getMethods())
				.filter(m -> m.getName().equals("run"))
				.collect(Collectors.toList())
				.get(0);
			var types = runMethod.getParameterTypes()[0];
			registry.reflection().forType(types).withAccess(TypeAccess.values()).build();
		}
		
		var spMap = beanFactory.getBeansOfType(StorageProvider.class);
		spMap.forEach((k, sp) -> {
			registry.reflection().forType(sp.getClass()).withAccess(TypeAccess.values()).build();
			log.info(k + "=" + sp.getClass());
		});
	}


}
