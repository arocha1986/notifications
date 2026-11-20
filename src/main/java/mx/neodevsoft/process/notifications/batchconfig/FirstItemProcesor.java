package mx.neodevsoft.process.notifications.batchconfig;

import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

import mx.neodevsoft.process.notifications.domain.entity.StudentCsv;

@Component
public class FirstItemProcesor implements ItemProcessor<StudentCsv, StudentCsv> {

	@Override
	public @Nullable StudentCsv process(StudentCsv item) throws Exception {
		System.out.println("Inside Item Procesor");
		item.setEmail(item.getEmail().toUpperCase());
		return item;
	}

}
