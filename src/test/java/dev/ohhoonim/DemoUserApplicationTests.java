package dev.ohhoonim;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

@Import(TestcontainersConfiguration.class)
class DemoUserApplicationTests {

	@Test
	void contextLoads() {
		ApplicationModules.of(DemoUserApplication.class).verify();
	}

	ApplicationModules modules = ApplicationModules.of(DemoUserApplication.class);

	@Test
	void writePlantUml(){
		new Documenter(modules)
				.writeModulesAsPlantUml()
				.writeIndividualModulesAsPlantUml();
	}


}
