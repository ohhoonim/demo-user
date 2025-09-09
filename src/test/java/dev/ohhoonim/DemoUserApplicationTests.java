package dev.ohhoonim;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

import dev.ohhoonim.DemoUserApplication;

@Import(TestcontainersConfiguration.class)
class DemoUserApplicationTests {

	@Test
	void contextLoads() {
		ApplicationModules.of(DemoUserApplication.class);
	}

	ApplicationModules modules = ApplicationModules.of(DemoUserApplication.class);

	@Test
	public void writePlantUml(){
		new Documenter(modules)
				.writeModulesAsPlantUml()
				.writeIndividualModulesAsPlantUml();
	}


}
