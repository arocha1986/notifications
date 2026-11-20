package mx.neodevsoft.process.notifications.batchconfig;

import java.util.List;

import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.stereotype.Component;

import mx.neodevsoft.process.notifications.domain.entity.StudentCsv;

@Component
public class FirstItemWriter implements ItemWriter<StudentCsv> {


	@Override
	public void write(Chunk<? extends StudentCsv> items) throws Exception {
		System.out.println("Inside item writter");
		items.getItems().stream().forEach(System.out::println);
	}

	
}
